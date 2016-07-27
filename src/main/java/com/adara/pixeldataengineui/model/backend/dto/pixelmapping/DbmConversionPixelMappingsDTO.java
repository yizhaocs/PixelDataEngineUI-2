package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class DbmConversionPixelMappingsDTO extends BaseDTO implements Serializable {
    private Integer conversion_pixel_id;
    private String dbm_url;

    public DbmConversionPixelMappingsDTO() {

    }

    public DbmConversionPixelMappingsDTO(Integer conversion_pixel_id, String dbm_url) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((conversion_pixel_id == null) ? 0 : conversion_pixel_id.hashCode());
        result = prime * result + ((dbm_url == null) ? 0 : dbm_url.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DbmConversionPixelMappingsDTO other = (DbmConversionPixelMappingsDTO) obj;
        if (conversion_pixel_id == null) {
            if (other.conversion_pixel_id != null) {
                return false;
            }
        } else if (!conversion_pixel_id.equals(other.conversion_pixel_id)) {
            return false;
        }
        if (dbm_url == null) {
            if (other.dbm_url != null) {
                return false;
            }
        } else if (!dbm_url.equals(other.dbm_url)) {
            return false;
        }

        return true;
    }
}
