package com.adara.pixeldataengineui.dao.pixelmapping;

import java.math.BigInteger;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface DataProviderFacebookPixelsDAO {
    String getFacebookPixelMappings() throws Exception;

    String getFacebookPixelMapping(String id) throws Exception;

    Integer insertMappingDataProviderFacebookPixels(Integer dpId, BigInteger facebookPixelId) throws Exception;

    Integer updateMappingDataProviderFacebookPixels(Integer dpId, BigInteger facebookPixelId) throws Exception;

    Integer deleteFacebookPixelMapping(String id) throws Exception;
}
