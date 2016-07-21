package com.adara.pixeldataengineui.dao.geofilemanager;

import com.adara.pixeldataengineui.model.frontend.requestbody.GeoFileManagerRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.sql.DataSource;

/**
 * Created by yzhao on 7/21/16.
 */
public class LocationDAOImpl implements  LocationDAO {
    private static final Log LOG = LogFactory.getLog(LocationDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Integer append(GeoFileManagerRequest request) throws Exception{
        return 33;
    }
    public Integer override(GeoFileManagerRequest request) throws Exception{
        return 33;
    }
}
