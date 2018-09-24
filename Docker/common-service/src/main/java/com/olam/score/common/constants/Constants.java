
package com.olam.score.common.constants;

public class Constants {
	private Constants()
	{
	   throw new IllegalStateException("Constants Utility class");
	}
	public static final String ACTIVE="Active";
	public static final String DRAFT="Draft";
	public static final String DEACTIVE="Deactive";
	public static final String DELETE="Delete";
	public static final String SAVE="Save";
	public static final String INACTIVE="InActive";
	public static final String CONTEXT_FACTORY_CLASS = "com.sun.jndi.ldap.LdapCtxFactory";
	public static final String DOMAIN_NAME = "ad.infosys.com";
	public static final String ORGNAMEDOMAIN = "infosys.com";
	public static final Integer PAGESIZE = 200;
	public static final String SAVE_FINCAL_SUCCESS_INACTIVE = "saved FINCAL as InActive";

	
	public static final Integer OPEN = 4;
	public static final Integer CLOSED =5;
	public static final Integer ERP_OPEN = 6;
	public static final Integer ERP_CLOSED = 7;
	public static final String STATUS_OPEN= "open";
	public static final String STATUS_CLOSED ="closed";
	public static final String SAVE_FINCAL_SUCCESS_DRAFT = "Saved FINCAL as Draft";
	public static final String SAVE_FINCAL_SUCCESS_ACTIVE = "saved FINCAL as Active";
	public static final String DUPLICATE_FINCAL = "1";
	public static final String FINCAL = "0";
	public static final String EMPTY= "LIST IS EMPTY";
	public static final String FINCAL_STATUS_SERVICE_CALL_URL="/statusservice/status"; 
	public static final String SUBMIT = "SUBMIT";
	public static final Integer SAVE_DRAFT =2;
	public static final Integer SUBMIT_ACTIVE =1;
	public static final String DELETE_SUCCESS = "Data deleted Successfully";
	public static final String UPDATE_SUCCESS = "data updated Successfully";
	public static final String NODATA_AVILABLE="no data avilable";
	public static final Integer INACIVATEID =3;
	public static final Integer ACIVATEID =1;
	public static final String STATUS_PKID="pkStatusId";
	public static final String STATUS_NAME="statusName";
	public static final String DATE_FORMAT="dd/MM/YYYY";
	
	

	//Forex
	public static final int DRAFT_STATUS_ID=2;
	public static final int ACTIVE_STATUS_ID=1;
	public static final int INACTIVE_STATUS_ID=3;
	public static final String FOREX_NAME="NAME";
	public static final String FOREX_CODE="CODE";
	public static final String FOREX_CURRENCY_PAIR="CP";
	public static final String FOREX_NAME_EXITS=" name already exists, Please check and submit again";
	public static final String FOREX_CODE_EXITS=" code already exists, Please check and submit again";
	public static final String FOREX_CURRENCY_PAIR_EXITS="currency pair already exists, Please check and submit again";
	public static final String FOREX_CURRENCY_SAME="Base Currency and Counter Currency cannot be same";
	public static final String FOREX_VALIDATION_SUCCESS="Success";
	public static final String FOREX_NAME_NULL="Forex name cannot be null";
	public static final String FOREX_CURR_DETAIL_URL="/currencyservice/currency/getCurr/{id}";
	public static final String FOREX_CURR_LIST_URL="/currencyservice/currency/getCurrList";
	public static final String FOREX_STATUS_SERVICE_CALL_URL="/statusservice/status/statusById/{id}";
	public static final String FOREX_SAVE_SUCCESS_DRAFT="Forex :name has been created and saved in �Draft� mode";
	public static final String FOREX_SAVE_SUCCESS_ACTIVE="Forex :name has been created and saved in �Active� mode";
	public static final String FOREX_SAVE_FAILURE="Some error occured Forex :name cannot be saved";
	public static final String FOREX_DELETE_SUCCESS="You have successfully deleted :name";
	public static final String FOREX_DEACTIVATE_SUCCESS="You have successfully deactivated :name";
	public static final String FOREX_REACTIVATE_SUCCESS="You have successfully reactivated :name";
	public static final String FOREX_DELETE_FAILURE="Failed";
	public static final String FOREX_NAME_MESSAGE=":name";
	public static final String URL="reference.url";
	public static final String STATUS_URL="/reference/statusservice/status/";
	public static final String EXCHANGE_TERM_MONTH_TYPE_URL="/exchangeTermMonthTypeservice/exchangeTermMonthType/";
	public static final String PARTY_URL = "/party/partyservice/party/";
	public static final String UOM_URL = "/reference/uomservice/uom/";
	public static final String PRODUCT_URL = "/product/productservice/product/";
	public static final String CURRENCY_URL = "/reference/currencyservice/currency/";
	public static final String HOLIDAY_CAL_URL = "/holidayservice/holiday/";
	public static final String CREATE = "create";
	public static final String PRECENTAGE="%";
	public static final String FOREX_DESC="DESC";
	public static final String SAVE_CAPS="SAVE";
	public static final int ZERO=0;
	
	//Primary Packing
		public static final String IP_ADD_FAILURE="Some error occured Primary Packing Type :name cannot be saved";
		public static final String IP_SAVE_SUCCESS_DRAFT="Primary Packing type :name has been created and saved in “Draft” mode";
		public static final String IP_SAVE_SUCCESS_ACTIVE="Primary Packing type :name has been created and saved in “Active” mode";
		public static final String IP_NAME_MESSAGE=":name";
		public static final String IP_CODE_MESSAGE=":code";
		public static final String IP_VALIDATION_SUCCESS="Success";
		public static final String IP_NAME_EXISTS="Primary Packing type name :name already exists";
		public static final String IP_CODE_EXISTS="Primary Packing type code :name already exists";
		public static final String IP_NAME_CODE_EXISTS="Primary Packing type name :name and code :code already exists";
		public static final String IP_DELETE_SUCCESS="Successfully deleted the Primary Packing Type :name";
		public static final String IP_REACTIVE_SUCCESS="Successfully Reactivated the Primary Packing Type :name";
		public static final String IP_DEACTIVE_SUCCESS="Successfully DeActivated the Primary Packing Type :name";
		public static final String IP_DELETE_FAILURE="Failed";
		public static final String IP_NAME="NAME";
		public static final String IP_CODE="CODE";
		public static final String STATUS_URL1="/statusservice/status/getStatusName/{id}";
		public static final String UOM_URL1="/reference/uomservice/uom/getUomName/{id}";
		public static final String UOM_URL2="/reference/uomservice/uom/getUomCode/{id}";

		public static final String IP_DELETE="DELETE";
		public static final String IP_UPDATE_REACTIVATE="REACTIVATE";
		public static final String IP_UPDATE_DEACTIVATE="DEACTIVATE";
		public static final String IP_UPDATE="UPDATE";
		public static final String IP_UPDATE_REACTIVE_SUCCESS="Successfully Updated and Reactivated the Primary Packing Type :name";
		public static final String IP_UPDATE_DEACTIVE_SUCCESS="Successfully Updated DeActivated the Primary Packing Type :name";
		public static final String IP_UPDATE_SUCCESS="Successfully Updated the Primary Packing Type :name";
		public static final String IP_UPDATE_HTTP_STATUS_COMPARISON_STRING="Successfully";
		public static final String IP_VIEW_DELETE="DELETE";
		public static final String IP_VIEW_ACTIVATE="ACTIVATE";
		public static final String IP_VIEW_DEACTIVATE="DEACTIVATE";
		public static final String IP_BULK="Yes";
		public static final String IP_NOT_BULK="No";
		
		//Brand Master
		public static final String BRAND_ADD_SUCCESS_DRAFT="Brand :name has been created and saved in “Draft” mode";
		public static final String BRAND_ADD_SUCCESS_ACTIVE="Brand :name has been created and saved in “Active” mode";
		public static final String BRAND_HTTP_RESPONSE_COMPARISON_STRING="mode";
		public static final String BRAND_ADD_FAILURE="Some error occured Brand :name cannot be saved";
		public static final String BRAND_NAME_MESSAGE=":name";
		public static final String BRAND_CODE_MESSAGE=":code";
		public static final String BRAND_VALIDATION_SUCCESS="Success";
		public static final String BRAND_NAME_EXISTS="Brand name :name already exists";
		public static final String BRAND_CODE_EXISTS="Brand code :name already exists";
		public static final String BRAND_NAME_CODE_EXISTS="Brand name :name and code :code already exists";
		public static final String BRAND_DELETE_SUCCESS="Successfully deleted the Brand :name";
		public static final String BRAND_REACTIVE_SUCCESS="Successfully Reactivated the Brand :name";
		public static final String BRAND_DEACTIVE_SUCCESS="Successfully DeActivated the Brand :name";
		public static final String BRAND_DELETE_FAILURE="Failed";
		public static final String BRAND_NAME="NAME";
		public static final String BRAND_CODE="CODE";
		public static final String BRAND_DELETE="DELETE";
		public static final String BRAND_UPDATE_REACTIVATE="ACTIVATE";
		public static final String BRAND_UPDATE_DEACTIVATE="DEACTIVATE";
		public static final String BRAND_UPDATE="UPDATE";
		public static final String BRAND_UPDATE_ACTIVE_SUCCESS="Successfully Updated and Activated the Brand :name";
		public static final String BRAND_UPDATE_REACTIVE_SUCCESS="Successfully Updated and Reactivated the Brand :name";
		public static final String BRAND_UPDATE_DEACTIVE_SUCCESS="Successfully Updated DeActivated the Brand :name";
		public static final String BRAND_UPDATE_SUCCESS="Successfully Updated the Brand :name";
		public static final String BRAND_VIEW_DELETE="DELETE";
		public static final String BRAND_VIEW_ACTIVATE="ACTIVATE";
		public static final String BRAND_VIEW_DEACTIVATE="DEACTIVATE";
		public static final String BRAND_INTERNAL="Internal";
		public static final String BRAND_EXTERNAL="External";
		public static final String YES_VIEW="1";
		public static final String NO_VIEW="0";
	/** Unit Master */
	public static final String UPDATE = "UPDATE";
	public static final String UPDATE_INACTIVATE = "UPDATEINACIVE";
	public static final String UPDATE_ACTIVATE = "UPDATEACIVE";
	public static final String UN = ":UN";
	public static final String SAVE_SUCCESS_DRAFT = "Saved :UN as Draft";
	public static final String SAVE_SUCCESS_ACTIVE = "updated :UN as Active";
	public static final String SAVE_SUCCESS_INACTIVE = "updated :UN as Inactive";
	
	public static final String REACTIVATED="Reactivated";
	public static final String ACTIVATED="Activated";
	
	//packingmeterial
    public static final String  YES= "Y";
    public static final String  NO= "N";
    public static final Integer ONE=1;
    public static final String NAME="NAME";
    public static final String CODE="CODE";
    public static final String PM_NAME_EXISTS="PackingMaterialName NAME already exists";
    public static final String PM_CODE_EXISTS="PackingMaterialCode CODE already exists";
    public static final String UOM_PKID="uomId";
    public static final String UOM_NAME="uomName";
    public static final String SAVE_PACKINGMATERIAL_SUCCESS_DRAFT = "Saved PackingMaterial as Draft";
    public static final String SAVE_PACKINGMATERIAL_SUCCESS_ACTIVE = "saved PackingMaterial as Active";
    public static final String SAVE_PACKINGMATERIAL_SUCCESS_INACTIVE = "saved PackingMaterial as InActive";
    public static final String No_RECORDS="No records avilable";
	public static final String HEDGE_RATIO = "0";
	
	//Limits basis type
	public static final String PARTY_BASISTYPE="PARTY";
    public static final String PRODUCT_BASISTYPE="PRODUCT";
    public static final String FOREX_BASISTYPE="FOREXCURRENCY";
    public static final String TRADER_BASISTYPE="TRADER";
    public static final String EXCHANGE_BASISTYPE="EXCHANGE";
    public static final String UNIT_BASISTYPE="UNIT";
    
    //Limit attribute type
    public static final String OUTRIGHT_LIMIT="OUTRIGHT LIMIT";
    public static final String BASIS_LIMIT="BASIS LIMIT";
    public static final String STRUCTURE_LIMIT="STRUCTURE LIMIT";
    public static final String DRAWDOWN_LIMIT="DRAWDOWN LIMIT";
    public static final String VAR_LIMIT="VAR LIMIT";
    public static final String DELTA_LIMIT="DELTA LIMIT";
    public static final String GAMMA_LIMIT="GAMMA LIMIT";
    public static final String VEGA_LIMIT="VEGA LIMIT";
    public static final String CURRENT_MONTH="CURRENT MONTH";
    public static final String FORWARD_MONTH="FORWARD MONTH";
    public static final String TOTAL="TOTAL";
    public static final String VALUE="VALUE";

    public static final String PREFIX_LIMIT_NO="LT";
    
    //Property file UOM Error Constants
    public static final String ERROR_DUPLICATE_UOM_NAME="error_duplicate_uom_name";
    public static final String ERROR_DUPLICATE_UOM_CODE="error_duplicate_uom_code";
    
    
    //Error type
    public static final String ERROR_TYPE_DUPLICATE_NAME="Duplicate Error for Name";
    public static final String ERROR_TYPE_DUPLICATE_CODE="Duplicate Error for Code";
    
    //Property file Limit Error Constants
    public static final String ERROR_TEMPORARY_DATE_OVERLAP="error_temporary_date_overlap";
    public static final String ERROR_PERMANENT_DATE_OVERLAP="error_permanent_date_overlap";
    public static final String ERROR_SAME_BASIS_ADDNLBASIS="error_same_basis_addnlbasis";
    public static final String ERROR_BASIS_BASISNAME_DUPLICATE="error_basis_basisname_duplicate";
    public static final String ERROR_BASIS_BASISNAME_ADDNLBASIS_ADDNLBASISNAME_DUPLICATE="error_basis_basisname_addnlbasis_addnlbasisname_duplicate";
    public static final String ERROR_LIMIT_IS_TEMPORARY_NOTNULL="error_istemp_notNull";
    public static final String ERROR_LIMIT_FROMDATE_NOTNULL="error_fromdate_notNull";
    public static final String ERROR_LIMIT_TODATE_NOTNULL="error_todate_notNull";
    
    //Error type for Limits
    public static final String ERROR_TYPE_TEMPORARY_DATE_OVERLAP="Temporary Date overlap Error";
    public static final String ERROR_TYPE_PERMANENT_DATE_OVERLAP="Permanent Date overlap Error";
    public static final String ERROR_TYPE_SAME_BASIS_ADDNLBASIS="Basis and Additioal Basis same Error";
    public static final String ERROR_TYPE_BASIS_BASISNAME_DUPLICATE="Basis and Basis name not unique Error";
    public static final String ERROR_TYPE_BASIS_BASISNAME_ADDNLBASIS_ADDNLBASISNAME_DUPLICATE="Basis, Basis name,Additioal Basis, Additioal Basis name not unique Error";
    public static final String ERROR_TYPE_LIMIT_IS_TEMPORARY_NOTNULL="Is Temporary null error";
    public static final String ERROR_TYPE_LIMIT_FROMDATE_NOTNULL="Limit From date null error";
    public static final String ERROR_TYPE_LIMIT_TODATE_NOTNULL="Limit To date null error";
    
    public static final char EQUAL='=';
    public static final String MARKET_DESK_URL="/reference/marketDesk";
    public static final String MARKET_DESK_ID="pkMarketDeskId";
    public static final String MARKET_DESK_CODE="marketDeskCode";
    public static final String MARKET_DESK_NAME="marketDeskName";
    public static final String GEO_ID="pkGeoId";
    public static final String ERROR_DUPLICATE_GEO_NAME="error_duplicate_geo_name";
    public static final String ERROR_DUPLICATE_GEO_CODE="error_duplicate_geo_code";
	
	//taxrate
    public static final String TAXRATE_DELETE_FAILURE="Failed";
    public static final int TAXRATE_ACTIVE_STATUS_ID=1;
    public static final int TAXRATE_DEACTIVE_STATUS_ID=2;	
	public static final int TAXRATE_DRAFT_STATUS_ID=3;
	public static final String TAXRATE_DELETE_SUCCESS="You have successfully deleted :name";
	public static final String TAXRATE_NAME_MESSAGE=":name";
	public static final String LOCATION_URL="/location/geoservice/geo";
	public static final String CALENDAR_DEACTIVATE_FAILURE = "Failed";
	public static final String TAXRATE_DEACTIVATE_SUCCESS="You have successfully deactivated :name";
	public static final String TAXRATE_REACTIVATE_SUCCESS="You have successfully reactivated :name";
	public static final String TAXRATE_BLANK_STATUS_ID="Wrong Status Value Accepted Status Values - ACTIVE, DEACTIVE,DRAFT";
	
	//reference claendar
	 public static final String CALENDAR_DELETE_FAILURE="Failed";
	 public static final String CALENDAR_NAME_MESSAGE=":name";
	 public static final String CALENDAR_DELETE_SUCCESS="You have successfully deleted :name";
	 public static final String CALENDAR_DEACTIVATE_SUCCESS="You have successfully deactivated :name";
  public static final String CALENDAR_REACTIVATE_SUCCESS="You have successfully reactivated :name";
  public static final String CALENDAR_BLANK_STATUS_ID="Wrong Status Value Accepted Status Values - ACTIVE, DEACTIVE,DRAFT";
    public static final String STATUS="status";
	
	//Error type for Certification
    public static final String ERROR_TYPE_DUPLICATE_CERTF_REGNO="Duplicate Error for Registration number";
    
    //Property file Base payment term Error Constants
    public static final String ERROR_DUPLICATE_BASE_PAYTERM_NAME="error_duplicate_base_payterm_name";
    public static final String ERROR_DUPLICATE_BASE_PAYTERM_CODE="error_duplicate_base_payterm_code";
    
    //Property file Payment term Error Constants
    public static final String ERROR_DUPLICATE_PAYTERM_NAME="error_duplicate_payterm_name";
    public static final String ERROR_DUPLICATE_PAYTERM_CODE="error_duplicate_payterm_code";
    
    //Property file Product term Error Constants
    public static final String ERROR_DUPLICATE_PRODUCT_NAME="error_duplicate_prod_name";
    public static final String ERROR_DUPLICATE_PRODUCT_CODE="error_duplicate_prod_code";
    
    //Property file Grade term Error Constants
    public static final String ERROR_DUPLICATE_GRADE_NAME="error_duplicate_grade_name";
    public static final String ERROR_DUPLICATE_GRADE_CODE="error_duplicate_grade_code";
    
    //Property file Crop year term Error Constants
    public static final String ERROR_DUPLICATE_CROPYEAR_NAME="error_duplicate_cropyear_name";
    public static final String ERROR_DUPLICATE_CROPYEAR_CODE="error_duplicate_cropyear_code";
    
    //blend constants
    public static final String BLEND_ADD_SUCESS="ADD_BLEND_01";
    public static final String WAREHOUSE="Warehouse";
    //Property file Certification Error Constants
    public static final String ERROR_DUPLICATE_CERTF_NAME="error_duplicate_certf_name";
    public static final String ERROR_DUPLICATE_CERTF_CODE="error_duplicate_certf_code";
    public static final String ERROR_DUPLICATE_CERTF_REGNO="error_duplicate_certf_regno";
    
 }


