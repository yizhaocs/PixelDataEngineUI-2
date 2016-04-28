package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.model.frontend.requestbody.InsertUpdateRequest;
import com.adara.pixeldataengineui.model.frontend.requestbody.RuleRequest;
import com.adara.pixeldataengineui.model.backend.dto.usermanagement.UserDTO;
import com.adara.pixeldataengineui.service.pixelmapping.PixelDataEngineRuleService;
import com.adara.pixeldataengineui.service.pixelmapping.*;
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
public class PixelDataEngineUIController {
    private static final Log LOG = LogFactory.getLog(PixelDataEngineUIController.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();

    @Autowired
    AdobeService mAdobeService;
    @Autowired
    FacebookService mFacebookService;
    @Autowired
    LiverampService mLiverampService;
    @Autowired
    UserManagementService mUserManagementService;
    @Autowired
    DeriveComboPixelService mDeriveComboPixelService;
    @Autowired
    KruxDpkeyService mKruxDpkeyService;
    @Autowired
    DbmConversionPixelMappingsService mDbmConversionPixelMappingsService;
    @Autowired
    PixelDataEngineRuleService mPixelDataEngineRuleService;

    @RequestMapping(value = "/mappings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> mappings(@RequestParam("type") String type) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "mappings");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "mappings" + ", " + "request data ->" + "type:" + type);
        if (type == null || type.equals("")) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        String result = "";
        if (type.equals("adobe")) {
            result = mAdobeService.getMappings();
        } else if (type.equals("liveramp-dp")) {
            result = mLiverampService.getLiverampDpMappings();
        } else if (type.equals("liveramp-key")) {
            result = mLiverampService.getLiverampKeyMappings();
        } else if (type.equals("facebook-pixel")) {
            result = mFacebookService.getFacebookPixelMappings();
        } else if (type.equals("facebook-dp")) {
            result = mFacebookService.getFacebookDpMappings();
        } else if (type.equals("facebook-key")) {
            result = mFacebookService.getFacebookKeyMappings();
        } else if (type.equals("derive-conversion")) {
            result = mDeriveComboPixelService.getMappings();
        } else if (type.equals("krux-dpkey")) {
            result = mKruxDpkeyService.getMappings();
        } else if (type.equals("dbm")) {
            result = mDbmConversionPixelMappingsService.getMappings();
        }

        ResponseEntity<String> response = null;
        if (result.length() < 4) {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        } else {
            response = new ResponseEntity<String>(result, HttpStatus.OK);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "mappings" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }

    @RequestMapping(value = "/mapping", method = RequestMethod.GET)
    public ResponseEntity<String> mapping(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "type", required = false) String type) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "mapping");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "mapping" + ", " + "request data ->" + "id:" + id + " ,type:" + type);
        if (type == null || type.equals("") || id == null || id.equals("")) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        String result = "";
        if (id.equals("0")) {
            return new ResponseEntity<String>(result, HttpStatus.NO_CONTENT);
        }

        if (type.equals("adobe")) {
            result = mAdobeService.getMapping(id);
        } else if (type.equals("liveramp-dp")) {
            result = mLiverampService.getLiverampDpMapping(id);
        } else if (type.equals("liveramp-key")) {
            result = mLiverampService.getLiverampKeyMapping(id);
        } else if (type.equals("facebook-pixel")) {
            result = mFacebookService.getFacebookPixelMapping(id);
        } else if (type.equals("facebook-dp")) {
            result = mFacebookService.getFacebookDpMapping(id);
        } else if (type.equals("facebook-key")) {
            result = mFacebookService.getFacebookKeyMapping(id);
        } else if (type.equals("derive-conversion")) {
            result = mDeriveComboPixelService.getMapping(id);
        } else if (type.equals("krux-dpkey")) {
            result = mKruxDpkeyService.getMapping(id);
        } else if (type.equals("dbm")) {
            result = mDbmConversionPixelMappingsService.getMapping(id);
        }

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

    @RequestMapping(value = "/insertMapping", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertMapping(@RequestBody InsertUpdateRequest request) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertMapping");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertMapping" + ", " + "request data ->" + request.toString());

        Integer result = 0;
        if (request.getType().equals("adobe")) {
            result = mAdobeService.insertMapping(request.getMapping().getAdobe_segment_id(), request.getMapping().getDp_key_id());
        } else if (request.getType().equals("liveramp-dp")) {
            result = mLiverampService.insertLiverampDpMapping(request.getMapping().getDp_name(), request.getMapping().getDp_id(), request.getMapping().getThreshold_mb());
        } else if (request.getType().equals("liveramp-key")) {
            result = mLiverampService.insertLiverampKeyMapping(request.getMapping().getLiveramp_segment_id(), request.getMapping().getDp_key_id(), request.getMapping().getValue());
        } else if (request.getType().equals("facebook-pixel")) {
            result = mFacebookService.insertMappingDataProviderFacebookPixels(request.getMapping().getDp_id(), request.getMapping().getFacebook_pixel_id());
        } else if (request.getType().equals("facebook-key")) {
            Byte useImagePixel = request.getMapping().getUse_image_pixel() == false ? (byte) 0 : 1;
            result = mFacebookService.insertMappingFacebookDpKeys(request.getMapping().getKey_id(), request.getMapping().getEnabled(), request.getMapping().getUpdate_interval(), useImagePixel);
        } else if (request.getType().equals("derive-conversion")) {
            result = mDeriveComboPixelService.insertMapping(request.getMapping().getDp_key_id(), request.getMapping().getAdvertiser_id(), request.getMapping().getCp_id());
        } else if (request.getType().equals("krux-dpkey")) {
            result = mKruxDpkeyService.insertMapping(request.getMapping().getKrux_segment_id(), request.getMapping().getDp_key_id());
        } else if (request.getType().equals("dbm")) {
            result = mDbmConversionPixelMappingsService.insertMapping(request.getMapping().getConversion_pixel_id(), request.getMapping().getDbm_url());
        }

        ResponseEntity<String> response = null;
        if (result > 0) {
            response = new ResponseEntity<String>("{\"status\":\"Success\"}", HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "insertMapping" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }


    @RequestMapping(value = "/updateMapping", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateMapping(@RequestBody InsertUpdateRequest request) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateMapping");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateMapping" + ", " + "request data ->" + request.toString());

        Integer result = 0;
        if (request.getType().equals("adobe")) {
            result = mAdobeService.updateMapping(request.getMapping().getAdobe_segment_id(), request.getMapping().getDp_key_id());
            LOG.info("result:" + result);
        } else if (request.getType().equals("liveramp-dp")) {
            result = mLiverampService.updateLiverampDpMapping(request.getMapping().getDp_name(), request.getMapping().getDp_id(), request.getMapping().getThreshold_mb());
        } else if (request.getType().equals("liveramp-key")) {
            result = mLiverampService.updateLiverampKeyMapping(request.getMapping().getLiveramp_segment_id(), request.getMapping().getDp_key_id(), request.getMapping().getValue());
        } else if (request.getType().equals("facebook-pixel")) {
            result = mFacebookService.updateMappingDataProviderFacebookPixels(request.getMapping().getDp_id(), request.getMapping().getFacebook_pixel_id());
        } else if (request.getType().equals("facebook-dp")) {
            Byte syncFacebook = request.getMapping().getSync_facebook() == false ? (byte) 0 : 1;
            result = mFacebookService.updateMappingDataProviders(request.getMapping().getId(), request.getMapping().getName(), syncFacebook);
        } else if (request.getType().equals("facebook-key")) {
            Byte useImagePixel = request.getMapping().getUse_image_pixel() == false ? (byte) 0 : 1;
            result = mFacebookService.updateMappingFacebookDpKeys(request.getMapping().getKey_id(), request.getMapping().getEnabled(), request.getMapping().getUpdate_interval(), useImagePixel);
        } else if (request.getType().equals("derive-conversion")) {
            result = mDeriveComboPixelService.updateMapping(request.getMapping().getDp_key_id(), request.getMapping().getAdvertiser_id(), request.getMapping().getCp_id());
        } else if (request.getType().equals("krux-dpkey")) {
            result = mKruxDpkeyService.updateMapping(request.getMapping().getKrux_segment_id(), request.getMapping().getDp_key_id());
        } else if (request.getType().equals("dbm")) {
            result = mDbmConversionPixelMappingsService.updateMapping(request.getMapping().getConversion_pixel_id(), request.getMapping().getDbm_url());
        }

        ResponseEntity<String> response = null;
        if (result > 0) {
            response = new ResponseEntity<String>("{\"status\":\"Success\"}", HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "updateMapping" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }

    @RequestMapping(value = "/deleteMapping", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteMapping(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "type", required = false) String type) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteMapping");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteMapping" + ", " + "request data ->" + "id:" + id + " ,type:" + type);
        if (type == null || type.equals("") || id == null || id.equals("")) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        Integer result = 0;
        if (id.equals("0")) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        if (type.equals("adobe")) {
            result = mAdobeService.deleteMapping(id);
        } else if (type.equals("liveramp-dp")) {
            result = mLiverampService.deleteLiverampDpMapping(id);
        } else if (type.equals("liveramp-key")) {
            result = mLiverampService.deleteLiverampKeyMapping(id);
        } else if (type.equals("facebook-pixel")) {
            result = mFacebookService.deleteFacebookPixelMapping(id);
        } else if (type.equals("facebook-key")) {
            result = mFacebookService.deleteFacebookKeyMapping(id);
        } else if (type.equals("derive-conversion")) {
            result = mDeriveComboPixelService.deleteMapping(id);
        } else if (type.equals("krux-dpkey")) {
            result = mKruxDpkeyService.deleteMapping(id);
        } else if (type.equals("dbm")) {
            result = mDbmConversionPixelMappingsService.deleteMapping(id);
        }

        ResponseEntity<String> response = null;
        if (result > 0) {
            response = new ResponseEntity<String>("{\"status\":\"Success\"}", HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        if (LOG.isDebugEnabled())
            LOG.debug("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteMapping" + ", " + "ResponseEntity:" + response.toString());

        return response;
    }

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
    public ResponseEntity<String> getRule(@RequestParam(value = "keyid", required = false) String keyid) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getRule");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "getRule" + ", " + "request data ->" + keyid);

        if (keyid.equals("0")) {
            return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
        }

        String result = "";
        result = mPixelDataEngineRuleService.getRule(keyid);

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
    public ResponseEntity<String> deleteRule(@RequestParam(value = "keyid", required = false) String keyid) {
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteRule");
        LOG.info("Invoked " + "Class -> " + CLASS_NAME + ", " + "method ->" + "deleteRule" + ", " + "request data ->" + keyid);

        Integer result = 0;
        result = mPixelDataEngineRuleService.deleteRule(keyid);

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