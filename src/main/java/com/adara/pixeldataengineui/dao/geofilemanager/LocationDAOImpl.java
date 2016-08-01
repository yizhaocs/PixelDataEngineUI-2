package com.adara.pixeldataengineui.dao.geofilemanager;

import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

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

    public ResponseEntity<ResponseDTO> append(MultipartFile file) throws Exception {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = new ResponseDTO();
        String fileName = "/Users/yzhao/Desktop/output.csv";
//       /* String query = "LOAD DATA LOCAL INFILE '" + fileName +
//                "' INTO TABLE geoip.location  (id,country,state,city,zipcode,latitude,longitude,metrocode,areacode,gmt_offset,cbsa_code,csa_code,md_code,md_title,income,political_affiliation,ethnicity,rent_owned,education,modification_ts);";*/
        String query = "LOAD DATA LOCAL INFILE '" + fileName +
                "' INTO TABLE geoip.location  FIELDS\n" +
                " TERMINATED BY ',';";
        if (inputStreamToFile(file)) {
            JdbcTemplate jdbcTemplateDeleteGroup = new JdbcTemplate(dataSource);
            jdbcTemplateDeleteGroup.execute(query);
        }



        return response;
    }

    public ResponseEntity<ResponseDTO> override(MultipartFile file) throws Exception {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = new ResponseDTO();

        return response;
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

        if (out != null) {
            out.close();
            success = true;
        }

        return success;

    }
}