package com.adara.pixeldataengineui.util.rule.condition;

import com.adara.pixeldataengineui.util.rule.parse.ParseResult;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * This class checks this case : 
 * in|string1|string2,...
 * 
 * @author jgao
 *
 */
public class InConditionChecker implements ConditionChecker {
	private static final Logger log = Logger.getLogger(InConditionChecker.class);
	
	private Set<String> checkSet = new HashSet<String>();
	
	/**
	 * constructor, only invoked once, to populate the check set
	 * @throws Exception 
	 */
	public InConditionChecker(String[] ruleArray) throws Exception {
		// populate the check set
		for (int i=1; i<ruleArray.length; i++) {
			String item = ruleArray[i];
			if (item!=null && item.length()>0) {
				checkSet.add(item);
			}
		}
		
		if (checkSet.isEmpty()) {
			throw new Exception("condition in: set can't be empty");
		}
		
		if (log.isDebugEnabled())
			log.debug("for rule:" + ruleArray + ", init the check set to be:" + checkSet);

	}

	/* (non-Javadoc)
	 * @see com.opinmind.pixeldataengine.rule.condition.ConditionChecker#checkCondition(java.lang.String, java.lang.String, com.opinmind.pixeldataengine.rule.parser.ParseResult)
	 */
	@Override
	public boolean checkCondition(String key, String value,
			ParseResult parsedValue) {
		return checkSet.contains(value);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InConditionChecker [checkSet=" + checkSet + "]";
	}

}
