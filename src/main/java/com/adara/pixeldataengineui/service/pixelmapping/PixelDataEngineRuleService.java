package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.model.frontend.requestbody.RuleRequest;

import java.util.Map;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface PixelDataEngineRuleService{
    Integer insertRule(RuleRequest request, Boolean isUITest);

    String getRules();

    String getRule(Integer gid, String keyId, Integer priority);

    Integer updateRule(RuleRequest request);

    Integer deleteRule(Integer gid, String keyId, Integer priority, Boolean isUITest);

    Map<String, String> testRule(PixelDataEngineService mPixelDataEngine, PixelDataEngineRuleService mPixelDataEngineRuleService, PixelDataEngineGroupService mPixelDataEngineGroupService, RuleRequest request);
}
