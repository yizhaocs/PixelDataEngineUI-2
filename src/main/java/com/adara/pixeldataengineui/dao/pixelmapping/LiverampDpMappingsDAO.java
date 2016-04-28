package com.adara.pixeldataengineui.dao.pixelmapping;

import java.math.BigInteger;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface LiverampDpMappingsDAO {
    String getLiverampDpMappings();

    String getLiverampDpMapping(String id);

    Integer insertLiverampDpMapping(String dpName, Integer liverampDpId, BigInteger thresholdMb);

    Integer updateLiverampDpMapping(String dpName, Integer liverampDpId, BigInteger thresholdMb);

    Integer deleteLiverampDpMapping(String id);
}
