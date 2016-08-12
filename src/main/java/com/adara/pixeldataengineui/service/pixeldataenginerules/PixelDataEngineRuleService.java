package com.adara.pixeldataengineui.service.pixeldataenginerules;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginerules.PixelDataEngineConfigsDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginerules.TestRuleDTO;
import com.adara.pixeldataengineui.model.frontend.requestbody.RuleRequest;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface PixelDataEngineRuleService {
    ResponseDTO insertRule(RuleRequest request, Boolean isUITest) throws Exception;

    GenericDTOList<PixelDataEngineConfigsDTO> getRules() throws Exception;

    PixelDataEngineConfigsDTO getRule(Integer gid, String keyId, Integer priority) throws Exception;

    ResponseDTO updateRule(RuleRequest request) throws Exception;

    ResponseDTO deleteRule(Integer gid, String keyId, Integer priority, Boolean isUITest) throws Exception;

    GenericDTOList<TestRuleDTO> testRule(PixelDataEngineRuleService mPixelDataEngineRuleService, PixelDataEngineGroupService mPixelDataEngineGroupService, RuleRequest request) throws Exception;

    void truncatePixelDataEngineConfigsTable(Boolean isUITest) throws Exception;
}
