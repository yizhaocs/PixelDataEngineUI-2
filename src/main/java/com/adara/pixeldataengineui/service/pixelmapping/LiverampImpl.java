package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.LiverampDpMappingsDAO;
import com.adara.pixeldataengineui.dao.pixelmapping.LiverampDpkeyMappingsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("liverampService")
@Transactional
public class LiverampImpl implements LiverampService {
    @Autowired
    private LiverampDpMappingsDAO mLiverampDpMappingsDAO;
    @Autowired
    private LiverampDpkeyMappingsDAO mLiverampDpkeyMappingsDAO;

    public String getLiverampDpMappings() throws Exception {
        return mLiverampDpMappingsDAO.getLiverampDpMappings();
    }

    public String getLiverampKeyMappings() throws Exception {
        return mLiverampDpkeyMappingsDAO.getLiverampKeyMappings();
    }

    public String getLiverampDpMapping(String id) throws Exception {
        return mLiverampDpMappingsDAO.getLiverampDpMapping(id);
    }

    public String getLiverampKeyMapping(String id) throws Exception {
        return mLiverampDpkeyMappingsDAO.getLiverampKeyMapping(id);
    }

    public Integer insertLiverampDpMapping(String dpName, Integer liverampDpId, BigInteger thresholdMb) throws Exception {
        return mLiverampDpMappingsDAO.insertLiverampDpMapping(dpName, liverampDpId, thresholdMb);
    }

    public Integer insertLiverampKeyMapping(Long liverampSegmentId, Integer liverampDpKeyId, String value) throws Exception {
        return mLiverampDpkeyMappingsDAO.insertLiverampKeyMapping(liverampSegmentId, liverampDpKeyId, value);
    }

    public Integer updateLiverampDpMapping(String dpName, Integer liverampDpId, BigInteger thresholdMb) throws Exception {
        return mLiverampDpMappingsDAO.updateLiverampDpMapping(dpName, liverampDpId, thresholdMb);
    }

    public Integer updateLiverampKeyMapping(Long liverampSegmentId, Integer liverampDpKeyId, String value) throws Exception {
        return mLiverampDpkeyMappingsDAO.updateLiverampKeyMapping(liverampSegmentId, liverampDpKeyId, value);
    }

    public Integer deleteLiverampDpMapping(String id) throws Exception {
        return mLiverampDpMappingsDAO.deleteLiverampDpMapping(id);
    }

    public Integer deleteLiverampKeyMapping(String id) throws Exception {
        return mLiverampDpkeyMappingsDAO.deleteLiverampKeyMapping(id);
    }
}
