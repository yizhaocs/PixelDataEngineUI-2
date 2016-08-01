package com.adara.pixeldataengineui.dao.geofilemanager;

import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yzhao on 7/21/16.
 */
public interface LocationDAO {
    ResponseEntity<ResponseDTO> append(MultipartFile file, String table) throws Exception;

    ResponseEntity<ResponseDTO> override(MultipartFile file, String table) throws Exception;
}
