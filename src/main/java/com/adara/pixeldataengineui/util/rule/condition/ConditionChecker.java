package com.adara.pixeldataengineui.util.rule.condition;


import com.adara.pixeldataengineui.util.rule.parse.ParseResult;

/**
 * This is the condition checker 
 * 
 * @author jgao
 *
 */
public interface ConditionChecker {
	/**
	 * checks the parsed data ( together with original data as well ) to see if we 
	 * want to apply any action on this piece of data
	 */
	public boolean checkCondition(String key, String value, ParseResult parsedValue);
}
