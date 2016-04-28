package com.adara.pixeldataengineui.dao.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface LiverampDpkeyMappingsDAO {
    String getLiverampKeyMappings();

    String getLiverampKeyMapping(String id);

    Integer insertLiverampKeyMapping(Long liverampSegmentId, Integer liverampDpKeyId, String value);

    Integer updateLiverampKeyMapping(Long liverampSegmentId, Integer liverampDpKeyId, String value);

    Integer deleteLiverampKeyMapping(String id);
}
