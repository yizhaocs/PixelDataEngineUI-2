package com.adara.pixeldataengineui.dao.pixelmapping;

import java.math.BigInteger;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface LiverampDpMappingsDAO {
    String getLiverampDpMappings() throws Exception;

    String getLiverampDpMapping(String id) throws Exception;

    Integer insertLiverampDpMapping(String dpName, Integer liverampDpId, BigInteger thresholdMb) throws Exception;

    Integer updateLiverampDpMapping(String dpName, Integer liverampDpId, BigInteger thresholdMb) throws Exception;

    Integer deleteLiverampDpMapping(String id) throws Exception;
}
