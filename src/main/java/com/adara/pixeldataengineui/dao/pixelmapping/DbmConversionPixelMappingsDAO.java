package com.adara.pixeldataengineui.dao.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface DbmConversionPixelMappingsDAO {
    String getMappings();

    String getMapping(String conversionPixelId);

    Integer insertMapping(Integer conversionPixelId, String dbmUrl);

    Integer updateMapping(Integer conversionPixelId, String dbmUrl);

    Integer deleteMapping(String conversionPixelId);
}
