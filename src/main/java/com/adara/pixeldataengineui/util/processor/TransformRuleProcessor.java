package com.adara.pixeldataengineui.util.processor;

import com.adara.pixeldataengineui.util.model.PixelDataEngineConfig;
import com.adara.pixeldataengineui.util.rule.action.Action;
import com.adara.pixeldataengineui.util.rule.condition.ConditionChecker;
import com.adara.pixeldataengineui.util.rule.parse.ParseResult;
import com.adara.pixeldataengineui.util.rule.parse.Parser;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * This is the processor that handles transform rule :
 *
 * Example : key10636 : ECONOMY:ALA:AUH-AUH:ALA
 *
 * The input is this key->value map : {10636: 'ECONOMY:ALA:AUH-AUH:ALA'}
 *
 * The output is a map with multiple keys and values. In this example, it would
 * be like : {10636: 'ECONOMY:ALA:AUH-AUH:ALA', 10936: 'ALA', 10937: 'AUH' }
 *
 * @author jgao
 *
 */
public class TransformRuleProcessor implements RuleProcessor {
    private static final Logger log = Logger.getLogger(TransformRuleProcessor.class);

    public void init() {
        // blank
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.opinmind.pixeldataengine.processor.RuleProcessor#processRule(java
     * .util.Map)
     */
    @Override
    public Map<String, String> processRule(String key, String value, PixelDataEngineConfig config)
            throws Exception {
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put(key, value);

        Parser parser = config.getParser();
        ConditionChecker conditionChecker = config.getConditionChecker();
        Action action = config.getAction();

        // if parser is not defined, then we will create a dummy parse result that only has
        // the original value
        ParseResult parseResult = new ParseResult(value, null, 0, null);
        if (parser != null) {
            parseResult = parser.parse(key, value);
        }

        if (log.isDebugEnabled()) {
            log.debug("we parsed value:" + value + " with parser:" + parser + " and got result: " + parseResult);
        }

        boolean shouldTakeAction = true;
        if (conditionChecker != null) {
            if (conditionChecker.checkCondition(key, value, parseResult) == false) {
                shouldTakeAction = false;
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("we checked condition for value:" + value + " with checker:" + conditionChecker + " and got result: " + shouldTakeAction);
        }

        if (shouldTakeAction) {
            resultMap = action.execute(key, value, parseResult);
        }

        if (log.isDebugEnabled()) {
            log.debug("we executed action for value:" + value + " with action:" + action + " and got result: " + resultMap);
        }

        return resultMap;
    }

}
