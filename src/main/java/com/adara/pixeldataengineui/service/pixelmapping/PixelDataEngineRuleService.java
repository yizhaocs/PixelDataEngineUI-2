package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.model.frontend.requestbody.RuleRequest;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface PixelDataEngineRuleService {
    Integer insertRule(RuleRequest request);

    String getRules();

    String getRule(String gid, String keyId, String priority);

    Integer updateRule(RuleRequest request);

    Integer deleteRule(String gid, String keyId, String priority);
}
