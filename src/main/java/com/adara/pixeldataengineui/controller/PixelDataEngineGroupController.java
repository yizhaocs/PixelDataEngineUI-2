package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.service.pixelmapping.PixelDataEngineGroupService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@RestController
public class PixelDataEngineGroupController {
    private static final Log LOG = LogFactory.getLog(PixelDataEngineGroupController.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();

    @Autowired
    PixelDataEngineGroupService mPixelDataEngineGroupService;

    @RequestMapping(value = "/getGroups", method = RequestMethod.GET)
    public ResponseEntity<String> getGroups() {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getGroups");

        String result = "";
        result = mPixelDataEngineGroupService.getGroups();

        ResponseEntity<String> response = null;
        if (result.length() < 4) {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity<String>(result, HttpStatus.OK);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getGroups" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }
}
