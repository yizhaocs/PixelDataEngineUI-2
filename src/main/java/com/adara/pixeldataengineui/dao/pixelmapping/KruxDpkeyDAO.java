package com.adara.pixeldataengineui.dao.pixelmapping;

/**
 * Created by yzhao on 4/18/16.
 */
public interface KruxDpkeyDAO {
    String getMappings() throws Exception;

    String getMapping(String kruxSegmentId) throws Exception;

    Integer insertMapping(String kruxSegmentId, Integer dpKeyId) throws Exception;

    Integer updateMapping(String kruxSegmentId, Integer dpKeyId) throws Exception;

    Integer deleteMapping(String kruxSegmentId) throws Exception;
}
