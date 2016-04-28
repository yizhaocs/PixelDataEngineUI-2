package com.adara.pixeldataengineui.service.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface AdobeService {
    String getMappings();

    String getMapping(String id);

    Integer insertMapping(Integer adobeSegmentId, Integer adobeDpKeyId);

    Integer updateMapping(Integer adobeSegmentId, Integer adobeDpKeyId);

    Integer deleteMapping(String id);
}
