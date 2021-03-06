package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.LiverampDpkeyMappingsDTO;
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
public class LiverampDpkeyMappingsDAOImpl implements LiverampDpkeyMappingsDAO {
    private static final Log LOG = LogFactory.getLog(LiverampDpkeyMappingsDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getLiverampKeyMappings() throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getLiverampKeyMappings" + "]";
        String query = "SELECT a.liveramp_segment_id, a.dp_key_id, a.value FROM marketplace.liveramp_dpkey_mappings a order by a.dp_key_id";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Map<String, Object> m : listMap) {
            LiverampDpkeyMappingsDTO mLiverampDpkeyMappingsDTO = new LiverampDpkeyMappingsDTO();
            mLiverampDpkeyMappingsDTO.setLiveramp_segment_id(Long.valueOf(String.valueOf(m.get("liveramp_segment_id"))));
            mLiverampDpkeyMappingsDTO.setDp_key_id(Integer.valueOf(String.valueOf(m.get("dp_key_id"))));
            mLiverampDpkeyMappingsDTO.setValue(String.valueOf(m.get("value")));
            // convert Java object to JSON (Jackson)
            ObjectMapper mapper = new ObjectMapper();
            String tmp = "";
            try {
                tmp = mapper.writeValueAsString(mLiverampDpkeyMappingsDTO);
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

    public String getLiverampKeyMapping(String id) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getLiverampKeyMapping" + "]";
        String query = "SELECT a.liveramp_segment_id, a.dp_key_id, a.value FROM marketplace.liveramp_dpkey_mappings a where a.liveramp_segment_id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String result = null;
        result = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                LiverampDpkeyMappingsDTO mLiverampDpkeyMappingsDTO = new LiverampDpkeyMappingsDTO();
                mLiverampDpkeyMappingsDTO.setLiveramp_segment_id(rs.getLong("liveramp_segment_id"));
                mLiverampDpkeyMappingsDTO.setDp_key_id(rs.getInt("dp_key_id"));
                mLiverampDpkeyMappingsDTO.setValue(rs.getString("value"));

                // convert Java object to JSON (Jackson)
                ObjectMapper mapper = new ObjectMapper();
                String result = "";
                try {
                    result = mapper.writeValueAsString(mLiverampDpkeyMappingsDTO);
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

    public Integer insertLiverampKeyMapping(Long liverampSegmentId, Integer liverampDpKeyId, String value) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "insertLiverampKeyMapping" + "]";
        String query = "insert into liveramp_dpkey_mappings(liveramp_segment_id, dp_key_id, value) values(?, ?, ?)";
        Object[] args = new Object[]{liverampSegmentId, liverampDpKeyId, value};

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());
        int result = 0;
        result = jdbcTemplate.update(query, args);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public Integer updateLiverampKeyMapping(Long liverampSegmentId, Integer liverampDpKeyId, String value) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateLiverampKeyMapping" + "]";
        String query = "UPDATE marketplace.liveramp_dpkey_mappings SET " + "liveramp_segment_id" + "=?" + "," + "dp_key_id" + "=?" + "," + "value" + "=?" + " WHERE liveramp_segment_id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{liverampSegmentId, liverampDpKeyId, value, liverampSegmentId};
        Integer result = 0;
        result = jdbcTemplate.update(query, args);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);
        return result;
    }

    public Integer deleteLiverampKeyMapping(String id) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteLiverampKeyMapping" + "]";
        String query = "DELETE FROM marketplace.liveramp_dpkey_mappings WHERE liveramp_segment_id = ? ";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = 0;
        result = jdbcTemplate.update(query, id);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }
}
