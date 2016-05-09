/**
 * 
 */
package com.adara.pixeldataengineui.util.rule.condition;


import com.adara.pixeldataengineui.util.rule.parse.ParseResult;

/**
 * dummy checker which always returns true
 * 
 * @author jgao
 *
 */
public class AlwaysConditionChecker implements ConditionChecker {

	/**
	 * 
	 */
	public AlwaysConditionChecker(String[] ruleArray) {
		// left blank
	}

	/* (non-Javadoc)
	 * @see com.opinmind.pixeldataengine.rule.condition.ConditionChecker#checkCondition(java.lang.String, java.lang.String, com.opinmind.pixeldataengine.rule.parser.ParseResult)
	 */
	@Override
	public boolean checkCondition(String key, String value,
			ParseResult parsedValue) {
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AlwaysConditionChecker []";
	}

}
