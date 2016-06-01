package com.adara.pixeldataengineui.dao.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface PixelDataEngineGroupDAO {
    Integer insertGroup(String triggerKeyId, Integer groupType, Boolean isUITest) throws Exception;

    String getGroups() throws Exception;

    String getGroup(String keyId) throws Exception;

    String getSameGroup(Integer gid) throws Exception;

    Integer updateGroup(String triggerKeyId, Integer groupType) throws Exception;

    Integer deleteGroup(String keyId, String gid, Boolean isUITest) throws Exception;
}
