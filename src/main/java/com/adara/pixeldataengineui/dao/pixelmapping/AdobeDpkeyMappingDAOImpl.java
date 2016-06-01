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

    public String getMappings() throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getMappings" + "]";
        String query = "SELECT a.adobe_segment_id, a.dp_key_id FROM marketplace.adobe_dpkey_mapping a order by a.adobe_segment_id";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
            listMap = jdbcTemplate.queryForList(query);


        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Map<String, Object> m : listMap) {
            AdobeDpkeyMappingDTO adobeDpkeyMappingDTO = new AdobeDpkeyMappingDTO();
            adobeDpkeyMappingDTO.setAdobe_segment_id(Integer.valueOf(String.valueOf(m.get("adobe_segment_id"))));
            adobeDpkeyMappingDTO.setDp_key_id(Integer.valueOf(String.valueOf(m.get("dp_key_id"))));

            // convert Java object to JSON (Jackson)
            ObjectMapper mapper = new ObjectMapper();
            String tmp = "";
                tmp = mapper.writeValueAsString(adobeDpkeyMappingDTO);

            sb.append(tmp + ",");
        }
        sb.deleteCharAt(sb.toString().length() - 1);
        sb.append("]");

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + sb.toString());

        return sb.toString();
    }

    public String getMapping(String id) throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getMapping" + "]";
        String query = "SELECT a.adobe_segment_id, a.dp_key_id FROM marketplace.adobe_dpkey_mapping a where a.adobe_segment_id= ?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String result = null;
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

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public Integer insertMapping(Integer adobeSegmentId, Integer adobeDpKeyId) throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "insertMapping" + "]";
        String query = "insert into marketplace.adobe_dpkey_mapping(adobe_segment_id, dp_key_id) values(?, ?)";
        Object[] args = new Object[]{adobeSegmentId, adobeDpKeyId};

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        int result = 0;
            result = jdbcTemplate.update(query, args);


        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public Integer updateMapping(Integer adobeSegmentId, Integer adobeDpKeyId) throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateMapping" + "]";
        String query = "UPDATE marketplace.adobe_dpkey_mapping SET " + "adobe_segment_id" + "=?" + "," + "dp_key_id" + "=?" + " WHERE adobe_segment_id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{adobeSegmentId, adobeDpKeyId, adobeSegmentId};
        Integer result = 0;
            result = jdbcTemplate.update(query, args);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public Integer deleteMapping(String id) throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteMapping" + "]";
        String query = "DELETE FROM marketplace.adobe_dpkey_mapping WHERE adobe_segment_id =?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = 0;
            result = jdbcTemplate.update(query, id);


        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }
}
