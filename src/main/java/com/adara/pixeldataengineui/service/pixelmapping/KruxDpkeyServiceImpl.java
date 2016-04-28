package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.KruxDpkeyDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("kruxDpkeyService")
@Transactional
public class KruxDpkeyServiceImpl implements KruxDpkeyService {
    @Autowired
    private KruxDpkeyDAO mKruxDpkeyDAO;

    public String getMappings() {
        return mKruxDpkeyDAO.getMappings();
    }

    public String getMapping(String kruxSegmentId) {
        return mKruxDpkeyDAO.getMapping(kruxSegmentId);
    }

    public Integer insertMapping(String kruxSegmentId, Integer dpKeyId) {
        return mKruxDpkeyDAO.insertMapping(kruxSegmentId, dpKeyId);
    }

    public Integer updateMapping(String kruxSegmentId, Integer dpKeyId) {
        return mKruxDpkeyDAO.updateMapping(kruxSegmentId, dpKeyId);
    }

    public Integer deleteMapping(String kruxSegmentId) {
        return mKruxDpkeyDAO.deleteMapping(kruxSegmentId);
    }
}
