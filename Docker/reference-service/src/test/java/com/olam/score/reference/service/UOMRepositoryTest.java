package com.olam.score.reference.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.olam.score.common.constants.Constants;
import com.olam.score.reference.domain.MasterUom;
import com.olam.score.reference.dto.UomDto;
import com.olam.score.reference.repository.UOMRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
// @DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class UOMRepositoryTest {

	@Autowired
    private UOMRepository repository;
	
	@Autowired
    private UOMService service;
	
	@Test
    public void testFindAll() throws Exception {
		
		List<MasterUom> uoms = new ArrayList<MasterUom>();
        MasterUom uom = new MasterUom();
        uom.setUomCode("UOM");
        uom.setPkUomId(1);
        uom.setUomName("UOM Name");
        uom.setUomFullname("UOM Full Name");
		MasterUom uomReturn = this.repository.save(uom);
		
        uoms = this.repository.findAll();
        System.out.println("Returned with:"+ uoms.size());
        assertThat(uoms.size()).isGreaterThan(0);
    }

	@Test
    public void testSave() throws Exception {
		List<MasterUom> uoms = new ArrayList<MasterUom>();
        MasterUom uom = new MasterUom();
        uom.setUomCode("UOM");
        uom.setPkUomId(1);
        uom.setUomName("UOM Name");
        uom.setUomFullname("UOM Full Name");
		MasterUom uomReturn = this.repository.save(uom);
        assertThat(uomReturn.getUomCode()).isEqualTo("UOM");
    }
	
	@Test
    public void testSaveService() throws Exception {
		UomDto uomDto = new UomDto();
		uomDto.setAction(Constants.SAVE);
		uomDto.setBaseUomCode("UOM2");
		uomDto.setUomCode("UOM2");
		uomDto.setUomName("UOM2");
		UomDto uomReturn = this.service.create(uomDto);
        assertThat(uomReturn.getUomCode()).isEqualTo("UOM2");
    }
}
