package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DbmConversionPixelMappingsDTO;
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
public class DbmConversionPixelMappingsDAOImpl implements DbmConversionPixelMappingsDAO{
    private static final Log LOG = LogFactory.getLog(DbmConversionPixelMappingsDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getMappings() {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getMappings");
        String query = "SELECT d.conversion_pixel_id, d.dbm_url FROM marketplace.dbm_conversion_pixel_mappings d order by d.conversion_pixel_id";
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
            DbmConversionPixelMappingsDTO mDbmConversionPixelMappingsDTO = new DbmConversionPixelMappingsDTO();
            mDbmConversionPixelMappingsDTO.setConversion_pixel_id(Integer.valueOf(String.valueOf(m.get("conversion_pixel_id"))));
            mDbmConversionPixelMappingsDTO.setDbm_url(String.valueOf(m.get("dbm_url")));

            // convert Java object to JSON (Jackson)
            ObjectMapper mapper = new ObjectMapper();
            String tmp = "";
            try {
                tmp = mapper.writeValueAsString(mDbmConversionPixelMappingsDTO);
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

    public String getMapping(String conversionPixelId) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getMapping");
        String query = "SELECT d.conversion_pixel_id, d.dbm_url FROM marketplace.dbm_conversion_pixel_mappings d where d.conversion_pixel_id= ?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getMapping" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String result = null;
        try {
            result = jdbcTemplate.queryForObject(query, new Object[]{conversionPixelId}, new RowMapper<String>() {

                @Override
                public String mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    DbmConversionPixelMappingsDTO mDbmConversionPixelMappingsDTO = new DbmConversionPixelMappingsDTO();
                    mDbmConversionPixelMappingsDTO.setConversion_pixel_id(rs.getInt("conversion_pixel_id"));
                    mDbmConversionPixelMappingsDTO.setDbm_url(rs.getString("dbm_url"));
                    // convert Java object to JSON (Jackson)
                    ObjectMapper mapper = new ObjectMapper();
                    String result = "";
                    try {
                        result = mapper.writeValueAsString(mDbmConversionPixelMappingsDTO);
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

    public Integer insertMapping(Integer conversionPixelId, String dbmUrl) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertMapping");
        String query = "insert into dbm_conversion_pixel_mappings(conversion_pixel_id, dbm_url) values(?, ?)";
        Object[] args = new Object[]{conversionPixelId, httpsChecker(dbmUrl)};

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

    public Integer updateMapping(Integer conversionPixelId, String dbmUrl) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateMapping");
        String query = "UPDATE marketplace.dbm_conversion_pixel_mappings SET " + "conversion_pixel_id" + "=?" + "," + "dbm_url" + "=?" + " WHERE conversion_pixel_id=?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateMapping" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{conversionPixelId, dbmUrl, conversionPixelId};
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

    public Integer deleteMapping(String conversionPixelId) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteMapping");
        String query = "DELETE FROM marketplace.dbm_conversion_pixel_mappings WHERE conversion_pixel_id =?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteMapping" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = 0;
        try {
            result = jdbcTemplate.update(query, conversionPixelId);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteMapping" + "  ,method return -> " + result);

        return result;
    }


    /*
    * This method will check the incoming url and do the following modification[if the url without "https://" or "http://" then added "https://" to
    * the front of url to the incoming url:
    * google.com -> https://google.com
    * www.google.com -> https://www.google.com
    * */
    //
    //
    // https://google.com -> https://google.com
    // http://google.com -> http://google.com
    private String httpsChecker(String url){
        if(url == null || url.length() == 0 || (url.length() > 7 && url.substring(0,8).equals("https://")) || (url.length() > 6 && url.substring(0,7).equals("http://")) ){
            return url;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("https://");
        sb.append(url);

        return sb.toString();
    }
}
