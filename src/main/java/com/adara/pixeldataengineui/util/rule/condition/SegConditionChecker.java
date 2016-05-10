package com.adara.pixeldataengineui.util.rule.condition;

import com.adara.pixeldataengineui.util.rule.parse.ParseResult;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;



/**
 * This class checks this case :  
 * seg|number_of_segments
 * 
 * Note, it only works with split parsers - checks if the number of segments is equal the number_of_segments
 * 
 * @author jgao
 *
 */
public class SegConditionChecker implements ConditionChecker {
	private static final Logger log = Logger.getLogger(SegConditionChecker.class);
	
	private Integer numberSeg = null;
	
	/**
	 *  constructor, only invoked once, to figure out the number of segments
	 * @throws Exception 
	 */
	public SegConditionChecker(String[] ruleArray) throws Exception {
		String numberSegStr = ruleArray[1];
		
		if (numberSegStr!=null && numberSegStr.length()>0) {
			numberSeg = Integer.valueOf(numberSegStr);
		}
		
		if (numberSeg<=0) {
			throw new Exception("condition seg: number of segments can't be 0 or negative");
		}
		
		if (log.isDebugEnabled())
			log.debug("for rule:" + ruleArray + ", init the number of segment to be:" + numberSeg);

	}

	/* (non-Javadoc)
	 * @see com.opinmind.pixeldataengine.rule.condition.ConditionChecker#checkCondition(java.lang.String, java.lang.String, com.opinmind.pixeldataengine.rule.parser.ParseResult)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean checkCondition(String key, String value,
			ParseResult parsedValue) {
		boolean result = false;
		
		if (parsedValue != null) {
			List list = parsedValue.getParsedValue();
			
			if (list != null) {
				result = (list.size() == numberSeg);
			}
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SegConditionChecker [numberSeg=" + numberSeg + "]";
	}


	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		SegConditionChecker checker = new SegConditionChecker(new String[]{"seg", "3"});
		
		String[] list = new String[]{"NYC", "NY", "US"};
		ParseResult p = new ParseResult("NYC,NY,US", Arrays.asList(list), 1, "slit1");
		
		boolean result = checker.checkCondition("100", "NYC,NY,USA", p);
		
		System.out.println("result: " + result);
	}

}
