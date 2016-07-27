package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.PixelDataEngineConfigsDTO;
import com.adara.pixeldataengineui.model.frontend.requestbody.RuleRequest;
import com.adara.pixeldataengineui.util.Tools;
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
public class PixelDataEngineRuleDAOImpl implements PixelDataEngineRuleDAO {
    private static final Log LOG = LogFactory.getLog(PixelDataEngineRuleDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Integer insertRule(RuleRequest request, Boolean isUITest) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "insertRule" + "]";

        String gid = request.getGid();
        String keyId = request.getKeyId();
        String priority = request.getPriority();
        String type = request.getType();
        String parseRuleValue = Tools.parseRuleBuilder(request);
        String conditionRuleValue = Tools.conditionRuleBuilder(request);
        String actionRuleValue = Tools.actionRuleBuilder(request);

        if (gid == null || keyId == null || priority == null || keyId.length() == 0 || type == null || type.length() == 0 || parseRuleValue == null || parseRuleValue.length() == 0 || conditionRuleValue == null || conditionRuleValue.length() == 0 || actionRuleValue == null || actionRuleValue.length() == 0) {
            LOG.error(LOG_HEADER + "  ,Error: keyId or type or parseRuleValue or conditionRuleValue or actionRuleValue is null");
            return -1;
        }

        String query = null;
        if (isUITest) {
            query = "INSERT INTO pde.pixel_data_engine_configs VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        } else {
            query = "INSERT INTO marketplace.pixel_data_engine_configs VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        }
        Object[] args = new Object[]{gid, keyId, priority, type, parseRuleValue, conditionRuleValue, actionRuleValue, "NULL", Tools.getCurrentDateTime()};

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());
        int result = 0;
        result = jdbcTemplate.update(query, args);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public String getRules() throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getRules" + "]";
        String query = "SELECT p.gid, p.key_id, p.priority, p.type, p.parse_rule, p.condition_rule, p.action_rule FROM marketplace.pixel_data_engine_configs p order by p.key_id";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);

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
            LOG.debug(LOG_HEADER + "  ,method return -> " + sb.toString());

        return sb.toString();
    }

    public String getRule(Integer gid, String keyId, Integer priority) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getRule" + "]";
        String query = "SELECT p.gid, p.key_id, p.priority, p.type, p.parse_rule, p.condition_rule, p.action_rule FROM marketplace.pixel_data_engine_configs p WHERE p.gid=? AND p.key_id=? AND p.priority=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String result = null;
        result = jdbcTemplate.queryForObject(query, new Object[]{gid, keyId, priority}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                PixelDataEngineConfigsDTO mPixelDataEngineConfigsDTO = new PixelDataEngineConfigsDTO();
                mPixelDataEngineConfigsDTO.setGid(rs.getString("gid"));
                mPixelDataEngineConfigsDTO.setKey_id(rs.getString("key_id"));
                mPixelDataEngineConfigsDTO.setPriority(rs.getString("priority"));
                mPixelDataEngineConfigsDTO.setType(rs.getString("type"));
                mPixelDataEngineConfigsDTO.setParse_rule(rs.getString("parse_rule"));
                mPixelDataEngineConfigsDTO.setCondition_rule(rs.getString("condition_rule"));
                mPixelDataEngineConfigsDTO.setAction_rule(rs.getString("action_rule"));
                // convert Java object to JSON (Jackson)
                ObjectMapper mapper = new ObjectMapper();
                String result = "";
                try {
                    result = mapper.writeValueAsString(mPixelDataEngineConfigsDTO);
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

    public Integer updateRule(RuleRequest request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateRule" + "]";

        String gid = request.getGid();

        String keyId = request.getKeyId();

        String priority = request.getPriority();

        String newPriority = request.getNewPriority();

        String type = request.getType();

        String parseRuleValue = Tools.parseRuleBuilder(request);

        String conditionRuleValue = Tools.conditionRuleBuilder(request);

        String actionRuleValue = Tools.actionRuleBuilder(request);

        if (keyId == null || keyId.length() == 0 || type == null || type.length() == 0 || parseRuleValue == null || parseRuleValue.length() == 0 || conditionRuleValue == null || conditionRuleValue.length() == 0 || actionRuleValue == null || actionRuleValue.length() == 0) {
            LOG.error(LOG_HEADER + "  ,Error: keyId or type or parseRuleValue or conditionRuleValue or actionRuleValue is null");
            return -1;
        }

        String query = "UPDATE marketplace.pixel_data_engine_configs SET " + "gid" + "=?" + "," + "key_id" + "=?" + "," + "priority" + "=?" + "," + "type" + "=?" + "," + "parse_rule" + "=?" + "," + "condition_rule" + "=?" + "," + "action_rule" + "=?" + " WHERE gid=? AND key_id=? AND priority=?";

        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{gid, keyId, newPriority, type, parseRuleValue, conditionRuleValue, actionRuleValue, gid, keyId, priority};

        Integer result = 0;
        result = jdbcTemplate.update(query, args);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public Integer deleteRule(Integer gid, String keyId, Integer priority, Boolean isUITest) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteRule" + "]";

        String query = null;
        if (isUITest) {
            query = "DELETE FROM pde.pixel_data_engine_configs WHERE gid=? AND key_id=? AND priority=?";
        } else {
            query = "DELETE FROM marketplace.pixel_data_engine_configs WHERE gid=? AND key_id=? AND priority=?";
        }

        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = 0;
        result = jdbcTemplate.update(query, gid, keyId, priority);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public void truncatePixelDataEngineConfigsTable(Boolean isUITest) throws Exception {
        String query = null;
        if (isUITest) {
            query = "truncate table pde.pixel_data_engine_configs";
        } else {
            query = "truncate table marketplace.pixel_data_engine_configs";
        }

        JdbcTemplate jdbcTemplateRulesToThatGroup = new JdbcTemplate(dataSource);
        try {
            jdbcTemplateRulesToThatGroup.execute(query);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }
    }


}
