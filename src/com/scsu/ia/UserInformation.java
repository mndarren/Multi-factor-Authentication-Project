package com.scsu.ia;

import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.PrintWriter;
import java.sql.SQLException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.naming.NamingException;
//import javax.naming.Context;
//import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @purpose UserInformation Servlet communicate with Client side
 * @author Darren
 */
public class UserInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInformation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		String ipAddr = request.getParameter("ipAddr");
		String mac = request.getParameter("macAddr");
		System.out.println("In Servlet, IP = "+ipAddr + "   MAC = "+mac);
		Gson gson = new Gson();		
		
		String day = getWeekday();
		int time = LocalDateTime.now().getHour();
		User loginInfo = new User();
		loginInfo.setIpAddr(ipAddr);
		loginInfo.setUserId(userId);
		loginInfo = (new GetLocationFromIp()).getLocation(loginInfo);
		loginInfo.setUserPass(userPass);
		loginInfo.setWeekday(day);
		loginInfo.setTimeFrame(convertTimeFrame(time));
		loginInfo.setMac(mac);
		 
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");
 
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
 
        JsonObject myObj = RiskEngine.INSTANCE.getInfo(loginInfo);
        if(!myObj.get("success").getAsBoolean()){
        	RiskEngine.INSTANCE.flushData();
        }
        int finalScore = myObj.get("finalScore").getAsInt();
        String barrier = getBarrier(finalScore);
        if("0".equals(barrier)){
        	try {
				RiskEngine.INSTANCE.updateDB();
				RiskEngine.INSTANCE.flushData();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        myObj.addProperty("barrier", barrier);
        JsonElement je = gson.toJsonTree(loginInfo);
        myObj.add("user", je);
        	
        System.out.println(myObj.toString());
        out.println(myObj.toString());
        System.out.println("end of the Servlet!");
        out.close();
	}

	private String getBarrier(int finalScore) {
		if(finalScore >= Policy.LEVEL_ZERO){
			return Policy.BARRIER[0];
		} else if(finalScore >= Policy.LEVEL_ONE){
			return Policy.BARRIER[1];
		}else if(finalScore >= Policy.LEVEL_TWO){
			return Policy.BARRIER[2];
		}else if(finalScore >= Policy.LEVEL_THREE){
			return Policy.BARRIER[3];
		} else {
			return Policy.BARRIER[4];
		}
		
	}

	private String convertTimeFrame(int time) {
		String timeFrame = null;
		switch(time){
		  case 0:  case 1:  case 2:  {timeFrame = Policy.TIMEFRAME[0]; break;}
		  case 3:  case 4:  case 5:  {timeFrame = Policy.TIMEFRAME[1]; break;}
		  case 6:  case 7:  case 8:  {timeFrame = Policy.TIMEFRAME[2]; break;}
		  case 9:  case 10: case 11: {timeFrame = Policy.TIMEFRAME[3]; break;}
		  case 12: case 13: case 14: {timeFrame = Policy.TIMEFRAME[4]; break;}
		  case 15: case 16: case 17: {timeFrame = Policy.TIMEFRAME[5]; break;}
		  case 18: case 19: case 20: {timeFrame = Policy.TIMEFRAME[6]; break;}
		  case 21: case 22: case 23: {timeFrame = Policy.TIMEFRAME[7]; break;}
		}
		return timeFrame;
	}

	private String getWeekday() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK); 
		String weekday = null;

		switch (day) {
		    case Calendar.SUNDAY: {weekday = "Sun"; break;}
		    case Calendar.MONDAY: {weekday = "Mon"; break;}
		    case Calendar.TUESDAY: {weekday = "Tue"; break;}
		    case Calendar.WEDNESDAY: {weekday = "Wed"; break;}
		    case Calendar.THURSDAY: {weekday = "Thu"; break;}
		    case Calendar.SATURDAY: {weekday = "Sat"; break;}
		    case Calendar.FRIDAY: {weekday = "Fri"; break;}
		}
		return weekday;
	}

}
