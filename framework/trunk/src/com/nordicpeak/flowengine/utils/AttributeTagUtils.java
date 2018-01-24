package com.nordicpeak.flowengine.utils;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import se.unlogic.hierarchy.core.interfaces.attributes.AttributeHandler;
import se.unlogic.standardutils.collections.CollectionUtils;
import se.unlogic.standardutils.html.HTMLUtils;


public class AttributeTagUtils {

	private static final Pattern ATTRIBUTE_TAG_PATTERN = Pattern.compile("(\\$attribute)[{](.*?)}");
	
	public static Set<String> getAttributeTags(String text) {

		Matcher matcher = ATTRIBUTE_TAG_PATTERN.matcher(text);
	    
		Set<String> attributeTags = null;
		
		while (matcher.find()) {
	        
			String tag = matcher.group(2);
			
			attributeTags = CollectionUtils.addAndInstantiateIfNeeded(attributeTags, tag);
		}
		
		return attributeTags;
	}
	
	public static String replaceTags(String text, AttributeHandler attributeHandler){
		
		return replaceTags(text, attributeHandler, false);
	}	
	
	public static String replaceTags(String text, AttributeHandler attributeHandler, boolean escapeHTML){
		
		Set<String> tags = getAttributeTags(text);
		
		if(tags == null){
			
			return text;
		}
		
		return replaceTags(text, attributeHandler, tags, escapeHTML);
	}
	
	public static String replaceTags(String text, AttributeHandler attributeHandler, Set<String> tags, boolean escapeHTML){
		
		for(String tag : tags){
			
			String value = attributeHandler.getString(tag);
			
			if(value == null){
				
				value = "";
				
			}else if(escapeHTML){
				
				value = HTMLUtils.escapeHTML(value);
			}
			
			text = text.replace("$attribute{" + tag + "}", value);
		}
		
		return text;
	}
}
