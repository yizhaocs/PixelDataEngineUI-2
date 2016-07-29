package com.adara.pixeldataengineui.dao.usermanagement;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.usermanagement.UserDTO;
import com.adara.pixeldataengineui.util.Constants;
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

    public GenericDTOList<UserDTO> getAllUser() throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getAllUser" + "]";
        String query = "SELECT * FROM pde.pixel_data_engine_users";
        LOG.info(LOG_HEADER + ", " + "Executing query -> "
                + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);

        GenericDTOList<UserDTO> result = new GenericDTOList<UserDTO>();
        for (Map<String, Object> m : listMap) {
            UserDTO mUserDTO = new UserDTO();
            mUserDTO.setUsername(String.valueOf(m.get("username")));
            mUserDTO.setPassword(String.valueOf(m.get("password")));

            result.add(mUserDTO);
        }


        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }

    public String getByUsername(String username) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getByUsername" + "]";
        String query = "SELECT * FROM pde.pixel_data_engine_users WHERE username =?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> "
                + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String result = null;
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
                        LOG.info(LOG_HEADER
                                + "  ,method return -> " + tmp);
                        return tmp;
                    }
                });

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public ResponseDTO login(UserDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "login" + "]";
        ResponseDTO result = new ResponseDTO();

        String query = "SELECT * FROM pde.pixel_data_engine_users p where p.username =?"
                + " and " + " p.password = ?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String retval = null;
        retval = jdbcTemplate
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

        if(retval.length() > 0){
            result.setMessage(Constants.SUCCESS);
        }else{
            result.setMessage(Constants.FAILURE);
        }

        return result;
    }

    public ResponseDTO createUser(UserDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "createUser" + "]";
        ResponseDTO result = new ResponseDTO();

        String username = request.getUsername();
        String password = request.getPassword();
        if (username == null || username.length() == 0 || password == null
                || password.length() == 0) {
            LOG.error(LOG_HEADER
                    + "  ,Error: username or password is null");
            result.setMessage(Constants.FAILURE);
            return result;
        }

        String query = "insert into pde.pixel_data_engine_users (username, password) values(?, ?)";
        Object[] args = new Object[]{username, password};
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOG.info(LOG_HEADER + ", " + "Executing query -> "
                + query.toString());
        LOG.info("Executing query:" + query.toString());

        int retval = 0;
        retval = jdbcTemplate.update(query, args);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        if(retval > 0){
            result.setMessage(Constants.SUCCESS);
        }else{
            result.setMessage(Constants.FAILURE);
        }

        return result;
    }

    public ResponseDTO deleteUser(String username) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteUser" + "]";
        ResponseDTO result = new ResponseDTO();
        String query = "DELETE FROM pde.pixel_data_engine_users WHERE username =?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> "
                + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int retval = 0;
        retval = jdbcTemplate.update(query, username);

        if(retval > 0){
            result.setMessage(Constants.SUCCESS);
        }else{
            result.setMessage(Constants.FAILURE);
        }

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public ResponseDTO updateUser(UserDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateUser" + "]";
        ResponseDTO result = new ResponseDTO();

        String username = request.getUsername();
        String password = request.getPassword();
        if (username == null || username.length() == 0 || password == null
                || password.length() == 0) {

            LOG.error(LOG_HEADER
                    + "  ,Error: username or password is null");
            result.setMessage(Constants.FAILURE);
            return result;
        }

        String query = "UPDATE pde.pixel_data_engine_users SET " + "username"
                + "=?" + "," + "password" + "=?" + " WHERE username=?";

        LOG.info(LOG_HEADER + ", " + "Executing query -> "
                + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{username, password, username};
        Integer retval = 0;
        retval = jdbcTemplate.update(query, args);

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        if(retval > 0){
            result.setMessage(Constants.SUCCESS);
        }else{
            result.setMessage(Constants.FAILURE);
        }

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }
}
