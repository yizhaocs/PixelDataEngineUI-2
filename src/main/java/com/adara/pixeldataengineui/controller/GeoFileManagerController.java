package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginemaps.PdeMapTableDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginemaps.PixelDataEngineMapsDTO;
import com.adara.pixeldataengineui.model.frontend.requestbody.GeoMapCreationRequest;
import com.adara.pixeldataengineui.service.geofilemanager.GeoFileManagerService;
import com.adara.pixeldataengineui.util.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(method = RequestMethod.POST, value = "/createPixelDataEngineMap", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createPixelDataEngineMap(@RequestBody GeoMapCreationRequest request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mGeoFileManagerService.createPixelDataEngineMap(request);
            if(retval.getMessage().equals(Constants.FAILURE)){
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            }else{
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[GeoFileManagerController.createPixelDataEngineMap] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updatePixelDataEngineMap", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updatePixelDataEngineMap(@RequestBody GeoMapCreationRequest request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mGeoFileManagerService.updatePixelDataEngineMap(request);
            if(retval.getMessage().equals(Constants.FAILURE)){
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            }else{
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[GeoFileManagerController.updatePixelDataEngineMap] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deletePixelDataEngineMap")
    public ResponseEntity<ResponseDTO> deletePixelDataEngineMap(@RequestParam("mapname") String mapName) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mGeoFileManagerService.deletePixelDataEngineMap(mapName);
            if(retval.getMessage().equals(Constants.FAILURE)){
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            }else{
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }

        } catch (Exception e) {
            LOG.error("[GeoFileManagerController.deletePixelDataEngineMap] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/getPixelDataEngineMaps", method = RequestMethod.GET)
    public ResponseEntity<GenericDTOList<PixelDataEngineMapsDTO>> getPixelDataEngineMaps() {
        ResponseEntity<GenericDTOList<PixelDataEngineMapsDTO>> response = null;
        GenericDTOList<PixelDataEngineMapsDTO> retval = null;

        try {
            retval = mGeoFileManagerService.getPixelDataEngineMaps();
            if (retval.getList().size() == 0) {
                response = new ResponseEntity<GenericDTOList<PixelDataEngineMapsDTO>>(retval, HttpStatus.NO_CONTENT);
            } else {
                response = new ResponseEntity<GenericDTOList<PixelDataEngineMapsDTO>>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[GeoFileManagerController.getPixelDataEngineMaps] Service error: " + e, e);
            response = new ResponseEntity<GenericDTOList<PixelDataEngineMapsDTO>>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/getPixelDataEngineMap", method = RequestMethod.GET)
    public ResponseEntity<PixelDataEngineMapsDTO> getPixelDataEngineMap(@RequestParam(value = "mapname", required = false) String mapName) {
        ResponseEntity<PixelDataEngineMapsDTO> response = null;
        PixelDataEngineMapsDTO retval = null;

        if (mapName.equals("0") || mapName.equals("undefined")) {
            response = new ResponseEntity<PixelDataEngineMapsDTO>(retval, HttpStatus.NO_CONTENT);
            return response;
        }
        try {
            retval = mGeoFileManagerService.getPixelDataEngineMap(mapName);

                response = new ResponseEntity<PixelDataEngineMapsDTO>(retval, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("[GeoFileManagerController.getPixelDataEngineMap] Service error: " + e, e);
            response = new ResponseEntity<PixelDataEngineMapsDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/appendTable")
    public ResponseEntity<ResponseDTO> appendTable(@RequestParam("file") MultipartFile file, @RequestParam(value = "table", required = false) String table
    ) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        if (!file.isEmpty()) {
            try {
                retval = mGeoFileManagerService.append(file, table);
                if(retval.getMessage().equals(Constants.FAILURE)){
                    response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
                }else{
                    response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
                }
            } catch (Exception e) {
                LOG.error("[GeoFileManagerController.appendTable] Service error: " + e, e);
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NO_CONTENT);
        }

        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/overrideTable")
    public ResponseEntity<ResponseDTO> overrideTable(@RequestParam("file") MultipartFile file, @RequestParam(value = "table", required = false) String table
    ) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        if (!file.isEmpty()) {
            try {
                retval = mGeoFileManagerService.override(file, table);
                if(retval.getMessage().equals(Constants.FAILURE)){
                    response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
                }else{
                    response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
                }
            } catch (Exception e) {
                LOG.error("[GeoFileManagerController.overrideTable] Service error: " + e, e);
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NO_CONTENT);
        }

        return response;
    }


    @RequestMapping(value = "/getPdeMap", method = RequestMethod.GET)
    public ResponseEntity<GenericDTOList<PdeMapTableDTO>> getPdeMap(@RequestParam(value = "mapname", required = false) String mapName) {
        ResponseEntity<GenericDTOList<PdeMapTableDTO>> response = null;
        GenericDTOList<PdeMapTableDTO> retval = null;

        if (mapName.equals("0") || mapName.equals("undefined")) {
            response = new ResponseEntity<GenericDTOList<PdeMapTableDTO>>(retval, HttpStatus.NO_CONTENT);
            return response;
        }
        try {
            retval = mGeoFileManagerService.getPdeMap(mapName);
            if (retval.getList().size() == 0) {
                response = new ResponseEntity<GenericDTOList<PdeMapTableDTO>>(retval, HttpStatus.NO_CONTENT);
            } else {
                response = new ResponseEntity<GenericDTOList<PdeMapTableDTO>>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[GeoFileManagerController.getPdeMap] Service error: " + e, e);
            response = new ResponseEntity<GenericDTOList<PdeMapTableDTO>>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
