package com.adara.pixeldataengineui.model.frontend.requestbody;

import com.adara.pixeldataengineui.model.frontend.generalcomponents.*;

import java.util.List;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class RuleRequest {
    private String parseRule;
    private String conditionRule;
    private String actionRule;
    private String gid;
    private String keyId;
    private String priority;
    private String newPriority; // for update rule only
    private String type;
    private Split1 split1;
    private Split2 split2;
    private List<LenArray> lenArray;
    private Seg seg;
    private List<ContainsArray> containsArray;
    private List<RangeArray> rangeArray;
    private Substr substr;
    private Dec dec;
    private String testValue;
    private String testOption;
    private List<InElementArray> inElementArray;
    private List<SetRuleArray> setRuleArray;

    public RuleRequest() {

    }

    public RuleRequest(String parseRule, String conditionRule, String actionRule, String gid, String keyId, String priority, String newPriority, String type, Split1 split1, Split2 split2, List<LenArray> lenArray,Seg seg, List<ContainsArray> containsArray, List<RangeArray> rangeArray, Substr substr, Dec dec, String testValue, String testOption, List<InElementArray> inElementArray, List<SetRuleArray> setRuleArray) {
        this.parseRule = parseRule;
        this.conditionRule = conditionRule;
        this.actionRule = actionRule;
        this.gid = gid;
        this.keyId = keyId;
        this.priority = priority;
        this.newPriority = newPriority;
        this.type = type;
        this.split1 = split1;
        this.split2 = split2;
        this.lenArray = lenArray;
        this.seg = seg;
        this.containsArray = containsArray;
        this.rangeArray = rangeArray;
        this.substr = substr;
        this.dec = dec;
        this.testValue = testValue;
        this.testOption = testOption;
        this.inElementArray = inElementArray;
        this.setRuleArray = setRuleArray;
    }

    public String getParseRule() {
        return parseRule;
    }

    public void setParseRule(String parseRule) {
        this.parseRule = parseRule;
    }

    public String getActionRule() {
        return actionRule;
    }

    public void setActionRule(String actionRule) {
        this.actionRule = actionRule;
    }

    public String getConditionRule() {
        return conditionRule;
    }

    public void setConditionRule(String conditionRule) {
        this.conditionRule = conditionRule;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getNewPriority() {
        return newPriority;
    }

    public void setNewPriority(String newPriority) {
        this.newPriority = newPriority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<InElementArray> getInElementArray() {
        return inElementArray;
    }

    public void setInElementArray(List<InElementArray> inElementArray) {
        this.inElementArray = inElementArray;
    }

    public Split1 getSplit1() {
        return split1;
    }

    public void setSplit1(Split1 split1) {
        this.split1 = split1;
    }

    public Split2 getSplit2() {
        return split2;
    }

    public void setSplit2(Split2 split2) {
        this.split2 = split2;
    }

    public List<LenArray> getLenArray() {
        return lenArray;
    }

    public void setLenArray(List<LenArray> lenArray) {
        this.lenArray = lenArray;
    }

    public Seg getSeg() {
        return seg;
    }

    public void setSeg(Seg seg) {
        this.seg = seg;
    }

    public List<ContainsArray> getContainsArray() {
        return containsArray;
    }

    public void setContainsArray(List<ContainsArray> containsArray) {
        this.containsArray = containsArray;
    }

    public List<RangeArray> getRangeArray() {
        return rangeArray;
    }

    public void setRangeArray(List<RangeArray> rangeArray) {
        this.rangeArray = rangeArray;
    }

    public Substr getSubstr() {
        return substr;
    }

    public void setSubstr(Substr substr) {
        this.substr = substr;
    }

    public Dec getDec() {
        return dec;
    }

    public void setDec(Dec dec) {
        this.dec = dec;
    }

    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }

    public List<SetRuleArray> getSetRuleArray() {
        return setRuleArray;
    }

    public void setSetRuleArray(List<SetRuleArray> setRuleArray) {
        this.setRuleArray = setRuleArray;
    }

    public String getTestOption() {
        return testOption;
    }

    public void setTestOption(String testOption) {
        this.testOption = testOption;
    }
}
