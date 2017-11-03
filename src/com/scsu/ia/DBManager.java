package com.scsu.ia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class DBManager {
//	INSTANCE;
	private User user = new User();
	private Connection conn = null;            
	private Statement stmt = null;  
	private PreparedStatement pstmt = null; 
	private CallableStatement cstmt = null;
	private ResultSet rs = null;
	private String sql = null;
//	private static String url = "jdbc:mysql://localhost:3306/ia683project";
//	private static String DBName = "root";
//	private static String DBPass = "";
//	private DBManager(){}
	
    public User getInfo(User loginInfo) {
        try {      
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection(); 
//            conn = DriverManager.getConnection(url, DBName, DBPass);
            stmt = conn.createStatement();
            String userId = loginInfo.getUserId();
            
            int total = getTotal(userId);
            int weekdayWeight = getDayWeight(userId,loginInfo.getWeekday());
            int timeWeight = getTimeWeight(userId,loginInfo.getTimeFrame());
            int zipcodeWeight = getZipcodeWeight(userId,loginInfo.getZipcode());
            int cityWeight = getCityWeitht(userId,loginInfo.getCity());
            int stateWeight = getStateWeight(userId,loginInfo.getState());
            int countryWeight = getCountryWeight(userId,loginInfo.getCountry());
            int macWeight = getMacWeight(userId, loginInfo.getMac());
             
            user.setUserId(userId);
            user.setTotal(total);
            user.setWeekdayCount(weekdayWeight);
            user.setCityCount(cityWeight);
            user.setStateCount(stateWeight);
            user.setCountryCount(countryWeight);
            user.setTimeFrameCount(timeWeight);
            user.setZipcodeCount(zipcodeWeight);
            user.setMacCount(macWeight);
 
//            System.out.println(user.toString());
            rs.close();                                                               
            stmt.close();                                                             
            stmt = null;                                                              
            conn.close();                                                             
            conn = null; 
        }catch(Exception e){System.out.println(e);
        }finally {
            if (stmt != null) {                                            
                try {                                                         
                    stmt.close();                                                
                } catch (SQLException sqlex) {                                
                    // ignore -- as we can't do anything about it here           
                }                                                             
                stmt = null;                                            
            }                                                        
 
            if (conn != null) {                                      
                try {                                                   
                    conn.close();                                          
                } catch (SQLException sqlex) {                          
                    // ignore -- as we can't do anything about it here     
                }                                                       
 
                conn = null;                                            
            }                                                        
        }              
        return user;
    }  
    public boolean updateUser(User loginInfo) throws NamingException, SQLException{
    	Context ctx = (Context) new InitialContext().lookup("java:comp/env");
        conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection(); 
//        conn = DriverManager.getConnection(url, DBName, DBPass);
        cstmt=conn.prepareCall("{call verifyAndUpdate(?,?,?,?,?,?,?,?,?)}"); 
        
        cstmt.setString(1,loginInfo.getUserId());  
        cstmt.setString(2,loginInfo.getWeekday());
        cstmt.setString(3,loginInfo.getTimeFrame());
        cstmt.setString(4,loginInfo.getIpAddr());
        cstmt.setString(5,loginInfo.getZipcode());
        cstmt.setString(6,loginInfo.getCity());
        cstmt.setString(7,loginInfo.getState());
        cstmt.setString(8,loginInfo.getCountry());
        cstmt.setString(9,loginInfo.getMac());
        
        cstmt.execute();         
//        flushData();
    	return true;
    }
    
    public void flushData(){
    	user = new User();
    	rs = null;
    	sql = null;
    }
	private int getMacWeight(String userId, String mac) throws SQLException {
		sql = "select count from device where user_id = '"+ userId +"' and mac_addr = '"+mac+"';";
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			int c=rs.getInt("count");
			System.out.println("Mac count = "+c);
			return c;
		}
		return 0;
	}
	private int getCountryWeight(String userId, String country) throws SQLException {
		sql = "select count from location_country where user_id = '"+ userId +"' and country = '"+country+"';";
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			int c=rs.getInt("count");
			System.out.println("Country count = "+c);
			return c;
		}
		return 0;
	}
	private int getStateWeight(String userId, String state) throws SQLException {
		sql = "select count from location_state where user_id = '"+ userId +"' and state = '"+state+"';";
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			int c=rs.getInt("count");
			System.out.println("State count = "+c);
			return c;
		}
		return 0;
	}
	private int getCityWeitht(String userId, String city) throws SQLException {
		sql = "select count from location_city where user_id = '"+ userId +"' and city = '"+city+"';";
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			int c=rs.getInt("count");
			System.out.println("City count = "+c);
			return c;
		}
		return 0;
	}
	private int getZipcodeWeight(String userId, String zip) throws SQLException {
		sql = "select count from location_zipcode where user_id = '"+ userId +"' and zipcode = '"+zip+"';";
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			int c=rs.getInt("count");
			System.out.println("Zipcode count = "+c);
			return c;
		}
	    return 0;
	}
	private int getTimeWeight(String userId, String timeFrame) throws SQLException {
		sql = "select count from time_frame where user_id = '"+ userId +"' and time_frame = '"+timeFrame+"';";
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			int c=rs.getInt("count");
			System.out.println("Time count = "+c);
			return c;
		}
	    return 0;
	}
	private int getDayWeight(String userId, String weekday) throws SQLException {
		sql = "select count from day_login where user_id = '"+ userId +"' and weekday = '"+weekday+"';";
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			int c=rs.getInt("count");
			System.out.println("Day count = "+c);
			return c;
		}
	    return 0;
	}
	private int getTotal(String userId) throws SQLException {
		sql = "select total from total where user_id = '"+ userId +"';";
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			int c=rs.getInt("total");
			System.out.println("Total = "+c);
			return c;
		}
	    return 0;
    }

	public boolean isCorrectUser(String userId, String userPass) {
		boolean isRightUser = false;
		try {      
            Context ctx = (Context) new InitialContext().lookup("java:comp/env");
            conn = ((DataSource) ctx.lookup("jdbc/mysql")).getConnection(); 
//			conn = DriverManager.getConnection(url, DBName, DBPass);
 
            sql = "select * from user where user_id = ? and user_pass = ?;"; 
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId.trim());
            pstmt.setString(2, userPass.trim());
            ResultSet rs = pstmt.executeQuery();
 
            if(rs.next()){
            	if(rs.getString("user_pass").equals(userPass)){
            		isRightUser = true;
            		System.out.println("userId and userPass are correct with DB record");
            	}
            }
            		      
            rs.close();                                                               
            pstmt.close();                                                             
            pstmt = null;                                                              
            conn.close();                                                             
            conn = null; 
        }catch(Exception e){System.out.println(e);
        }finally {
            if (pstmt != null) {                                            
                try {                                                         
                    pstmt.close();                                                
                } catch (SQLException sqlex) {                                
                    // ignore -- as we can't do anything about it here           
                }                                                             
                pstmt = null;                                            
            }                                                        
            if (conn != null) {                                      
                try {                                                   
                    conn.close();                                          
                } catch (SQLException sqlex) {                          
                    // ignore -- as we can't do anything about it here     
                }                                                       
                conn = null;                                            
            }                                                        
        }              
		return isRightUser;
	}

}
