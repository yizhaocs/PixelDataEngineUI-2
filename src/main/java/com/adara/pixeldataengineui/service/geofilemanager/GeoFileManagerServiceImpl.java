package com.adara.pixeldataengineui.service.geofilemanager;

import com.adara.pixeldataengineui.dao.geofilemanager.GeoFileManagerDAOImpl;
import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginemaps.PixelDataEngineMapsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public GenericDTOList<PixelDataEngineMapsDTO> getGeoMaps() throws Exception{
        return mLocationDAOImpl.getGeoMaps();
    }

    public ResponseEntity<ResponseDTO> append(MultipartFile file, String table) throws Exception {
        return mLocationDAOImpl.append(file, table);
    }

    public ResponseEntity<ResponseDTO> override(MultipartFile file, String table) throws Exception {
        return mLocationDAOImpl.override(file, table);
    }
}
