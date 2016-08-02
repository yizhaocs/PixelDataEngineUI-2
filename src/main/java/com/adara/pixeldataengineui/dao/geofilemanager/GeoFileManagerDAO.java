package com.adara.pixeldataengineui.dao.geofilemanager;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginemaps.PixelDataEngineMapsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yzhao on 7/21/16.
 */
public interface GeoFileManagerDAO {
    GenericDTOList<PixelDataEngineMapsDTO> getPixelDataEngineMaps() throws Exception;

    ResponseEntity<ResponseDTO> append(MultipartFile file, String table) throws Exception;

    ResponseEntity<ResponseDTO> override(MultipartFile file, String table) throws Exception;
}
