package com.adara.pixeldataengineui.service.pixelmapping;

/**
 * Created by yzhao on 4/28/16.
 */
public interface PixelDataEngineGroupService {
    Integer insertGroup(String triggerKeyId, Integer groupType, Boolean isUITest) throws Exception;

    String getGroups() throws Exception;

    String getGroup(String triggerKeyId) throws Exception;

    String getSameGroup(Integer gid) throws Exception;

    Integer updateGroup(String triggerKeyId, Integer groupType) throws Exception;

    Integer deleteGroup(String triggerKeyId, String gid, Boolean isUITest) throws Exception;
}
