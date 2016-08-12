package com.adara.pixeldataengineui.dao.geofilemanager;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginemaps.PixelDataEngineMapsDTO;
import com.adara.pixeldataengineui.model.frontend.requestbody.GeoMapCreationRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yzhao on 7/21/16.
 */
public interface GeoFileManagerDAO {
    ResponseDTO createPixelDataEngineMap(GeoMapCreationRequest request) throws Exception;

    ResponseDTO updatePixelDataEngineMap(GeoMapCreationRequest request) throws Exception;

    ResponseDTO deletePixelDataEngineMap(String mapName) throws Exception;

    GenericDTOList<PixelDataEngineMapsDTO> getPixelDataEngineMaps() throws Exception;

    ResponseDTO append(MultipartFile file, String table) throws Exception;

    ResponseDTO override(MultipartFile file, String table) throws Exception;

    void getPdeMap(String tableName) throws Exception;

    PixelDataEngineMapsDTO getPixelDataEngineMap(String tableName) throws Exception;
}
