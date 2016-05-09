package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.model.frontend.requestbody.RuleRequest;

import java.util.Map;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface PixelDataEngineRuleService {
    Integer insertRule(RuleRequest request);

    String getRules();

    String getRule(Integer gid, String keyId, Integer priority);

    Integer updateRule(RuleRequest request);

    Integer deleteRule(Integer gid, String keyId, Integer priority);

    Map<String, String> testRule(RuleRequest request);
}
