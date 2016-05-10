package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.model.frontend.requestbody.RuleRequest;
import com.adara.pixeldataengineui.service.pixelmapping.PixelDataEngineRuleService;
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
    PixelDataEngineRuleService mPixelDataEngineRuleService;

    @RequestMapping(value = "/insertRule", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertRule(@RequestBody RuleRequest request) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertRule");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertRule" + ", " + "request data ->" + request.toString());

        Integer result = 0;
        result = mPixelDataEngineRuleService.insertRule(request);

        ResponseEntity<String> response = null;
        if (result > 0) {
            response = new ResponseEntity<String>("{\"status\":\"Success\"}", HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertRule" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }

    @RequestMapping(value = "/getRules", method = RequestMethod.GET)
    public ResponseEntity<String> getRules() {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getRules");

        String result = "";
        result = mPixelDataEngineRuleService.getRules();

        ResponseEntity<String> response = null;
        if (result.length() < 4) {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity<String>(result, HttpStatus.OK);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getRules" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }

    @RequestMapping(value = "/getRule", method = RequestMethod.GET)
    public ResponseEntity<String> getRule(@RequestParam(value = "gid", required = false) Integer gid, @RequestParam(value = "keyid", required = false) String keyid, @RequestParam(value = "priority", required = false) Integer priority) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getRule");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getRule" + ", " + "request data -> gid:" + gid + "  ,keyid:" + keyid + "  ,priority:" + priority);

        if (keyid.equals("0")) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        String result = "";
        result = mPixelDataEngineRuleService.getRule(gid, keyid, priority);

        ResponseEntity<String> response = null;
        if (result.length() < 4) {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity<String>(result, HttpStatus.OK);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getRule" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }

    @RequestMapping(value = "/updateRule", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateRule(@RequestBody RuleRequest request) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateRule");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateRule" + ", " + "request data ->" + request.toString());

        Integer result = 0;
        result = mPixelDataEngineRuleService.updateRule(request);

        ResponseEntity<String> response = null;
        if (result > 0) {
            response = new ResponseEntity<String>("{\"status\":\"Success\"}", HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateRule" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }

    @RequestMapping(value = "/deleteRule", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteRule(@RequestParam(value = "gid", required = false) Integer gid, @RequestParam(value = "keyid", required = false) String keyid, @RequestParam(value = "priority", required = false) Integer priority) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteRule");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteRule" + ", " + "request data -> gid:" + gid + "  ,keyid:" + keyid + "  ,priority:" + priority);

        Integer result = 0;
        result = mPixelDataEngineRuleService.deleteRule(gid, keyid, priority);

        ResponseEntity<String> response = null;
        if (result > 0) {
            response = new ResponseEntity<String>("{\"status\":\"Success\"}", HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteRule" + ", " + "ResponseEntity:" + response.toString());
        return response;
    }

    @RequestMapping(value = "/testRule", method = RequestMethod.POST)
    public ResponseEntity<String> testRule(@RequestBody RuleRequest request) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "testRule");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "testRule" + ", " + "request data ->" + request.toString());


        Map<String, String> resultMap = mPixelDataEngineRuleService.testRule(request);
        StringBuilder result = new StringBuilder();
        result.append("{");
        result.append("\"data\":");
        result.append("[");
        for(String s: resultMap.keySet()){
            result.append("\"" + s + "|" + resultMap.get(s) + "\"");
            result.append(",");
        }

        if(resultMap.size() != 0){
            result.deleteCharAt(result.toString().length() - 1);
        }

        result.append("]");
        result.append("}");
        ResponseEntity<java.lang.String> response = null;
        if (result.toString().length() < 4) {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity<String>(result.toString(), HttpStatus.OK);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateRule" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }
}
