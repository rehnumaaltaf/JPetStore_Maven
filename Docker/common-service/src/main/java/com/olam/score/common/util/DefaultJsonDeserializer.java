package com.olam.score.common.util;

import static org.jsoup.parser.Parser.unescapeEntities;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

@JsonComponent
public class DefaultJsonDeserializer extends JsonDeserializer<String> implements ContextualDeserializer {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property)
			throws JsonMappingException {
		return this;
	}

	@Override
	public String deserialize(JsonParser parser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		log.debug("Using custom deserializer: DefaultJsonDeserializer");
		String value = parser.getValueAsString();
		if (StringUtils.isEmpty(value))
			return value;
		else {
			String originalWithUnescaped = XSSUtils.unescapeUntilNoHtmlEntityFound(value);
			return unescapeEntities(XSSUtils.sanitizeHTML(originalWithUnescaped), true);
		}
	}
}
