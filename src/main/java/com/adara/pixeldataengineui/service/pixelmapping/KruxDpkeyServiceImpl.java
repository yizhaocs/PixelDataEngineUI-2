package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.KruxDpkeyDAO;
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

    public String getMappings() throws Exception {
        return mKruxDpkeyDAO.getMappings();
    }

    public String getMapping(String kruxSegmentId) throws Exception {
        return mKruxDpkeyDAO.getMapping(kruxSegmentId);
    }

    public Integer insertMapping(String kruxSegmentId, Integer dpKeyId) throws Exception {
        return mKruxDpkeyDAO.insertMapping(kruxSegmentId, dpKeyId);
    }

    public Integer updateMapping(String kruxSegmentId, Integer dpKeyId) throws Exception {
        return mKruxDpkeyDAO.updateMapping(kruxSegmentId, dpKeyId);
    }

    public Integer deleteMapping(String kruxSegmentId) throws Exception {
        return mKruxDpkeyDAO.deleteMapping(kruxSegmentId);
    }
}
