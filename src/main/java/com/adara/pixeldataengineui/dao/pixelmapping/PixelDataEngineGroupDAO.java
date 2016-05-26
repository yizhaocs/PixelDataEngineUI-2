package com.adara.pixeldataengineui.dao.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface PixelDataEngineGroupDAO {
    Integer insertGroup(String triggerKeyId, Integer groupType, Boolean isUITest);

    String getGroups();

    String getGroup(String keyId);

    String getSameGroup(Integer gid);

    Integer updateGroup(String triggerKeyId, Integer groupType);

    Integer deleteGroup(String keyId, String gid, Boolean isUITest);
}
