package com.adara.pixeldataengineui.service.pixelmapping;

import java.math.BigInteger;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface LiverampService {
    String getLiverampDpMappings() throws Exception;

    String getLiverampKeyMappings() throws Exception;

    String getLiverampDpMapping(String id) throws Exception;

    String getLiverampKeyMapping(String id) throws Exception;

    Integer insertLiverampDpMapping(String dpName, Integer liverampDpId, BigInteger thresholdMb) throws Exception;

    Integer insertLiverampKeyMapping(Long liverampSegmentId, Integer liverampDpKeyId, String value) throws Exception;

    Integer updateLiverampDpMapping(String dpName, Integer liverampDpId, BigInteger thresholdMb) throws Exception;

    Integer updateLiverampKeyMapping(Long liverampSegmentId, Integer liverampDpKeyId, String value) throws Exception;

    Integer deleteLiverampDpMapping(String id) throws Exception;

    Integer deleteLiverampKeyMapping(String id) throws Exception;

}
