package com.adara.pixeldataengineui.service.geofilemanager;

import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yzhao on 7/21/16.
 */
public interface GeoFileManagerService {
    ResponseEntity<ResponseDTO> append(MultipartFile file) throws Exception;

    ResponseEntity<ResponseDTO> override(MultipartFile file) throws Exception;
}
