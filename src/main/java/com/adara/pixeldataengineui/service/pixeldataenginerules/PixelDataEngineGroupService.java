package com.adara.pixeldataengineui.service.pixeldataenginerules;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginerules.PixelDataEngineConfigsDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginerules.PixelDataEngineGroupsDTO;
import com.adara.pixeldataengineui.model.frontend.requestbody.GeoMapCreationRequest;

/**
 * Created by yzhao on 4/28/16.
 */
public interface PixelDataEngineGroupService {
    ResponseDTO insertGroup(String triggerKeyId, Integer groupType, Boolean isUITest) throws Exception;

    GenericDTOList<PixelDataEngineGroupsDTO> getGroups() throws Exception;

    PixelDataEngineGroupsDTO getGroup(String triggerKeyId) throws Exception;

    GenericDTOList<PixelDataEngineConfigsDTO> getSameGroup(Integer gid) throws Exception;

    ResponseDTO updateGroup(String triggerKeyId, Integer groupType) throws Exception;

    ResponseDTO deleteGroup(String triggerKeyId, String gid, Boolean isUITest) throws Exception;

    void truncatePixelDataEngineGroupsTable(Boolean isUITest) throws Exception;
}
