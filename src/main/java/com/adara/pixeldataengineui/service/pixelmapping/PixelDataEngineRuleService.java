package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.model.frontend.requestbody.RuleRequest;

import java.util.Map;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface PixelDataEngineRuleService {
    Integer insertRule(RuleRequest request, Boolean isUITest) throws Exception;

    String getRules() throws Exception;

    String getRule(Integer gid, String keyId, Integer priority) throws Exception;

    Integer updateRule(RuleRequest request) throws Exception;

    Integer deleteRule(Integer gid, String keyId, Integer priority, Boolean isUITest) throws Exception;

    Map<String, String> testRule(PixelDataEngineService mPixelDataEngine, PixelDataEngineRuleService mPixelDataEngineRuleService, PixelDataEngineGroupService mPixelDataEngineGroupService, RuleRequest request) throws Exception;
}
