package com.adara.pixeldataengineui.dao.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface DataProvidersDAO {
    String getFacebookDpMappings();

    String getFacebookDpMapping(String id);

    Integer updateMappingDataProviders(Integer id, String name, Byte sync_facebook);
}
