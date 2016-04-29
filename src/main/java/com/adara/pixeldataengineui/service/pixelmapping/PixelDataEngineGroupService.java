package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.model.frontend.requestbody.GroupRequest;

/**
 * Created by yzhao on 4/28/16.
 */
public interface PixelDataEngineGroupService {
    Integer insertGroup(GroupRequest request);

    String getGroups();

    String getGroup(String keyId);

    Integer updateGroup(GroupRequest request);

    Integer deleteGroup(String keyId);
}
