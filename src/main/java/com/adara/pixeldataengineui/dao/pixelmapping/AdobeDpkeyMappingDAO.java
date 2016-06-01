package com.adara.pixeldataengineui.dao.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface AdobeDpkeyMappingDAO {
    String getMappings() throws Exception;

    String getMapping(String id) throws Exception;

    Integer insertMapping(Integer adobeSegmentId, Integer adobeDpKeyId) throws Exception;

    Integer updateMapping(Integer adobeSegmentId, Integer adobeDpKeyId) throws Exception;

    Integer deleteMapping(String id) throws Exception;
}
