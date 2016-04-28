package com.adara.pixeldataengineui.dao.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface DeriveComboPixelDao {
    String getMappings();

    String getMapping(String dpKeyId);

    Integer insertMapping(Integer dpKeyId, Integer advertiserId, Integer cpId);

    Integer updateMapping(Integer dpKeyId, Integer advertiserId, Integer cpId);

    Integer deleteMapping(String dpKeyId);
}
