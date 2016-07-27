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

    public String getFacebookKeyMappings() throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getFacebookKeyMappings" + "]";
        String query = "SELECT a.key_id, a.enabled, a.update_interval, a.use_image_pixel FROM marketplace.facebook_dp_keys a order by a.key_id";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);

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
            LOG.debug(LOG_HEADER + "  ,method return -> " + sb.toString());

        return sb.toString();
    }

    public String getFacebookKeyMapping(String id) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getFacebookKeyMapping" + "]";
        String query = "SELECT a.key_id, a.enabled, a.update_interval, a.use_image_pixel FROM marketplace.facebook_dp_keys a where a.key_id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String result = null;
        result = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                FacebookDpKeysDTO mFacebookDpKeysDTO = new FacebookDpKeysDTO();
                mFacebookDpKeysDTO.setKey_id(rs.getInt("key_id"));
                mFacebookDpKeysDTO.setEnabled(rs.getByte("enabled"));
                String updateInterval = rs.getString("update_interval");
                // Update Interval is allow null
                if (updateInterval != null) {
                    mFacebookDpKeysDTO.setUpdate_interval(rs.getByte("update_interval"));
                } else {
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

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public Integer insertMappingFacebookDpKeys(Integer keyId, Byte enabled, Byte updateInterval, Byte useImagePixel) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "insertMappingFacebookDpKeys" + "]";
        String query = "insert into facebook_dp_keys(key_id, enabled, update_interval, use_image_pixel) values(?, ?, ?, ?)";
        if (updateInterval == -1) {
            updateInterval = null;
        }

        Object[] args = new Object[]{keyId, enabled, updateInterval, useImagePixel};

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());
        int result = 0;
        result = jdbcTemplate.update(query, args);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public Integer updateMappingFacebookDpKeys(Integer keyId, Byte enabled, Byte updateInterval, Byte useImagePixel) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateMappingFacebookDpKeys" + "]";
        String query = "UPDATE marketplace.facebook_dp_keys SET " + "key_id" + "=?" + "," + "enabled" + "=?" + "," + "update_interval" + "=?" + "," + "use_image_pixel" + "=?" + " WHERE key_id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        if (updateInterval == -1) {
            updateInterval = null;
        }

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{keyId, enabled, updateInterval, useImagePixel, keyId};
        // Integer result = query.executeUpdate();
        Integer result = 0;
        result = jdbcTemplate.update(query, args);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public Integer deleteFacebookKeyMapping(String id) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteFacebookKeyMapping" + "]";
        String query = "DELETE FROM marketplace.facebook_dp_keys WHERE key_id =?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = 0;
        result = jdbcTemplate.update(query, id);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }
}
