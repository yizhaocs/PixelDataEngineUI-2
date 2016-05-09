package com.adara.pixeldataengineui.util.rule.action;

import com.adara.pixeldataengineui.util.rule.parse.ParseResult;
import org.apache.commons.lang.text.StrTokenizer;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the 'set' action
 * 
 * It handles something like this :  set|10636:a|10936:0,1|10937:0,2
 * 
 * @author jgao
 *
 */
public class SetAction implements Action {
	private static final Logger log = Logger.getLogger(SetAction.class);
			
	private Map<String, String[]> ruleMap = new HashMap<String, String[]>(); 
	
	/**
	 * parse the rule and populate the ruleMap
	 * 
	 * note, this is done only once
	 * 
	 * @param rule
	 */
	public SetAction(String[] ruleArray) {
		StrTokenizer st2 = StrTokenizer.getCSVInstance();
		st2.setDelimiterChar(':');
		
		StrTokenizer st3 = StrTokenizer.getCSVInstance();
		st3.setDelimiterChar(',');
		
		for (int i=1; i<ruleArray.length; i++) {
			String s = ruleArray[i]; // get something like 10936:0,1
			
			String[] keyRuleArray = st2.reset(s).getTokenArray();
			
			String key = keyRuleArray[0]; // get 10936
			String indices = keyRuleArray[1];  // get 0,1
			
			String[] indexArray = st3.reset(indices).getTokenArray(); 
			ruleMap.put(key, indexArray);
			
			if (log.isDebugEnabled())
				log.debug("for key:" + key + " put indices in: " + indexArray);
		}
	}

	/**
	 * apply the rule 
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, String> execute(String key, String value,
			ParseResult parsedValue) {
		Map<String, String> retVal = new HashMap<String, String>();
		
		for (String ruleKey : ruleMap.keySet()) {
			String[] ruleIndexArray = ruleMap.get(ruleKey);
			String index1Str = ruleIndexArray[0];
			if ("a".equals(index1Str)) {
				// put the original value 
				retVal.put(ruleKey, value);
			}
			else {
				Integer index1 = Integer.valueOf(index1Str);
				if (parsedValue.getListDepth() == 1) {
					// single split
					String ruleValue = (String)parsedValue.getParsedValue().get(index1);
					retVal.put(ruleKey, ruleValue);
				}
				else if (parsedValue.getListDepth() == 2) {
					// double split
					String index2Str = ruleIndexArray[1];
					Integer index2 = Integer.valueOf(index2Str);
					
					List list2 = (List)parsedValue.getParsedValue().get(index1);
					String ruleValue = (String)list2.get(index2);
					
					retVal.put(ruleKey, ruleValue);
				}
			}
		}
		
		return retVal;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SetAction [ruleMap=" + ruleMap + "]";
	}

}
