package com.adara.pixeldataengineui.dao.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface DeriveComboPixelDao {
    String getMappings() throws Exception;

    String getMapping(String dpKeyId) throws Exception;

    Integer insertMapping(Integer dpKeyId, Integer advertiserId, Integer cpId) throws Exception;

    Integer updateMapping(Integer dpKeyId, Integer advertiserId, Integer cpId) throws Exception;

    Integer deleteMapping(String dpKeyId) throws Exception;
}
