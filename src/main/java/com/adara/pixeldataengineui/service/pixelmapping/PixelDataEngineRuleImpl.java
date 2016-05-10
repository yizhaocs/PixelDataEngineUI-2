package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.PixelDataEngineRuleDAO;
import com.adara.pixeldataengineui.model.frontend.requestbody.RuleRequest;
import com.adara.pixeldataengineui.util.Tools;
import com.adara.pixeldataengineui.util.model.PixelDataEngineConfig;
import com.adara.pixeldataengineui.util.processor.RuleProcessor;
import com.adara.pixeldataengineui.util.processor.TransformRuleProcessor;
import com.adara.pixeldataengineui.util.rule.RuleFactory;
import com.adara.pixeldataengineui.util.rule.action.Action;
import com.adara.pixeldataengineui.util.rule.condition.ConditionChecker;
import com.adara.pixeldataengineui.util.rule.parse.Parser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("pixelDataEngineRuleService")
@Transactional
public class PixelDataEngineRuleImpl implements PixelDataEngineRuleService {
    private static final Log LOG = LogFactory.getLog(PixelDataEngineRuleImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private final TransformRuleProcessor mTransformRuleProcessor = new TransformRuleProcessor();
    @Autowired
    private PixelDataEngineRuleDAO mPixelDataEngineRuleDAO;

    public Integer insertRule(RuleRequest request) {
        return mPixelDataEngineRuleDAO.insertRule(request);
    }

    public String getRules() {
        return mPixelDataEngineRuleDAO.getRules();
    }

    public String getRule(Integer gid, String keyId, Integer priority) {
        return mPixelDataEngineRuleDAO.getRule(gid, keyId, priority);
    }

    public Integer updateRule(RuleRequest request) {
        return mPixelDataEngineRuleDAO.updateRule(request);
    }

    public Integer deleteRule(Integer gid, String keyId, Integer priority) {
        return mPixelDataEngineRuleDAO.deleteRule(gid, keyId, priority);
    }

    public Map<String, String> testRule(RuleRequest request) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "testRule");
        /*
        *
        * */
        String testKey = request.getKeyId();
        String testValue = request.getTestValue();
        /*
        *
        * */
        String gid = request.getGid();

        String keyId = request.getKeyId();

        String priority = request.getPriority();

        String newPriority = request.getNewPriority();

        String type = request.getType();

        String parseRule = Tools.parseRuleBuilder(request);

        String conditionRule = Tools.conditionRuleBuilder(request);

        String actionRule = Tools.actionRuleBuilder(request);
        /*
        *
        * */
        PixelDataEngineConfig rule = null;
        try {
            Parser parser = RuleFactory.createParser(parseRule);
            ConditionChecker conditionChecker = RuleFactory.createConditionChecker(conditionRule);
            Action action = RuleFactory.createAction(actionRule);
            rule = new PixelDataEngineConfig(gid, keyId, priority, type, parser, conditionChecker, action, null);
        } catch (Exception e) {
            LOG.error("error parsing pixel data engine rule for key_id:" + keyId, e);
        }

        Map<String, RuleProcessor> ruleProcessorMap = new HashMap<String, RuleProcessor>();
        ruleProcessorMap.put("transform", mTransformRuleProcessor);

        Map<String, String> resultMap = new HashMap<String, String>();

        try {
            if (testKey != null && testValue != null) {
                RuleProcessor ruleProcessor = ruleProcessorMap.get(type);
                if (ruleProcessor != null) {
                    resultMap = ruleProcessor.processRule(testKey, testValue, rule);
                }
            }
        } catch (Exception e) {
            LOG.warn("testKey:" + testKey + " failed data engine processing", e);
        }


        return resultMap;
    }
}
