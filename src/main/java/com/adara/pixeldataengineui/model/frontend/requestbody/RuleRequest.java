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
    private Len len;
    private Seg seg;
    private Range range;
    private Substr substr;
    private Dec dec;
    private String testValue;
    private List<InElementArray> inElementArray;
    private List<SetRuleArray> setRuleArray;

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

    public Len getLen() {
        return len;
    }

    public void setLen(Len len) {
        this.len = len;
    }

    public Seg getSeg() {
        return seg;
    }

    public void setSeg(Seg seg) {
        this.seg = seg;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
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
}
