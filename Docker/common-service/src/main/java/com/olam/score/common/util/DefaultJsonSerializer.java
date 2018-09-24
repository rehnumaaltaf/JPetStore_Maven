package com.olam.score.common.util;

import static org.jsoup.parser.Parser.unescapeEntities;

import java.io.IOException;

import org.owasp.esapi.ESAPI;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

@JsonComponent
public class DefaultJsonSerializer extends JsonSerializer<String> implements ContextualSerializer {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	 public void serialize(
             final String value, final JsonGenerator jgen, final SerializerProvider provider)
             throws IOException {
		 if (value != null) {
			 String encodedValue = XSSUtils.sanitizeHTML(value);
			 encodedValue = XSSUtils.unescapeUntilNoHtmlEntityFound(encodedValue);
			 log.debug("Using custom serializer: DefaultJsonDeserializer for XSS");
			 jgen.writeString(encodedValue);
		 }
     }
	
	@Override
	public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty beanProperty) throws JsonMappingException {
		return this;
	}

}
