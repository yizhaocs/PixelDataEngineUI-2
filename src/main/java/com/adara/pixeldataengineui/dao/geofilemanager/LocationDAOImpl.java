package com.adara.pixeldataengineui.dao.geofilemanager;

import com.adara.pixeldataengineui.model.frontend.requestbody.GeoFileManagerRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

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
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "append" + "]";
        String query = "insert into marketplace.adobe_dpkey_mapping(adobe_segment_id, dp_key_id) values(?, ?)";
        Object[] args = new Object[]{null, null};

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        int result = 0;
        result = jdbcTemplate.update(query, args);


        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }
    public Integer override(GeoFileManagerRequest request) throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "override" + "]";
        String query = "insert into marketplace.adobe_dpkey_mapping(adobe_segment_id, dp_key_id) values(?, ?)";
        Object[] args = new Object[]{null, null};

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        int result = 0;
        result = jdbcTemplate.update(query, args);


        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
}
