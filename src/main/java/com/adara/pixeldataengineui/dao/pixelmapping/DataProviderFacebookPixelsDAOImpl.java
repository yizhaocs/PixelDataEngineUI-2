package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DataProviderFacebookPixelsDTO;
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
public class DataProviderFacebookPixelsDAOImpl implements DataProviderFacebookPixelsDAO {
    private static final Log LOG = LogFactory.getLog(DataProviderFacebookPixelsDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getFacebookPixelMappings() throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getFacebookPixelMappings" + "]";
        String query = "SELECT a.dp_id, a.facebook_pixel_id FROM marketplace.data_provider_facebook_pixels a order by a.dp_id";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
            listMap = jdbcTemplate.queryForList(query);


        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Map<String, Object> m : listMap) {
            DataProviderFacebookPixelsDTO mDataProviderFacebookPixelsDTO = new DataProviderFacebookPixelsDTO();
            mDataProviderFacebookPixelsDTO.setDp_id(Integer.valueOf(String.valueOf(m.get("dp_id"))));
            mDataProviderFacebookPixelsDTO.setFacebook_pixel_id(Long.valueOf(String.valueOf(m.get("facebook_pixel_id"))));
            // convert Java object to JSON (Jackson)
            ObjectMapper mapper = new ObjectMapper();
            String tmp = "";
                tmp = mapper.writeValueAsString(mDataProviderFacebookPixelsDTO);

            sb.append(tmp + ",");
        }
        sb.deleteCharAt(sb.toString().length() - 1);
        sb.append("]");

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + sb.toString());

        return sb.toString();
    }

    public String getFacebookPixelMapping(String id) throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getFacebookPixelMapping" + "]";
        String query = "SELECT a.dp_id, a.facebook_pixel_id FROM marketplace.data_provider_facebook_pixels a where a.dp_id= ?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String result = null;
            result = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<String>() {

                @Override
                public String mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    DataProviderFacebookPixelsDTO mDataProviderFacebookPixelsDTO = new DataProviderFacebookPixelsDTO();
                    mDataProviderFacebookPixelsDTO.setDp_id(rs.getInt("dp_id"));
                    mDataProviderFacebookPixelsDTO.setFacebook_pixel_id(rs.getLong("facebook_pixel_id"));

                    // convert Java object to JSON (Jackson)
                    ObjectMapper mapper = new ObjectMapper();
                    String result = "";
                    try {
                        result = mapper.writeValueAsString(mDataProviderFacebookPixelsDTO);
                    }catch (Exception e){
                        LOG.error("Failed to convert Java object to JSON", e);
                    }

                    return result;
                }
            });


        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public Integer insertMappingDataProviderFacebookPixels(Integer dpId, BigInteger facebookPixelId) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "insertMappingDataProviderFacebookPixels" + "]";
        String query = "insert into data_provider_facebook_pixels(dp_id, facebook_pixel_id) values(?, ?)";
        Object[] args = new Object[]{dpId, facebookPixelId};

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());
        int result = 0;
            result = jdbcTemplate.update(query, args);


        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public Integer updateMappingDataProviderFacebookPixels(Integer dpId, BigInteger facebookPixelId) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateMappingDataProviderFacebookPixels" + "]";
        String query = "UPDATE marketplace.data_provider_facebook_pixels SET " + "dp_id" + "=?" + "," + "facebook_pixel_id" + "=?" + " WHERE dp_id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{dpId, facebookPixelId, dpId};
        Integer result = 0;
            result = jdbcTemplate.update(query, args);


        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public Integer deleteFacebookPixelMapping(String id) throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteFacebookPixelMapping" + "]";
        String query = "DELETE FROM marketplace.data_provider_facebook_pixels WHERE dp_id = ?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = 0;
            result = jdbcTemplate.update(query, id);


        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }
}
