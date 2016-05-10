package com.adara.pixeldataengineui.service.pixelmapping;

/**
 * Created by yzhao on 4/28/16.
 */
public interface PixelDataEngineGroupService {
    Integer insertGroup(String triggerKeyId, Integer groupType);

    String getGroups();

    String getGroup(String triggerKeyId);

    String getSameGroup(Integer gid);

    Integer updateGroup(String triggerKeyId, Integer groupType);

    Integer deleteGroup(String triggerKeyId);
}
