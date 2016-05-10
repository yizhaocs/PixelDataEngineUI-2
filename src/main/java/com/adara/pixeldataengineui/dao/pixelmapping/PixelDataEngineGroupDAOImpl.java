package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.PixelDataEngineGroupsDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.PixelDataEngineConfigsDTO;
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
public class PixelDataEngineGroupDAOImpl implements PixelDataEngineGroupDAO{
    private static final Log LOG = LogFactory.getLog(PixelDataEngineGroupDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Integer insertGroup(String trigger_key_id, Integer group_type){
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertGroup");
        String query = "insert into marketplace.pixel_data_engine_groups(trigger_key_id, group_type) values(?, ?)";
         Object[] args = new Object[]{trigger_key_id, group_type};

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertGroup" + ", " + "Executing query -> " + query.toString());

        int result = 0;
        try {
            result = jdbcTemplate.update(query, args);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertGroup" + "  ,method return -> " + result);

        return result;
    }

    public String getGroups(){
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getGroups");
        String query = "SELECT a.trigger_key_id, a.gid, a.group_type FROM marketplace.pixel_data_engine_groups a order by a.trigger_key_id";
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
            PixelDataEngineGroupsDTO mPixelDataEngineGroupsDTO = new PixelDataEngineGroupsDTO();
            mPixelDataEngineGroupsDTO.setTrigger_key_id(String.valueOf(m.get("trigger_key_id")));
            mPixelDataEngineGroupsDTO.setGid(Integer.valueOf(String.valueOf(m.get("gid"))));
            mPixelDataEngineGroupsDTO.setGroup_type(Integer.valueOf(String.valueOf(m.get("group_type"))));

            // convert Java object to JSON (Jackson)
            ObjectMapper mapper = new ObjectMapper();
            String tmp = "";
            try {
                tmp = mapper.writeValueAsString(mPixelDataEngineGroupsDTO);
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

    public String getGroup(String keyId){
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getGroup");
        String query = "SELECT a.trigger_key_id, a.gid, a.group_type FROM marketplace.pixel_data_engine_groups a where a.trigger_key_id= ?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getGroup" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String result = null;
        try {
            result = jdbcTemplate.queryForObject(query, new Object[]{keyId}, new RowMapper<String>() {

                @Override
                public String mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    PixelDataEngineGroupsDTO mPixelDataEngineGroupsDTO = new PixelDataEngineGroupsDTO();
                    mPixelDataEngineGroupsDTO.setTrigger_key_id(rs.getString("trigger_key_id"));
                    mPixelDataEngineGroupsDTO.setGid(rs.getInt("gid"));
                    mPixelDataEngineGroupsDTO.setGroup_type(rs.getInt("group_type"));
                    // convert Java object to JSON (Jackson)
                    ObjectMapper mapper = new ObjectMapper();
                    String result = "";
                    try {
                        result = mapper.writeValueAsString(mPixelDataEngineGroupsDTO);
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
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getGroup" + "  ,method return -> " + result);

        return result;
    }

    public String getSameGroup(Integer gid){
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getSameGroup");
        String query = "SELECT a.gid, a.key_id, a.priority, a.type, a.parse_rule, a.condition_rule, a.action_rule FROM marketplace.pixel_data_engine_configs a where a.gid=" + gid;
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getSameGroup" + ", " + "Executing query -> " + query.toString());

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
            PixelDataEngineConfigsDTO mPixelDataEngineConfigsDTO = new PixelDataEngineConfigsDTO();
            mPixelDataEngineConfigsDTO.setGid(String.valueOf(m.get("gid")));
            mPixelDataEngineConfigsDTO.setKey_id(String.valueOf(m.get("key_id")));
            mPixelDataEngineConfigsDTO.setPriority(String.valueOf(m.get("priority")));
            mPixelDataEngineConfigsDTO.setType(String.valueOf(m.get("type")));
            mPixelDataEngineConfigsDTO.setParse_rule(String.valueOf(m.get("parse_rule")));
            mPixelDataEngineConfigsDTO.setCondition_rule(String.valueOf(m.get("condition_rule")));
            mPixelDataEngineConfigsDTO.setAction_rule(String.valueOf(m.get("action_rule")));
            // convert Java object to JSON (Jackson)
            ObjectMapper mapper = new ObjectMapper();
            String tmp = "";
            try {
                tmp = mapper.writeValueAsString(mPixelDataEngineConfigsDTO);
            } catch (Exception e) {
                LOG.error("Failed to execute sql query", e);
            }
            sb.append(tmp + ",");
        }

        sb.deleteCharAt(sb.toString().length() - 1);
        sb.append("]");

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getGroup" + "  ,method return -> " + sb.toString());

        return sb.toString();
    }


    public Integer updateGroup(String trigger_key_id, Integer group_type){
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateGroup");
        String query = "UPDATE marketplace.pixel_data_engine_groups SET " + "trigger_key_id" + "=?" + "," + "group_type" + "=?" + " WHERE trigger_key_id=?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateGroup" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{trigger_key_id, group_type, trigger_key_id};
        Integer result = 0;
        try {
            result = jdbcTemplate.update(query, args);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateGroup" + "  ,method return -> " + result);

        return result;
    }

    public Integer deleteGroup(String trigger_key_id){
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteGroup");
        String query = "DELETE FROM marketplace.pixel_data_engine_groups WHERE trigger_key_id =?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteGroup" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = 0;
        try {
            result = jdbcTemplate.update(query, trigger_key_id);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteGroup" + "  ,method return -> " + result);

        return result;
    }
}
