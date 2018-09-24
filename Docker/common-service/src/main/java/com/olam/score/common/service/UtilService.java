package com.olam.score.common.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.security.auth.login.*;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olam.score.common.constants.Constants;
import com.olam.score.common.domain.UserBean;
import com.olam.score.common.dto.AuthorizationWrapper;
import com.olam.score.common.dto.Link;
import com.olam.score.common.util.Mapping;

import javax.naming.CommunicationException;
import javax.naming.Context;

import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;


@Service
public class UtilService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	EntityManager entityManager;

	@Transactional
	public List<Link<?>> getFeatures(Class<? extends Object> currectFearure) {
		UserBean userBean = new UserBean();
		// Remove the hard coding below. Get the user details from from session
		// and DB. Then set the user id below.
		userBean.setUserId(1);
		
		Query query = entityManager.createNativeQuery("select DISTINCT f.PK_FEATURE_ID, f.FEATURE_NAME, "
				+ "pl.PK_PERMISSION_LEVEL_ID, pl.PERMISSION_LEVEL_NAME, "
				+ "e.PK_ENTITY_ID, e.ENTITY_NAME, e.ENTITY_URL, " + "m.PK_MODULE_ID, m.MODULE_NAME, m.MODULE_URL "
				+ "from auth.AUTH_PERMISSION_LEVEL pl "
				+ "join auth.AUTH_FEATURE f on pl.PK_PERMISSION_LEVEL_ID=f.FK_PERMISSION_LEVEL_ID "
				+ "join auth.AUTH_MODULE m on f.FK_MODULE_ID=m.PK_MODULE_ID "
				+ "left outer join auth.AUTH_ENTITY e on f.FK_ENTITY_ID=e.PK_ENTITY_ID "
				+ "left outer join auth.AUTH_FEATURE_ATTRIBUTE_MAPPING fam on f.PK_FEATURE_ID=fam.FK_FEATURE_ID "
				+ "join auth.AUTH_ROLE_FEATURE_MAPPING rfm on f.PK_FEATURE_ID = rfm.FK_FEATURE_ID "
				+ "join auth.AUTH_ROLE r on rfm.FK_ROLE_ID=r.PK_ROLE_ID "
				+ "join auth.AUTH_USER_ROLE_MAPPING urm on r.PK_ROLE_ID=urm.FK_ROLE_ID "
				+ "join auth.AUTH_PERMISSION_GROUP_ROLE_MAPPING pgrm on r.PK_ROLE_ID=pgrm.FK_ROLE_ID "
				+ "where fk_app_user_id in(select pk_app_user__id = :userId) "
				+ "and (f.FK_ENTITY_ID in(select PK_ENTITY_ID from auth.AUTH_ENTITY where ENTITY_NAME = :CURRENT_FEATURE_NAME) "
				+ "OR  f.FK_MODULE_ID in (select PK_MODULE_ID from auth.AUTH_MODULE where MODULE_NAME = :CURRENT_FEATURE_NAME) "
				+ "OR fam.FK_ATTRIBUTE_ID in (select PK_ATTRIBUTE_ID from auth.AUTH_ATTRIBUTE where ATTRIBUTE_NAME = :CURRENT_FEATURE_NAME))");
		query.setParameter("userId", userBean.getUserId());
		query.setParameter("CURRENT_FEATURE_NAME", currectFearure.getAnnotation(Mapping.class).featureName());
		List<Object[]> resultList = query.getResultList();

		List<AuthorizationWrapper> authorizationWrappers = new ArrayList<>();
		for (Object[] obj : resultList) {
			AuthorizationWrapper authorizationWrapper = new AuthorizationWrapper();
			authorizationWrapper.setFeatureId((Integer) obj[0]);
			authorizationWrapper.setFeatureName(obj[1].toString());
			authorizationWrapper.setPermissionLevelId((Integer) obj[2]);
			authorizationWrapper.setPermissionLevelname(obj[3].toString());
			authorizationWrapper.setEntityId((Integer) obj[4]);
			authorizationWrapper.setEntityName(obj[5].toString());
			authorizationWrapper.setEntityUrl(obj[6].toString());
			authorizationWrapper.setModuleId((Integer) obj[7]);
			authorizationWrapper.setModuleName(obj[8].toString());
			authorizationWrapper.setModuleUrl(obj[9].toString());

			authorizationWrappers.add(authorizationWrapper);
		}
		List<Link<?>> links = new ArrayList<>();
		for (AuthorizationWrapper authorizationWrapper : authorizationWrappers) {
			links.add(new Link(authorizationWrapper));
		}
		return links;
	}
	@Transactional
	public String[] nsLookup(String domainname) throws Exception {
		try {
			Hashtable<Object, Object> env = new Hashtable<>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
			env.put("java.naming.provider.url", "dns:");
			DirContext ctx = new InitialDirContext(env);
			Attributes attributes = ctx
					.getAttributes(String.format("_ldap._tcp.%s", domainname), new String[] { "srv" });
			// try thrice to get the KDC servers before throwing error
			for (int i = 0; i < 3; i++) {
				Attribute a = attributes.get("srv");
				if (a != null) {
					List<String> domainServers = new ArrayList<>();
					NamingEnumeration<?> enumeration = a.getAll();
					while (enumeration.hasMoreElements()) {
						String srvAttr = (String) enumeration.next();
						String []values = srvAttr.split(" ");
						domainServers.add(String.format("ldap://%s:%s", values[3], values[2]));
					}
					String domainServersArray[] = new String[domainServers.size()];
					domainServers.toArray(domainServersArray);
					return domainServersArray;
				}
			}
			throw new Exception("Unable to find srv attribute for the domain " + domainname);
		} catch (NamingException exp) {
			throw new Exception("Error while performing nslookup. Root Cause: " + exp.getMessage(), exp);
		}
	}
}
