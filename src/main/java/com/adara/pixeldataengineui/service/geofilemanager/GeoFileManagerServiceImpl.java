package com.adara.pixeldataengineui.service.geofilemanager;

import com.adara.pixeldataengineui.dao.geofilemanager.LocationDAOImpl;
import com.adara.pixeldataengineui.model.frontend.requestbody.GeoFileManagerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yzhao on 7/21/16.
 */
@Service("geoFileManagerService")
@Transactional
public class GeoFileManagerServiceImpl implements GeoFileManagerService {
    @Autowired
    private LocationDAOImpl mLocationDAOImpl;


    public Integer append(GeoFileManagerRequest request) throws Exception {

        return mLocationDAOImpl.append(request);
    }

    public Integer override(GeoFileManagerRequest request) throws Exception {
        return mLocationDAOImpl.override(request);
    }
}
