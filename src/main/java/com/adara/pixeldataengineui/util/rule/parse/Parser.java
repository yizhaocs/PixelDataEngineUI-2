package com.adara.pixeldataengineui.util.rule.parse;

/**
 * This is the parser interface
 * 
 * @author jgao
 *
 */
public interface Parser {
	/**
	 * parse the original key/value pair and re-arrange the data in the way that we want 
	 *  
	 * @param key
	 * @param value
	 * @return any object 
	 */
	public ParseResult parse(String key, String value);
}
