package com.adara.pixeldataengineui.service.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface DeriveComboPixelService {
    String getMappings() throws Exception;

    String getMapping(String dpKeyId) throws Exception;

    Integer insertMapping(Integer dpKeyId, Integer advertiserId, Integer cpId) throws Exception;

    Integer updateMapping(Integer dpKeyId, Integer advertiserId, Integer cpId) throws Exception;

    Integer deleteMapping(String dpKeyId) throws Exception;
}
