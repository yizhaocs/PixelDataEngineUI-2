package com.adara.pixeldataengineui.dao.geofilemanager;

import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.*;

/**
 * Created by yzhao on 7/21/16.
 */
public class LocationDAOImpl implements LocationDAO {
    private static final Log LOG = LogFactory.getLog(LocationDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ResponseEntity<ResponseDTO> append(MultipartFile file, String table) throws Exception {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = new ResponseDTO();
        String fileName = "/Users/yzhao/Desktop/output.csv";

        if (inputStreamToFile(file)) {
            JdbcTemplate jt = new JdbcTemplate(dataSource);
            appendFileWithoutOverride(jt, fileName, table);
            //       /* String query = "LOAD DATA LOCAL INFILE '" + fileName +
//         "' INTO TABLE pde.location  (id,country,state,city,zipcode,latitude,longitude,metrocode,areacode,gmt_offset,cbsa_code,csa_code,md_code,md_title,income,political_affiliation,ethnicity,rent_owned,education,modification_ts);";*/
//            String query = "LOAD DATA LOCAL INFILE '" + fileName +
        }


        return response;
    }

    public ResponseEntity<ResponseDTO> override(MultipartFile file, String table) throws Exception {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = new ResponseDTO();
        String fileName = "/Users/yzhao/Desktop/output.csv";

        if (inputStreamToFile(file)) {
            JdbcTemplate jt = new JdbcTemplate(dataSource);

            truncateTable(jt, table);
            appendFileWithoutOverride(jt, fileName, table);
        }

        return response;
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