package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.AdobeDpkeyMappingDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("adobeService")
@Transactional
public class AdobeServiceImpl implements AdobeService {
    @Autowired
    private AdobeDpkeyMappingDAO mAdobeDpkeyMappingDAO;

    public String getMappings() throws Exception {
        return mAdobeDpkeyMappingDAO.getMappings();
    }

    public String getMapping(String id) throws Exception {
        return mAdobeDpkeyMappingDAO.getMapping(id);
    }

    public Integer insertMapping(Integer adobeSegmentId, Integer adobeDpKeyId) throws Exception {
        return mAdobeDpkeyMappingDAO.insertMapping(adobeSegmentId, adobeDpKeyId);
    }

    public Integer updateMapping(Integer adobeSegmentId, Integer adobeDpKeyId) throws Exception {
        return mAdobeDpkeyMappingDAO.updateMapping(adobeSegmentId, adobeDpKeyId);
    }

    public Integer deleteMapping(String id) throws Exception {
        return mAdobeDpkeyMappingDAO.deleteMapping(id);
    }
}
