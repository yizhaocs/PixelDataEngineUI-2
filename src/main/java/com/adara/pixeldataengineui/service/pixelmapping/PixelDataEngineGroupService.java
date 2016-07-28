package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.PixelDataEngineConfigsDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.PixelDataEngineGroupsDTO;

/**
 * Created by yzhao on 4/28/16.
 */
public interface PixelDataEngineGroupService {
    Integer insertGroup(String triggerKeyId, Integer groupType, Boolean isUITest) throws Exception;

    GenericDTOList<PixelDataEngineGroupsDTO> getGroups() throws Exception;

    PixelDataEngineGroupsDTO getGroup(String triggerKeyId) throws Exception;

    GenericDTOList<PixelDataEngineConfigsDTO> getSameGroup(Integer gid) throws Exception;

    Integer updateGroup(String triggerKeyId, Integer groupType) throws Exception;

    Integer deleteGroup(String triggerKeyId, String gid, Boolean isUITest) throws Exception;

    void truncatePixelDataEngineGroupsTable(Boolean isUITest) throws Exception;
}
