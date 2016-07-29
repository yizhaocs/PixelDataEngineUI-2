package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
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
    public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mUserManagementService.login(request);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("[UserManagementController.login] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }


    @RequestMapping(value = "/usermanagement/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createUser(@RequestBody UserDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mUserManagementService.createUser(request);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("[UserManagementController.createUser] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }


    @RequestMapping(value = "/usermanagement/users", method = RequestMethod.GET)
    public ResponseEntity<GenericDTOList<UserDTO>> getAllUser() {
        ResponseEntity<GenericDTOList<UserDTO>> response = null;
        GenericDTOList<UserDTO> retval = null;

        try {
            retval = mUserManagementService.getAllUser();
            if (retval.getList().size() == 0) {
                response = new ResponseEntity<GenericDTOList<UserDTO>>(retval, HttpStatus.NO_CONTENT);
            } else {
                response = new ResponseEntity<GenericDTOList<UserDTO>>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[UserManagementController.getAllUser] Service error: " + e, e);
            response = new ResponseEntity<GenericDTOList<UserDTO>>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/usermanagement/users/{username}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getByUsername(@PathVariable("username") String username) {
        ResponseEntity<UserDTO> response = null;
        UserDTO retval = null;


        try {
            retval = mUserManagementService.getByUsername(username);
            response = new ResponseEntity<UserDTO>(retval, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("[UserManagementController.getByUsername] Service error: " + e, e);
            response = new ResponseEntity<UserDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }


    @RequestMapping(value = "/usermanagement/users/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("username") String username) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mUserManagementService.deleteUser(username);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("[UserManagementController.deleteUser] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }


    @RequestMapping(value = "/usermanagement/users", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateUser(@RequestBody UserDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mUserManagementService.updateUser(request);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("[UserManagementController.updateUser] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
