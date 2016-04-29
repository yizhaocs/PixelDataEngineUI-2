package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.PixelDataEngineGroupDAO;
import com.adara.pixeldataengineui.model.frontend.requestbody.GroupRequest;
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

    public Integer insertGroup(GroupRequest request) {
        return mPixelDataEngineGroupDAO.insertGroup(request);
    }

    public String getGroups() {
        return mPixelDataEngineGroupDAO.getGroups();
    }

    public String getGroup(String keyId) {
        return mPixelDataEngineGroupDAO.getGroup(keyId);
    }

    public Integer updateGroup(GroupRequest request) {
        return mPixelDataEngineGroupDAO.updateGroup(request);
    }

    public Integer deleteGroup(String keyId) {
        return mPixelDataEngineGroupDAO.deleteGroup(keyId);
    }

}
