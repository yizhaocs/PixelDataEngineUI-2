package com.adara.pixeldataengineui.util.rule.action;

import com.adara.pixeldataengineui.util.rule.RuleFactory;
import com.adara.pixeldataengineui.util.rule.parse.ParseResult;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * This class takes care of the follow use case : 
 * substr|L/R|start_index|length
 * 
 * Here L or R is the direction
 * 
 * @author jgao
 *
 */
public class SubStringAction implements Action {
	private static final Logger log = Logger.getLogger(SubStringAction.class);
	
	private Boolean leftDirection = Boolean.TRUE; // Left
	private Integer startIndex = 0; 
	private Integer subStringLength = 0;
	
	private static final String LEFT = "L";
	private static final String RIGHT = "R";
	
	/**
	 * constructor, only invoked once, to get the direction, index and length
	 * @throws Exception 
	 */
	public SubStringAction(String[] ruleArray) throws Exception {
		// get the direction, index and length
		if (LEFT.equals(ruleArray[1])) {
			leftDirection = Boolean.TRUE;
		}
		else if (RIGHT.equals(ruleArray[1])) {
			leftDirection = Boolean.FALSE;
		}
		else {
			throw new Exception("in substring action, direction is neither L nor R");
		}

		startIndex = Integer.valueOf(ruleArray[2]);
		subStringLength = Integer.valueOf(ruleArray[3]);
		
		if (log.isDebugEnabled())
			log.debug("for rule:" + ruleArray + ", init the dir to be:" + leftDirection + " start index: " + startIndex + " length: " + subStringLength);

	}

	/* (non-Javadoc)
	 * @see com.opinmind.pixeldataengine.rule.action.Action#execute(java.lang.String, java.lang.String, com.opinmind.pixeldataengine.rule.parser.ParseResult)
	 */
	@Override
	public Map<String, String> execute(String key, String value,
			ParseResult parsedValue) {
		Map<String, String> resultMap = new HashMap<String, String>();
		
		int firstIndex, lastIndex;
		int valueLength = value.length();
				
		if (leftDirection) {
			// start from left
			firstIndex = startIndex;
			lastIndex = startIndex + subStringLength;
			if (lastIndex > valueLength - 1) {
				lastIndex = valueLength - 1;
			}
		}
		else {
			// start from right 
			lastIndex = valueLength - startIndex;
			firstIndex = lastIndex - subStringLength;
			
			if (firstIndex < 0) {
				firstIndex = 0;
			}
		}
		
		resultMap.put(key, value.substring(firstIndex, lastIndex));
		
		return resultMap;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubStringAction [leftDirection=" + leftDirection
				+ ", startIndex=" + startIndex + ", subStringLength="
				+ subStringLength + "]";
	}

	public static void main(String[] argv) {
		System.out.println("start");
		
		try {
			Action action = RuleFactory.createAction("substr|L|0|1");
			Map<String, String> result = action.execute("123", "Gold", null);
			for (String key : result.keySet()) {
				System.out.println("key:"+key+" value:"+result.get(key));
			}
			
			Action action2 = RuleFactory.createAction("substr|R|0|3");
			result = action2.execute("123", "CA-SFO", null);
			for (String key : result.keySet()) {
				System.out.println("key:"+key+" value:"+result.get(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("end");
	}
}
