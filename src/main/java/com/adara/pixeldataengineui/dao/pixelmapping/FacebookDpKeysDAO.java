package com.adara.pixeldataengineui.dao.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface FacebookDpKeysDAO {
    String getFacebookKeyMappings() throws Exception;

    String getFacebookKeyMapping(String id) throws Exception;

    Integer insertMappingFacebookDpKeys(Integer keyId, Byte enabled, Byte updateInterval, Byte useImagePixel) throws Exception;

    Integer updateMappingFacebookDpKeys(Integer keyId, Byte enabled, Byte updateInterval, Byte useImagePixel) throws Exception;

    Integer deleteFacebookKeyMapping(String id) throws Exception;
}
