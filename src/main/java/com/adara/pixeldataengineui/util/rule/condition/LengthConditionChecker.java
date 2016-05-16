package com.adara.pixeldataengineui.util.rule.condition;

import com.adara.pixeldataengineui.util.rule.parse.ParseResult;
import org.apache.log4j.Logger;


/**
 * This is the condition checker for string length
 *
 * @author jgao
 */
public class LengthConditionChecker implements ConditionChecker {
	private static final Logger log = Logger.getLogger(LengthConditionChecker.class);

	private Integer lower;
	private Integer upper;

	/**
	 * @throws Exception
	 *
	 */
	public LengthConditionChecker(String[] ruleArray) throws Exception {
		// figure out the upper and lower boundaries
		String lowerStr = null;
		String upperStr = null;

		lowerStr = ruleArray[1];
		if (ruleArray.length > 1)
			upperStr = ruleArray[2];

		if (lowerStr!=null && lowerStr.length()>0) {
			lower = Integer.valueOf(lowerStr);
		}

		if (upperStr!=null && upperStr.length()>0) {
			upper = Integer.valueOf(upperStr);
		}

		if (lower==null && upper==null) {
			throw new Exception("condition len: both upper and lower limit are null");
		}

		if (lower!=null && upper!=null && upper<lower) {
			throw new Exception("condition len: upper limit(" + upper +") is greater than lower limit(" + lower + ")");
		}

		if (lower!=null && lower<0) {
			throw new Exception("condition len: lower limit has to be >= 0");
		}

		if (upper!=null && upper<0) {
			throw new Exception("condition len: upper limit has to be >= 0");
		}

		if (log.isDebugEnabled())
			log.debug("for rule:" + ruleArray + ", init the lower limit to be:" + lower + " and the upper limit to be:" + upper);

	}

	/* (non-Javadoc)
	 * @see com.opinmind.pixeldataengine.rule.condition.ConditionChecker#checkCondition(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean checkCondition(String key, String value, ParseResult parsedValue) {
		boolean result = true;

		Integer intValue = Integer.valueOf(value.length());

		if (lower != null) {
			if (intValue < lower) {
				result = false;
			}
		}

		if (upper != null) {
			if (intValue > upper) {
				result = false;
			}
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LengthConditionChecker [lower=" + lower + " upper=" + upper + "]";
	}

}

