package com.adara.pixeldataengineui.dao.usermanagement;

import com.adara.pixeldataengineui.model.backend.dto.usermanagement.UserDTO;
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
public class UserManagementDAOImpl implements UserManagementDAO {
    private static final Log LOG = LogFactory
            .getLog(UserManagementDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getAllUser() {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->"
                + "getAllUser");
        String query = "SELECT * FROM pde.pixel_data_engine_users";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->"
                + "getAllUser" + ", " + "Executing query -> "
                + query.toString());

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
            UserDTO mUserDTO = new UserDTO();
            mUserDTO.setUsername(String.valueOf(m.get("username")));
            mUserDTO.setPassword(String.valueOf(m.get("password")));
            // convert Java object to JSON (Jackson)
            ObjectMapper mapper = new ObjectMapper();
            String tmp = "";
            try {
                tmp = mapper.writeValueAsString(mUserDTO);
            } catch (Exception e) {
                LOG.error("Failed to convert Java object to JSON", e);
            }
            sb.append(tmp + ",");

        }
        sb.deleteCharAt(sb.toString().length() - 1);
        sb.append("]");

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->"
                    + "getAllUser" + "  ,method return -> " + sb.toString());

        return sb.toString();
    }

    public String getByUsername(String username) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->"
                + "getByUsername");
        String query = "SELECT * FROM pde.pixel_data_engine_users WHERE username =?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->"
                + "getByUsername" + ", " + "Executing query -> "
                + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String result = null;
        try {
            result = jdbcTemplate.queryForObject(query,
                    new Object[]{username}, new RowMapper<String>() {

                        @Override
                        public String mapRow(ResultSet rs, int rowNum)
                                throws SQLException {
                            UserDTO mUserDTO = new UserDTO();
                            mUserDTO.setUsername(rs
                                    .getString("username"));
                            mUserDTO.setPassword(rs
                                    .getString("password"));
                            // convert Java object to JSON (Jackson)
                            ObjectMapper mapper = new ObjectMapper();
                            String tmp = "";
                            try {
                                tmp = mapper.writeValueAsString(mUserDTO);
                            } catch (Exception e) {

                                LOG.error(
                                        "Failed to convert Java object to JSON",
                                        e);
                            }
                            LOG.info("Invoked " + "Class -> " + CLASS_NAME
                                    + ", " + "method ->" + "getByUsername"
                                    + "  ,method return -> " + tmp);
                            return tmp;
                        }
                    });
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->"
                    + "getRule" + "  ,method return -> " + result);

        return result;
    }

    public Integer login(UserDTO request) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->"
                + "login");

        String query = "SELECT * FROM pde.pixel_data_engine_users p where p.username =?"
                + " and " + " p.password = ?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->"
                + "login" + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String result = null;
        try {
            result = jdbcTemplate
                    .queryForObject(query, new Object[]{
                                    request.getUsername(), request.getPassword()},
                            new RowMapper<String>() {

                                @Override
                                public String mapRow(ResultSet rs, int rowNum)
                                        throws SQLException {
                                    UserDTO mUserDTO = new UserDTO();
                                    mUserDTO.setUsername(rs
                                            .getString("username"));
                                    mUserDTO.setPassword(rs
                                            .getString("password"));
                                    // convert Java object to JSON (Jackson)
                                    ObjectMapper mapper = new ObjectMapper();
                                    String result = "";
                                    try {
                                        result = mapper
                                                .writeValueAsString(mUserDTO);
                                    } catch (Exception e) {

                                        LOG.error(
                                                "Failed to convert Java object to JSON",
                                                e);
                                    }
                                    return result;
                                }
                            });
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        return result.length();
    }

    public Integer createUser(UserDTO request) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->"
                + "createUser");

        String username = request.getUsername();
        String password = request.getPassword();
        if (username == null || username.length() == 0 || password == null
                || password.length() == 0) {
            LOG.error("Invoked " + "Class -> " + CLASS_NAME + ", "
                    + "method ->" + "createUser"
                    + "  ,Error: username or password is null");
            return -1;
        }

        String query = "insert into pde.pixel_data_engine_users (username, password) values(?, ?)";
        Object[] args = new Object[]{username, password};
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->"
                + "createUser" + ", " + "Executing query -> "
                + query.toString());
        LOG.info("Executing query:" + query.toString());

        int result = 0;
        try {
            result = jdbcTemplate.update(query, args);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->"
                    + "createUser" + "  ,method return -> " + result);

        return result;
    }

    public Integer deleteUser(String username) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->"
                + "deleteUser");
        String query = "DELETE FROM pde.pixel_data_engine_users WHERE username =?";
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->"
                + "deleteUser" + ", " + "Executing query -> "
                + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int result = 0;
        try {
            result = jdbcTemplate.update(query, username);
        } catch (Exception e) {
            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->"
                    + "deleteUser" + "  ,method return -> " + result);

        return result;
    }

    public Integer updateUser(UserDTO request) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->"
                + "updateUser");

        String username = request.getUsername();
        String password = request.getPassword();
        if (username == null || username.length() == 0 || password == null
                || password.length() == 0) {

            LOG.error("Invoked " + "Class -> " + CLASS_NAME + ", "
                    + "method ->" + "updateUser"
                    + "  ,Error: username or password is null");
            return -1;
        }

        String query = "UPDATE pde.pixel_data_engine_users SET " + "username"
                + "=?" + "," + "password" + "=?" + " WHERE username=?";

        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->"
                + "updateUser" + ", " + "Executing query -> "
                + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{username, password, username};
        Integer result = 0;
        try {
            result = jdbcTemplate.update(query, args);
        } catch (Exception e) {

            LOG.error("Failed to execute sql query", e);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->"
                    + "updateUser" + "  ,method return -> " + result);

        return result;
    }
}
