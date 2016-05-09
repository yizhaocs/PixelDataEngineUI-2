package com.adara.pixeldataengineui.util.rule.action;

import com.adara.pixeldataengineui.util.rule.parse.ParseResult;

import java.util.Map;

public interface Action {
	public Map<String, String> execute(String key, String value, ParseResult parsedValue);
}
