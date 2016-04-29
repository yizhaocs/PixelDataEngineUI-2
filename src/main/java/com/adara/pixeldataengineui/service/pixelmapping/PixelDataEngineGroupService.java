package com.adara.pixeldataengineui.service.pixelmapping;

/**
 * Created by yzhao on 4/28/16.
 */
public interface PixelDataEngineGroupService {
    Integer insertGroup(Integer key_id, Integer gid, String type);

    String getGroups();

    String getGroup(String keyId);

    Integer updateGroup(Integer key_id, Integer gid, String type);

    Integer deleteGroup(String keyId);
}
