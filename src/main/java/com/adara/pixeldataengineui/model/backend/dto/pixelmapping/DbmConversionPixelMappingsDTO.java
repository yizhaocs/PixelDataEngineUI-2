package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class DbmConversionPixelMappingsDTO {
    private Integer conversion_pixel_id;
    private String dbm_url;

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
}
