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

    Integer insertMappingDataProviderFacebookPixels(Integer dp_id, BigInteger facebook_pixel_id);

    Integer insertMappingFacebookDpKeys(Integer key_id, Byte enabled, Byte update_interval, Byte use_image_pixel);

    Integer updateMappingDataProviderFacebookPixels(Integer dp_id, BigInteger facebook_pixel_id);

    Integer updateMappingDataProviders(Integer id, String name, Byte sync_facebook);

    Integer updateMappingFacebookDpKeys(Integer key_id, Byte enabled, Byte update_interval, Byte use_image_pixel);

    Integer deleteFacebookPixelMapping(String id);

    Integer deleteFacebookKeyMapping(String id);
}
