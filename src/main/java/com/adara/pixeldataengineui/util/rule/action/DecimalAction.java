/**
 * 
 */
package com.adara.pixeldataengineui.util.rule.action;

import com.adara.pixeldataengineui.util.rule.RuleFactory;
import com.adara.pixeldataengineui.util.rule.parse.ParseResult;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


/**
 * This class handles this use case: 
 * dec|2
 * 
 * @author jgao
 *
 */
public class DecimalAction implements Action {
	private static final Logger log = Logger.getLogger(DecimalAction.class);
	
	// number of decimals we need 
	private int decimal;

	/**
	 * constructor, invoked only once, to init the decimal
	 */
	public DecimalAction(String[] ruleArray) {
		// init the decimal
		decimal = Integer.valueOf(ruleArray[1]);
		
		if (log.isDebugEnabled())
			log.debug("for rule:" + ruleArray + ", init the decimal to be:" + decimal);

	}

	/* (non-Javadoc)
	 * @see com.opinmind.pixeldataengine.rule.action.Action#execute(java.lang.String, java.lang.String, com.opinmind.pixeldataengine.rule.parser.ParseResult)
	 */
	@Override
	public Map<String, String> execute(String key, String value,
			ParseResult parsedValue) {
		Map<String, String> resultMap = new HashMap<String, String>();
		
		// use String.format to format the value
		String formatString = "%." + decimal + "f";
		
		Double doubleValue = Double.valueOf(value);
		String newValue = String.format(formatString, doubleValue);
		
		resultMap.put(key, newValue);
				
		return resultMap;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DecimalAction [decimal=" + decimal + "]";
	}

	public static void main(String[] argv) {
		System.out.println("start");
		
		try {
			Action action = RuleFactory.createAction("dec|2");
			
			Map<String, String> result1 = action.execute("123", "123.456", null);

			System.out.println(result1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("end");

	}
}
