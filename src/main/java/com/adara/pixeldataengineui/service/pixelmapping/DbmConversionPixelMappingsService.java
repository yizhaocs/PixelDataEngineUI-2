package com.adara.pixeldataengineui.service.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface DbmConversionPixelMappingsService {
    String getMappings() throws Exception;

    String getMapping(String conversionPixelId) throws Exception;

    Integer insertMapping(Integer conversionPixelId, String dbmUrl) throws Exception;

    Integer updateMapping(Integer conversionPixelId, String dbmUrl) throws Exception;

    Integer deleteMapping(String conversionPixelId) throws Exception;
}
