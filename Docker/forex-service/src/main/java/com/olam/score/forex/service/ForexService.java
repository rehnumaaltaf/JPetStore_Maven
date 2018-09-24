package com.olam.score.forex.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olam.score.forex.domain.MasterForex;
import com.olam.score.forex.domain.MasterForexForwardTenor;
import com.olam.score.forex.domain.MasterForexFrequency;
import com.olam.score.forex.domain.MasterTenorDayType;
import com.olam.score.forex.domain.MasterTenorDurationType;
import com.olam.score.forex.domain.MasterTenorType;
import com.olam.score.forex.dto.ForexDto;
import com.olam.score.forex.dto.ForexForwardTenorDto;
import com.olam.score.forex.model.DropDownModel;
import com.olam.score.forex.repository.ForexForwardTenorRepository;
import com.olam.score.forex.repository.ForexFrequencyRepository;
import com.olam.score.forex.repository.ForexRepository;
import com.olam.score.forex.repository.TenorDayTypeRepository;
import com.olam.score.forex.repository.TenorDurationTypeRepository;
import com.olam.score.forex.repository.TenorTypeRepository;
import com.olam.score.common.constants.Constants;


@Service
public class ForexService {
                private final Logger log = LoggerFactory.getLogger(getClass());

                @Autowired
                private ForexRepository repository;

                @Autowired
                private ForexForwardTenorRepository forexFrequencyTenorRepository;

                @Autowired
                private ForexFrequencyService forexFrequencyService;

                @Autowired
                private ForexFrequencyRepository frequencyRepository;

                @Autowired
                private TenorTypeRepository tenorTypeRepository;
                
                @Autowired
                private TenorDurationTypeRepository tenorDurationTypeRepository;
                
                @Autowired
                private TenorDayTypeRepository tenorDayTypeRepository;
                
                @Autowired
                private Environment env;

                protected EntityManager entityManager;

                @Transactional
                public String create(ForexDto model) {
                                MasterForex inputData = new MasterForex();
                                try {
                                                if (model.getForexName() == null){
                                                                return Constants.FOREX_NAME_NULL;
                                                                }
                                                else {
                                                                if (Constants.SAVE_CAPS.equalsIgnoreCase(model.getAction())){
                                                                                inputData.setFkStatusId(Constants.DRAFT_STATUS_ID);
                                                                }
                                                                else{
                                                                                inputData.setFkStatusId(Constants.ACTIVE_STATUS_ID);
                                                                }
                                                                inputData=createAndReturnMasterForex(inputData, model);
                                                                inputData=repository.save(inputData);
                                                                createForexForwardTenor(model, inputData);
                                                }
                                } catch (Exception e) {
                                                log.info("create:", e);
                                                String response=Constants.FOREX_SAVE_FAILURE;
                                                response=response.replace(Constants.FOREX_NAME_MESSAGE, model.getForexName());
                                                return response;
                                }
                                if(Constants.SAVE_CAPS.equalsIgnoreCase(model.getAction())){
                                                String response=Constants.FOREX_SAVE_SUCCESS_DRAFT;
                                                response=response.replace(Constants.FOREX_NAME_MESSAGE, model.getForexName());
                                                return response;
                                }else{
                                                String response=Constants.FOREX_SAVE_SUCCESS_ACTIVE;
                                                response=response.replace(Constants.FOREX_NAME_MESSAGE, model.getForexName());
                                                return response;
                                }
                }
                public MasterForex createAndReturnMasterForex(MasterForex inputData,ForexDto model)
                {
                                
                                inputData.setCreatedBy("Sample1");
                                inputData.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                                if(model.getForexCode()!=null){
                                                inputData.setForexCode(model.getForexCode());
                                }
                                inputData.setForexName(model.getForexName());
                                if(model.getForexDesc()!=null){
                                inputData.setForexDesc(model.getForexDesc());
                                }
                                if(model.getBaseCurrency()!=null){
                                inputData.setFkBaseCurrencyId(Integer.parseInt(model.getBaseCurrency()));
                                }
                                if(model.getCounterCurrency()!=null){
                                inputData.setFkCounterCurrencyId(Integer.parseInt(model.getCounterCurrency()));
                                }
                                if(model.getFrequency()!=null){
                                inputData.setFkForexFrequencyId(repository.findByFrequencyId(Integer.parseInt(model.getFrequency())));
                                }
                                return inputData;
                }
                
                @Transactional
                public List<MasterForexForwardTenor> createForexForwardTenor(ForexDto model, MasterForex forex) {
                                List<MasterForexForwardTenor> list = new ArrayList<>();
                                try {

                                                List<ForexForwardTenorDto> tenorList = model.getTenorList();
                                                
                                                for (ForexForwardTenorDto eachModel : tenorList) {
                                                                MasterForexForwardTenor inputData = new MasterForexForwardTenor(); 
                                                                createAndReturnMasterForexForwardTenor(inputData, eachModel, forex);
                                                                forexFrequencyTenorRepository.saveAndFlush(inputData);

                                                }
                                } catch (Exception ex) {
                                                log.info("create", ex);
                                }
                                return list;
                }
                
                public MasterForexForwardTenor createAndReturnMasterForexForwardTenor(MasterForexForwardTenor inputData,ForexForwardTenorDto eachModel,MasterForex forex)
                {
                                inputData.setCreatedBy("Test");
                                inputData.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                                inputData.setForexTickerCode(eachModel.getTicketerCode());
                                int tenor=Integer.parseInt(eachModel.getTenorType());
                                MasterTenorType tenortype = tenorTypeRepository.getByPkTenorTypeId(tenor);
                                inputData.setFkTenorTypeId(tenortype);
                                if(eachModel.getSpan().intValue()>=0)
                                inputData.setForexSpan(eachModel.getSpan());
                                else
                                                inputData.setForexSpan(new BigDecimal("0"));
                                if(eachModel.getTenorDurationType()!=null){
                                MasterTenorDurationType tenorDurationType =tenorDurationTypeRepository.getByPkTenorDurationTypeId(Integer.parseInt(eachModel.getTenorDurationType()));
                                inputData.setFkTenorDurationTypeId(tenorDurationType);
                                }
                                if(eachModel.getTenorDayType()!=null){
                                MasterTenorDayType tenorDayType = tenorDayTypeRepository.getByPkTenorDayTypeId(Integer.parseInt(eachModel.getTenorDayType()));
                                inputData.setFkTenorDayTypeId(tenorDayType);
                                }
                                inputData.setFkForexId(forex);
                                inputData.setFkStatusId(forex.getFkStatusId());
                                return inputData;
                }
                public List<String> returnValuesForKeying(String toKey,String toBeKeyed)
                {
                                if(Constants.FOREX_NAME.equalsIgnoreCase(toKey))
                                                return repository.findByforexNameKeying(toBeKeyed.concat(Constants.PRECENTAGE));
                                else
                                                return repository.findByforexDescKeying(toBeKeyed.concat(Constants.PRECENTAGE));
                }
                
                public List<ForexDto> createAndReturnForexViewModelList(List<MasterForex> forexList) {
                                /*List<ForexDto> forexViewList = new ArrayList<>();
                                for (MasterForex forex : forexList) {
                                                ForexDto model = new ForexDto();
                                                model.setForexId(forex.getPkForexId());
                                                model.setForexCode(forex.getForexCode());
                                                model.setForexName(forex.getForexName());
                                                model.setForexDesc(forex.getForexDesc());
                                                try {
                                                                if (forex.getFkForexFrequencyId() != null) {
                                                                                MasterForexFrequency frequency = frequencyRepository.getByPkForexFrequencyId(forex.getFkForexFrequencyId().getPkForexFrequencyId());
                                                                                model.setFrequency(frequency.getForexFrequencyName());
                                                                }
                                                } catch (Exception ex) {
                                                                log.info("createAndReturnForexViewModelList:", ex);
                                                }
                                                try {
                                                                if (forex.getFkBaseCurrencyId() != null)
                                                                                //model.setBaseCurrency(returnCurrencyName(forex.getFkBaseCurrencyId().toString()));
                                                                if (forex.getFkCounterCurrencyId() != null)
                                                                                //model.setCounterCurrency(returnCurrencyName(forex.getFkCounterCurrencyId().toString()));
                                                } catch (Exception ex) {
                                                                log.info("Currency service call", ex);
                                                }

                                                try {
                                                                if (forex.getFkStatusId() != null){
                                                                                model.setStatus(returnStatusName(forex.getFkStatusId().toString()));
                                                                                model.setStatusId(forex.getFkStatusId());
                                                                }
                                                } catch (Exception ex) {
                                                                log.info("inside getting status name", ex);
                                                                
                                                }

                                                forexViewList.add(model);
                                }
                                return forexViewList;*/
return null;          }
                
                public List<ForexForwardTenorDto> createAndReturnTenorViewList(List<MasterForexForwardTenor> tenorMasterList) {
                                List<ForexForwardTenorDto> list = new ArrayList<>();
                                for (MasterForexForwardTenor tenor : tenorMasterList) {
                                                ForexForwardTenorDto model = new ForexForwardTenorDto();
                                                model.setSpan(tenor.getForexSpan());
                                                model.setForexForwardTenorId(tenor.getPkForexForwardTenorId().toString());
                                                try {
                                                                if (tenor.getFkTenorDayTypeId() != null) {
                                                                                model.setTenorDayType(tenorDayTypeRepository
                                                                                                                .getByPkTenorDayTypeId(tenor.getFkTenorDayTypeId().getPkTenorDayTypeId())
                                                                                                                .getTenorDayTypeName());
                                                                }
                                                } catch (Exception ex) {
                                                                log.info("createAndReturnTenorViewList1", ex);
                                                }
                                                try {
                                                                if (tenor.getFkTenorDurationTypeId() != null) {
                                                                                model.setTenorDurationType(tenorDurationTypeRepository
                                                                                                                .getByPkTenorDurationTypeId(tenor.getFkTenorDurationTypeId().getPkTenorDurationTypeId())
                                                                                                                .getTenorDurationTypeName());
                                                                }
                                                } catch (Exception ex) {
                                                                log.info("createAndReturnTenorViewList2", ex);
                                                }
                                                try {
                                                                model.setTenorType(tenorTypeRepository
                                                                                                .getByPkTenorTypeId(tenor.getFkTenorTypeId().getPkTenorTypeId()).getTenorTypeName());
                                                } catch (Exception ex) {
                                                                log.info("createAndReturnTenorViewList3", ex);
                                                }
                                                try {
                                                                model.setTicketerCode(tenor.getForexTickerCode());
                                                } catch (Exception e) {
                                                                log.info("createAndReturnTenorViewList4", e);
                                                }

                                                list.add(model);
                                }
                                return list;
                }

/*           public String returnStatusName(String id) {
                                //MasterStatus status = new MasterStatus();
                                try {
                                                String urlToHit = env.getProperty(Constants.URL).concat(Constants.FOREX_STATUS_SERVICE_CALL_URL);
                                                urlToHit = urlToHit.replace("{id}", id);
                                                URL url = new URL(urlToHit);
                                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                                conn.setRequestMethod("GET");
                                                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                                                String output;

                                                while ((output = br.readLine()) != null) {
                                                                
                                  JSONParser parser = new JSONParser(); 
                                  JSONObject json = (JSONObject) parser.parse(output);
                                  String toConvert=json.get("body").toString();
                                  ObjectMapper mapper = new ObjectMapper();
                                //  status = mapper.readValue(toConvert, MasterStatus.class);

                                                }
                                } catch (Exception e) {
                                                log.info("Inside return currency name", e);
                                }
                                return "";
                }*/

                /*public String returnCurrencyName(String id) {

                                //MasterCurrency curr = new MasterCurrency();
                                try {
                                                String urlToHit = env.getProperty(Constants.URL).concat(Constants.FOREX_CURR_DETAIL_URL);
                                                urlToHit = urlToHit.replace("{id}", id);
                                                URL url = new URL(urlToHit);
                                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                                conn.setRequestMethod("GET");
                                                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                                                String output;

                                                while ((output = br.readLine()) != null) {
                                                                JSONParser parser = new JSONParser(); 
                JSONObject json = (JSONObject) parser.parse(output);
                String toConvert=json.get("body").toString();
                ObjectMapper mapper = new ObjectMapper();
                                                                //curr = mapper.readValue(toConvert, MasterCurrency.class);

                                                }
                                } catch (Exception e) {
                                                log.info("Inside return currency name", e);
                                }
                                return curr.getCurrencyName();
                }*/

                public ForexDto returnForexDetailsToViewForex(int id) {
                                ForexDto model = new ForexDto();
                                MasterForex forex = repository.getByPkForexId(id);
                                List<MasterForexForwardTenor> tenorList = forexFrequencyTenorRepository.getByFkForexId(forex);
                                model.setForexCode(forex.getForexCode());
                                model.setForexName(forex.getForexName());
                                model.setForexId(forex.getPkForexId());
                                model.setForexDesc(forex.getForexDesc());
                                //model.setBaseCurrency(returnCurrencyName(forex.getFkBaseCurrencyId().toString()));
                                //model.setCounterCurrency(returnCurrencyName(forex.getFkBaseCurrencyId().toString()));
                                model.setTenorList(createAndReturnTenorViewList(tenorList));
                                return model;
                }

                public String deactivateOrReactivateForex(int id,int statusId) {
                                try {
                                                if (id <= 0)
                                                                return Constants.FOREX_DELETE_FAILURE;
                                                else {
                                                                MasterForex forex=repository.getByPkForexId(id);
                                                                if (Constants.DRAFT_STATUS_ID == statusId){
                                                                                repository.deleteForexForwarTenor(repository.getByPkForexId(id));
                                                                                repository.delete(id);
                                                                                String response=Constants.FOREX_DELETE_SUCCESS;
                                                                                response=response.replace(Constants.FOREX_NAME_MESSAGE, forex.getForexName());
                                                                                return response;
                                                                }
                                                                else if (Constants.ACTIVE_STATUS_ID == statusId) {
                                                                                int status1 = Constants.INACTIVE_STATUS_ID;
                                                                                repository.deactivateOrReactivateForex(status1, id);
                                                                                repository.deactivateOrReactivateForexForwarTenor(status1, repository.findOne(id));
                                                                                String response=Constants.FOREX_DEACTIVATE_SUCCESS;
                                                                                response=response.replace(Constants.FOREX_NAME_MESSAGE, forex.getForexName());
                                                                                return response;
                                                                } else {
                                                                                int status1 = Constants.ACTIVE_STATUS_ID;
                                                                                repository.deactivateOrReactivateForex(status1, id);
                                                                                repository.deactivateOrReactivateForexForwarTenor(status1, repository.findOne(id));
                                                                                String response=Constants.FOREX_REACTIVATE_SUCCESS;
                                                                                response=response.replace(Constants.FOREX_NAME_MESSAGE, forex.getForexName());
                                                                                return response;
                                                                }
                                                }
                                                
                                } catch (Exception ex) {
                                                log.info("delete forex:", ex);
                                                return Constants.FOREX_DELETE_FAILURE;
                                }
                }

                public ForexDto returnAllDropDownValues() {
                                ForexDto dto = new ForexDto();
                                try {
                                                List<MasterForexFrequency> forexFrequencyList = frequencyRepository.returnAll(Constants.ACTIVE_STATUS_ID);
                                                List<MasterTenorType> tenorTypeList = tenorTypeRepository.returnAll(Constants.ACTIVE_STATUS_ID);
                                                List<MasterTenorDayType> tenorDayTypeList = tenorDayTypeRepository.returnAll(Constants.ACTIVE_STATUS_ID);
                                                List<MasterTenorDurationType> tenorDurationTypeList = tenorDurationTypeRepository.returnAll(Constants.ACTIVE_STATUS_ID);
                                                //convertCurrencyToForexDtoList(dto, returnActiveCurrencyList());
                                                converForexFrequencyToForexDtoList(dto, forexFrequencyList);
                                                converTenorDayTypeToForexDtoList(dto, tenorDayTypeList);
                                                converTenorDuratioTypeToForexDtoList(dto, tenorDurationTypeList);
                                                converTenorTypeToForexDtoList(dto, tenorTypeList);
                                } catch (Exception ex) {
                                                log.info("Inside return drop down value", ex);
                                }
                                return dto;
                }
                
                /*public List<MasterCurrency> returnActiveCurrencyList()
                {
                                List<MasterCurrency> currList = new ArrayList<>();
                                try{
                                                String urlToHit = env.getProperty(Constants.URL).concat(Constants.FOREX_CURR_LIST_URL);
                                                URL url = new URL(urlToHit);
                                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                                conn.setRequestMethod("GET");
                                                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                                                
                                                String output;
                                                while ((output = br.readLine()) != null) {
                                                                JSONParser parser = new JSONParser();
                                                                JSONObject json = (JSONObject) parser.parse(output);
                String toConvert=json.get("body").toString();
                JSONArray jsonArray = (JSONArray)parser.parse(toConvert);
                ObjectMapper mapper = new ObjectMapper();
                                                                for (int i = 0; i < jsonArray.size(); i++) {
                                                                                MasterCurrency obj = mapper.readValue(jsonArray.get(i).toString(), MasterCurrency.class);
                                                                                currList.add(obj);
                                                                }
                                                }
                                }catch(Exception ex)
                                {
                                                log.info("", ex);
                                }
                                return currList;
                }*/

                /*public ForexDto convertCurrencyToForexDtoList(ForexDto forexDto, List<MasterCurrency> currencyList) {
                                List<DropDownModel> baseCurrencyDropDownModel = new ArrayList<>();
                                List<DropDownModel> counterCurrencyDropDownModel = new ArrayList<>();
                                for (MasterCurrency currency : currencyList) {
                                                DropDownModel model = new DropDownModel();
                                                model.setCode(currency.getPkCurrencyId().toString());
                                                model.setValue(currency.getCurrencyName());
                                                baseCurrencyDropDownModel.add(model);
                                                counterCurrencyDropDownModel.add(model);
                                }
                                forexDto.setBaseCurrencyList(baseCurrencyDropDownModel);
                                forexDto.setCounterCurrencyList(counterCurrencyDropDownModel);
                                return forexDto;
                }*/

                public ForexDto converForexFrequencyToForexDtoList(ForexDto forexDto, List<MasterForexFrequency> frequencyList) {
                                List<DropDownModel> frequencyDropDownList = new ArrayList<>();
                                for (MasterForexFrequency frequency : frequencyList) {
                                                DropDownModel model = new DropDownModel();
                                                model.setCode(frequency.getPkForexFrequencyId().toString());
                                                model.setValue(frequency.getForexFrequencyName());
                                                frequencyDropDownList.add(model);
                                }
                                forexDto.setFrequencyList(frequencyDropDownList);
                                return forexDto;
                }

                public ForexDto converTenorTypeToForexDtoList(ForexDto forexDto, List<MasterTenorType> tenorList) {
                                List<DropDownModel> tenorDropDownList = new ArrayList<>();
                                for (MasterTenorType tenor : tenorList) {
                                                DropDownModel model = new DropDownModel();
                                                model.setCode(tenor.getPkTenorTypeId().toString());
                                                model.setValue(tenor.getTenorTypeName());
                                                tenorDropDownList.add(model);
                                }
                                forexDto.setTenorDropDownList(tenorDropDownList);
                                return forexDto;
                }

                public ForexDto converTenorDuratioTypeToForexDtoList(ForexDto forexDto,
                                                List<MasterTenorDurationType> tenorDurationTypeList) {
                                List<DropDownModel> tenorDurationTypeDropDownList = new ArrayList<>();
                                for (MasterTenorDurationType tenorDuration : tenorDurationTypeList) {
                                                DropDownModel model = new DropDownModel();
                                                model.setCode(tenorDuration.getPkTenorDurationTypeId().toString());
                                                model.setValue(tenorDuration.getTenorDurationTypeName());
                                                tenorDurationTypeDropDownList.add(model);
                                }
                                forexDto.setTenorDurationTypeList(tenorDurationTypeDropDownList);
                                return forexDto;
                }

                public ForexDto converTenorDayTypeToForexDtoList(ForexDto forexDto, List<MasterTenorDayType> tenorDayTypeList) {
                                List<DropDownModel> tenorDayTypeDropDownList = new ArrayList<>();
                                for (MasterTenorDayType tenorDay : tenorDayTypeList) {
                                                DropDownModel model = new DropDownModel();
                                                model.setCode(tenorDay.getPkTenorDayTypeId().toString());
                                                model.setValue(tenorDay.getTenorDayTypeName());
                                                tenorDayTypeDropDownList.add(model);
                                }
                                forexDto.setTenorDayTypeList(tenorDayTypeDropDownList);
                                return forexDto;
                }

                public String validateForex(ForexDto forexDto) {
                /*           String response = null;
                                if (Constants.FOREX_NAME.equals(forexDto.getToValidate())) {
                                                int count = repository.getByName(forexDto.getForexName());
                                                                if (count!=Constants.ZERO)
                                                                                response = forexDto.getForexName().concat(Constants.FOREX_NAME_EXITS);
                                } else if (Constants.FOREX_CODE.equals(forexDto.getToValidate())) {
                                                int count = repository.getByCode(forexDto.getForexCode());
                                                if (count!=Constants.ZERO)
                                                                response = forexDto.getForexCode().concat(Constants.FOREX_CODE_EXITS);
                                } else if (Constants.FOREX_CURRENCY_PAIR.equals(forexDto.getToValidate())) {
                                                if(forexDto.getBaseCurrency().equalsIgnoreCase(forexDto.getCounterCurrency()))
                                                                response=Constants.FOREX_CURRENCY_SAME;
                                                try{
                                                int count = repository.findByCurrency(Integer.parseInt(forexDto.getBaseCurrency()), Integer.parseInt(forexDto.getCounterCurrency()));
                                                if (count!=Constants.ZERO)
                                                                response = returnCurrencyName(forexDto.getBaseCurrency()).concat(",").concat(returnCurrencyName(forexDto.getCounterCurrency())).concat(" ").concat(Constants.FOREX_CURRENCY_PAIR_EXITS);
                                                }catch(Exception ex){
                                                                log.info("Inside currency pair cheack", ex);
                                                }
                                }
                                return response;*/
                                return null;
                }
                
                
}
