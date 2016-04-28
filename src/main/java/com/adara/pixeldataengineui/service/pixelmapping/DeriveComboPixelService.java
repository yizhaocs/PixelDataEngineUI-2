package com.adara.pixeldataengineui.service.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface DeriveComboPixelService {
    String getMappings();

    String getMapping(String dpKeyId);

    Integer insertMapping(Integer dpKeyId, Integer advertiserId, Integer cpId);

    Integer updateMapping(Integer dpKeyId, Integer advertiserId, Integer cpId);

    Integer deleteMapping(String dpKeyId);
}
