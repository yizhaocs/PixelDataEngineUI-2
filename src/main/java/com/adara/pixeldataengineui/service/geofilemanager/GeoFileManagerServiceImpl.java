package com.adara.pixeldataengineui.service.geofilemanager;

import com.adara.pixeldataengineui.dao.geofilemanager.GeoFileManagerDAOImpl;
import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginemaps.PdeMapTableDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginemaps.PixelDataEngineMapsDTO;
import com.adara.pixeldataengineui.model.frontend.requestbody.GeoMapCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by yzhao on 7/21/16.
 */
@Service("geoFileManagerService")
@Transactional
public class GeoFileManagerServiceImpl implements GeoFileManagerService {
    @Autowired
    private GeoFileManagerDAOImpl mGeoFileManagerDAOImpl;

    public ResponseDTO createPixelDataEngineMap(GeoMapCreationRequest request) throws Exception{
        return mGeoFileManagerDAOImpl.createPixelDataEngineMap(request);
    }

    public ResponseDTO updatePixelDataEngineMap(GeoMapCreationRequest request) throws Exception{
        return mGeoFileManagerDAOImpl.updatePixelDataEngineMap(request);
    }

    public ResponseDTO deletePixelDataEngineMap(String mapName) throws Exception{
        return mGeoFileManagerDAOImpl.deletePixelDataEngineMap(mapName);
    }

    public GenericDTOList<PixelDataEngineMapsDTO> getPixelDataEngineMaps() throws Exception{
        return mGeoFileManagerDAOImpl.getPixelDataEngineMaps();
    }

    public ResponseDTO append(MultipartFile file, String table) throws Exception {
        return mGeoFileManagerDAOImpl.append(file, table);
    }

    public ResponseDTO override(MultipartFile file, String table) throws Exception {
        return mGeoFileManagerDAOImpl.override(file, table);
    }

    public void getPdeMap(String tableName) throws Exception{
        mGeoFileManagerDAOImpl.getPdeMap(tableName);
    }

    public PixelDataEngineMapsDTO getPixelDataEngineMap(String tableName) throws Exception{
        return mGeoFileManagerDAOImpl.getPixelDataEngineMap(tableName);
    }


    private boolean fileWriter(GenericDTOList<PdeMapTableDTO> data) throws Exception{
        Boolean isDataWritingFinished = false;
        FileWriter out = null;
        try {
            // When you're working with FileWriter you don't have to create the file first,
            // you can simply start writing to it.
            out = new FileWriter("/Users/yzhao/Desktop/output.csv");
//            Collection<PdeMapTableDTO> result = data.list;
//
//            for(PdeMapTableDTO p: result){
//                out.write(p.getValue() + "," + p.getMapped_value() + "\n");
//            }
            String x ="NYNYNYNY, NEW YORK" + "\n";
            int i = 2000000;
            while (i>0) {
                out.write(x);
                i--;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Caught FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Caught IOException: " + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
                isDataWritingFinished = true;
            }
        }
        return isDataWritingFinished;
    }
}
