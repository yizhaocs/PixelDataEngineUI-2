package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginerules.PixelDataEngineConfigsDTO;
import com.adara.pixeldataengineui.model.frontend.requestbody.RuleRequest;
import com.adara.pixeldataengineui.service.pixeldataenginerules.PixelDataEngineGroupService;
import com.adara.pixeldataengineui.service.pixeldataenginerules.PixelDataEngineRuleService;
import com.adara.pixeldataengineui.service.pixeldataenginerules.PixelDataEngineService;
import com.adara.pixeldataengineui.util.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@RestController
public class PixelDataEngineRuleController {
    private static final Log LOG = LogFactory.getLog(PixelDataEngineRuleController.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();

    @Autowired
    private PixelDataEngineService mPixelDataEngineService;

    @Autowired
    private PixelDataEngineRuleService mPixelDataEngineRuleService;

    @Autowired
    private PixelDataEngineGroupService mPixelDataEngineGroupService;


    @RequestMapping(value = "/insertRule", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertRule(@RequestBody RuleRequest request) {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "insertRule" + "]";
        LOG.info(LOG_HEADER + ", " + "request data ->" + request.toString());

        Integer result = 0;
        try {
            result = mPixelDataEngineRuleService.insertRule(request, false);
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

    @RequestMapping(value = "/getRules", method = RequestMethod.GET)
    public ResponseEntity<GenericDTOList<PixelDataEngineConfigsDTO>> getRules() {
        ResponseEntity<GenericDTOList<PixelDataEngineConfigsDTO>> response = null;
        GenericDTOList<PixelDataEngineConfigsDTO> retval = null;

        try {
            retval = mPixelDataEngineRuleService.getRules();
            if (retval.getList().size() == 0) {
                response = new ResponseEntity<GenericDTOList<PixelDataEngineConfigsDTO>>(retval, HttpStatus.NO_CONTENT);
            } else {
                response = new ResponseEntity<GenericDTOList<PixelDataEngineConfigsDTO>>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[PixelDataEngineRuleController.getRules] Service error: " + e, e);
            response = new ResponseEntity<GenericDTOList<PixelDataEngineConfigsDTO>>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/getRule", method = RequestMethod.GET)
    public ResponseEntity<PixelDataEngineConfigsDTO> getRule(@RequestParam(value = "gid", required = false) Integer gid, @RequestParam(value = "keyid", required = false) String keyid, @RequestParam(value = "priority", required = false) Integer priority) {
        ResponseEntity<PixelDataEngineConfigsDTO> response = null;
        PixelDataEngineConfigsDTO retval = null;

        if (keyid.equals("0")) {
            response = new ResponseEntity<PixelDataEngineConfigsDTO>(retval, HttpStatus.NO_CONTENT);
        } else {
            try {
                retval = mPixelDataEngineRuleService.getRule(gid, keyid, priority);
                response = new ResponseEntity<PixelDataEngineConfigsDTO>(retval, HttpStatus.OK);
            } catch (Exception e) {
                LOG.error("[PixelDataEngineRuleController.getRule] Service error: " + e, e);
                response = new ResponseEntity<PixelDataEngineConfigsDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return response;
    }

    @RequestMapping(value = "/updateRule", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateRule(@RequestBody RuleRequest request) {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateRule" + "]";
        LOG.info(LOG_HEADER + ", " + "request data ->" + request.toString());

        Integer result = 0;
        try {
            result = mPixelDataEngineRuleService.updateRule(request);
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

    @RequestMapping(value = "/deleteRule", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteRule(@RequestParam(value = "gid", required = false) Integer gid, @RequestParam(value = "keyid", required = false) String keyid, @RequestParam(value = "priority", required = false) Integer priority) {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteRule" + "]";
        LOG.info(LOG_HEADER + ", " + "request data -> gid:" + gid + "  ,keyid:" + keyid + "  ,priority:" + priority);

        Integer result = 0;
        try {
            result = mPixelDataEngineRuleService.deleteRule(gid, keyid, priority, false);
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

    @RequestMapping(value = "/testRule", method = RequestMethod.POST)
    public ResponseEntity<String> testRule(@RequestBody RuleRequest request) {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "testRule" + "]";
        LOG.info(LOG_HEADER + ", " + "request data ->" + request.toString());


        Map<String, String> resultMap = null;
        try {
            resultMap = mPixelDataEngineRuleService.testRule(mPixelDataEngineService, mPixelDataEngineRuleService, mPixelDataEngineGroupService, request);
        } catch (Exception e) {
            LOG.error(LOG_HEADER + " Service error: " + e, e);
        }
        StringBuilder result = new StringBuilder();
        result.append("{\"success\":true,\"data\":");
        result.append("[");
        for (String s : resultMap.keySet()) {
            // we don't take the value is null when we do "ignore Action Rule"
            if (resultMap.get(s) != null) {
                result.append("\"" + s + "|" + resultMap.get(s) + "\"");
                result.append(",");
            }
        }

        if (result.toString().charAt(result.toString().length() - 1) == ',') {
            result.deleteCharAt(result.toString().length() - 1);
        }

        result.append("]");
        result.append("}");
        ResponseEntity<java.lang.String> response = null;
        if (result.toString().length() < 4) {
            response = new ResponseEntity<String>(Constants.SUCCESS_FALSE, HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity<String>(result.toString(), HttpStatus.OK);
        }

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + ", " + "ResponseEntity:" + response.toString());

        return response;
    }
}
