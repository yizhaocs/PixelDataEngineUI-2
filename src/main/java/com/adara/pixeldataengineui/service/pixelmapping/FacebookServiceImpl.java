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

    public Integer insertMappingDataProviderFacebookPixels(Integer dp_id, BigInteger facebook_pixel_id) {
        return mDataProviderFacebookPixelsDAO.insertMappingDataProviderFacebookPixels(dp_id, facebook_pixel_id);
    }

    public Integer insertMappingFacebookDpKeys(Integer key_id, Byte enabled, Byte update_interval, Byte use_image_pixel) {
        return mFacebookDpKeysDAO.insertMappingFacebookDpKeys(key_id, enabled, update_interval, use_image_pixel);
    }

    public Integer updateMappingDataProviderFacebookPixels(Integer dp_id, BigInteger facebook_pixel_id) {
        return mDataProviderFacebookPixelsDAO.updateMappingDataProviderFacebookPixels(dp_id, facebook_pixel_id);
    }

    public Integer updateMappingDataProviders(Integer id, String name, Byte sync_facebook) {
        return mDataProvidersDAO.updateMappingDataProviders(id, name, sync_facebook);
    }

    public Integer updateMappingFacebookDpKeys(Integer key_id, Byte enabled, Byte update_interval, Byte use_image_pixel) {
        return mFacebookDpKeysDAO.updateMappingFacebookDpKeys(key_id, enabled, update_interval, use_image_pixel);
    }

    public Integer deleteFacebookPixelMapping(String id) {
        return mDataProviderFacebookPixelsDAO.deleteFacebookPixelMapping(id);
    }

    public Integer deleteFacebookKeyMapping(String id) {
        return mFacebookDpKeysDAO.deleteFacebookKeyMapping(id);
    }

}
