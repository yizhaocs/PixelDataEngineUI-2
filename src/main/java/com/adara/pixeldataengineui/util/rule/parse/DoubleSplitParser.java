/**
 * 
 */
package com.adara.pixeldataengineui.util.rule.parse;

import org.apache.commons.lang.text.StrTokenizer;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jgao
 *
 */
public class DoubleSplitParser implements com.adara.pixeldataengineui.util.rule.parse.Parser {
	private static final Logger log = Logger.getLogger(DoubleSplitParser.class);
	
	private StrTokenizer level1Tokenizer = StrTokenizer.getCSVInstance();
	private StrTokenizer level2Tokenizer = StrTokenizer.getCSVInstance();
	
	/**
	 * note, this is called only once 
	 * @throws Exception 
	 */
	public DoubleSplitParser(String[] ruleArray) throws Exception {
		// we will initialize the rule tokenizer here
		String level1Delimiter = ruleArray[1];
		String level2Delimiter = ruleArray[2];
		
		if (level1Delimiter.length()==0 || level2Delimiter.length()==0) {
			throw new Exception("double split parser: delimiters can't be null or empty string");
		}
		
		level1Tokenizer.setDelimiterString(level1Delimiter);
		level2Tokenizer.setDelimiterString(level2Delimiter);
		
		if (log.isDebugEnabled())
			log.debug("for rule:" + ruleArray + ", init the tokenizer with the 1st level delimiter:" + level1Delimiter 
				+ " and level 2 delimiter: " + level2Delimiter);

	}

	/* (non-Javadoc)
	 * @see com.opinmind.pixeldataengine.rule.parser.Parser#parse(java.lang.String, java.lang.String)
	 */
	@Override
	public com.adara.pixeldataengineui.util.rule.parse.ParseResult parse(String key, String value) {
		// do 2 levels of parsing here
		String[] level1Array = level1Tokenizer.reset(value).getTokenArray();
		
		List resultList = new ArrayList();
		
		for (String section : level1Array) {
			String[] level2Array = level2Tokenizer.reset(section).getTokenArray();
			
			List level2List = Arrays.asList(level2Array);
			
			resultList.add(level2List);
		}
		
		com.adara.pixeldataengineui.util.rule.parse.ParseResult parseResult = new com.adara.pixeldataengineui.util.rule.parse.ParseResult(value, resultList, 2, "split2");
		
		return parseResult;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DoubleSplitParser [level1Tokenizer=" + level1Tokenizer
				+ ", level2Tokenizer=" + level2Tokenizer + "]";
	}

}
