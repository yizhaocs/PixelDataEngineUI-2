package com.adara.pixeldataengineui.service.pixelmapping;

import java.math.BigInteger;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface FacebookService {
    String getFacebookPixelMappings() throws Exception;

    String getFacebookDpMappings() throws Exception;

    String getFacebookKeyMappings() throws Exception;

    String getFacebookPixelMapping(String id) throws Exception;

    String getFacebookDpMapping(String id) throws Exception;

    String getFacebookKeyMapping(String id) throws Exception;

    Integer insertMappingDataProviderFacebookPixels(Integer dpId, BigInteger facebookPixelId) throws Exception;

    Integer insertMappingFacebookDpKeys(Integer keyId, Byte enabled, Byte updateInterval, Byte useImagePixel) throws Exception;

    Integer updateMappingDataProviderFacebookPixels(Integer dpId, BigInteger facebookPixelId) throws Exception;

    Integer updateMappingDataProviders(Integer id, String name, Byte syncFacebook) throws Exception;

    Integer updateMappingFacebookDpKeys(Integer keyId, Byte enabled, Byte updateInterval, Byte useImagePixel) throws Exception;

    Integer deleteFacebookPixelMapping(String id) throws Exception;

    Integer deleteFacebookKeyMapping(String id) throws Exception;
}
