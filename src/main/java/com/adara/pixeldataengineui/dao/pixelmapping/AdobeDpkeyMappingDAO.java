package com.adara.pixeldataengineui.dao.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface AdobeDpkeyMappingDAO {
    String getMappings();

    String getMapping(String id);

    Integer insertMapping(Integer adobeSegmentId, Integer adobeDpKeyId);

    Integer updateMapping(Integer adobeSegmentId, Integer adobeDpKeyId);

    Integer deleteMapping(String id);
}
