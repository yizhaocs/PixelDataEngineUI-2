package com.adara.pixeldataengineui.dao.pixelmapping;

import java.math.BigInteger;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface DataProviderFacebookPixelsDAO {
    String getFacebookPixelMappings();

    String getFacebookPixelMapping(String id);

    Integer insertMappingDataProviderFacebookPixels(Integer dp_id, BigInteger facebook_pixel_id);

    Integer updateMappingDataProviderFacebookPixels(Integer dp_id, BigInteger facebook_pixel_id);

    Integer deleteFacebookPixelMapping(String id);
}
