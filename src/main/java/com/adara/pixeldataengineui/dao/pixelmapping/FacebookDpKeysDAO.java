package com.adara.pixeldataengineui.dao.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface FacebookDpKeysDAO {
    String getFacebookKeyMappings();

    String getFacebookKeyMapping(String id);

    Integer insertMappingFacebookDpKeys(Integer key_id, Byte enabled, Byte update_interval, Byte use_image_pixel);

    Integer updateMappingFacebookDpKeys(Integer key_id, Byte enabled, Byte update_interval, Byte use_image_pixel);

    Integer deleteFacebookKeyMapping(String id);
}
