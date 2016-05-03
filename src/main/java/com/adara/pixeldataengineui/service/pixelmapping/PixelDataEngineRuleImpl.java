package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.PixelDataEngineRuleDAO;
import com.adara.pixeldataengineui.model.frontend.requestbody.RuleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("pixelDataEngineRuleService")
@Transactional
public class PixelDataEngineRuleImpl implements PixelDataEngineRuleService {
    @Autowired
    private PixelDataEngineRuleDAO mPixelDataEngineRuleDAO;

    public Integer insertRule(RuleRequest request) {
        return mPixelDataEngineRuleDAO.insertRule(request);
    }

    public String getRules() {
        return mPixelDataEngineRuleDAO.getRules();
    }

    public String getRule(String gid, String keyId, String priority) {
        return mPixelDataEngineRuleDAO.getRule(gid, keyId, priority);
    }

    public Integer updateRule(RuleRequest request) {
        return mPixelDataEngineRuleDAO.updateRule(request);
    }

    public Integer deleteRule(String keyId) {
        return mPixelDataEngineRuleDAO.deleteRule(keyId);
    }
}
