package com.olam.score.common.util;
import java.util.Locale;
import org.springframework.stereotype.Component;
import com.olam.score.common.dto.UserDto;

/**
 * 
 * @author sanjay.das
 *
 */
@Component
public class UserUtil {
	
	public UserDto GetLoggedInUser() {
		UserDto userObj = new UserDto();
		userObj.setName("Manu");
		userObj.setUserId(1);
		userObj.setLocale(new Locale("en"));
		userObj.setSecretKey("KEY"); 
		return userObj;
	}
}
