package com.adara.pixeldataengineui.dao.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface PixelDataEngineGroupDAO {
    Integer insertGroup(Integer key_id, Integer gid, String type);

    String getGroups();

    String getGroup(String keyId);

    Integer updateGroup(Integer key_id, Integer gid, String type);

    Integer deleteGroup(String keyId);
}
