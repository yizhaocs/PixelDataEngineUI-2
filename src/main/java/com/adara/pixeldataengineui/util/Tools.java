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
        String conditionSubselect = request.getConditionSubselect();
        StringBuilder conditionRuleValue = new StringBuilder();
        conditionRuleValue.append(conditionRuleKey);


        if (conditionRuleKey.equals("len")) {
            Len len = request.getLen();

            String conditionSubselectSeg = len.getSeg();
            String conditionSubselectRow = len.getRow();
            String conditionSubselectColumn = len.getColumn();
            String rangeFrom = len.getRangeFrom();
            String rangeTo = len.getRangeTo();

            if (len != null && len.equals("") == false) {
                if (conditionSubselect.equals("orig")) {

                    if (rangeFrom != null && rangeFrom.equals("") == false) {
                        conditionRuleValue.append("|");
                        conditionRuleValue.append(rangeFrom);
                    }

                    if (rangeTo != null && rangeTo.equals("") == false) {
                        conditionRuleValue.append("|");
                        conditionRuleValue.append(rangeTo);
                    }
                } else if (conditionSubselect.equals("split1")) {
                    conditionRuleValue.append("|");
                    conditionRuleValue.append(conditionSubselect);
                    conditionRuleValue.append("|");
                    conditionRuleValue.append(conditionSubselectSeg);

                    if (rangeFrom != null && rangeFrom.equals("") == false) {
                        conditionRuleValue.append("|");
                        conditionRuleValue.append(rangeFrom);
                    }

                    if (rangeTo != null && rangeTo.equals("") == false) {
                        conditionRuleValue.append("|");
                        conditionRuleValue.append(rangeTo);
                    }
                } else if (conditionSubselect.equals("split2")) {
                    conditionRuleValue.append("|");
                    conditionRuleValue.append(conditionSubselect);
                    conditionRuleValue.append("|");
                    conditionRuleValue.append(conditionSubselectRow);
                    conditionRuleValue.append(",");
                    conditionRuleValue.append(conditionSubselectColumn);

                    if (rangeFrom != null && rangeFrom.equals("") == false) {
                        conditionRuleValue.append("|");
                        conditionRuleValue.append(rangeFrom);
                    }

                    if (rangeTo != null && rangeTo.equals("") == false) {
                        conditionRuleValue.append("|");
                        conditionRuleValue.append(rangeTo);
                    }
                }
            }
        } else if (conditionRuleKey.equals("range")) {
            Range mRange = request.getRange();
            String conditionSubselectSeg = mRange.getSeg();
            String conditionSubselectRow = mRange.getRow();
            String conditionSubselectColumn = mRange.getColumn();
            String rangeFrom = mRange.getRangeFrom();
            String rangeTo = mRange.getRangeTo();

            if (mRange != null && mRange.equals("") == false) {
                if (conditionSubselect.equals("orig")) {
                    conditionRuleValue.append("|");

                    if (rangeFrom != null && rangeFrom.equals("") == false) {
                        conditionRuleValue.append(rangeFrom);
                    }

                    if (rangeTo != null && rangeTo.equals("") == false) {
                        conditionRuleValue.append("|");
                        conditionRuleValue.append(rangeTo);
                    }
                } else if (conditionSubselect.equals("split1")) {
                    conditionRuleValue.append("|");
                    conditionRuleValue.append(conditionSubselect);
                    conditionRuleValue.append("|");
                    conditionRuleValue.append(conditionSubselectSeg);

                    if (rangeFrom != null && rangeFrom.equals("") == false) {
                        conditionRuleValue.append("|");
                        conditionRuleValue.append(rangeFrom);
                    }

                    if (rangeTo != null && rangeTo.equals("") == false) {
                        conditionRuleValue.append("|");
                        conditionRuleValue.append(rangeTo);
                    }
                } else if (conditionSubselect.equals("split2")) {
                    conditionRuleValue.append("|");
                    conditionRuleValue.append(conditionSubselect);
                    conditionRuleValue.append("|");
                    conditionRuleValue.append(conditionSubselectRow);
                    conditionRuleValue.append(",");
                    conditionRuleValue.append(conditionSubselectColumn);

                    if (rangeFrom != null && rangeFrom.equals("") == false) {
                        conditionRuleValue.append("|");
                        conditionRuleValue.append(rangeFrom);
                    }

                    if (rangeTo != null && rangeTo.equals("") == false) {
                        conditionRuleValue.append("|");
                        conditionRuleValue.append(rangeTo);
                    }
                }
            }
        } else {
            if (conditionRuleKey.equals("in")) {
                In in = request.getIn();
                String conditionSubselectSeg = in.getSeg();
                String conditionSubselectRow = in.getRow();
                String conditionSubselectColumn = in.getColumn();
                List<InElementArray> inElementArrayList = in.getInElementArray();


                if (in != null && in.equals("") == false) {
                    if (conditionSubselect.equals("orig")) {
                        conditionRuleValue.append("|");

                        for (InElementArray i : inElementArrayList) {
                            if (i.getColumn2() != null && i.getColumn2().equals("") == false) {
                                conditionRuleValue.append(i.getColumn2());
                                conditionRuleValue.append("|");
                            }
                        }
                        conditionRuleValue.deleteCharAt(conditionRuleValue.length() - 1);
                    } else if (conditionSubselect.equals("split1")) {
                        conditionRuleValue.append("|");
                        conditionRuleValue.append(conditionSubselect);
                        conditionRuleValue.append("|");
                        conditionRuleValue.append(conditionSubselectSeg);
                        conditionRuleValue.append("|");

                        for (InElementArray i : inElementArrayList) {
                            if (i.getColumn2() != null && i.getColumn2().equals("") == false) {
                                conditionRuleValue.append(i.getColumn2());
                                conditionRuleValue.append("|");
                            }
                        }
                        conditionRuleValue.deleteCharAt(conditionRuleValue.length() - 1);
                    } else if (conditionSubselect.equals("split2")) {
                        conditionRuleValue.append("|");
                        conditionRuleValue.append(conditionSubselect);
                        conditionRuleValue.append("|");
                        conditionRuleValue.append(conditionSubselectRow);
                        conditionRuleValue.append(",");
                        conditionRuleValue.append(conditionSubselectColumn);
                        conditionRuleValue.append("|");

                        for (InElementArray i : inElementArrayList) {
                            if (i.getColumn2() != null && i.getColumn2().equals("") == false) {
                                conditionRuleValue.append(i.getColumn2());
                                conditionRuleValue.append("|");
                            }
                        }
                        conditionRuleValue.deleteCharAt(conditionRuleValue.length() - 1);
                    }
                }
            } else if (conditionRuleKey.equals("seg")) {
                String seg = request.getSeg().toString();
                if (seg != null && seg.equals("") == false) {
                    conditionRuleValue.append("|");
                    conditionRuleValue.append(seg);
                }
            } else if (conditionRuleKey.equals("contains")) {
                Contains contains = request.getContains();

                String conditionSubselectSeg = contains.getSeg();
                String conditionSubselectRow = contains.getRow();
                String conditionSubselectColumn = contains.getColumn();
                String charString = contains.getCharString();

                if (contains != null && contains.equals("") == false) {
                    if (conditionSubselect.equals("orig")) {
                        conditionRuleValue.append("|");
                        if (charString.equals("|")) {
                            conditionRuleValue.append("\"" + charString + "\"");
                        } else {
                            conditionRuleValue.append(charString);
                        }
                    } else if (conditionSubselect.equals("split1")) {
                        conditionRuleValue.append("|");
                        conditionRuleValue.append(conditionSubselect);
                        conditionRuleValue.append("|");
                        conditionRuleValue.append(conditionSubselectSeg);
                        conditionRuleValue.append("|");
                        if (charString.equals("|")) {
                            conditionRuleValue.append("\"" + charString + "\"");
                        } else {
                            conditionRuleValue.append(charString);
                        }
                    } else if (conditionSubselect.equals("split2")) {
                        conditionRuleValue.append("|");
                        conditionRuleValue.append(conditionSubselect);
                        conditionRuleValue.append("|");
                        conditionRuleValue.append(conditionSubselectRow);
                        conditionRuleValue.append(",");
                        conditionRuleValue.append(conditionSubselectColumn);
                        conditionRuleValue.append("|");
                        if (charString.equals("|")) {
                            conditionRuleValue.append("\"" + charString + "\"");
                        } else {
                            conditionRuleValue.append(charString);
                        }
                    }
                }
            }
        }

        return conditionRuleValue.toString();
    }

    public static String actionRuleBuilder(RuleRequest request) {
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
            if (substrDirection != null && substrDirection.equals("") == false) {
                actionRuleValue.append("|");
                actionRuleValue.append(substrDirection);
            }

            if (substrStartIndex != null && substrStartIndex.equals("") == false) {
                actionRuleValue.append("|");
                actionRuleValue.append(substrStartIndex);
            }

            if (substrLength != null && substrLength.equals("") == false) {
                actionRuleValue.append("|");
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
            dec = request.getDec().toString();
            if (dec != null && dec.equals("") == false) {
                actionRuleValue.append("|");
                actionRuleValue.append(dec);
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
