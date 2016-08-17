package com.adara.pixeldataengineui.service.geofilemanager;

import com.adara.pixeldataengineui.dao.geofilemanager.GeoFileManagerDAOImpl;
import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginemaps.PixelDataEngineMapsDTO;
import com.adara.pixeldataengineui.model.frontend.requestbody.GeoMapCreationRequest;
import com.adara.pixeldataengineui.model.frontend.requestbody.UpdateLoadingInProgressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yzhao on 7/21/16.
 */
@Service("geoFileManagerService")
@Transactional
public class GeoFileManagerServiceImpl implements GeoFileManagerService {
    @Autowired
    private GeoFileManagerDAOImpl mGeoFileManagerDAOImpl;

    public ResponseDTO createPixelDataEngineMap(GeoMapCreationRequest request) throws Exception {
        return mGeoFileManagerDAOImpl.createPixelDataEngineMap(request);
    }

    public ResponseDTO updatePixelDataEngineMap(GeoMapCreationRequest request) throws Exception {
        return mGeoFileManagerDAOImpl.updatePixelDataEngineMap(request);
    }

    public ResponseDTO updateLoadingInProgress(UpdateLoadingInProgressRequest request) throws Exception {
        return mGeoFileManagerDAOImpl.updateLoadingInProgress(request);
    }

    public ResponseDTO deletePixelDataEngineMap(String mapName) throws Exception {
        return mGeoFileManagerDAOImpl.deletePixelDataEngineMap(mapName);
    }

    public GenericDTOList<PixelDataEngineMapsDTO> getPixelDataEngineMaps() throws Exception {
        return mGeoFileManagerDAOImpl.getPixelDataEngineMaps();
    }

    public ResponseDTO append(MultipartFile file, String table) throws Exception {
        return mGeoFileManagerDAOImpl.append(file, table);
    }

    public ResponseDTO override(MultipartFile file, String table) throws Exception {
        return mGeoFileManagerDAOImpl.override(file, table);
    }

    public void getPdeMap(String tableName) throws Exception {
        mGeoFileManagerDAOImpl.getPdeMap(tableName);
    }

    public PixelDataEngineMapsDTO getPixelDataEngineMap(String tableName) throws Exception {
        return mGeoFileManagerDAOImpl.getPixelDataEngineMap(tableName);
    }
}
