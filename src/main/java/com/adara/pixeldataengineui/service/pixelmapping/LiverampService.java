package com.adara.pixeldataengineui.service.pixelmapping;

import java.math.BigInteger;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface LiverampService {
    String getLiverampDpMappings();

    String getLiverampKeyMappings();

    String getLiverampDpMapping(String id);

    String getLiverampKeyMapping(String id);

    Integer insertLiverampDpMapping(String dpName, Integer liverampDpId, BigInteger thresholdMb);

    Integer insertLiverampKeyMapping(Long liverampSegmentId, Integer liverampDpKeyId, String value);

    Integer updateLiverampDpMapping(String dpName, Integer liverampDpId, BigInteger thresholdMb);

    Integer updateLiverampKeyMapping(Long liverampSegmentId, Integer liverampDpKeyId, String value);

    Integer deleteLiverampDpMapping(String id);

    Integer deleteLiverampKeyMapping(String id);

}
