package com.scsu.ia;

import java.io.File;
import java.io.IOException;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;

public class GetLocationFromIp {
//	public static void main(String[] args) {
//		GetLocationFromIp obj = new GetLocationFromIp();
//		ServerLocation location = obj.getLocation("206.190.36.45");
//		System.out.println(location);
//	  }

//	  public ServerLocation getLocation(String ipAddress) {
//
//		File file = new File(Policy.GEODATA_PATH);
//		return getLocation(ipAddress, file);
//
//	  }

	  public User getLocation(User user) {
//		User user1 = user;
		File file = new File(Policy.GEODATA_PATH);
//		ServerLocation serverLocation = null;

		try {

//		serverLocation = new ServerLocation();

		LookupService lookup = new LookupService(file,LookupService.GEOIP_MEMORY_CACHE);
		Location locationServices = lookup.getLocation(user.getIpAddr());

		user.setCountry(locationServices.countryCode);
//		serverLocation.setCountryName(locationServices.countryName);
		user.setState(locationServices.region);
//		serverLocation.setRegionName(regionName.regionNameByCode(
//	             locationServices.countryCode, locationServices.region));
		user.setCity(locationServices.city);
		user.setZipcode(locationServices.postalCode);
//		serverLocation.setLatitude(String.valueOf(locationServices.latitude));
//		serverLocation.setLongitude(String.valueOf(locationServices.longitude));

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return user;
	  }
}
