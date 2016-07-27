package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.PixelDataEngineGroupDAO;
import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.PixelDataEngineConfigsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("pixelDataEngineGroupService")
@Transactional
public class PixelDataEngineGroupImpl implements PixelDataEngineGroupService {
    @Autowired
    private PixelDataEngineGroupDAO mPixelDataEngineGroupDAO;

    public Integer insertGroup(String triggerKeyId, Integer groupType, Boolean isUITest) throws Exception{
        return mPixelDataEngineGroupDAO.insertGroup(triggerKeyId, groupType, isUITest);
    }

    public String getGroups() throws Exception {
        return mPixelDataEngineGroupDAO.getGroups();
    }

    public String getGroup(String triggerKeyId) throws Exception {
        return mPixelDataEngineGroupDAO.getGroup(triggerKeyId);
    }

    public GenericDTOList<PixelDataEngineConfigsDTO> getSameGroup(Integer gid) throws Exception {
        return mPixelDataEngineGroupDAO.getSameGroup(gid);
    }

    public Integer updateGroup(String triggerKeyId, Integer groupType) throws Exception {
        return mPixelDataEngineGroupDAO.updateGroup(triggerKeyId, groupType);
    }

    public Integer deleteGroup(String triggerKeyId, String gid, Boolean isUITest) throws Exception {
        return mPixelDataEngineGroupDAO.deleteGroup(triggerKeyId, gid, isUITest);
    }

    public void truncatePixelDataEngineGroupsTable(Boolean isUITest) throws Exception {
        mPixelDataEngineGroupDAO.truncatePixelDataEngineGroupsTable(isUITest);
    }

}
