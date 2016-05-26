package com.adara.pixeldataengineui.dao.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface FacebookDpKeysDAO {
    String getFacebookKeyMappings();

    String getFacebookKeyMapping(String id);

    Integer insertMappingFacebookDpKeys(Integer keyId, Byte enabled, Byte updateInterval, Byte useImagePixel);

    Integer updateMappingFacebookDpKeys(Integer keyId, Byte enabled, Byte updateInterval, Byte useImagePixel);

    Integer deleteFacebookKeyMapping(String id);
}
