package com.adara.pixeldataengineui.util.rule.condition;

import com.adara.pixeldataengineui.util.rule.parse.ParseResult;
import org.apache.log4j.Logger;


/**
 * This class checks this case :  
 * range|lower_limit|upper_limit
 * 
 * Note, it only works on numeric values
 * 
 * @author jgao
 *
 */
public class RangeConditionChecker implements ConditionChecker {
	private static final Logger log = Logger.getLogger(RangeConditionChecker.class);
	
	private Long upper = null;
	private Long lower = null;
	
	/**
	 * constructor, only invoked once, to figure out the upper and lower boundaries
	 * @throws Exception 
	 */
	public RangeConditionChecker(String[] ruleArray) throws Exception {
		// figure out the upper and lower boundaries
		String lowerStr = ruleArray[1];
		String upperStr = ruleArray[2];
		
		if (lowerStr!=null && lowerStr.length()>0) {
			lower = Long.valueOf(ruleArray[1]);
		}
		
		if (upperStr!=null && upperStr.length()>0) {
			upper = Long.valueOf(ruleArray[2]);
		}
		
		if (lower==null && upper==null) {
			throw new Exception("condition range: both upper and lower limit are null");
		}
		
		if (lower!=null && upper!=null && upper<lower) {
			throw new Exception("condition range: upper limit(" + upper +") is greater than lower limit(" + lower + ")");
		}
		
		if (log.isDebugEnabled())
			log.debug("for rule:" + ruleArray + ", init the lower limit to be:" + lower + " and the upper limit to be:" + upper);

	}

	/* (non-Javadoc)
	 * @see com.opinmind.pixeldataengine.rule.condition.ConditionChecker#checkCondition(java.lang.String, java.lang.String, com.opinmind.pixeldataengine.rule.parser.ParseResult)
	 */
	@Override
	public boolean checkCondition(String key, String value,
			ParseResult parsedValue) {
		boolean result = true;
		
		Double doubleValue = Double.valueOf(value);
		
		if (lower != null) {
			if (doubleValue < lower) {
				result = false;
			}
		}
		
		if (upper != null) {
			if (doubleValue > upper) {
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
		return "RangeConditionChecker [upper=" + upper + ", lower=" + lower
				+ "]";
	}

}
