package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.AdobeDpkeyMappingDTO;
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
public class AdobeDpkeyMappingDAOImpl implements AdobeDpkeyMappingDAO {
    private static final Log LOG = LogFactory.getLog(AdobeDpkeyMappingDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getMappings() {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getMappings");
        String query = "SELECT a.adobe_segment_id, a.dp_key_id FROM marketplace.adobe_dpkey_mapping a order by a.adobe_segment_id";
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
            AdobeDpkeyMappingDTO adobeDpkeyMappingDTO = new AdobeDpkeyMappingDTO();
            adobeDpkeyMappingDTO.setAdobe_segment_id(Integer.valueOf(String.valueOf(m.get("adobe_segment_id"))));
            adobeDpkeyMappingDTO.setDp_key_id(Integer.valueOf(String.valueOf(m.get("dp_key_id"))));

            // convert Java object to JSON (Jackson)
            ObjectMapper mapper = new ObjectMapper();
            String tmp = "";
            try {
                tmp = mapper.writeValueAsString(adobeDpkeyMappingDTO);
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

    public String getMapping(String id) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getMapping");
        String query = "SELECT a.adobe_segment_id, a.dp_key_id FROM marketplace.adobe_dpkey_mapping a where a.adobe_segment_id= ?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getMapping" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String result = null;
        try {
            result = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<String>() {

                @Override
                public String mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    AdobeDpkeyMappingDTO adobeDpkeyMappingDTO = new AdobeDpkeyMappingDTO();
                    adobeDpkeyMappingDTO.setAdobe_segment_id(rs.getInt("adobe_segment_id"));
                    adobeDpkeyMappingDTO.setDp_key_id(rs.getInt("dp_key_id"));
                    // convert Java object to JSON (Jackson)
                    ObjectMapper mapper = new ObjectMapper();
                    String result = "";
                    try {
                        result = mapper.writeValueAsString(adobeDpkeyMappingDTO);
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

    public Integer insertMapping(Integer adobeSegmentId, Integer adobeDpKeyId) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertMapping");
        String query = "insert into marketplace.adobe_dpkey_mapping(adobe_segment_id, dp_key_id) values(?, ?)";
        Object[] args = new Object[]{adobeSegmentId, adobeDpKeyId};

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

    public Integer updateMapping(Integer adobeSegmentId, Integer adobeDpKeyId) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateMapping");
        String query = "UPDATE marketplace.adobe_dpkey_mapping SET " + "adobe_segment_id" + "=?" + "," + "dp_key_id" + "=?" + " WHERE adobe_segment_id=?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateMapping" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{adobeSegmentId, adobeDpKeyId, adobeSegmentId};
        Integer result = 0;
        try {
            result = jdbcTemplate.update(query, args);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateMapping" + "  ,method return -> " + result);

        return result;
    }

    public Integer deleteMapping(String id) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteMapping");
        String query = "DELETE FROM marketplace.adobe_dpkey_mapping WHERE adobe_segment_id =?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteMapping" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = 0;
        try {
            result = jdbcTemplate.update(query, id);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteMapping" + "  ,method return -> " + result);

        return result;
    }
}
