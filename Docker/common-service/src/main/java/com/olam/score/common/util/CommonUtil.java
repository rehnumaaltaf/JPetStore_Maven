package com.olam.score.common.util;


import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.transaction.SystemException;

import org.springframework.web.client.RestTemplate;

import com.olam.score.common.domain.UserBean;
import com.olam.score.common.dto.Link;

/**
 * 
 * @author sanjay.das
 *
 */
public class CommonUtil {
	private static Map<Locale,ResourceBundle> bundleMap;

	private CommonUtil() {
	}

	/**
	 * 
	 * @param href
	 * @param relativePath
	 * @param token
	 * @return
	 */
	public static String generateUrl(String href, String relativePath, String token) {
		String url;
		if (relativePath != null) {
			String[] parts = relativePath.split(token);
			url = parts[0].concat(href);
		} else {
			url = href;
		}
		return url;
	}

	// TODO : Needs to be dynamic based on Access of user
	// TODO : Get all features of user based on Access
	public static List<Link<?>> setLinks(Class<? extends Object> class1, String method, UserBean userBean) {
		List<Link<?>> links = new ArrayList<>();

		// For masterService all the services needs to be sent as part of links
		// based on Access of user
		if (class1.getSimpleName().equalsIgnoreCase("MasterController")) {
			links.add(new Link(Object.class, method, false));
		} else {
			links.add(new Link(class1, method, true));
			// Adding CRUD access as part for demo. These will be later dynamic
			// based on Access of user
			links.add(new Link(class1, "DELETE", true));
			links.add(new Link(class1, "UPDATE", true));
			links.add(new Link(class1, "CREATE", true));
		}

		return links;
	}

	/**
	 * 
	 * @param conn
	 * @param methodPath
	 * @param parameters
	 * @return
	 * @throws SystemException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean isLinkAccessable(Connection conn, String methodPath, Map<String, Object> parameters) {
		String methodPathNotNull = methodPath;// TODO: NUll Check to be
												// implemented
		int lastDotIndx = methodPathNotNull.lastIndexOf('.');
		if (lastDotIndx == -1)
			return true;
		String className = methodPathNotNull.substring(0, lastDotIndx);
		String methodName = methodPathNotNull.substring(lastDotIndx + 1);
		try {
			Class cls = Class.forName(className);
			Object obj = cls.newInstance();
			Method method = cls.getDeclaredMethod(methodName, new Class[] { Connection.class, Map.class });
			return (boolean) method.invoke(obj, conn, parameters);
		} catch (Exception e) {
			// TODO: Error Handling
		}
		return false;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String getStatusNameById(String url){

		RestTemplate restTemplate = new RestTemplate();
		String responseEntity=restTemplate.getForObject(url, String.class);
		return responseEntity;
	}

public static String getTranslationMessage(Locale locale, String key)





























	{
		String retMessage = "";
		
		if(bundleMap==null)
		{
			bundleMap = new HashMap<>();
			bundleMap.put(locale, ResourceBundle.getBundle("Messages", Locale.getDefault()));
		}
		else if(!bundleMap.containsKey(locale))
		{
			bundleMap.put(locale, ResourceBundle.getBundle("Messages", Locale.getDefault()));			
		}
		ResourceBundle resBundle = bundleMap.get(locale);
		retMessage = resBundle.getString(key);
		return retMessage;
	}

	public static boolean isEmpty(String value) {
		boolean isEmpty = true;
		if (value != null && !"".equals(value.trim())) {
			isEmpty = false;
		}
		return isEmpty;
	}
}
