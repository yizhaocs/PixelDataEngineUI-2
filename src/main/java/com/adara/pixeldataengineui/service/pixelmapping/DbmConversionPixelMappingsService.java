package com.adara.pixeldataengineui.service.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface DbmConversionPixelMappingsService {
    String getMappings();

    String getMapping(String conversionPixelId);

    Integer insertMapping(Integer conversionPixelId, String dbmUrl);

    Integer updateMapping(Integer conversionPixelId, String dbmUrl);

    Integer deleteMapping(String conversionPixelId);
}
