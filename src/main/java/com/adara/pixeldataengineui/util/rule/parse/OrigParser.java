package com.adara.pixeldataengineui.util.rule.parse;

import java.util.ArrayList;
import java.util.List;



/**
 * dummy parser which always return the original value
 * 
 * @author jgao
 *
 */
public class OrigParser implements Parser {

	/**
	 * constructor
	 */
	public OrigParser(String[] ruleArray) {
		// left blank
	}

	/* (non-Javadoc)
	 * @see com.opinmind.pixeldataengine.rule.parser.Parser#parse(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ParseResult parse(String key, String value) {
		List splitValue = new ArrayList();
		splitValue.add(value);
		ParseResult pr = new ParseResult(value, splitValue, 1, "orig");
		
		return pr;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrigParser []";
	}

}
