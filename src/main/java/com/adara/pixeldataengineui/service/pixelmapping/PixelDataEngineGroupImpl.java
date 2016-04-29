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

    public Integer insertGroup(Integer key_id, Integer gid, String type) {
        return mPixelDataEngineGroupDAO.insertGroup(key_id, gid, type);
    }

    public String getGroups() {
        return mPixelDataEngineGroupDAO.getGroups();
    }

    public String getGroup(String keyId) {
        return mPixelDataEngineGroupDAO.getGroup(keyId);
    }

    public Integer updateGroup(Integer key_id, Integer gid, String type) {
        return mPixelDataEngineGroupDAO.updateGroup(key_id, gid, type);
    }

    public Integer deleteGroup(String keyId) {
        return mPixelDataEngineGroupDAO.deleteGroup(keyId);
    }

}
