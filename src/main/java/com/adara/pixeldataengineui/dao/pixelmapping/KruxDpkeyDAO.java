package com.adara.pixeldataengineui.dao.pixelmapping;

/**
 * Created by yzhao on 4/18/16.
 */
public interface KruxDpkeyDAO {
    String getMappings();

    String getMapping(String kruxSegmentId);

    Integer insertMapping(String kruxSegmentId, Integer dpKeyId);

    Integer updateMapping(String kruxSegmentId, Integer dpKeyId);

    Integer deleteMapping(String kruxSegmentId);
}
