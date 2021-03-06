package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.LiverampDpMappingsDTO;
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
public class LiverampDpMappingsDAOImpl implements LiverampDpMappingsDAO {
    private static final Log LOG = LogFactory.getLog(LiverampDpMappingsDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getLiverampDpMappings() throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getLiverampDpMappings" + "]";
        String query = "SELECT a.dp_name, a.dp_id, a.threshold_mb FROM marketplace.liveramp_dp_mappings a order by a.dp_name";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Map<String, Object> m : listMap) {
            LiverampDpMappingsDTO mLiverampDpMappingsDTO = new LiverampDpMappingsDTO();
            mLiverampDpMappingsDTO.setDp_name(String.valueOf(m.get("dp_name")));
            mLiverampDpMappingsDTO.setDp_id(Integer.valueOf(String.valueOf(m.get("dp_id"))));
            mLiverampDpMappingsDTO.setThreshold_mb(Long.valueOf(String.valueOf(m.get("threshold_mb"))));
            // convert Java object to JSON (Jackson)
            ObjectMapper mapper = new ObjectMapper();
            String tmp = "";
            try {
                tmp = mapper.writeValueAsString(mLiverampDpMappingsDTO);
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

    public String getLiverampDpMapping(String id) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getLiverampDpMapping" + "]";
        String query = "SELECT a.dp_name, a.dp_id, a.threshold_mb FROM marketplace.liveramp_dp_mappings a where a.dp_name=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String result = null;
        result = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                LiverampDpMappingsDTO mLiverampDpMappingsDTO = new LiverampDpMappingsDTO();
                mLiverampDpMappingsDTO.setDp_name(rs.getString("dp_name"));
                mLiverampDpMappingsDTO.setDp_id(rs.getInt("dp_id"));
                mLiverampDpMappingsDTO.setThreshold_mb(rs.getLong("threshold_mb"));

                // convert Java object to JSON (Jackson)
                ObjectMapper mapper = new ObjectMapper();
                String result = "";
                try {
                    result = mapper.writeValueAsString(mLiverampDpMappingsDTO);
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

    public Integer insertLiverampDpMapping(String dpName, Integer liverampDpId, BigInteger thresholdMb) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "insertLiverampDpMapping" + "]";

        String query = "insert into liveramp_dp_mappings(dp_name, dp_id, threshold_mb) values(?, ?, ?)";
        Object[] args = new Object[]{dpName, liverampDpId, thresholdMb};

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());
        int result = 0;
        result = jdbcTemplate.update(query, args);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public Integer updateLiverampDpMapping(String dpName, Integer liverampDpId, BigInteger thresholdMb) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateLiverampDpMapping" + "]";
        String query = "UPDATE marketplace.liveramp_dp_mappings SET " + "dp_name" + "=?" + "," + "dp_id" + "=?" + "," + "threshold_mb" + "=?" + " WHERE dp_name=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{dpName, liverampDpId, thresholdMb, dpName};
        Integer result = 0;
        result = jdbcTemplate.update(query, args);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }


    public Integer deleteLiverampDpMapping(String id) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteLiverampDpMapping" + "]";
        String query = "DELETE FROM marketplace.liveramp_dp_mappings WHERE dp_name= ?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = 0;
        result = jdbcTemplate.update(query, id);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }
}
