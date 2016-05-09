package com.adara.pixeldataengineui.util.rule.parse;

import org.apache.commons.lang.text.StrTokenizer;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

import static com.adara.pixeldataengineui.util.rule.RuleFactory.createParser;


/**
 * This is the singleSplitParser
 * 
 * At run time, we will create one parser instance per rule 
 * 
 * @author jgao
 */
public class SingleSplitParser implements Parser {
	private static final Logger log = Logger.getLogger(SingleSplitParser.class);
	
	private StrTokenizer ruleTokenizer = StrTokenizer.getCSVInstance();	
	
	private String delimiter; 
	
	public SingleSplitParser(String[] ruleArray) throws Exception {
		// we will initialize the rule tokenizer here		
		delimiter = ruleArray[1];		
		
		if (delimiter==null || delimiter.length()==0) {
			throw new Exception("single split parser: delimiter can't be null or empty string");
		}
		
		ruleTokenizer.setDelimiterString(delimiter);
		
		if (log.isDebugEnabled())
			log.debug("for rule:" + ruleArray + ", init the tokenizer with the delimitor:" + delimiter);
	}
	
	/* (non-Javadoc)
	 * @see com.opinmind.pixeldataengine.rule.parser.Parser#parse(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ParseResult parse(String key, String value) { 
		String[] splitArray = ruleTokenizer.reset(value).getTokenArray();

		List splitList = Arrays.asList(splitArray);
		
		ParseResult parseResult = new ParseResult(value, splitList, 1, "split1");

		return parseResult;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SingleSplitParser [ruleTokenizer=" + ruleTokenizer
				+ ", delimiter=" + delimiter + "]";
	}

	public static void main(String[] argv) {
		System.out.println("start");
		
		try {
			Parser ssp = createParser(" split1 | \"|\"");

			ParseResult pr = ssp.parse("123", "abc|foo");
			pr = ssp.parse("123", " abc | foo ");
			
			Parser ssp1 = createParser(" split1 | \" \"");
			
			pr = ssp1.parse("123", "abc foo");
			pr = ssp1.parse("123", " abc  foo ");
			
			Parser ssp2 = createParser(" split1 | ");
			pr = ssp2.parse("123", "abc,foo");
			
			Parser ssp3 = createParser(" split1 | , ");
			pr = ssp3.parse("123", "abc");
			
			System.out.println(pr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("end");
	}

}
