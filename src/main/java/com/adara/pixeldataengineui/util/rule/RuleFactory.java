package com.adara.pixeldataengineui.util.rule;

import com.adara.pixeldataengineui.util.rule.action.*;
import com.adara.pixeldataengineui.util.rule.condition.*;
import com.adara.pixeldataengineui.util.rule.parse.DoubleSplitParser;
import com.adara.pixeldataengineui.util.rule.parse.OrigParser;
import com.adara.pixeldataengineui.util.rule.parse.Parser;
import com.adara.pixeldataengineui.util.rule.parse.SingleSplitParser;
import org.apache.commons.lang.text.StrTokenizer;
import org.apache.log4j.Logger;

/**
 * @author jgao
 *
 */
public class RuleFactory {
    private static final Logger log = Logger.getLogger(RuleFactory.class);

    private enum ParserType { split1, split2, orig };
    private enum ConditionType { len, range, in, seg, always };
    private enum ActionType { set, ignore, substr, dec };

    public static Parser createParser(String rule) throws Exception {
        Parser parser = null;

        StrTokenizer st = StrTokenizer.getCSVInstance();
        st.setDelimiterChar('|');
        String[] ruleArray = st.reset(rule).getTokenArray();
        String ruleType = ruleArray[0];

        switch (ParserType.valueOf(ruleType)) {
            case split1:
                parser = new SingleSplitParser(ruleArray);
                break;
            case split2:
                parser = new DoubleSplitParser(ruleArray);
                break;
            case orig:
                parser = new OrigParser(ruleArray);
                break;
        }

        if (log.isDebugEnabled())
            log.debug("created a parser: " + parser);

        return parser;
    }

    public static ConditionChecker createConditionChecker(String rule) throws Exception {
        ConditionChecker conditionChecker = null;

        StrTokenizer st = StrTokenizer.getCSVInstance();
        st.setDelimiterChar('|');
        String[] ruleArray = st.reset(rule).getTokenArray();
        String ruleType = ruleArray[0];

        switch (ConditionType.valueOf(ruleType)) {
            case len:
                conditionChecker = new LengthConditionChecker(ruleArray);
                break;
            case range:
                conditionChecker = new RangeConditionChecker(ruleArray);
                break;
            case in:
                conditionChecker = new InConditionChecker(ruleArray);
                break;
            case seg:
                conditionChecker = new SegConditionChecker(ruleArray);
                break;
            case always:
                conditionChecker = new AlwaysConditionChecker(ruleArray);
                break;
        }

        if (log.isDebugEnabled())
            log.debug("created a condition checker: " + conditionChecker);

        return conditionChecker;
    }

    public static Action createAction(String rule) throws Exception {
        Action action = null;

        StrTokenizer st = StrTokenizer.getCSVInstance();
        st.setDelimiterChar('|');
        String[] ruleArray = st.reset(rule).getTokenArray();
        String ruleType = ruleArray[0];

        switch (ActionType.valueOf(ruleType)) {
            case set:
                action = new SetAction(ruleArray);
                break;
            case ignore:
                action = new IgnoreAction(ruleArray);
                break;
            case substr:
                action = new SubStringAction(ruleArray);
                break;
            case dec:
                action = new DecimalAction(ruleArray);
                break;
        }

        if (log.isDebugEnabled())
            log.debug("created an action: " + action);

        return action;
    }

    public static void main(String[] argv) {
        Action action = null;

        try {
            Parser p = RuleFactory.createParser("split2|,|:");
            System.out.println("parser is : " + p);

            action = null;

            action = RuleFactory.createAction("substr|L|0|2");

            ConditionChecker checker = RuleFactory.createConditionChecker("seg|3");
            System.out.println("checker is: " + checker);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("action is : " + action);
    }
}