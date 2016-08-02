package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginemaps.PixelDataEngineMapsDTO;
import com.adara.pixeldataengineui.service.geofilemanager.GeoFileManagerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yzhao on 7/21/16.
 */
@RestController
public class GeoFileManagerController {
    private static final Log LOG = LogFactory.getLog(GeoFileManagerController.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    public static final String ROOT = "upload-dir";
    @Autowired
    GeoFileManagerService mGeoFileManagerService;

    @RequestMapping(value = "/getGeoMaps", method = RequestMethod.GET)
    public ResponseEntity<GenericDTOList<PixelDataEngineMapsDTO>> getGroups() {
        ResponseEntity<GenericDTOList<PixelDataEngineMapsDTO>> response = null;
        GenericDTOList<PixelDataEngineMapsDTO> retval = null;

        try {
            retval = mGeoFileManagerService.getGeoMaps();
            if (retval.getList().size() == 0) {
                response = new ResponseEntity<GenericDTOList<PixelDataEngineMapsDTO>>(retval, HttpStatus.NO_CONTENT);
            } else {
                response = new ResponseEntity<GenericDTOList<PixelDataEngineMapsDTO>>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[PixelDataEngineGroupController.getGroups] Service error: " + e, e);
            response = new ResponseEntity<GenericDTOList<PixelDataEngineMapsDTO>>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/appendTable")
    public ResponseEntity<ResponseDTO> appendTable(@RequestParam("file") MultipartFile file, @RequestParam(value = "table", required = false) String table
    ) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = new ResponseDTO();

        String x = table;
        System.out.println(x);

        if (!file.isEmpty()) {
            try {
                mGeoFileManagerService.append(file, table);
                //inputStreamToFile(file);
//                Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
//                redirectAttributes.addFlashAttribute("message",
//                        "You successfully uploaded " + file.getOriginalFilename() + "!");
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            } catch (Exception e) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NO_CONTENT);
            // redirectAttributes.addFlashAttribute("message", "Failed to upload " + file.getOriginalFilename() + " because it was empty");
        }

        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/overrideTable")
    public ResponseEntity<ResponseDTO> overrideTable(@RequestParam("file") MultipartFile file, @RequestParam(value = "table", required = false) String table
    ) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = new ResponseDTO();

        if (!file.isEmpty()) {
            try {
                mGeoFileManagerService.override(file, table);
                //inputStreamToFile(file);
//                Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
//                redirectAttributes.addFlashAttribute("message",
//                        "You successfully uploaded " + file.getOriginalFilename() + "!");
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            } catch (Exception e) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NO_CONTENT);
            // redirectAttributes.addFlashAttribute("message", "Failed to upload " + file.getOriginalFilename() + " because it was empty");
        }

        return response;
    }



}
