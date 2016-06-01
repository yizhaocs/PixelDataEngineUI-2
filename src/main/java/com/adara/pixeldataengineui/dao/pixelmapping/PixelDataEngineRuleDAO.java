package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.frontend.requestbody.RuleRequest;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface PixelDataEngineRuleDAO {
    Integer insertRule(RuleRequest request, Boolean isUITest) throws Exception;

    String getRules() throws Exception;

    String getRule(Integer gid, String keyId, Integer priority) throws Exception;

    Integer updateRule(RuleRequest request) throws Exception;

    Integer deleteRule(Integer gid, String keyId, Integer priority, Boolean isUITest) throws Exception;
}
