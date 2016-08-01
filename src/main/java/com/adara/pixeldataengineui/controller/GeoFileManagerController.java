package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @RequestMapping(method = RequestMethod.POST, value = "/fileUpload")
    public ResponseEntity<ResponseDTO>  handleFileUpload(@RequestParam("file") MultipartFile file,
                                                         RedirectAttributes redirectAttributes) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = new ResponseDTO();



        if (!file.isEmpty()) {
            try {
                mGeoFileManagerService.append(file);
                //inputStreamToFile(file);
//                Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
//                redirectAttributes.addFlashAttribute("message",
//                        "You successfully uploaded " + file.getOriginalFilename() + "!");
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }catch (Exception  e) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NO_CONTENT);
           // redirectAttributes.addFlashAttribute("message", "Failed to upload " + file.getOriginalFilename() + " because it was empty");
        }

        return response;
    }



/*
    @RequestMapping(value = "/appendLocationTable", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> appendLocationTable(@RequestBody GeoFileManagerRequest request) {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "appendLocationTable" + "]";
        LOG.info(LOG_HEADER + ", " + "request data ->" + request.toString());

        Integer result = 0;

        try {
            result = mGeoFileManagerService.append(request);
        } catch (Exception e) {
            LOG.error(LOG_HEADER + " Service error: " + e, e);
        }
        ResponseEntity<String> response = null;
        if (result > 0) {
            response = new ResponseEntity<String>(Constants.SUCCESS_TRUE, HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>(Constants.SUCCESS_FALSE, HttpStatus.NO_CONTENT);
        }

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + ", " + "ResponseEntity:" + response.toString());

        return response;
    }

    @RequestMapping(value = "/overrideLocationTable", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> overrideLocationTable(@RequestBody GeoFileManagerRequest request) {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "overrideLocationTable" + "]";
        LOG.info(LOG_HEADER + ", " + "request data ->" + request.toString());

        Integer result = 0;

        try {
            result = mGeoFileManagerService.override(request);
        } catch (Exception e) {
            LOG.error(LOG_HEADER + " Service error: " + e, e);
        }
        ResponseEntity<String> response = null;
        if (result > 0) {
            response = new ResponseEntity<String>(Constants.SUCCESS_TRUE, HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>(Constants.SUCCESS_FALSE, HttpStatus.NO_CONTENT);
        }

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + ", " + "ResponseEntity:" + response.toString());

        return response;
    }
    */
}
