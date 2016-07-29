package com.adara.pixeldataengineui.dao.pixeldataenginerules;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginerules.PixelDataEngineConfigsDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginerules.PixelDataEngineGroupsDTO;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface PixelDataEngineGroupDAO {
    ResponseDTO insertGroup(String triggerKeyId, Integer groupType, Boolean isUITest) throws Exception;

    GenericDTOList<PixelDataEngineGroupsDTO> getGroups() throws Exception;

    PixelDataEngineGroupsDTO getGroup(String keyId) throws Exception;

    GenericDTOList<PixelDataEngineConfigsDTO> getSameGroup(Integer gid) throws Exception;

    ResponseDTO updateGroup(String triggerKeyId, Integer groupType) throws Exception;

    ResponseDTO deleteGroup(String keyId, String gid, Boolean isUITest) throws Exception;

    void truncatePixelDataEngineGroupsTable(Boolean isUITest) throws Exception;
}
