package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.PixelDataEngineConfigsDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.PixelDataEngineGroupsDTO;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface PixelDataEngineGroupDAO {
    ResponseDTO insertGroup(String triggerKeyId, Integer groupType, Boolean isUITest) throws Exception;

    GenericDTOList<PixelDataEngineGroupsDTO> getGroups() throws Exception;

    PixelDataEngineGroupsDTO getGroup(String keyId) throws Exception;

    GenericDTOList<PixelDataEngineConfigsDTO> getSameGroup(Integer gid) throws Exception;

    Integer updateGroup(String triggerKeyId, Integer groupType) throws Exception;

    Integer deleteGroup(String keyId, String gid, Boolean isUITest) throws Exception;

    void truncatePixelDataEngineGroupsTable(Boolean isUITest) throws Exception;
}
