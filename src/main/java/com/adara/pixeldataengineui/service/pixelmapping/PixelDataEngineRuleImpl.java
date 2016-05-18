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

    public Integer insertRule(RuleRequest request, Boolean isUITest) {
        return mPixelDataEngineRuleDAO.insertRule(request, isUITest);
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

    public Integer deleteRule(Integer gid, String keyId, Integer priority, Boolean isUITest) {
        return mPixelDataEngineRuleDAO.deleteRule(gid, keyId, priority, isUITest);
    }

    public Map<String, String> testRule(PixelDataEngineService mPixelDataEngineService, PixelDataEngineRuleService mPixelDataEngineRuleService, RuleRequest request) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "testRule");

        String testValue = request.getTestValue();

        request.setKeyId("1");
        request.setGid("1");
        mPixelDataEngineRuleService.insertRule(request, true);

        // let the mock table refresh its pixelDataEngineConfigs
        mPixelDataEngineService.mPixelDataEngine.init();
        Map<String, String> resultMap =  mPixelDataEngineService.mPixelDataEngine.processRule("1", testValue);

        mPixelDataEngineRuleService.deleteRule(1,"1",Integer.valueOf(request.getPriority()),true);

        return resultMap;
    }
}
