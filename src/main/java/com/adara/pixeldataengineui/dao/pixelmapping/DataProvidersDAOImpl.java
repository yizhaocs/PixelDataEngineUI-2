package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DataProvidersDTO;
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
public class DataProvidersDAOImpl implements DataProvidersDAO {
    private static final Log LOG = LogFactory.getLog(DataProvidersDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getFacebookDpMappings() throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getFacebookDpMappings" + "]";
        String query = "SELECT a.id, a.name, a.sync_facebook FROM marketplace.data_providers a order by a.id";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);


        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Map<String, Object> m : listMap) {
            DataProvidersDTO mDataProvidersDTO = new DataProvidersDTO();
            mDataProvidersDTO.setId(Integer.valueOf(String.valueOf(m.get("id"))));
            mDataProvidersDTO.setName(String.valueOf(m.get("name")));
            mDataProvidersDTO.setSync_facebook(Boolean.valueOf(String.valueOf(m.get("sync_facebook"))));
            // convert Java object to JSON (Jackson)
            ObjectMapper mapper = new ObjectMapper();
            String tmp = "";
            tmp = mapper.writeValueAsString(mDataProvidersDTO);

            sb.append(tmp + ",");
        }
        sb.deleteCharAt(sb.toString().length() - 1);
        sb.append("]");

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + sb.toString());

        return sb.toString();
    }

    public String getFacebookDpMapping(String id) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getFacebookDpMapping" + "]";
        String query = "SELECT a.id, a.name, a.sync_facebook FROM marketplace.data_providers a where a.id= ?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String result = null;
        result = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                DataProvidersDTO mDataProvidersDTO = new DataProvidersDTO();
                mDataProvidersDTO.setId(rs.getInt("id"));
                mDataProvidersDTO.setName(rs.getString("name"));
                mDataProvidersDTO.setSync_facebook(rs.getBoolean("sync_facebook"));

                // convert Java object to JSON (Jackson)
                ObjectMapper mapper = new ObjectMapper();
                String result = "";
                try {
                    result = mapper.writeValueAsString(mDataProvidersDTO);
                } catch (Exception e) {

                    LOG.error("Failed to convert Java object to JSON", e);
                }

                LOG.info(LOG_HEADER + "  ,method return -> " + result);
                return result;
            }
        });

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public Integer updateMappingDataProviders(Integer id, String name, Byte syncFacebook) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateMappingDataProviders" + "]";
        String query = "UPDATE marketplace.data_providers SET " + "id" + "=?" + "," + "name" + "=?" + "," + "sync_facebook" + "=?" + " WHERE id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{id, name, syncFacebook, id};
        Integer result = 0;
        result = jdbcTemplate.update(query, args);


        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);
        return result;
    }
}
