package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DataProviderFacebookPixelsDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class DataProviderFacebookPixelsDAOImpl implements DataProviderFacebookPixelsDAO {
    private static final Log LOG = LogFactory.getLog(DataProviderFacebookPixelsDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getFacebookPixelMappings() {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getFacebookPixelMappings");
        String query = "SELECT a.dp_id, a.facebook_pixel_id FROM marketplace.data_provider_facebook_pixels a order by a.dp_id";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getFacebookPixelMappings" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        try {
            listMap = jdbcTemplate.queryForList(query);
        } catch (Exception e) {

            LOG.error("Failed to execute sql query", e);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Map<String, Object> m : listMap) {
            DataProviderFacebookPixelsDTO mDataProviderFacebookPixelsDTO = new DataProviderFacebookPixelsDTO();
            mDataProviderFacebookPixelsDTO.setDp_id(Integer.valueOf(String.valueOf(m.get("dp_id"))));
            mDataProviderFacebookPixelsDTO.setFacebook_pixel_id(Long.valueOf(String.valueOf(m.get("facebook_pixel_id"))));
            // convert Java object to JSON (Jackson)
            ObjectMapper mapper = new ObjectMapper();
            String tmp = "";
            try {
                tmp = mapper.writeValueAsString(mDataProviderFacebookPixelsDTO);
            } catch (Exception e) {

                LOG.error("Failed to convert Java object to JSON", e);
            }
            sb.append(tmp + ",");
        }
        sb.deleteCharAt(sb.toString().length() - 1);
        sb.append("]");

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getFacebookPixelMappings" + "  ,method return -> " + sb.toString());

        return sb.toString();
    }

    public String getFacebookPixelMapping(String id) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getFacebookPixelMapping");
        String query = "SELECT a.dp_id, a.facebook_pixel_id FROM marketplace.data_provider_facebook_pixels a where a.dp_id= ?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getFacebookPixelMapping" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String result = null;
        try {
            result = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<String>() {

                @Override
                public String mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    DataProviderFacebookPixelsDTO mDataProviderFacebookPixelsDTO = new DataProviderFacebookPixelsDTO();
                    mDataProviderFacebookPixelsDTO.setDp_id(rs.getInt("dp_id"));
                    mDataProviderFacebookPixelsDTO.setFacebook_pixel_id(rs.getLong("facebook_pixel_id"));

                    // convert Java object to JSON (Jackson)
                    ObjectMapper mapper = new ObjectMapper();
                    String result = "";
                    try {
                        result = mapper.writeValueAsString(mDataProviderFacebookPixelsDTO);
                    } catch (Exception e) {

                        LOG.error("Failed to convert Java object to JSON", e);
                    }

                    return result;
                }
            });
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getRule" + "  ,method return -> " + result);

        return result;
    }

    public Integer insertMappingDataProviderFacebookPixels(Integer dp_id, BigInteger facebook_pixel_id) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertMappingDataProviderFacebookPixels");
        String query = "insert into data_provider_facebook_pixels(dp_id, facebook_pixel_id) values(?, ?)";
        Object[] args = new Object[]{dp_id, facebook_pixel_id};

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertMappingDataProviderFacebookPixels" + ", " + "Executing query -> " + query.toString());
        int result = 0;
        try {
            result = jdbcTemplate.update(query, args);
        } catch (Exception e) {

            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertMappingDataProviderFacebookPixels" + "  ,method return -> " + result);

        return result;
    }

    public Integer updateMappingDataProviderFacebookPixels(Integer dp_id, BigInteger facebook_pixel_id) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateMappingDataProviderFacebookPixels");
        String query = "UPDATE marketplace.data_provider_facebook_pixels SET " + "dp_id" + "=?" + "," + "facebook_pixel_id" + "=?" + " WHERE dp_id=?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateMappingDataProviderFacebookPixels" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{dp_id, facebook_pixel_id, dp_id};
        Integer result = 0;
        try {
            result = jdbcTemplate.update(query, args);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateMappingDataProviderFacebookPixels" + "  ,method return -> " + result);

        return result;
    }

    public Integer deleteFacebookPixelMapping(String id) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteFacebookPixelMapping");
        String query = "DELETE FROM marketplace.data_provider_facebook_pixels WHERE dp_id = ?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteFacebookPixelMapping" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = 0;
        try {
            result = jdbcTemplate.update(query, id);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteFacebookPixelMapping" + "  ,method return -> " + result);

        return result;
    }
}
