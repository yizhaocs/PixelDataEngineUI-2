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

    public Integer insertGroup(Integer triggeringKeyId, Integer gid, String groupType) {
        return mPixelDataEngineGroupDAO.insertGroup(triggeringKeyId, gid, groupType);
    }

    public String getGroups() {
        return mPixelDataEngineGroupDAO.getGroups();
    }

    public String getGroup(String triggeringKeyId) {
        return mPixelDataEngineGroupDAO.getGroup(triggeringKeyId);
    }

    public String getSameGroup(String gid) {
        return mPixelDataEngineGroupDAO.getSameGroup(gid);
    }

    public Integer updateGroup(Integer triggeringKeyId, Integer gid, String groupType) {
        return mPixelDataEngineGroupDAO.updateGroup(triggeringKeyId, gid, groupType);
    }

    public Integer deleteGroup(String triggeringKeyId) {
        return mPixelDataEngineGroupDAO.deleteGroup(triggeringKeyId);
    }

}
