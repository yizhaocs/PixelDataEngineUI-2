package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.model.frontend.requestbody.InsertUpdateRequest;
import com.adara.pixeldataengineui.service.pixelmapping.PixelDataEngineGroupService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@RestController
public class PixelDataEngineGroupController {
    private static final Log LOG = LogFactory.getLog(PixelDataEngineGroupController.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();

    @Autowired
    PixelDataEngineGroupService mPixelDataEngineGroupService;


    @RequestMapping(value = "/insertGroup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertGroup(@RequestBody InsertUpdateRequest request) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertGroup");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertGroup" + ", " + "request data ->" + request.toString());

        Integer result = 0;

        result = mPixelDataEngineGroupService.insertGroup(request.getMapping().getTriggering_key_id(), request.getMapping().getGroup_type());

        ResponseEntity<String> response = null;
        if (result > 0) {
            response = new ResponseEntity<String>("{\"status\":\"Success\"}", HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertGroup" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public ResponseEntity<String> group(@RequestParam(value = "id", required = false) String id) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "mapping");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "mapping" + ", " + "request data ->" + "id:" + id);

        String result = "";
        if (id.equals("0")) {
            return new ResponseEntity<String>(result, HttpStatus.NO_CONTENT);
        }

        result = mPixelDataEngineGroupService.getGroup(id);

        ResponseEntity<String> response = null;
        if (result.length() < 4) {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity<String>(result, HttpStatus.OK);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "mapping" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }

    @RequestMapping(value = "/samegroup", method = RequestMethod.GET)
    public ResponseEntity<String> samegroup(@RequestParam(value = "id", required = false) Integer id) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "samegroup");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "samegroup" + ", " + "request data ->" + "id:" + id);

        String result = "";
        if (id.equals("0")) {
            return new ResponseEntity<String>(result, HttpStatus.NO_CONTENT);
        }

        result = mPixelDataEngineGroupService.getSameGroup(id);

        ResponseEntity<String> response = null;
        if (result.length() < 4) {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity<String>(result, HttpStatus.OK);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "samegroup" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }

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

    @RequestMapping(value = "/updateGroup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateGroup(@RequestBody InsertUpdateRequest request) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateGroup");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateGroup" + ", " + "request data ->" + request.toString());

        Integer result = 0;

        result = mPixelDataEngineGroupService.updateGroup(request.getMapping().getTriggering_key_id(), request.getMapping().getGroup_type());

        ResponseEntity<String> response = null;
        if (result > 0) {
            response = new ResponseEntity<String>("{\"status\":\"Success\"}", HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateGroup" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteGroup(@RequestParam(value = "id", required = false) String id) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteGroup");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteGroup" + ", " + "request data ->" + "id:" + id);

        Integer result = 0;
        if (id.equals("0")) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        result = mPixelDataEngineGroupService.deleteGroup(id);

        ResponseEntity<String> response = null;
        if (result > 0) {
            response = new ResponseEntity<String>("{\"status\":\"Success\"}", HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteGroup" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }
}
