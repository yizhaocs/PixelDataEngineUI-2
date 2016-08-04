package com.adara.pixeldataengineui.dao.geofilemanager;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginemaps.PdeMapTableDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginemaps.PixelDataEngineMapsDTO;
import com.adara.pixeldataengineui.model.frontend.requestbody.GeoMapCreationRequest;
import com.adara.pixeldataengineui.util.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
        String query = "SELECT a.map_name, a.table_name, a.description FROM pde.pixel_data_engine_maps a";
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
            result.add(mPixelDataEngineMapsDTO);
        }

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }
    public ResponseDTO append(MultipartFile file, String table) throws Exception {
        ResponseDTO retval = new ResponseDTO();
        String fileName = "/Users/yzhao/Desktop/output.csv";

        if (inputStreamToFile(file)) {
            JdbcTemplate jt = new JdbcTemplate(dataSource);
            try {
                retval.setMessage(Constants.SUCCESS);
                appendFileWithoutOverride(jt, fileName, table);
            }catch (Exception e){
                retval.setMessage(Constants.FAILURE);
            }
            //       /* String query = "LOAD DATA LOCAL INFILE '" + fileName +
//         "' INTO TABLE pde.location  (id,country,state,city,zipcode,latitude,longitude,metrocode,areacode,gmt_offset,cbsa_code,csa_code,md_code,md_title,income,political_affiliation,ethnicity,rent_owned,education,modification_ts);";*/
//            String query = "LOAD DATA LOCAL INFILE '" + fileName +
        }


        return retval;
    }

    public ResponseDTO override(MultipartFile file, String table) throws Exception {
        ResponseDTO retval = new ResponseDTO();
        String fileName = "/Users/yzhao/Desktop/output.csv";

        if (inputStreamToFile(file)) {
            JdbcTemplate jt = new JdbcTemplate(dataSource);

            try {
                retval.setMessage(Constants.SUCCESS);
                truncateTable(jt, table);
                appendFileWithoutOverride(jt, fileName, table);
            }catch (Exception e){
                retval.setMessage(Constants.FAILURE);
            }
        }


        return retval;
    }


    public GenericDTOList<PdeMapTableDTO> getPdeMap(String tableName) throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getGroups" + "]";
        String query = "SELECT a.value, a.mapped_value FROM pde.pde_map_" + tableName + " a";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);

        GenericDTOList<PdeMapTableDTO> result = new GenericDTOList<PdeMapTableDTO>();
        for (Map<String, Object> m : listMap) {
            PdeMapTableDTO mPdeMapTableDTO = new PdeMapTableDTO();
            mPdeMapTableDTO.setValue(String.valueOf(m.get("value")));
            mPdeMapTableDTO.setMapped_value(String.valueOf(m.get("mapped_value")));
            result.add(mPdeMapTableDTO);
        }

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }

    public PixelDataEngineMapsDTO getPixelDataEngineMap(String tableName) throws Exception{
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getPixelDataEngineMap" + "]";
        String query = "SELECT a.map_name, a.table_name, a.description FROM pde.pixel_data_engine_maps a where a.map_name= ?";
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

        InputStream in = new FileInputStream(new File("/Users/yzhao/Desktop/output.csv"));
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
        out = new FileWriter("/Users/yzhao/Desktop/output.csv");
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