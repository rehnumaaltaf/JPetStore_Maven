package com.olam.score.party.constants;

public class PartyConstants {

	public static final Object CLASSIFICATION_WAREHOUSE = "Warehouse";
	public static final Object CLASSIFICATION_ROASTER = "Roaster";
	public static final Object TYPE_CUSTOMER = "Customer";
	public static final String FLAG_YES = "Y";
	public static final String FLAG_NO = "N";
	public static final Object CLASSIFICATION_BROKER = "Broker";
	public static final Object CLASSIFICATION_EXCHANGE = "Exchange";
	public static final String DEV = "dev";
	public static final String EXCHANGE_DETAILS_URI = "/marketdata/partyexchangeservice/exchangeservice/";
	public static final String PARTY_LIMITS_URI = "/limit/counterpartylimitservice/counterpartylimit/";

	public static final String SAP_CUSTOMER_TYPE = "C";
    public static final String SAP_VENDOR_TYPE = "V";
    public static final String EXTERNAL_SYSTEM_SAP = "SAP";
    public static final String TYPE_VENDOR = "Vendor";
	public static final String SAP_BLOCKED_VALUE = "X";
	public static final Integer EXTERNAL_SYSTEM_SAP_MAPPING = 1;
	public static final String VALID = "Valid";
	public static final Integer STATUS_SAP_DRAFT_ID = 8;
	public static final Integer STATUS_INACTIVE_ID = 3;
	
	public static final String PARTY_CODE_EXISTS_INVALID_DATA = "Party Code already exists in CTRM ,Invalid data coming from SAP";
	public static final String INVALID_DATA_FROM_SAP_FOR_PARTY_NAME_CANNOT_BE_EMPTY = "Invalid data from SAP for Party Name – Cannot be empty";
	public static final String STATUS_CODE_INVALID = "Status Code Invalid";
	public static final String INVALID_DATA_FOR_CUSTOMER_VENDOR_CODE = "Invalid data for Customer / Vendor code";
	public static final String INVALID_DATA_COMING_FROM_SAP = "Invalid data coming from SAP.";
	
	public static final Integer INVALID_DATA = 400;
	public static final String TRANSACTION_FAILURE = "FAIL";
	public static final String TRANSACTION_SUCCESS = "SUCCESS";
	public static final Integer SUCCESS_CODE = 200;
	public static final Integer VENDOR_TYPE_CODE = 420;
	public static final Integer CUSTOMER_TYPE_CODE = 421;
	
}
