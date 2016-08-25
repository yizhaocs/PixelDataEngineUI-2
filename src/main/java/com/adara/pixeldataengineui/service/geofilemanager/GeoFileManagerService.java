package com.adara.pixeldataengineui.service.geofilemanager;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginemaps.PixelDataEngineMapsDTO;
import com.adara.pixeldataengineui.model.frontend.requestbody.GeoMapCreationRequest;
import com.adara.pixeldataengineui.model.frontend.requestbody.UpdateLoadingInProgressRequest;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yzhao on 7/21/16.
 */
public interface GeoFileManagerService {
    ResponseDTO createPixelDataEngineMap(GeoMapCreationRequest request) throws Exception;

    ResponseDTO updatePixelDataEngineMap(GeoMapCreationRequest request) throws Exception;

    ResponseDTO updateLoadingInProgress(UpdateLoadingInProgressRequest request) throws Exception;

    ResponseDTO deletePixelDataEngineMap(String mapName) throws Exception;

    GenericDTOList<PixelDataEngineMapsDTO> getPixelDataEngineMaps() throws Exception;

    ResponseDTO append(MultipartFile file, String table, String appendWhenCreatingTable) throws Exception;

    ResponseDTO override(MultipartFile file, String table) throws Exception;

    void createCSVFromTable(String tableName) throws Exception;

    PixelDataEngineMapsDTO getPixelDataEngineMap(String tableName) throws Exception;
}
