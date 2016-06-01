package com.adara.pixeldataengineui.dao.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface DataProvidersDAO {
    String getFacebookDpMappings() throws Exception;

    String getFacebookDpMapping(String id) throws Exception;

    Integer updateMappingDataProviders(Integer id, String name, Byte syncFacebook) throws Exception;
}
