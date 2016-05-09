/**
 * 
 */
package com.adara.pixeldataengineui.util.rule.action;

import com.adara.pixeldataengineui.util.rule.parse.ParseResult;

import java.util.HashMap;
import java.util.Map;


/**
 * @author jgao
 *
 */
public class IgnoreAction implements Action {

	/**
	 * constructor
	 */
	public IgnoreAction(String[] ruleArray) {
		// left blank
	}

	/* (non-Javadoc)
	 * @see com.opinmind.pixeldataengine.rule.action.Action#execute(java.lang.String, java.lang.String, com.opinmind.pixeldataengine.rule.parser.ParseResult)
	 */
	@Override
	public Map<String, String> execute(String key, String value,
			ParseResult parsedValue) {
		// return an empty map
		return new HashMap<String, String>();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IgnoreAction []";
	}

}
