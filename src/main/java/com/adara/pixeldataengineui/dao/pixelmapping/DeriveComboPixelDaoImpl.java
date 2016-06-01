package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DeriveComboPixelDTO;
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
public class DeriveComboPixelDaoImpl implements DeriveComboPixelDao {
    private static final Log LOG = LogFactory.getLog(DeriveComboPixelDaoImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getMappings() throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getMappings" + "]";
        String query = "SELECT d.dp_key_id, d.advertiser_id, d.cp_id FROM marketplace.derive_combo_pixel_mappings d order by d.dp_key_id";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
            listMap = jdbcTemplate.queryForList(query);

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Map<String, Object> m : listMap) {
            DeriveComboPixelDTO deriveComboPixelDTO = new DeriveComboPixelDTO();
            deriveComboPixelDTO.setDp_key_id(Integer.valueOf(String.valueOf(m.get("dp_key_id"))));
            deriveComboPixelDTO.setAdvertiser_id(Integer.valueOf(String.valueOf(m.get("advertiser_id"))));
            deriveComboPixelDTO.setCp_id(Integer.valueOf(String.valueOf(m.get("cp_id"))));
            // convert Java object to JSON (Jackson)
            ObjectMapper mapper = new ObjectMapper();
            String tmp = "";
            try {
                tmp = mapper.writeValueAsString(deriveComboPixelDTO);
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

    public String getMapping(String dpKeyId) throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getMapping" + "]";
        String query = "SELECT d.dp_key_id, d.advertiser_id, d.cp_id FROM marketplace.derive_combo_pixel_mappings d where d.dp_key_id= ?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String result = null;
            result = jdbcTemplate.queryForObject(query, new Object[]{dpKeyId}, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    DeriveComboPixelDTO deriveComboPixelDTO = new DeriveComboPixelDTO();
                    deriveComboPixelDTO.setDp_key_id(rs.getInt("dp_key_id"));
                    deriveComboPixelDTO.setAdvertiser_id(rs.getInt("advertiser_id"));
                    deriveComboPixelDTO.setCp_id(rs.getInt("cp_id"));
                    // convert Java object to JSON (Jackson)
                    ObjectMapper mapper = new ObjectMapper();
                    String result = "";
                    try {
                        result = mapper.writeValueAsString(deriveComboPixelDTO);
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

    public Integer insertMapping(Integer dpKeyId, Integer advertiserId, Integer cpId) throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "insertMapping" + "]";
        String query = "insert into marketplace.derive_combo_pixel_mappings(dp_key_id, advertiser_id, cp_id) values(?, ?, ?)";
        Object[] args = new Object[]{dpKeyId, advertiserId, cpId};

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());
        int result = 0;
            result = jdbcTemplate.update(query, args);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public Integer updateMapping(Integer dpKeyId, Integer advertiserId, Integer cpId)throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateMapping" + "]";
        String query = "UPDATE marketplace.derive_combo_pixel_mappings SET " + "dp_key_id" + "=?" + "," + "advertiser_id" + "=?" + "," + "cp_id" + "=?" + " WHERE dp_key_id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{dpKeyId, advertiserId, cpId, dpKeyId};

        int result = 0;
            result = jdbcTemplate.update(query, args);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public Integer deleteMapping(String dpKeyId) throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteMapping" + "]";
        String query = "DELETE FROM marketplace.derive_combo_pixel_mappings WHERE dp_key_id =?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = 0;
            result = jdbcTemplate.update(query, dpKeyId);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }
}
