package com.adara.pixeldataengineui.service.pixelmapping;

import java.math.BigInteger;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface FacebookService {
    String getFacebookPixelMappings();

    String getFacebookDpMappings();

    String getFacebookKeyMappings();

    String getFacebookPixelMapping(String id);

    String getFacebookDpMapping(String id);

    String getFacebookKeyMapping(String id);

    Integer insertMappingDataProviderFacebookPixels(Integer dpId, BigInteger facebookPixelId);

    Integer insertMappingFacebookDpKeys(Integer keyId, Byte enabled, Byte updateInterval, Byte useImagePixel);

    Integer updateMappingDataProviderFacebookPixels(Integer dpId, BigInteger facebookPixelId);

    Integer updateMappingDataProviders(Integer id, String name, Byte syncFacebook);

    Integer updateMappingFacebookDpKeys(Integer keyId, Byte enabled, Byte updateInterval, Byte useImagePixel);

    Integer deleteFacebookPixelMapping(String id);

    Integer deleteFacebookKeyMapping(String id);
}
