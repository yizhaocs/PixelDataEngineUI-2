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
    private String keyId;
    private String type;
    private Split1 split1;
    private Split2 split2;
    private Len len;
    private Range range;
    private Substr substr;
    private Dec dec;
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

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
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

    public List<SetRuleArray> getSetRuleArray() {
        return setRuleArray;
    }

    public void setSetRuleArray(List<SetRuleArray> setRuleArray) {
        this.setRuleArray = setRuleArray;
    }
}
