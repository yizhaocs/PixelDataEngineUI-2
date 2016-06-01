package com.adara.pixeldataengineui.service.pixelmapping;

/**
 * Created by yzhao on 4/18/16.
 */
public interface KruxDpkeyService {
    String getMappings() throws Exception;

    String getMapping(String kruxSegmentId) throws Exception;

    Integer insertMapping(String kruxSegmentId, Integer dpKeyId) throws Exception;

    Integer updateMapping(String kruxSegmentId, Integer dpKeyId) throws Exception;

    Integer deleteMapping(String kruxSegmentId) throws Exception;
}
