package com.adara.pixeldataengineui.dao.geofilemanager;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginemaps.PdeMapTableDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginemaps.PixelDataEngineMapsDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yzhao on 7/21/16.
 */
public interface GeoFileManagerDAO {
    ResponseDTO createPixelDataEngineMap(String mapName) throws Exception;

    ResponseDTO deletePixelDataEngineMap(String mapName) throws Exception;

    GenericDTOList<PixelDataEngineMapsDTO> getPixelDataEngineMaps() throws Exception;

    ResponseDTO append(MultipartFile file, String table) throws Exception;

    ResponseDTO override(MultipartFile file, String table) throws Exception;

    GenericDTOList<PdeMapTableDTO> getPdeMap(String tableName) throws Exception;
}
