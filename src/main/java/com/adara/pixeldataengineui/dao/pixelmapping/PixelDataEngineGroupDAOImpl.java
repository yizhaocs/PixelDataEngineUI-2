package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.PixelDataEngineConfigsDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.PixelDataEngineGroupsDTO;
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
public class PixelDataEngineGroupDAOImpl implements PixelDataEngineGroupDAO {
    private static final Log LOG = LogFactory.getLog(PixelDataEngineGroupDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Integer insertGroup(String triggerKeyId, Integer groupType, Boolean isUITest) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "insertGroup" + "]";
        String query = null;
        Object[] args = null;
        if (isUITest) {
            query = "insert into pde.pixel_data_engine_groups(trigger_key_id, gid, group_type) values(?, ?, ?)";
            args = new Object[]{triggerKeyId, 1, groupType};
        } else {
            query = "insert into marketplace.pixel_data_engine_groups(trigger_key_id, group_type) values(?, ?)";
            args = new Object[]{triggerKeyId, groupType};
        }


        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        int result = 0;
        result = jdbcTemplate.update(query, args);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public String getGroups() throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getGroups" + "]";
        String query = "SELECT a.trigger_key_id, a.gid, a.group_type FROM marketplace.pixel_data_engine_groups a order by a.trigger_key_id";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);

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
            LOG.debug(LOG_HEADER + "  ,method return -> " + sb.toString());

        return sb.toString();
    }

    public PixelDataEngineGroupsDTO getGroup(String keyId) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getGroup" + "]";
        String query = "SELECT a.trigger_key_id, a.gid, a.group_type FROM marketplace.pixel_data_engine_groups a where a.trigger_key_id= ?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        final PixelDataEngineGroupsDTO result = new PixelDataEngineGroupsDTO();
        jdbcTemplate.queryForObject(query, new Object[]{keyId}, new RowMapper<PixelDataEngineGroupsDTO>() {
            @Override
            public PixelDataEngineGroupsDTO mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                result.setTrigger_key_id(rs.getString("trigger_key_id"));
                result.setGid(rs.getInt("gid"));
                result.setGroup_type(rs.getInt("group_type"));
                // convert Java object to JSON (Jackson)
               /* ObjectMapper mapper = new ObjectMapper();
                String result = "";
                try {
                    result = mapper.writeValueAsString(mPixelDataEngineGroupsDTO);
                } catch (Exception e) {
                    LOG.error("Failed to convert Java object to JSON", e);
                }*/
                return result;
            }
        });

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public GenericDTOList<PixelDataEngineConfigsDTO> getSameGroup(Integer gid) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getSameGroup" + "]";
        String query = "SELECT a.gid, a.key_id, a.priority, a.type, a.parse_rule, a.condition_rule, a.action_rule FROM marketplace.pixel_data_engine_configs a where a.gid=" + gid;
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);

        GenericDTOList<PixelDataEngineConfigsDTO> result = new GenericDTOList<PixelDataEngineConfigsDTO>();
//        StringBuilder sb = new StringBuilder();
//        sb.append("[");
        for (Map<String, Object> m : listMap) {
            PixelDataEngineConfigsDTO mPixelDataEngineConfigsDTO = new PixelDataEngineConfigsDTO();
            mPixelDataEngineConfigsDTO.setGid(String.valueOf(m.get("gid")));
            mPixelDataEngineConfigsDTO.setKey_id(String.valueOf(m.get("key_id")));
            mPixelDataEngineConfigsDTO.setPriority(String.valueOf(m.get("priority")));
            mPixelDataEngineConfigsDTO.setType(String.valueOf(m.get("type")));
            mPixelDataEngineConfigsDTO.setParse_rule(String.valueOf(m.get("parse_rule")));
            mPixelDataEngineConfigsDTO.setCondition_rule(String.valueOf(m.get("condition_rule")));
            mPixelDataEngineConfigsDTO.setAction_rule(String.valueOf(m.get("action_rule")));
            result.add(mPixelDataEngineConfigsDTO);
//            // convert Java object to JSON (Jackson)
//            ObjectMapper mapper = new ObjectMapper();
//            String tmp = "";
//            try {
//                tmp = mapper.writeValueAsString(mPixelDataEngineConfigsDTO);
//            } catch (Exception e) {
//                LOG.error("Failed to execute sql query", e);
//            }
//            sb.append(tmp + ",");
        }

//        sb.deleteCharAt(sb.toString().length() - 1);
//        sb.append("]");

//        if (LOG.isDebugEnabled())
//            LOG.debug(LOG_HEADER + "  ,method return -> " + sb.toString());

        return result;
    }


    public Integer updateGroup(String triggerKeyId, Integer groupType) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateGroup" + "]";
        String query = "UPDATE marketplace.pixel_data_engine_groups SET " + "trigger_key_id" + "=?" + "," + "group_type" + "=?" + " WHERE trigger_key_id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{triggerKeyId, groupType, triggerKeyId};
        Integer result = 0;
        result = jdbcTemplate.update(query, args);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public Integer deleteGroup(String triggerKeyId, String gid, Boolean isUITest) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteGroup" + "]";

        /*
        * Delete group
        * */
        String queryDeleteGroup = null;
        if (isUITest) {
            queryDeleteGroup = "DELETE FROM pde.pixel_data_engine_groups WHERE trigger_key_id =?";
        } else {
            queryDeleteGroup = "DELETE FROM marketplace.pixel_data_engine_groups WHERE trigger_key_id =?";
        }
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + queryDeleteGroup.toString());

        JdbcTemplate jdbcTemplateDeleteGroup = new JdbcTemplate(dataSource);
        int resultDeleteGroup = 0;
        try {
            resultDeleteGroup = jdbcTemplateDeleteGroup.update(queryDeleteGroup, triggerKeyId);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method resultDeleteGroup -> " + resultDeleteGroup);


        /*
        * Delete all rules to that group
        * */
        String queryDeleteRulesToThatGroup = null;
        if (isUITest) {
            queryDeleteRulesToThatGroup = "DELETE FROM pde.pixel_data_engine_configs WHERE gid =?";
        } else {
            queryDeleteRulesToThatGroup = "DELETE FROM marketplace.pixel_data_engine_configs WHERE gid =?";
        }
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteGroup" + ", " + "Executing query -> " + queryDeleteRulesToThatGroup.toString());

        JdbcTemplate jdbcTemplateRulesToThatGroup = new JdbcTemplate(dataSource);
        int resultDeleteRulesToThatGroup = 0;
        try {
            resultDeleteRulesToThatGroup = jdbcTemplateRulesToThatGroup.update(queryDeleteRulesToThatGroup, gid);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteGroup" + "  ,method resultDeleteRulesToThatGroup -> " + resultDeleteRulesToThatGroup);

        return resultDeleteRulesToThatGroup;
    }

    public void truncatePixelDataEngineGroupsTable(Boolean isUITest) throws Exception {
        String query = null;
        if (isUITest) {
            query = "truncate table pde.pixel_data_engine_groups";
        } else {
            query = "truncate table marketplace.pixel_data_engine_groups";
        }

        JdbcTemplate jdbcTemplateRulesToThatGroup = new JdbcTemplate(dataSource);
        try {
            jdbcTemplateRulesToThatGroup.execute(query);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }
    }
}
