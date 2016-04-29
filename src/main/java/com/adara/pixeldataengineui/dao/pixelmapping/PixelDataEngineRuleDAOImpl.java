package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.PixelDataEngineConfigsDTO;
import com.adara.pixeldataengineui.model.frontend.generalcomponents.InElementArray;
import com.adara.pixeldataengineui.model.frontend.generalcomponents.SetRuleArray;
import com.adara.pixeldataengineui.model.frontend.requestbody.RuleRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public Integer insertRule(RuleRequest request) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertRule");

        String gid = request.getGid();
        String keyId = request.getKeyId();
        String priority = request.getPriority();
        String type = request.getType();
        String parseRuleValue = parseRuleBuilder(request);
        String conditionRuleValue = conditionRuleBuilder(request);
        String actionRuleValue = actionRuleBuilder(request);

        if (gid == null || keyId == null || priority == null || keyId.length() == 0 || type == null || type.length() == 0 || parseRuleValue == null || parseRuleValue.length() == 0 || conditionRuleValue == null || conditionRuleValue.length() == 0 || actionRuleValue == null || actionRuleValue.length() == 0) {
            LOG.error("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertRule" + "  ,Error: keyId or type or parseRuleValue or conditionRuleValue or actionRuleValue is null");
            return -1;
        }

        String query = "INSERT INTO pixel_data_engine_configs VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = new Object[]{gid, keyId, priority, type, parseRuleValue, conditionRuleValue, actionRuleValue, "NULL", getCurrentDateTime()};

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertRule" + ", " + "Executing query -> " + query.toString());
        int result = 0;
        try {
            result = jdbcTemplate.update(query, args);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertRule" + "  ,method return -> " + result);

        return result;
    }

    public String getRules() {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getRules");
        String query = "SELECT p.gid, p.key_id, p.priority, p.type, p.parse_rule, p.condition_rule, p.action_rule FROM marketplace.pixel_data_engine_configs p order by p.key_id";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getRules" + ", " + "Executing query -> " + query.toString());

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
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getRules" + "  ,method return -> " + sb.toString());

        return sb.toString();
    }

    public String getRule(String keyId) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getRule");
        String query = "SELECT p.gid, p.key_id, p.priority, p.type, p.parse_rule, p.condition_rule, p.action_rule FROM marketplace.pixel_data_engine_configs p where p.key_id=?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getRule" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String result = null;
        try {
            result = jdbcTemplate.queryForObject(query, new Object[]{keyId}, new RowMapper<String>() {

                @Override
                public String mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    PixelDataEngineConfigsDTO mPixelDataEngineConfigsDTO = new PixelDataEngineConfigsDTO();
                    mPixelDataEngineConfigsDTO.setGid(String.valueOf(rs.getObject("gid")));
                    mPixelDataEngineConfigsDTO.setKey_id(String.valueOf(rs.getObject("key_id")));
                    mPixelDataEngineConfigsDTO.setPriority(String.valueOf(rs.getObject("priority")));
                    mPixelDataEngineConfigsDTO.setType(String.valueOf(rs.getObject("type")));
                    mPixelDataEngineConfigsDTO.setParse_rule(String.valueOf(rs.getObject("parse_rule")));
                    mPixelDataEngineConfigsDTO.setCondition_rule(String.valueOf(rs.getObject("condition_rule")));
                    mPixelDataEngineConfigsDTO.setAction_rule(String.valueOf(rs.getObject("action_rule")));
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
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getRule" + "  ,method return -> " + result);
        return result;
    }

    public Integer updateRule(RuleRequest request) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateRule");

        String gid = request.getGid();

        String keyId = request.getKeyId();

        String priority = request.getPriority();

        String type = request.getType();

        String parseRuleValue = parseRuleBuilder(request);

        String conditionRuleValue = conditionRuleBuilder(request);

        String actionRuleValue = actionRuleBuilder(request);

        if (keyId == null || keyId.length() == 0 || type == null || type.length() == 0 || parseRuleValue == null || parseRuleValue.length() == 0 || conditionRuleValue == null || conditionRuleValue.length() == 0 || actionRuleValue == null || actionRuleValue.length() == 0) {
            LOG.error("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateRule" + "  ,Error: keyId or type or parseRuleValue or conditionRuleValue or actionRuleValue is null");
            return -1;
        }

        String query = "UPDATE marketplace.pixel_data_engine_configs SET " + "gid" + "=?" + "," + "key_id" + "=?" + "," + "priority" + "=?" + "," + "type" + "=?" + "," + "parse_rule" + "=?" + "," + "condition_rule" + "=?" + "," + "action_rule" + "=?" + " WHERE key_id=?";

        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateRule" + ", " + "Executing query -> " + query.toString());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{gid, keyId, priority, type, parseRuleValue, conditionRuleValue, actionRuleValue, keyId};

        Integer result = 0;
        try {
            result = jdbcTemplate.update(query, args);
        } catch (Exception e) {

            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateRule" + "  ,method return -> " + result);

        return result;
    }

    public Integer deleteRule(String keyId) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteRule");
        String query = "DELETE FROM marketplace.pixel_data_engine_configs WHERE key_id =?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteRule" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = 0;
        try {
            result = jdbcTemplate.update(query, keyId);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteRule" + "  ,method return -> " + result);

        return result;
    }

    private String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date).toString();
    }

    private String parseRuleBuilder(RuleRequest request) {
        String parseRuleKey = request.getParseRule();
        StringBuilder parseRuleValue = new StringBuilder();
        parseRuleValue.append(parseRuleKey);
        String split1 = null;
        String split2Level1SplitString = null;
        String split2Level2SplitString = null;
        if (parseRuleKey.equals("split1")) {
            split1 = request.getSplit1().toString();
            parseRuleValue.append("|");
            if (split1.equals("|")) {
                split1 = "\"" + split1 + "\"";
            }

            parseRuleValue.append(split1);
        } else if (parseRuleKey.equals("split2")) {
            split2Level1SplitString = request.getSplit2().getColumn1();
            split2Level2SplitString = request.getSplit2().getColumn2();
            parseRuleValue.append("|");
            if (split2Level1SplitString.equals("|")) {
                split2Level1SplitString = "\"" + split2Level1SplitString + "\"";
            }

            parseRuleValue.append(split2Level1SplitString);
            parseRuleValue.append("|");
            if (split2Level2SplitString.equals("|")) {
                split2Level2SplitString = "\"" + split2Level2SplitString + "\"";
            }

            parseRuleValue.append(split2Level2SplitString);
        }

        return parseRuleValue.toString();
    }

    private String conditionRuleBuilder(RuleRequest request) {
        String conditionRuleKey = request.getConditionRule();
        StringBuilder conditionRuleValue = new StringBuilder();
        conditionRuleValue.append(conditionRuleKey);
        String len = null;
        String rangeFrom = null;
        String rangeTo = null;
        List<InElementArray> inElementArrayList = null;
        if (conditionRuleKey.equals("len")) {
            len = request.getLen().toString();
            conditionRuleValue.append("|");
            conditionRuleValue.append(len);
        } else if (conditionRuleKey.equals("range")) {
            rangeFrom = request.getRange().getColumn1();
            rangeTo = request.getRange().getColumn2();
            conditionRuleValue.append("|");
            conditionRuleValue.append(rangeFrom);
            conditionRuleValue.append("|");
            conditionRuleValue.append(rangeTo);
        } else if (conditionRuleKey.equals("in")) {
            inElementArrayList = request.getInElementArray();
            conditionRuleValue.append("|");
            for (InElementArray i : inElementArrayList) {
                if (i.getColumn2().length() > 0) {
                    conditionRuleValue.append(i.getColumn2());
                    conditionRuleValue.append("|");
                }
            }
            conditionRuleValue.deleteCharAt(conditionRuleValue.length() - 1);
        }

        return conditionRuleValue.toString();
    }

    private String actionRuleBuilder(RuleRequest request) {
        String actionRuleKey = request.getActionRule();
        StringBuilder actionRuleValue = new StringBuilder();
        actionRuleValue.append(actionRuleKey);
        String substrDirection = null;
        String substrStartIndex = null;
        String substrLength = null;
        List<SetRuleArray> setRuleArrayDTOList = null;
        String dec;
        if (actionRuleKey.equals("substr")) {
            substrDirection = request.getSubstr().getColumn1();
            substrStartIndex = request.getSubstr().getColumn2();
            substrLength = request.getSubstr().getColumn3();
            actionRuleValue.append("|");
            actionRuleValue.append(substrDirection);
            actionRuleValue.append("|");
            actionRuleValue.append(substrStartIndex);
            actionRuleValue.append("|");
            actionRuleValue.append(substrLength);
        } else if (actionRuleKey.equals("set")) {
            setRuleArrayDTOList = request.getSetRuleArray();
            actionRuleValue.append("|");
            for (SetRuleArray s : setRuleArrayDTOList) {
                if (s.getColumn2().length() > 0) {
                    actionRuleValue.append(s.getColumn2());
                    actionRuleValue.append("|");
                }
            }
            actionRuleValue.deleteCharAt(actionRuleValue.length() - 1);
        } else if (actionRuleKey.equals("dec")) {
            dec = request.getDec().toString();
            actionRuleValue.append("|");
            actionRuleValue.append(dec);
        }
        return actionRuleValue.toString();
    }
}
