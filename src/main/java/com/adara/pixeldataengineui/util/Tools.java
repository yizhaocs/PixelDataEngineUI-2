package com.adara.pixeldataengineui.util;

import com.adara.pixeldataengineui.model.frontend.generalcomponents.*;
import com.adara.pixeldataengineui.model.frontend.requestbody.RuleRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yzhao on 5/9/16.
 */
public class Tools {
    public static String getCurrentDateTime() {
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
            if (split1 != null && split1.equals("") == false) {
                parseRuleValue.append("|");
                if (split1.equals("|")) {
                    split1 = "\"" + split1 + "\"";
                }
                parseRuleValue.append(split1);
            }

        } else if (parseRuleKey.equals("split2")) {
            split2Level1SplitString = request.getSplit2().getColumn1();
            split2Level2SplitString = request.getSplit2().getColumn2();

            if (split2Level1SplitString != null && split2Level1SplitString.equals("") == false) {
                parseRuleValue.append("|");
                if (split2Level1SplitString.equals("|")) {
                    split2Level1SplitString = "\"" + split2Level1SplitString + "\"";
                }
                parseRuleValue.append(split2Level1SplitString);
            }

            if (split2Level2SplitString != null && split2Level2SplitString.equals("") == false) {
                parseRuleValue.append("|");
                if (split2Level2SplitString.equals("|")) {
                    split2Level2SplitString = "\"" + split2Level2SplitString + "\"";
                }
                parseRuleValue.append(split2Level2SplitString);
            }

        }

        return parseRuleValue.toString();
    }

    public static String conditionRuleBuilder(RuleRequest request) {
        String conditionRuleKey = request.getConditionRule();
        StringBuilder conditionRuleValue = new StringBuilder();
        conditionRuleValue.append(conditionRuleKey);
        String seg = null;
        List<InElementArray> inElementArrayList = null;
        List<LenArray> lenArrayList = null;
        List<RangeArray> rangeArrayList = null;
        List<ContainsArray> containsArrayList = null;
        if (conditionRuleKey.equals("len")) {
            lenArrayList = request.getLenArray();
            conditionRuleValue.append("|");
            for (LenArray i : lenArrayList) {
                if (i.getColumn2() != null && i.getColumn2().equals("") == false) {
                    conditionRuleValue.append(i.getColumn2());
                    conditionRuleValue.append("|");
                }
            }
            conditionRuleValue.deleteCharAt(conditionRuleValue.length() - 1);
        } else if (conditionRuleKey.equals("range")) {
            rangeArrayList = request.getRangeArray();
            conditionRuleValue.append("|");
            for (RangeArray i : rangeArrayList) {
                if (i.getColumn2() != null && i.getColumn2().equals("") == false) {
                    conditionRuleValue.append(i.getColumn2());
                    conditionRuleValue.append("|");
                }
            }
            conditionRuleValue.deleteCharAt(conditionRuleValue.length() - 1);

        } else if (conditionRuleKey.equals("in")) {
            inElementArrayList = request.getInElementArray();
            conditionRuleValue.append("|");
            for (InElementArray i : inElementArrayList) {
                if (i.getColumn2() != null && i.getColumn2().equals("") == false) {
                    conditionRuleValue.append(i.getColumn2());
                    conditionRuleValue.append("|");
                }
            }
            conditionRuleValue.deleteCharAt(conditionRuleValue.length() - 1);
        } else if (conditionRuleKey.equals("seg")) {
            seg = request.getSeg().toString();
            if (seg != null && seg.equals("") == false) {
                conditionRuleValue.append("|");
                conditionRuleValue.append(seg);
            }
        } else if (conditionRuleKey.equals("contains")) {
            containsArrayList = request.getContainsArray();
            conditionRuleValue.append("|");
            for (ContainsArray i : containsArrayList) {
                if (i.getColumn2() != null && i.getColumn2().equals("") == false) {
                    conditionRuleValue.append(i.getColumn2());
                    conditionRuleValue.append("|");
                }
            }
            conditionRuleValue.deleteCharAt(conditionRuleValue.length() - 1);
        }

        return conditionRuleValue.toString();
    }

    public static String actionRuleBuilder(RuleRequest request) {
        String actionRuleKey = request.getActionRule();
        StringBuilder actionRuleValue = new StringBuilder();
        actionRuleValue.append(actionRuleKey);

        List<SetRuleArray> setRuleArrayDTOList = null;
        if (actionRuleKey.equals("substr")) {
            String position = request.getSubstr().getColumn1();
            String substrDirection = request.getSubstr().getColumn2();
            String substrStartIndex = request.getSubstr().getColumn3();
            String substrLength = request.getSubstr().getColumn4();

            if (position != null && position.equals("") == false) {
                actionRuleValue.append("|");
                actionRuleValue.append(position);
            }

            if (substrDirection != null && substrDirection.equals("") == false) {
                actionRuleValue.append(":");
                actionRuleValue.append(substrDirection);
            }

            if (substrStartIndex != null && substrStartIndex.equals("") == false) {
                actionRuleValue.append(",");
                actionRuleValue.append(substrStartIndex);
            }

            if (substrLength != null && substrLength.equals("") == false) {
                actionRuleValue.append(",");
                actionRuleValue.append(substrLength);
            }
        } else if (actionRuleKey.equals("set")) {
            setRuleArrayDTOList = request.getSetRuleArray();
            actionRuleValue.append("|");
            for (SetRuleArray s : setRuleArrayDTOList) {
                if (s.getColumn2() != null && s.getColumn2().equals("") == false) {
                    actionRuleValue.append(s.getColumn2());
                    actionRuleValue.append("|");
                }
            }
            actionRuleValue.deleteCharAt(actionRuleValue.length() - 1);
        } else if (actionRuleKey.equals("dec")) {
            String position = request.getDec().getColumn1();
            String dec = request.getDec().getColumn2();

            if (position != null && position.equals("") == false) {
                actionRuleValue.append("|");
                actionRuleValue.append(position);
            }

            if (dec != null && dec.equals("") == false) {
                actionRuleValue.append(":");
                actionRuleValue.append(dec);
            }
        } else if (actionRuleKey.equals("map")) {
            String table = request.getMap().getColumn1();
            String key = request.getMap().getColumn2();
            String position = request.getMap().getColumn3();

            if (table != null && table.equals("") == false) {
                actionRuleValue.append("|");
                actionRuleValue.append(table);
            }

            if (key != null && key.equals("") == false) {
                actionRuleValue.append("|");
                actionRuleValue.append(key);
            }

            if (position != null && position.equals("") == false) {
                actionRuleValue.append(":");
                actionRuleValue.append(position);
            }
        }
        return actionRuleValue.toString();
    }

    public static StringBuilder resultMaker(String result) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"success\":true,\"body\":");
        sb.append(result);
        sb.append("}");
        return sb;
    }
}