package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.DataProviderFacebookPixelsDAO;
import com.adara.pixeldataengineui.dao.pixelmapping.DataProvidersDAO;
import com.adara.pixeldataengineui.dao.pixelmapping.FacebookDpKeysDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("facebookService")
@Transactional
public class FacebookServiceImpl implements FacebookService {
    @Autowired
    private DataProvidersDAO mDataProvidersDAO;
    @Autowired
    private FacebookDpKeysDAO mFacebookDpKeysDAO;
    @Autowired
    private DataProviderFacebookPixelsDAO mDataProviderFacebookPixelsDAO;

    public String getFacebookPixelMappings() {
        return mDataProviderFacebookPixelsDAO.getFacebookPixelMappings();
    }

    public String getFacebookDpMappings() {
        return mDataProvidersDAO.getFacebookDpMappings();
    }

    public String getFacebookKeyMappings() {
        return mFacebookDpKeysDAO.getFacebookKeyMappings();
    }

    public String getFacebookPixelMapping(String id) {
        return mDataProviderFacebookPixelsDAO.getFacebookPixelMapping(id);
    }

    public String getFacebookDpMapping(String id) {
        return mDataProvidersDAO.getFacebookDpMapping(id);
    }

    public String getFacebookKeyMapping(String id) {
        return mFacebookDpKeysDAO.getFacebookKeyMapping(id);
    }

    public Integer insertMappingDataProviderFacebookPixels(Integer dpId, BigInteger facebookPixelId) {
        return mDataProviderFacebookPixelsDAO.insertMappingDataProviderFacebookPixels(dpId, facebookPixelId);
    }

    public Integer insertMappingFacebookDpKeys(Integer keyId, Byte enabled, Byte updateInterval, Byte useImagePixel) {
        return mFacebookDpKeysDAO.insertMappingFacebookDpKeys(keyId, enabled, updateInterval, useImagePixel);
    }

    public Integer updateMappingDataProviderFacebookPixels(Integer dpId, BigInteger facebookPixelId) {
        return mDataProviderFacebookPixelsDAO.updateMappingDataProviderFacebookPixels(dpId, facebookPixelId);
    }

    public Integer updateMappingDataProviders(Integer id, String name, Byte syncFacebook) {
        return mDataProvidersDAO.updateMappingDataProviders(id, name, syncFacebook);
    }

    public Integer updateMappingFacebookDpKeys(Integer keyId, Byte enabled, Byte updateInterval, Byte useImagePixel) {
        return mFacebookDpKeysDAO.updateMappingFacebookDpKeys(keyId, enabled, updateInterval, useImagePixel);
    }

    public Integer deleteFacebookPixelMapping(String id) {
        return mDataProviderFacebookPixelsDAO.deleteFacebookPixelMapping(id);
    }

    public Integer deleteFacebookKeyMapping(String id) {
        return mFacebookDpKeysDAO.deleteFacebookKeyMapping(id);
    }

}
