package com.adara.pixeldataengineui.dao.geofilemanager;

import com.adara.pixeldataengineui.model.frontend.requestbody.GeoFileManagerRequest;

/**
 * Created by yzhao on 7/21/16.
 */
public interface LocationDAO {
    Integer append(GeoFileManagerRequest request) throws Exception;

    Integer override(GeoFileManagerRequest request) throws Exception;
}
