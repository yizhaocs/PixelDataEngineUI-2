package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.frontend.requestbody.GroupRequest;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface PixelDataEngineGroupDAO {
    Integer insertGroup(GroupRequest request);

    String getGroups();

    String getGroup(String gid);

    Integer updateGroup(GroupRequest request);

    Integer deleteGroup(String keyId);
}
