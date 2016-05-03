package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.frontend.requestbody.RuleRequest;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface PixelDataEngineRuleDAO {
    Integer insertRule(RuleRequest request);

    String getRules();

    String getRule(String gid, String keyId, String priority);

    Integer updateRule(RuleRequest request);

    Integer deleteRule(String keyId);
}
