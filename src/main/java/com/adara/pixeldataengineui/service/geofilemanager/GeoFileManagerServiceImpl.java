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

/**
 * Created by yzhao on 7/21/16.
 */
@Service("geoFileManagerService")
@Transactional
public class GeoFileManagerServiceImpl implements GeoFileManagerService {
    @Autowired
    private GeoFileManagerDAOImpl mLocationDAOImpl;

    public ResponseDTO createPixelDataEngineMap(GeoMapCreationRequest request) throws Exception{
        return mLocationDAOImpl.createPixelDataEngineMap(request);
    }

    public ResponseDTO deletePixelDataEngineMap(String mapName) throws Exception{
        return mLocationDAOImpl.deletePixelDataEngineMap(mapName);
    }

    public GenericDTOList<PixelDataEngineMapsDTO> getPixelDataEngineMaps() throws Exception{
        return mLocationDAOImpl.getPixelDataEngineMaps();
    }

    public ResponseDTO append(MultipartFile file, String table) throws Exception {
        return mLocationDAOImpl.append(file, table);
    }

    public ResponseDTO override(MultipartFile file, String table) throws Exception {
        return mLocationDAOImpl.override(file, table);
    }

    public GenericDTOList<PdeMapTableDTO> getPdeMap(String tableName) throws Exception{
        return mLocationDAOImpl.getPdeMap(tableName);
    }
}
