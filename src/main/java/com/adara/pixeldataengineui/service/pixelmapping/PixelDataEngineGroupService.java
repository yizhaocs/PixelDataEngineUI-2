package com.adara.pixeldataengineui.service.pixelmapping;

/**
 * Created by yzhao on 4/28/16.
 */
public interface PixelDataEngineGroupService {
    Integer insertGroup(Integer triggeringKeyId, Integer gid, String groupType);

    String getGroups();

    String getGroup(String triggeringKeyId);

    String getSameGroup(String gid);

    Integer updateGroup(Integer triggeringKeyId, Integer gid, String groupType);

    Integer deleteGroup(String triggeringKeyId);
}
