package com.olam.score.common.util;

import static org.jsoup.parser.Parser.unescapeEntities;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.reference.DefaultEncoder;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class XSSUtils {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public static String sanitizeHTML(String value){
		value = ESAPI.encoder().canonicalize(value);
		value = ESAPI.encoder().encodeForHTML(value);
				
		PolicyFactory policy = new HtmlPolicyBuilder()
            .allowAttributes("src").onElements("img")
            .allowAttributes("href").onElements("a")
            .allowStandardUrlProtocols()
            .allowElements(
            "a", "img"
            ).toFactory();

        return policy.sanitize(value); 
    }
	
	public static String unescapeUntilNoHtmlEntityFound(String value) {
		String unescaped = unescapeEntities(value, true);
		if (!unescaped.equals(value))
			return unescapeUntilNoHtmlEntityFound(unescaped);
		else
			return unescaped;
	}
}
