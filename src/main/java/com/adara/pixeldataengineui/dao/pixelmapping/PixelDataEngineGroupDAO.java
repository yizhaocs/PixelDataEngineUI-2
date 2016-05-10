package com.adara.pixeldataengineui.dao.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface PixelDataEngineGroupDAO {
    Integer insertGroup(String trigger_key_id, Integer group_type);

    String getGroups();

    String getGroup(String keyId);

    String getSameGroup(Integer gid);

    Integer updateGroup(String trigger_key_id, Integer group_type);

    Integer deleteGroup(String keyId);
}
