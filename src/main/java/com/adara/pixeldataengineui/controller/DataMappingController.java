package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.model.frontend.requestbody.InsertUpdateRequest;
import com.adara.pixeldataengineui.service.pixelmapping.*;
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
public class DataMappingController {
    private static final Log LOG = LogFactory.getLog(DataMappingController.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();

    @Autowired
    AdobeService mAdobeService;
    @Autowired
    FacebookService mFacebookService;
    @Autowired
    LiverampService mLiverampService;
    @Autowired
    DeriveComboPixelService mDeriveComboPixelService;
    @Autowired
    KruxDpkeyService mKruxDpkeyService;
    @Autowired
    DbmConversionPixelMappingsService mDbmConversionPixelMappingsService;


    @RequestMapping(value = "/mappings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getMappings(@RequestParam("type") String type) {
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
    public ResponseEntity<String> getMapping(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "type", required = false) String type) {
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
}