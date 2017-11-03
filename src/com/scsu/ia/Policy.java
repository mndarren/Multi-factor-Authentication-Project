package com.scsu.ia;

public class Policy {
	public final static int LOCATION = 30;
	public final static int LOCATION_ZIPCODE = 5;
	public final static int LOCATION_CITY = 10;
	public final static int LOCATION_STATE = 10;
	public final static int LOCATION_COUNTRY = 5;
	public final static int DEVICE = 40;
	public final static int WEEKDAY = 15;
	public final static int TIME = 15;
	
	public final static double TRUST_RATE = 0.25; //one quarter means trust
	public final static double EXIST_RATE = 0.8;  // Score rate if less than one quarter
	
	public static final int LEVEL_ZERO = 90;
	public static final int LEVEL_ONE = 70;
	public static final int LEVEL_TWO = 50;
	public static final int LEVEL_THREE = 30;
	public static final int LEVEL_FURE = 0;
	
	
	public final static String[] TIMEFRAME = {"0-3am","3-6am","6-9am","9-12am",
									   "0-3pm","3-6pm","6-9pm","9-12pm"};
	public final static String[] BARRIER = {"true","secret_question","email_code",
			                           "phone_code","face_verification"};
	
	public static final String GEODATA_PATH = "E:\\ganhuo\\location\\GeoLiteCity.dat";
}
