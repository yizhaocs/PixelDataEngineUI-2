package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.model.backend.dto.usermanagement.UserDTO;
import com.adara.pixeldataengineui.service.usermanagement.UserManagementService;
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
public class UserManagementController {
    private static final Log LOG = LogFactory.getLog(UserManagementController.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();

    @Autowired
    UserManagementService mUserManagementService;

    @RequestMapping(value = "/usermanagement/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody UserDTO request) {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "login" + "]";
        LOG.info(LOG_HEADER + ", " + "request data ->" + request.toString());

        Integer result = 0;
        try {
            result = mUserManagementService.login(request);
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


    @RequestMapping(value = "/usermanagement/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody UserDTO request) {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "createUser" + "]";
        LOG.info(LOG_HEADER + ", " + "request data ->" + request.toString());

        Integer result = 0;
        try {
            result = mUserManagementService.createUser(request);
        } catch (Exception e) {
            LOG.error(LOG_HEADER + " Service error: " + e, e);
        }

        ResponseEntity<String> response = null;
        if (result > 0) {
            response = new ResponseEntity<String>(Constants.SUCCESS_TRUE, HttpStatus.OK);
            // response = new ResponseEntity<String>("{\"success\":\"true\"}", HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>(Constants.SUCCESS_FALSE, HttpStatus.NO_CONTENT);
        }

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + ", " + "ResponseEntity:" + response.toString());

        return response;
    }


    @RequestMapping(value = "/usermanagement/users", method = RequestMethod.GET)
    public ResponseEntity<String> getAllUser() {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getAllUser" + "]";

        String result = "";
        try {
            result = mUserManagementService.getAllUser();
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

    @RequestMapping(value = "/usermanagement/users/{username}", method = RequestMethod.GET)
    public ResponseEntity<String> getByUsername(@PathVariable("username") String username) {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getByUsername" + "]";
        LOG.info(LOG_HEADER + ", " + "request data ->" + username);

        String result = "";
        try {
            result = mUserManagementService.getByUsername(username);
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


    @RequestMapping(value = "/usermanagement/users/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("username") String username) {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteUser" + "]";
        LOG.info(LOG_HEADER + ", " + "request data ->" + username);

        Integer result = 0;
        try {
            result = mUserManagementService.deleteUser(username);
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


    @RequestMapping(value = "/usermanagement/users", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUser(@RequestBody UserDTO request) {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateUser" + "]";
        LOG.info(LOG_HEADER + ", " + "request data ->" + request.toString());

        Integer result = 0;
        try {
            result = mUserManagementService.updateUser(request);
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
