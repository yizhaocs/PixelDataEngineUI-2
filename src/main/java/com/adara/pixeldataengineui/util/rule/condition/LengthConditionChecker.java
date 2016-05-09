package com.adara.pixeldataengineui.util.rule.condition;

import org.apache.log4j.Logger;

import com.opinmind.pixeldataengine.rule.parser.ParseResult;

/**
 * This is the condition checker for string length
 * 
 * @author jgao
 */
public class LengthConditionChecker implements ConditionChecker {
	private static final Logger log = Logger.getLogger(LengthConditionChecker.class);
	
	private Integer len;
	
	/**
	 * @throws Exception 
	 * 
	 */
	public LengthConditionChecker(String[] ruleArray) throws Exception {
		// init the len
		len = Integer.valueOf(ruleArray[1]);
		
		if (len <= 0) {
			throw new Exception("condition length: length can't be zero or negative");
		}
		
		if (log.isDebugEnabled())
			log.debug("for rule:" + ruleArray + ", init the len limit to be:" + len);
	}

	/* (non-Javadoc)
	 * @see com.opinmind.pixeldataengine.rule.condition.ConditionChecker#checkCondition(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean checkCondition(String key, String value, ParseResult parsedValue) {
		return value.length() > len;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LengthConditionChecker [len=" + len + "]";
	}

}
