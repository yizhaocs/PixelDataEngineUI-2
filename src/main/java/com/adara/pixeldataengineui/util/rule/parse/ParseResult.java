package com.adara.pixeldataengineui.util.rule.parse;

import java.util.List;

/**
 * @author jgao
 *
 */
@SuppressWarnings("rawtypes")
public class ParseResult {
	private String origValue;
	private List parsedValue;
	private Integer listDepth;
	private String type;
	
	public ParseResult(String origValue, List parsedValue, Integer listDepth,
			String type) {
		this.origValue = origValue;
		this.parsedValue = parsedValue;
		this.listDepth = listDepth;
		this.type = type;
	}
	
	public String getOrigValue() {
		return origValue;
	}
	public void setOrigValue(String origValue) {
		this.origValue = origValue;
	}	
	public List getParsedValue() {
		return parsedValue;
	}
	public void setParsedValue(List parsedValue) {
		this.parsedValue = parsedValue;
	}
	public Integer getListDepth() {
		return listDepth;
	}
	public void setListDepth(Integer listDepth) {
		this.listDepth = listDepth;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((listDepth == null) ? 0 : listDepth.hashCode());
		result = prime * result
				+ ((origValue == null) ? 0 : origValue.hashCode());
		result = prime * result
				+ ((parsedValue == null) ? 0 : parsedValue.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParseResult other = (ParseResult) obj;
		if (listDepth == null) {
			if (other.listDepth != null)
				return false;
		} else if (!listDepth.equals(other.listDepth))
			return false;
		if (origValue == null) {
			if (other.origValue != null)
				return false;
		} else if (!origValue.equals(other.origValue))
			return false;
		if (parsedValue == null) {
			if (other.parsedValue != null)
				return false;
		} else if (!parsedValue.equals(other.parsedValue))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ParseResult [origValue=" + origValue + ", parsedValue="
				+ parsedValue + ", listDepth=" + listDepth + ", type=" + type
				+ "]";
	}
	

}
