package com.scsu.ia;

public class User {

	private String userId = null;
	private String userPass = null;
	private String email = null;
	private String phone = null;
	
	private String ipAddr = null;
	private String zipcode = null;
	private String city = null;
	private String state = null;
	private String country = null;
	private String mac = null;
	private String weekday = null;
	private String timeFrame = null;
	
	private int ipAddrCount = 0;
	private int zipcodeCount = 0;
	private int cityCount = 0;
	private int stateCount = 0;
	private int countryCount = 0;
	private int macCount = 0;
	private int weekdayCount = 0;
	private int timeFrameCount = 0;
	private int total = 0;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getWeekday() {
		return weekday;
	}
	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}
	public String getTimeFrame() {
		return timeFrame;
	}
	public void setTimeFrame(String timeFrame) {
		this.timeFrame = timeFrame;
	}
	public int getIpAddrCount() {
		return ipAddrCount;
	}
	public void setIpAddrCount(int ipAddrCount) {
		this.ipAddrCount = ipAddrCount;
	}
	public int getZipcodeCount() {
		return zipcodeCount;
	}
	public void setZipcodeCount(int zipcodeCount) {
		this.zipcodeCount = zipcodeCount;
	}
	public int getCityCount() {
		return cityCount;
	}
	public void setCityCount(int cityCount) {
		this.cityCount = cityCount;
	}
	public int getStateCount() {
		return stateCount;
	}
	public void setStateCount(int stateCount) {
		this.stateCount = stateCount;
	}
	public int getCountryCount() {
		return countryCount;
	}
	public void setCountryCount(int countryCount) {
		this.countryCount = countryCount;
	}
	public int getMacCount() {
		return macCount;
	}
	public void setMacCount(int macCount) {
		this.macCount = macCount;
	}
	public int getWeekdayCount() {
		return weekdayCount;
	}
	public void setWeekdayCount(int weekdayCount) {
		this.weekdayCount = weekdayCount;
	}
	public int getTimeFrameCount() {
		return timeFrameCount;
	}
	public void setTimeFrameCount(int timeFrameCount) {
		this.timeFrameCount = timeFrameCount;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userPass=" + userPass + ", email=" + email + ", phone=" + phone
				+ ", ipAddr=" + ipAddr + ", zipcode=" + zipcode + ", city=" + city + ", state=" + state + ", country="
				+ country + ", mac=" + mac + ", weekday=" + weekday + ", timeFrame=" + timeFrame + ", ipAddrCount="
				+ ipAddrCount + ", zipcodeCount=" + zipcodeCount + ", cityCount=" + cityCount + ", stateCount="
				+ stateCount + ", countryCount=" + countryCount + ", macCount=" + macCount + ", weekdayCount="
				+ weekdayCount + ", timeFrameCount=" + timeFrameCount + ", total=" + total + "]";
	}
	
	
}
