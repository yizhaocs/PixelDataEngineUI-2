package com.adara.pixeldataengineui.dao.pixelmapping;

import java.math.BigInteger;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface DataProviderFacebookPixelsDAO {
    String getFacebookPixelMappings();

    String getFacebookPixelMapping(String id);

    Integer insertMappingDataProviderFacebookPixels(Integer dpId, BigInteger facebookPixelId);

    Integer updateMappingDataProviderFacebookPixels(Integer dpId, BigInteger facebookPixelId);

    Integer deleteFacebookPixelMapping(String id);
}
