package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.DeriveComboPixelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("deriveComboPixelService")
@Transactional
public class DeriveComboPixelServiceImpl implements DeriveComboPixelService {
    @Autowired
    private DeriveComboPixelDao mDeriveComboPixelDao;

    public String getMappings() throws Exception {
        return mDeriveComboPixelDao.getMappings();
    }

    public String getMapping(String dpKeyId) throws Exception {
        return mDeriveComboPixelDao.getMapping(dpKeyId);
    }

    public Integer insertMapping(Integer dpKeyId, Integer advertiserId, Integer cpId) throws Exception {
        return mDeriveComboPixelDao.insertMapping(dpKeyId, advertiserId, cpId);
    }

    public Integer updateMapping(Integer dpKeyId, Integer advertiserId, Integer cpId) throws Exception {
        return mDeriveComboPixelDao.updateMapping(dpKeyId, advertiserId, cpId);
    }

    public Integer deleteMapping(String dpKeyId) throws Exception {
        return mDeriveComboPixelDao.deleteMapping(dpKeyId);
    }
}
