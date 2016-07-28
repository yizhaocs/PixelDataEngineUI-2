package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.PixelDataEngineConfigsDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.PixelDataEngineGroupsDTO;
import com.adara.pixeldataengineui.model.frontend.requestbody.InsertUpdateRequest;
import com.adara.pixeldataengineui.service.pixelmapping.PixelDataEngineGroupService;
import com.adara.pixeldataengineui.util.Constants;
import com.adara.pixeldataengineui.util.Tools;
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
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "insertGroup" + "]";
        LOG.info(LOG_HEADER + ", " + "request data ->" + request.toString());

        Integer result = 0;

        try {
            result = mPixelDataEngineGroupService.insertGroup(request.getMapping().getTriggering_key_id(), request.getMapping().getGroup_type(), false);
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

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public ResponseEntity<PixelDataEngineGroupsDTO> getGroup(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<PixelDataEngineGroupsDTO> response = null;
        PixelDataEngineGroupsDTO retval = null;

        if(id.equals("0")){
            response = new ResponseEntity<PixelDataEngineGroupsDTO>(retval, HttpStatus.NO_CONTENT);
        }else {
            try {
                retval = mPixelDataEngineGroupService.getGroup(id);
                response = new ResponseEntity<PixelDataEngineGroupsDTO>(retval, HttpStatus.OK);
            } catch (Exception e) {
                LOG.error("[PixelDataEngineGroupController.group] Service error: " + e, e);
                response = new ResponseEntity<PixelDataEngineGroupsDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return response;
    }

    @RequestMapping(value = "/samegroup", method = RequestMethod.GET)
    public ResponseEntity<GenericDTOList<PixelDataEngineConfigsDTO>> samegroup(@RequestParam(value = "id", required = false) Integer id) {
        ResponseEntity<GenericDTOList<PixelDataEngineConfigsDTO>> response = null;
        GenericDTOList<PixelDataEngineConfigsDTO> retval = null;

        if(id.equals("0")){
            response = new ResponseEntity<GenericDTOList<PixelDataEngineConfigsDTO>>(retval, HttpStatus.NO_CONTENT);
        }else {
            try {
                retval = mPixelDataEngineGroupService.getSameGroup(id);
                if (retval.getList().size() == 0) {
                    response = new ResponseEntity<GenericDTOList<PixelDataEngineConfigsDTO>>(retval, HttpStatus.NO_CONTENT);
                } else {
                    response = new ResponseEntity<GenericDTOList<PixelDataEngineConfigsDTO>>(retval, HttpStatus.OK);
                }
            } catch (Exception e) {
                LOG.error("[PixelDataEngineGroupController.samegroup] Service error: " + e, e);
                response = new ResponseEntity<GenericDTOList<PixelDataEngineConfigsDTO>>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return response;
    }

    @RequestMapping(value = "/getGroups", method = RequestMethod.GET)
    public ResponseEntity<String> getGroups() {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getGroups" + "]";

        String result = "";
        try {
            result = mPixelDataEngineGroupService.getGroups();
        } catch (Exception e) {
            LOG.error(LOG_HEADER + " Service error: " + e, e);
        }

        ResponseEntity<String> response = null;
        if (result.length() < 4) {
            response = new ResponseEntity<String>(Constants.SUCCESS_FALSE, HttpStatus.NO_CONTENT);
        } else {
            StringBuilder sb = Tools.resultMaker(result);
            response = new ResponseEntity<String>(sb.toString(), HttpStatus.OK);
        }

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + ", " + "ResponseEntity:" + response.toString());

        return response;
    }

    @RequestMapping(value = "/updateGroup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateGroup(@RequestBody InsertUpdateRequest request) {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateGroup" + "]";
        LOG.info(LOG_HEADER + ", " + "request data ->" + request.toString());

        Integer result = 0;

        try {
            result = mPixelDataEngineGroupService.updateGroup(request.getMapping().getTriggering_key_id(), request.getMapping().getGroup_type());
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

    @RequestMapping(value = "/deleteGroup", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteGroup(@RequestParam(value = "triggerkeyid", required = false) String triggerKeyId, @RequestParam(value = "gid", required = false) String gid) {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteGroup" + "]";
        LOG.info(LOG_HEADER + ", " + "request data ->" + "triggerKeyId:" + triggerKeyId);

        Integer result = 0;
        if (triggerKeyId.equals("0")) {
            return new ResponseEntity<String>(Constants.SUCCESS_FALSE, HttpStatus.NO_CONTENT);
        }

        try {
            result = mPixelDataEngineGroupService.deleteGroup(triggerKeyId, gid, false);
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

}
