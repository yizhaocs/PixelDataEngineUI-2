package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.KruxDpkeyDTO;
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
 * Created by yzhao on 4/18/16.
 */
public class KruxDpkeyDAOImpl implements KruxDpkeyDAO {
    private static final Log LOG = LogFactory.getLog(KruxDpkeyDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getMappings() {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getMappings");
        String query = "SELECT k.krux_segment_id, k.dp_key_id FROM marketplace.krux_dpkey_mappings k order by k.krux_segment_id";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getMappings" + ", " + "Executing query -> " + query.toString());

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
            KruxDpkeyDTO kruxDpkeyDTO = new KruxDpkeyDTO();
            kruxDpkeyDTO.setKrux_segment_id(String.valueOf(m.get("krux_segment_id")));
            kruxDpkeyDTO.setDp_key_id(Integer.valueOf(String.valueOf(m.get("dp_key_id"))));
            // convert Java object to JSON (Jackson)
            ObjectMapper mapper = new ObjectMapper();
            String tmp = "";
            try {
                tmp = mapper.writeValueAsString(kruxDpkeyDTO);
            } catch (Exception e) {

                LOG.error("Failed to convert Java object to JSON", e);
            }
            sb.append(tmp + ",");

        }
        sb.deleteCharAt(sb.toString().length() - 1);
        sb.append("]");

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getMappings" + "  ,method return -> " + sb.toString());

        return sb.toString();
    }

    public String getMapping(String kruxSegmentId) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getMapping");
        String query = "SELECT k.krux_segment_id, k.dp_key_id FROM marketplace.krux_dpkey_mappings k where k.krux_segment_id= ?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getMapping" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String result = null;
        try {
            result = jdbcTemplate.queryForObject(query, new Object[]{kruxSegmentId}, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    KruxDpkeyDTO kruxDpkeyDTO = new KruxDpkeyDTO();
                    kruxDpkeyDTO.setKrux_segment_id(rs.getString("krux_segment_id"));
                    kruxDpkeyDTO.setDp_key_id(rs.getInt("dp_key_id"));
                    // convert Java object to JSON (Jackson)
                    ObjectMapper mapper = new ObjectMapper();
                    String result = "";
                    try {
                        result = mapper.writeValueAsString(kruxDpkeyDTO);
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
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getMapping" + "  ,method return -> " + result);

        return result;
    }

    public Integer insertMapping(String kruxSegmentId, Integer dpKeyId) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertMapping");
        String query = "insert into marketplace.krux_dpkey_mappings(krux_segment_id, dp_key_id) values(?, ?)";
        Object[] args = new Object[]{kruxSegmentId, dpKeyId};

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertMapping" + ", " + "Executing query -> " + query.toString());
        int result = 0;
        try {
            result = jdbcTemplate.update(query, args);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }
        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertMapping" + "  ,method return -> " + result);

        return result;
    }

    public Integer updateMapping(String kruxSegmentId, Integer dpKeyId) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateMapping");
        String query = "UPDATE marketplace.krux_dpkey_mappings SET " + "krux_segment_id" + "=?" + "," + "dp_key_id" + "=?" + " WHERE krux_segment_id=?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateMapping" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{kruxSegmentId, dpKeyId, kruxSegmentId};
        int result = 0;
        try {
            result = jdbcTemplate.update(query, args);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateMapping" + "  ,method return -> " + result);

        return result;
    }

    public Integer deleteMapping(String kruxSegmentId) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteMapping");
        String query = "DELETE FROM marketplace.krux_dpkey_mappings WHERE krux_segment_id =?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteMapping" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = 0;
        try {
            result = jdbcTemplate.update(query, kruxSegmentId);
        } catch (Exception e) {

            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteMapping" + "  ,method return -> " + result);

        return result;
    }
}
