package com.scsu.ia;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
/**
 * @purpose RiskEngie is a singleton which is charge of calculating the risk score
 *          for each user login based on location, time, date, device MAC
 * @author Darren
 *
 */
public enum RiskEngine {
	INSTANCE;
    private DBManager dbManager = new DBManager();
    private boolean isCorrectUser = false;
    private int finalScore = 0;
    private User user = null;
    private User loginInfo = null;
    private JsonObject jo = new JsonObject();
    
    private RiskEngine(){}
    
    public JsonObject getInfo(User loginInfo) {
		int locationScore=0,macScore=0,dayScore=0,timeScore=0;
    	if(dbManager.isCorrectUser(loginInfo.getUserId(), loginInfo.getUserPass())){
			isCorrectUser = true;
			this.loginInfo = loginInfo;
			user = dbManager.getInfo(loginInfo);
			
			locationScore = calLocationScore(user);
			macScore = calMacScore(user);
			dayScore = calDayScore(user);
			timeScore = calTimeScore(user);
			finalScore = locationScore+macScore+dayScore+timeScore;
			System.out.println(user);
			System.out.println("locationScore = " + locationScore + ", MacScore = " + macScore
					+ ", DayScore = " + dayScore + ", TimeScore = " + timeScore + "\nFinalScore = "+finalScore);
		} /*else {
			flushData();
		}*/
		//build Json object
        jo.addProperty("success", isCorrectUser ? true : false);
        jo.addProperty("locationScore", locationScore);
        jo.addProperty("macScore", macScore);
        jo.addProperty("dayScore", dayScore);
        jo.addProperty("timeScore", timeScore);
        jo.addProperty("finalScore", finalScore); 
        return jo;
    }
	//reset data
	public void flushData() {

		dbManager = new DBManager();
	    isCorrectUser = false;
	    finalScore = 0;
	    user = null;
	    loginInfo = null;
	    jo = new JsonObject();
	}

	private int calTimeScore(User user2) {
		double timeWeight = (double)user2.getTimeFrameCount() / user2.getTotal();
		System.out.println("timeWeight = "+timeWeight);
		if (timeWeight > 0){
			if (timeWeight >= Policy.TRUST_RATE){
				return Policy.TIME;
			} else {
				return (int) (Policy.TIME * Policy.EXIST_RATE);
			}
		} else {
			return 0;
		}

	}

	private int calDayScore(User user2) {
		double dayWeight = (double)user2.getWeekdayCount() / user2.getTotal();
		System.out.println("dayWeight = "+dayWeight);
		if (dayWeight > 0){
			if (dayWeight >= Policy.TRUST_RATE){
				return Policy.WEEKDAY;
			} else {
				return (int) (Policy.WEEKDAY * Policy.EXIST_RATE);
			}
		} else {
			return 0;
		}
	}

	private int calMacScore(User user2) {
		double macWeight = (double)user2.getMacCount() / user2.getTotal();
		System.out.println("macWeight = "+macWeight);
		if (macWeight > 0){
			if (macWeight >= Policy.TRUST_RATE){
				return Policy.DEVICE;
			} else {
				return (int) (Policy.DEVICE * Policy.EXIST_RATE);
			}
		} else {
			return 0;
		}
	}

	private int calLocationScore(User user2) {
		double zipcodeWeight = (double)user2.getZipcodeCount()/user2.getTotal();
		double cityWeight = (double)user2.getCityCount() / user2.getTotal();
		double countryWeight = (double)user2.getCountryCount() / user2.getTotal();
		double stateWeight = (double)user2.getStateCount() / user2.getTotal();
		System.out.println("zipcodeWeight = "+zipcodeWeight);
		System.out.println("cityWeight = "+cityWeight);
		System.out.println("countryWeight = "+countryWeight);
		System.out.println("stateWeight = "+stateWeight);
		if (zipcodeWeight > 0){
			if(zipcodeWeight >= Policy.TRUST_RATE){
				return Policy.LOCATION;
			} else {
				return (int) (Policy.LOCATION - Policy.LOCATION_ZIPCODE * (1-Policy.EXIST_RATE));
			}
		} else if(cityWeight > 0) {
			if(cityWeight >= Policy.TRUST_RATE){
				return Policy.LOCATION - Policy.LOCATION_ZIPCODE;				
			} else {
				return (int) (Policy.LOCATION_COUNTRY + Policy.LOCATION_STATE
						+ Policy.LOCATION_CITY * Policy.EXIST_RATE);
			}
		} else if (stateWeight > 0){
			if (stateWeight > Policy.TRUST_RATE){
				return Policy.LOCATION_COUNTRY + Policy.LOCATION_COUNTRY;
			} else {
				return (int) (Policy.LOCATION_STATE * Policy.EXIST_RATE
						+ Policy.LOCATION_COUNTRY);
			}
		} else if (countryWeight > 0){
			if (countryWeight > Policy.TRUST_RATE){
				return Policy.LOCATION_COUNTRY;
			} else {
				return (int) ( Policy.LOCATION_COUNTRY * Policy.EXIST_RATE);
			}
		} else {
			return 0;
		}
	}
	//for second check
	public JsonObject isCorrect4SecondCheck(String userId, String userAnswer) throws NamingException, SQLException{
		JsonObject jsonObject = new JsonObject();
		if("right".equals(userAnswer) && userId.equals(loginInfo.getUserId())){
			updateDB();
			System.out.println("Finished updating DB");
			jsonObject.addProperty("success", true);
		} else {
			jsonObject.addProperty("success", false);
		}
		flushData();
		System.out.println("Finished flushData in RiskEngine");
		return jsonObject;
	}

	public boolean updateDB() throws NamingException, SQLException{
		return dbManager.updateUser(loginInfo);
	}
	
}
