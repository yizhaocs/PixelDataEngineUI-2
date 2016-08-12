package com.adara.pixeldataengineui.dao.geofilemanager;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginemaps.PixelDataEngineMapsDTO;
import com.adara.pixeldataengineui.model.frontend.requestbody.GeoMapCreationRequest;
import com.adara.pixeldataengineui.model.frontend.requestbody.UpdateLoadingInProgressRequest;
import com.adara.pixeldataengineui.service.geofilemanager.GeoFileManagerService;
import com.adara.pixeldataengineui.util.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by yzhao on 7/21/16.
 */
public class GeoFileManagerDAOImpl implements GeoFileManagerDAO {
    private static final Log LOG = LogFactory.getLog(GeoFileManagerDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;
    @Autowired
    private GeoFileManagerService mGeoFileManagerService;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ResponseDTO createPixelDataEngineMap(GeoMapCreationRequest request) throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "createPixelDataEngineMap" + "]";
        String mapName = request.getMapName();
        String description = request.getDescription();
        String tableName = "pde_map_" + mapName;
        String query1 = "INSERT INTO pde.pixel_data_engine_maps(map_name, table_name, description) VALUES(?, ?, ?)";
        String query2 = "CREATE TABLE pde." + tableName + " (value varchar(255) , mapped_value varchar(255))";
        String query3 = "SELECT * FROM pde." + tableName;
        Object[] args = new Object[]{mapName, tableName, description};

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query1.toString());

        /*
        * Insert the relationship to the "pixel_data_engine_maps" table
        * */
        int retval1 = jdbcTemplate.update(query1, args);

        /*
        * Create a table for the new table
        * */
        jdbcTemplate.execute(query2);

        /*
        * Select * from the new table, and we should get 0 return
        * */
        List<Map<String, Object>> listMap = null;
        try {
            listMap = jdbcTemplate.queryForList(query3);
        }catch(Exception e){
            /*
            * Delete the relationship in pixel_data_engine_maps since the table creation failed
            * */
            if(retval1 > 0){
                String query4 = "DELETE FROM pde.pixel_data_engine_maps WHERE map_name=?";
                Object[] args2 = new Object[]{mapName};
                jdbcTemplate.update(query4, args2);
            }
        }
        ResponseDTO result = new ResponseDTO();
        if(retval1 > 0 && listMap.size() == 0){
            result.setMessage(Constants.SUCCESS);
        }else{
            result.setMessage(Constants.FAILURE);
        }
        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public ResponseDTO updatePixelDataEngineMap(GeoMapCreationRequest request) throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updatePixelDataEngineMap" + "]";
        String mapName = request.getMapName();
        String description = request.getDescription();
        String tableName = "pde_map_" + mapName;
        String query = "UPDATE pde.pixel_data_engine_maps SET " + "description" + "=?" + " WHERE map_name=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{description, mapName};
        Integer retval = 0;
        retval = jdbcTemplate.update(query, args);

        ResponseDTO result = new ResponseDTO();
        if(retval > 0){
            result.setMessage(Constants.SUCCESS);
        }else{
            result.setMessage(Constants.FAILURE);
        }

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public ResponseDTO updateLoadingInProgress(UpdateLoadingInProgressRequest request) throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updatePixelDataEngineMap" + "]";
        String mapName = request.getMap_name();
        Boolean loading_in_progress = request.getLoading_in_progress();
        String query = "UPDATE pde.pixel_data_engine_maps SET " + "loading_in_progress" + "=?" + " WHERE map_name=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{loading_in_progress, mapName};
        Integer retval = 0;
        retval = jdbcTemplate.update(query, args);

        ResponseDTO result = new ResponseDTO();
        if(retval > 0){
            result.setMessage(Constants.SUCCESS);
        }else{
            result.setMessage(Constants.FAILURE);
        }

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public ResponseDTO deletePixelDataEngineMap(String mapName) throws Exception{
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        ResponseDTO result = result = new ResponseDTO();
        String query1 = "DELETE FROM pde.pixel_data_engine_maps WHERE map_name=?";
        String query2 = "DROP TABLE pde.pde_map_" + mapName;
        String query3 = "SELECT * FROM pde.pde_map_" + mapName;
        Object[] args = new Object[]{mapName};
        /*
        * Delete the map relationship in pixel_data_engine_maps table
        * */
        int retval1 = jdbcTemplate.update(query1, args);
        /*
        * Drop the pde_map_table
        * */
        jdbcTemplate.execute(query2);
/*
        * Select * from the table, because table has been dropped so that we would expect from exception
        * */
        boolean isException =  false;
        try {
            jdbcTemplate.queryForList(query3);
        }catch(Exception e){
            isException = true;
        }

        /*
        * Generate the result
        * */
        if(retval1 > 0 && isException){

            result.setMessage(Constants.SUCCESS);
        }else{
            result.setMessage(Constants.FAILURE);
        }
        return result;
    }

    public GenericDTOList<PixelDataEngineMapsDTO> getPixelDataEngineMaps() throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getGroups" + "]";
        String query = "SELECT a.map_name, a.table_name, a.description, a.version, a.loading_in_progress, a.modification_ts FROM pde.pixel_data_engine_maps a";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);

        GenericDTOList<PixelDataEngineMapsDTO> result = new GenericDTOList<PixelDataEngineMapsDTO>();
        for (Map<String, Object> m : listMap) {
            PixelDataEngineMapsDTO mPixelDataEngineMapsDTO = new PixelDataEngineMapsDTO();
            mPixelDataEngineMapsDTO.setMap_name(String.valueOf(m.get("map_name")));
            mPixelDataEngineMapsDTO.setTable_name(String.valueOf(m.get("table_name")));
            mPixelDataEngineMapsDTO.setDescription(String.valueOf((m.get("description"))));
            mPixelDataEngineMapsDTO.setLoading_in_progress(Boolean.valueOf(String.valueOf(m.get("loading_in_progress"))));
            mPixelDataEngineMapsDTO.setVersion(String.valueOf((m.get("version"))));
            mPixelDataEngineMapsDTO.setModification_ts(String.valueOf((m.get("modification_ts"))));
            result.add(mPixelDataEngineMapsDTO);
        }

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }
    public ResponseDTO append(MultipartFile file, String table) throws Exception {

        ResponseDTO retval = new ResponseDTO();

        if (inputStreamToFile(file)) {
            JdbcTemplate jt = new JdbcTemplate(dataSource);
            try {
                retval.setMessage(Constants.SUCCESS);
                appendFileWithoutOverride(jt, Constants.fileUploadingPath, table);
            }catch (Exception e){
                retval.setMessage(Constants.FAILURE);
            }
        }

        updateVersion(retval, table);
        return retval;
    }

    public ResponseDTO override(MultipartFile file, String table) throws Exception {
        ResponseDTO retval = new ResponseDTO();

        if (inputStreamToFile(file)) {
            JdbcTemplate jt = new JdbcTemplate(dataSource);

            try {
                retval.setMessage(Constants.SUCCESS);
                truncateTable(jt, table);
                appendFileWithoutOverride(jt, Constants.fileUploadingPath, table);
            }catch (Exception e){
                retval.setMessage(Constants.FAILURE);
            }
        }

        updateVersion(retval, table);
        return retval;
    }

    private void updateVersion(ResponseDTO retval, String table) throws Exception{
        String mapName = null;
        PixelDataEngineMapsDTO mPixelDataEngineMapsDTO = null;
        if(retval.getMessage().equals(Constants.SUCCESS)){
            mapName = table.substring(8, table.length()); // remove "pde_map_" from "pde_map_city" to get the mapName
            mPixelDataEngineMapsDTO = mGeoFileManagerService.getPixelDataEngineMap(mapName);

            String query = "UPDATE pde.pixel_data_engine_maps SET " + "version" + "=?" + " WHERE map_name=?";
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            Object[] args = new Object[]{String.valueOf(Integer.valueOf(mPixelDataEngineMapsDTO.getVersion()) + 1), mapName};

            if(jdbcTemplate.update(query, args) <= 0){
                retval.setMessage(Constants.FAILURE);
            }
        }
    }

    public void getPdeMap(String tableName) throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getGroups" + "]";

        File file = new File(Constants.fileDirector);
        if (!file.exists()) {
            file.mkdir();
        }

        String query = "SELECT value, mapped_value INTO OUTFILE" + Constants.fileDownloadingPath + " FIELDS TERMINATED BY ','  LINES TERMINATED BY '\\n' FROM " + "pde." + tableName;
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute(query);
    }

    public PixelDataEngineMapsDTO getPixelDataEngineMap(String tableName) throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getPixelDataEngineMap" + "]";
        String query = "SELECT a.map_name, a.table_name, a.description, a.version, a.loading_in_progress, a.modification_ts FROM pde.pixel_data_engine_maps a where a.map_name= ?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        final PixelDataEngineMapsDTO result = new PixelDataEngineMapsDTO();
        jdbcTemplate.queryForObject(query, new Object[]{tableName}, new RowMapper<PixelDataEngineMapsDTO>() {
            @Override
            public PixelDataEngineMapsDTO mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                result.setMap_name(rs.getString("map_name"));
                result.setTable_name(rs.getString("table_name"));
                result.setDescription(rs.getString("description"));
                result.setVersion(rs.getString("version"));
                result.setLoading_in_progress(rs.getBoolean("loading_in_progress"));
                result.setModification_ts(rs.getString("modification_ts"));
                return result;
            }
        });

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    private void truncateTable (JdbcTemplate jt, String table) throws Exception {
        String query = "truncate table pde." + table;
        jt.execute(query);
    }

    private void appendFileWithoutOverride(JdbcTemplate jt,String fileName, String table) throws Exception {
        String query = "LOAD DATA LOCAL INFILE '" + fileName +
                "' INTO TABLE pde." + table + "  FIELDS\n" +
                " TERMINATED BY ',';";
        jt.execute(query);
    }

    private void appendFileWithOverride(JdbcTemplate jt, String table) throws Exception {
        String query = "INSERT INTO pde." + table + " VALUES(?,?)";

        InputStream in = new FileInputStream(new File(Constants.fileUploadingPath));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] stringArray = line.split(",");
            Object[] args = new Object[]{stringArray[0].trim(), stringArray[1].trim()};
            jt.update(query, args);
        }

        if (reader != null) {
            reader.close();
        }

        if (in != null) {
            in.close();
        }

    }

    private boolean inputStreamToFile(MultipartFile file) throws Exception {
        Boolean success = false;
        InputStream inputStream = null;
        BufferedReader br = null;
        FileWriter out = null;
        inputStream = file.getInputStream();
        br = new BufferedReader(new InputStreamReader(inputStream));
        out = new FileWriter(Constants.fileUploadingPath);
        String line;
        while ((line = br.readLine()) != null) {

            out.write(trimLine(line));
            out.write("\n");
            //sb.append(line);
        }

        if (inputStream != null) {
            inputStream.close();
        }

        if (br != null) {
            br.close();
        }

        if (out != null) {
            out.close();
            success = true;
        }

        return success;
    }


    private String trimLine(String line){
        StringBuilder sb = new StringBuilder();
        String[] sa = line.split(",");
        for(String s: sa){
            sb.append(s.trim());
            sb.append(",");
        }

        if(sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}