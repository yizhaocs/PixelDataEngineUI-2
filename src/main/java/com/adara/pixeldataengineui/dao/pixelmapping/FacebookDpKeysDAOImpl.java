package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.FacebookDpKeysDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class FacebookDpKeysDAOImpl implements FacebookDpKeysDAO {
    private static final Log LOG = LogFactory.getLog(FacebookDpKeysDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getFacebookKeyMappings() {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getFacebookKeyMappings");
        String query = "SELECT a.key_id, a.enabled, a.update_interval, a.use_image_pixel FROM marketplace.facebook_dp_keys a order by a.key_id";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getFacebookKeyMappings" + ", " + "Executing query -> " + query.toString());

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
            FacebookDpKeysDTO mFacebookDpKeysDTO = new FacebookDpKeysDTO();
            mFacebookDpKeysDTO.setKey_id(Integer.valueOf(String.valueOf(m.get("key_id"))));
            mFacebookDpKeysDTO.setEnabled(Byte.valueOf(String.valueOf(m.get("enabled"))));
            mFacebookDpKeysDTO.setUpdate_interval(m.get("update_interval") == null ? null : Byte.valueOf(String.valueOf(m.get("update_interval"))));
            mFacebookDpKeysDTO.setUse_image_pixel(Boolean.valueOf(String.valueOf(m.get("use_image_pixel"))));
            // convert Java object to JSON (Jackson)
            ObjectMapper mapper = new ObjectMapper();
            String tmp = "";
            try {
                tmp = mapper.writeValueAsString(mFacebookDpKeysDTO);
            } catch (Exception e) {

                LOG.error("Failed to convert Java object to JSON", e);
            }
            sb.append(tmp + ",");
        }
        sb.deleteCharAt(sb.toString().length() - 1);
        sb.append("]");

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getFacebookKeyMappings" + "  ,method return -> " + sb.toString());

        return sb.toString();
    }

    public String getFacebookKeyMapping(String id) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getFacebookKeyMapping");
        String query = "SELECT a.key_id, a.enabled, a.update_interval, a.use_image_pixel FROM marketplace.facebook_dp_keys a where a.key_id=?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getFacebookKeyMapping" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String result = null;
        try {
            result = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<String>() {

                @Override
                public String mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    FacebookDpKeysDTO mFacebookDpKeysDTO = new FacebookDpKeysDTO();
                    mFacebookDpKeysDTO.setKey_id(rs.getInt("key_id"));
                    mFacebookDpKeysDTO.setEnabled(rs.getByte("enabled"));
                    String updateInterval = rs.getString("update_interval");
                    // Update Interval is allow null
                    if(updateInterval.equals("null") == false){
                        mFacebookDpKeysDTO.setUpdate_interval(rs.getByte("update_interval"));
                    }else{
                        mFacebookDpKeysDTO.setUpdate_interval(null);
                    }

                    mFacebookDpKeysDTO.setUse_image_pixel(rs.getBoolean("use_image_pixel"));

                    // convert Java object to JSON (Jackson)
                    ObjectMapper mapper = new ObjectMapper();
                    String result = "";
                    try {
                        result = mapper.writeValueAsString(mFacebookDpKeysDTO);
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

    public Integer insertMappingFacebookDpKeys(Integer key_id, Byte enabled, Byte update_interval, Byte use_image_pixel) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertMappingFacebookDpKeys");
        String query = "insert into facebook_dp_keys(key_id, enabled, update_interval, use_image_pixel) values(?, ?, ?, ?)";
        Object[] args = new Object[]{key_id, enabled, update_interval, use_image_pixel};

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertMappingFacebookDpKeys" + ", " + "Executing query -> " + query.toString());
        int result = 0;
        try {
            result = jdbcTemplate.update(query, args);
        } catch (Exception e) {

            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertMappingFacebookDpKeys" + "  ,method return -> " + result);

        return result;
    }

    public Integer updateMappingFacebookDpKeys(Integer key_id, Byte enabled, Byte update_interval, Byte use_image_pixel) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateMappingFacebookDpKeys");
        String query = "UPDATE marketplace.facebook_dp_keys SET " + "key_id" + "=?" + "," + "enabled" + "=?" + "," + "update_interval" + "=?" + "," + "use_image_pixel" + "=?" + " WHERE key_id=?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateMappingFacebookDpKeys" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{key_id, enabled, update_interval, use_image_pixel, key_id};
        // Integer result = query.executeUpdate();
        Integer result = 0;
        try {
            result = jdbcTemplate.update(query, args);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateMappingFacebookDpKeys" + "  ,method return -> " + result);

        return result;
    }

    public Integer deleteFacebookKeyMapping(String id) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteFacebookKeyMapping");
        String query = "DELETE FROM marketplace.facebook_dp_keys WHERE key_id =?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteFacebookKeyMapping" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = 0;
        try {
            result = jdbcTemplate.update(query, id);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteFacebookKeyMapping" + "  ,method return -> " + result);

        return result;
    }
}
