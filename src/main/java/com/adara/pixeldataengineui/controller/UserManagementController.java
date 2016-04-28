package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.model.backend.dto.usermanagement.UserDTO;
import com.adara.pixeldataengineui.service.usermanagement.UserManagementService;
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
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "login");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "login" + ", " + "request data ->" + request.toString());

        Integer result = 0;
        result = mUserManagementService.login(request);

        ResponseEntity<String> response = null;
        if (result > 0) {
            response = new ResponseEntity<String>("{\"status\":\"Success\"}", HttpStatus.OK);
            // response = new ResponseEntity<String>("{\"success\":\"true\"}", HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "login" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }


    @RequestMapping(value = "/usermanagement/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody UserDTO request) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "createUser");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "createUser" + ", " + "request data ->" + request.toString());

        Integer result = 0;
        result = mUserManagementService.createUser(request);

        ResponseEntity<String> response = null;
        if (result > 0) {
            response = new ResponseEntity<String>("{\"status\":\"Success\"}", HttpStatus.OK);
            // response = new ResponseEntity<String>("{\"success\":\"true\"}", HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "createUser" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }


    @RequestMapping(value = "/usermanagement/users", method = RequestMethod.GET)
    public ResponseEntity<String> getAllUser() {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getAllUser");

        String result = "";
        result = mUserManagementService.getAllUser();

        ResponseEntity<String> response = null;
        if (result.length() < 4) {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity<String>(result, HttpStatus.OK);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getAllUser" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }

    @RequestMapping(value = "/usermanagement/users/{username}", method = RequestMethod.GET)
    public ResponseEntity<String> getByUsername(@PathVariable("username") String username) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getByUsername");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getByUsername" + ", " + "request data ->" + username);

        String result = "";
        result = mUserManagementService.getByUsername(username);

        ResponseEntity<String> response = null;
        if (result.length() < 4) {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity<String>(result, HttpStatus.OK);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getByUsername" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }


    @RequestMapping(value = "/usermanagement/users/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("username") String username) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteUser");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteUser" + ", " + "request data ->" + username);

        Integer result = 0;
        result = mUserManagementService.deleteUser(username);

        ResponseEntity<String> response = null;
        if (result > 0) {
            response = new ResponseEntity<String>("{\"status\":\"Success\"}", HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteUser" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }


    @RequestMapping(value = "/usermanagement/users", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUser(@RequestBody UserDTO request) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateUser");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateUser" + ", " + "request data ->" + request.toString());

        Integer result = 0;
        result = mUserManagementService.updateUser(request);

        ResponseEntity<String> response = null;
        if (result > 0) {
            response = new ResponseEntity<String>("{\"status\":\"Success\"}", HttpStatus.OK);
            // response = new ResponseEntity<String>("{\"success\":\"true\"}", HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateUser" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }
}
