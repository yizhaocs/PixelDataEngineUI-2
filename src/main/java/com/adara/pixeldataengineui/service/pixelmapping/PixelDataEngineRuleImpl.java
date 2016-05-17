package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.PixelDataEngineRuleDAO;
import com.adara.pixeldataengineui.model.frontend.requestbody.RuleRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("pixelDataEngineRuleService")
@Transactional
public class PixelDataEngineRuleImpl implements PixelDataEngineRuleService {
    private static final Log LOG = LogFactory.getLog(PixelDataEngineRuleImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();

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

    public Map<String, String> testRule(PixelDataEngineService mPixelDataEngineService, RuleRequest request) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "testRule");
        String testKey = request.getKeyId();
        String testValue = request.getTestValue();


        return mPixelDataEngineService.mPixelDataEngine.processRule(testKey, testValue);
    }
}
