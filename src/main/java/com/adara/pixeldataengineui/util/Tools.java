package com.adara.pixeldataengineui.util;

import com.adara.pixeldataengineui.model.frontend.generalcomponents.InElementArray;
import com.adara.pixeldataengineui.model.frontend.generalcomponents.Len;
import com.adara.pixeldataengineui.model.frontend.generalcomponents.Range;
import com.adara.pixeldataengineui.model.frontend.generalcomponents.SetRuleArray;
import com.adara.pixeldataengineui.model.frontend.requestbody.RuleRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yzhao on 5/9/16.
 */
public class Tools {
    public static  String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date).toString();
    }

    public static String parseRuleBuilder(RuleRequest request) {
        String parseRuleKey = request.getParseRule();
        StringBuilder parseRuleValue = new StringBuilder();
        parseRuleValue.append(parseRuleKey);
        String split1 = null;
        String split2Level1SplitString = null;
        String split2Level2SplitString = null;
        if (parseRuleKey.equals("split1")) {
            split1 = request.getSplit1().toString();
            parseRuleValue.append("|");
            if (split1.equals("|")) {
                split1 = "\"" + split1 + "\"";
            }

            parseRuleValue.append(split1);
        } else if (parseRuleKey.equals("split2")) {
            split2Level1SplitString = request.getSplit2().getColumn1();
            split2Level2SplitString = request.getSplit2().getColumn2();
            parseRuleValue.append("|");
            if (split2Level1SplitString.equals("|")) {
                split2Level1SplitString = "\"" + split2Level1SplitString + "\"";
            }

            parseRuleValue.append(split2Level1SplitString);
            parseRuleValue.append("|");
            if (split2Level2SplitString.equals("|")) {
                split2Level2SplitString = "\"" + split2Level2SplitString + "\"";
            }

            parseRuleValue.append(split2Level2SplitString);
        }

        return parseRuleValue.toString();
    }

    public static  String conditionRuleBuilder(RuleRequest request) {
        String conditionRuleKey = request.getConditionRule();
        StringBuilder conditionRuleValue = new StringBuilder();
        conditionRuleValue.append(conditionRuleKey);
        String len = null;
        String seg = null;
        String rangeFrom = null;
        String rangeTo = null;
        List<InElementArray> inElementArrayList = null;
        if (conditionRuleKey.equals("len")) {
            Len mLen = request.getLen();
            rangeFrom = mLen.getColumn1();
            rangeTo = mLen.getColumn2();
            conditionRuleValue.append("|");
            conditionRuleValue.append(rangeFrom);
            conditionRuleValue.append("|");
            conditionRuleValue.append(rangeTo);
        } else if (conditionRuleKey.equals("range")) {
            Range mRange = request.getRange();
            rangeFrom = mRange.getColumn1();
            rangeTo = mRange.getColumn2();
            conditionRuleValue.append("|");
            conditionRuleValue.append(rangeFrom);
            conditionRuleValue.append("|");
            conditionRuleValue.append(rangeTo);
        } else if (conditionRuleKey.equals("in")) {
            inElementArrayList = request.getInElementArray();
            conditionRuleValue.append("|");
            for (InElementArray i : inElementArrayList) {
                if (i.getColumn2().length() > 0) {
                    conditionRuleValue.append(i.getColumn2());
                    conditionRuleValue.append("|");
                }
            }
            conditionRuleValue.deleteCharAt(conditionRuleValue.length() - 1);
        } else if (conditionRuleKey.equals("seg")) {
            seg = request.getSeg().toString();
            conditionRuleValue.append("|");
            conditionRuleValue.append(seg);
        }

        return conditionRuleValue.toString();
    }

    public static  String actionRuleBuilder(RuleRequest request) {
        String actionRuleKey = request.getActionRule();
        StringBuilder actionRuleValue = new StringBuilder();
        actionRuleValue.append(actionRuleKey);
        String substrDirection = null;
        String substrStartIndex = null;
        String substrLength = null;
        List<SetRuleArray> setRuleArrayDTOList = null;
        String dec;
        if (actionRuleKey.equals("substr")) {
            substrDirection = request.getSubstr().getColumn1();
            substrStartIndex = request.getSubstr().getColumn2();
            substrLength = request.getSubstr().getColumn3();
            actionRuleValue.append("|");
            actionRuleValue.append(substrDirection);
            actionRuleValue.append("|");
            actionRuleValue.append(substrStartIndex);
            actionRuleValue.append("|");
            actionRuleValue.append(substrLength);
        } else if (actionRuleKey.equals("set")) {
            setRuleArrayDTOList = request.getSetRuleArray();
            actionRuleValue.append("|");
            for (SetRuleArray s : setRuleArrayDTOList) {
                if (s.getColumn2().length() > 0) {
                    actionRuleValue.append(s.getColumn2());
                    actionRuleValue.append("|");
                }
            }
            actionRuleValue.deleteCharAt(actionRuleValue.length() - 1);
        } else if (actionRuleKey.equals("dec")) {
            dec = request.getDec().toString();
            actionRuleValue.append("|");
            actionRuleValue.append(dec);
        }
        return actionRuleValue.toString();
    }
}
