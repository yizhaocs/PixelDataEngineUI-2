package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class DbmConversionPixelMappingsDTO  extends BaseDTO implements Serializable {
    private Integer conversion_pixel_id;
    private String dbm_url;

    public DbmConversionPixelMappingsDTO(){

    }

    public DbmConversionPixelMappingsDTO(Integer conversion_pixel_id, String dbm_url){
        this.conversion_pixel_id = conversion_pixel_id;
        this.dbm_url = dbm_url;
    }

    public Integer getConversion_pixel_id() {
        return conversion_pixel_id;
    }

    public void setConversion_pixel_id(Integer conversion_pixel_id) {
        this.conversion_pixel_id = conversion_pixel_id;
    }

    public String getDbm_url() {
        return dbm_url;
    }

    public void setDbm_url(String dbm_url) {
        this.dbm_url = dbm_url;
    }

    @Override
    public String toString() {
        return "DbmConversionPixelMappingsDTO [conversion_pixel_id=" + conversion_pixel_id + ", dbm_url=" + dbm_url + "]";
    }

}
