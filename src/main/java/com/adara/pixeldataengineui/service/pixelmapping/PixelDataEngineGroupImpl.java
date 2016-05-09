package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.PixelDataEngineGroupDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("pixelDataEngineGroupService")
@Transactional
public class PixelDataEngineGroupImpl implements PixelDataEngineGroupService{
    @Autowired
    private PixelDataEngineGroupDAO mPixelDataEngineGroupDAO;

    public Integer insertGroup(String triggerKeyId, Integer gid, Integer groupType) {
        return mPixelDataEngineGroupDAO.insertGroup(triggerKeyId, gid, groupType);
    }

    public String getGroups() {
        return mPixelDataEngineGroupDAO.getGroups();
    }

    public String getGroup(String triggerKeyId) {
        return mPixelDataEngineGroupDAO.getGroup(triggerKeyId);
    }

    public String getSameGroup(Integer gid) {
        return mPixelDataEngineGroupDAO.getSameGroup(gid);
    }

    public Integer updateGroup(String triggerKeyId, Integer gid, Integer groupType) {
        return mPixelDataEngineGroupDAO.updateGroup(triggerKeyId, gid, groupType);
    }

    public Integer deleteGroup(String triggerKeyId) {
        return mPixelDataEngineGroupDAO.deleteGroup(triggerKeyId);
    }

}
