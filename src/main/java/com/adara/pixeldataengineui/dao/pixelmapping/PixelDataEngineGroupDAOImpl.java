package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.PdeGroupsDTO;
import com.adara.pixeldataengineui.model.frontend.requestbody.GroupRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class PixelDataEngineGroupDAOImpl implements PixelDataEngineGroupDAO{
    private static final Log LOG = LogFactory.getLog(PixelDataEngineGroupDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Integer insertGroup(GroupRequest request){
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertMapping");
        String query = "insert into marketplace.pde_groups(key_id, gid, type) values(?, ?, ?)";
        // Object[] args = new Object[]{key_id, gid, type};

//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertMapping" + ", " + "Executing query -> " + query.toString());
//
//        int result = 0;
//        try {
//            result = jdbcTemplate.update(query, args);
//        } catch (Exception e) {
//
//            LOG.error("Failed to execute sql query", e);
//        }
//
//        if (LOG.isDebugEnabled())
//            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertMapping" + "  ,method return -> " + result);

        return null;
    }

    public String getGroups(){
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getGroups");
        String query = "SELECT a.key_id, a.gid, a.type FROM marketplace.pde_groups a order by a.key_id";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getGroups" + ", " + "Executing query -> " + query.toString());

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
            PdeGroupsDTO mPdeGroupsDTO = new PdeGroupsDTO();
            mPdeGroupsDTO.setKey_id(Integer.valueOf(String.valueOf(m.get("key_id"))));
            mPdeGroupsDTO.setGid(Integer.valueOf(String.valueOf(m.get("gid"))));
            mPdeGroupsDTO.setType(String.valueOf(m.get("type")));

            // convert Java object to JSON (Jackson)
            ObjectMapper mapper = new ObjectMapper();
            String tmp = "";
            try {
                tmp = mapper.writeValueAsString(mPdeGroupsDTO);
            } catch (Exception e) {

                LOG.error("Failed to convert Java object to JSON", e);
            }
            sb.append(tmp + ",");
        }
        sb.deleteCharAt(sb.toString().length() - 1);
        sb.append("]");

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getGroups" + "  ,method return -> " + sb.toString());

        return sb.toString();
    }

    public String getGroup(String gid){
        return null;
    }

    public Integer updateGroup(GroupRequest request){
        return null;
    }

    public Integer deleteGroup(String keyId){
        return null;
    }
}
