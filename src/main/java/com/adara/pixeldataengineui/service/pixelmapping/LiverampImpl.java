package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.LiverampDpMappingsDAO;
import com.adara.pixeldataengineui.dao.pixelmapping.LiverampDpkeyMappingsDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    public String getLiverampDpMappings() {
        return mLiverampDpMappingsDAO.getLiverampDpMappings();
    }

    public String getLiverampKeyMappings() {
        return mLiverampDpkeyMappingsDAO.getLiverampKeyMappings();
    }

    public String getLiverampDpMapping(String id) {
        return mLiverampDpMappingsDAO.getLiverampDpMapping(id);
    }

    public String getLiverampKeyMapping(String id) {
        return mLiverampDpkeyMappingsDAO.getLiverampKeyMapping(id);
    }

    public Integer insertLiverampDpMapping(String dpName, Integer liverampDpId, BigInteger thresholdMb) {
        return mLiverampDpMappingsDAO.insertLiverampDpMapping(dpName, liverampDpId, thresholdMb);
    }

    public Integer insertLiverampKeyMapping(Long liverampSegmentId, Integer liverampDpKeyId, String value) {
        return mLiverampDpkeyMappingsDAO.insertLiverampKeyMapping(liverampSegmentId, liverampDpKeyId, value);
    }

    public Integer updateLiverampDpMapping(String dpName, Integer liverampDpId, BigInteger thresholdMb) {
        return mLiverampDpMappingsDAO.updateLiverampDpMapping(dpName, liverampDpId, thresholdMb);
    }

    public Integer updateLiverampKeyMapping(Long liverampSegmentId, Integer liverampDpKeyId, String value) {
        return mLiverampDpkeyMappingsDAO.updateLiverampKeyMapping(liverampSegmentId, liverampDpKeyId, value);
    }

    public Integer deleteLiverampDpMapping(String id) {
        return mLiverampDpMappingsDAO.deleteLiverampDpMapping(id);
    }

    public Integer deleteLiverampKeyMapping(String id) {
        return mLiverampDpkeyMappingsDAO.deleteLiverampKeyMapping(id);
    }
}
