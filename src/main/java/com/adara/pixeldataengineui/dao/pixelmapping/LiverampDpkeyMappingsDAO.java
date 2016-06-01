package com.adara.pixeldataengineui.dao.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface LiverampDpkeyMappingsDAO {
    String getLiverampKeyMappings() throws Exception;

    String getLiverampKeyMapping(String id) throws Exception;

    Integer insertLiverampKeyMapping(Long liverampSegmentId, Integer liverampDpKeyId, String value) throws Exception;

    Integer updateLiverampKeyMapping(Long liverampSegmentId, Integer liverampDpKeyId, String value) throws Exception;

    Integer deleteLiverampKeyMapping(String id) throws Exception;
}
