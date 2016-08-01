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

            //       /* String query = "LOAD DATA LOCAL INFILE '" + fileName +
//                "' INTO TABLE geoip.location  (id,country,state,city,zipcode,latitude,longitude,metrocode,areacode,gmt_offset,cbsa_code,csa_code,md_code,md_title,income,political_affiliation,ethnicity,rent_owned,education,modification_ts);";*/
            String query = "LOAD DATA LOCAL INFILE '" + fileName +
                    "' INTO TABLE geoip."  + table + "  FIELDS\n" +
                    " TERMINATED BY ',';";
            jt.execute(query);
        }


        return response;
    }

    public ResponseEntity<ResponseDTO> override(MultipartFile file, String table) throws Exception {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = new ResponseDTO();
        String fileName = "/Users/yzhao/Desktop/output.csv";

        if (inputStreamToFile(file)) {
            JdbcTemplate jt = new JdbcTemplate(dataSource);

            String firstQuery = "truncate table geoip.location;";

            String secondQuery = "LOAD DATA LOCAL INFILE '" + fileName +
                    "' INTO TABLE geoip." + table + "  FIELDS\n" +
                    " TERMINATED BY ',';";

            jt.execute(firstQuery);
            jt.execute(secondQuery);
        }
/*
        String query = "UPDATE geoip.location SET " + "id" + "=?" + "," + "country" + "=?" + "," + "state" + "=?" + "," + "city" + "=?" + "," + "zipcode" + "=?" + "," + "latitude" + "=?" + "," + "longitude" + "=?"
                + "," + "metrocode" + "=?"
                + "," + "areacode" + "=?"
                + "," + "gmt_offset" + "=?"
                + "," + "cbsa_code" + "=?"
                + "," + "csa_code" + "=?"
                + "," + "md_code" + "=?"
                + "," + "md_title" + "=?"
                + "," + "income" + "=?"
                + "," + "political_affiliation" + "=?"
                + "," + "ethnicity" + "=?"
                + "," + "rent_owned" + "=?"
                + "," + "education" + "=?"
                + "," + "modification_ts" + "=?"
                + " WHERE id=?";
        */
        return response;
    }

    private void readFile(JdbcTemplate jt, String table) throws Exception {
        String query = "UPDATE geoip." + table + " SET " +
                "id" + "=?" + "," +
                "country" + "=?" + "," +
                "state" + "=?" + "," +
                "city" + "=?" + "," +
                "zipcode" + "=?" + "," +
                "latitude" + "=?" + "," +
                "longitude" + "=?" + "," +
                "metrocode" + "=?" + "," +
                "areacode" + "=?" + "," +
                "gmt_offset" + "=?" + "," +
                "cbsa_code" + "=?" + "," +
                "csa_code" + "=?" + "," +
                "md_code" + "=?" + "," +
                "md_title" + "=?" + "," +
                "income" + "=?" + "," +
                "political_affiliation" + "=?" + "," +
                "ethnicity" + "=?" + "," +
                "rent_owned" + "=?" + "," +
                "education" + "=?" + "," +
                "modification_ts" + "=?"
                + " WHERE id=?";

        InputStream in = new FileInputStream(new File("/Users/yzhao/Desktop/output.csv"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] stringArray = line.split(",");
            Object[] args = new Object[]{stringArray[0], stringArray[1], stringArray[2], stringArray[3], stringArray[4], stringArray[5], stringArray[6], stringArray[7], stringArray[8], stringArray[9], stringArray[10], stringArray[11], stringArray[12], stringArray[13], stringArray[14], stringArray[15], stringArray[16], stringArray[17], stringArray[18], stringArray[19], stringArray[20], stringArray[0]};
            jt.update(query, args);
        }

        if(reader != null){
            reader.close();
        }

        if(in!=null){
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
            out.write(line);
            out.write("\n");
            //sb.append(line);
        }

        if(inputStream != null){
            inputStream.close();
        }

        if(br != null){
            br.close();
        }

        if (out != null) {
            out.close();
            success = true;
        }

        return success;

    }
}