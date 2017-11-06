package com.scsu.ia;

import java.io.File;
import java.io.IOException;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;
/**
 * @purpose GetLocationFromIP class will use 3rd party library to convert IP address
 *          to Location information
 * @author Darren
 *
 */
public class GetLocationFromIp {

	  public User getLocation(User user) {
		File file = new File(Policy.GEODATA_PATH);

		try {

		LookupService lookup = new LookupService(file,LookupService.GEOIP_MEMORY_CACHE);
		Location locationServices = lookup.getLocation(user.getIpAddr());

		user.setCountry(locationServices.countryCode);
		user.setState(locationServices.region);

		user.setCity(locationServices.city);
		user.setZipcode(locationServices.postalCode);

		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		return user;
	  }
}
