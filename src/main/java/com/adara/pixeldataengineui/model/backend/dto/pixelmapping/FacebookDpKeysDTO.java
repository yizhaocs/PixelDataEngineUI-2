package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class FacebookDpKeysDTO extends BaseDTO implements Serializable {
    private Integer key_id; // int(11)
    private Byte enabled; // tinyint(4)
    private Byte update_interval; // tinyint(4)
    private Boolean use_image_pixel; // tinyint(1)

    public FacebookDpKeysDTO() {

    }

    public FacebookDpKeysDTO(Integer key_id, Byte enabled, Byte update_interval, Boolean use_image_pixel) {
        this.key_id = key_id;
        this.enabled = enabled;
        this.update_interval = update_interval;
        this.use_image_pixel = use_image_pixel;
    }

    public Boolean getUse_image_pixel() {
        return use_image_pixel;
    }

    public void setUse_image_pixel(Boolean use_image_pixel) {
        this.use_image_pixel = use_image_pixel;
    }

    public Byte getUpdate_interval() {
        return update_interval;
    }

    public void setUpdate_interval(Byte update_interval) {
        this.update_interval = update_interval;
    }

    public Byte getEnabled() {
        return enabled;
    }

    public void setEnabled(Byte enabled) {
        this.enabled = enabled;
    }

    public Integer getKey_id() {
        return key_id;
    }

    public void setKey_id(Integer key_id) {
        this.key_id = key_id;
    }

    @Override
    public String toString() {
        return "FacebookDpKeysDTO [key_id=" + key_id + ", enabled=" + enabled
                + ", update_interval=" + update_interval + ", use_image_pixel=" + use_image_pixel + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((key_id == null) ? 0 : key_id.hashCode());
        result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
        result = prime * result + ((update_interval == null) ? 0 : update_interval.hashCode());
        result = prime * result + ((use_image_pixel == null) ? 0 : use_image_pixel.hashCode());

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
        FacebookDpKeysDTO other = (FacebookDpKeysDTO) obj;
        if (key_id == null) {
            if (other.key_id != null) {
                return false;
            }
        } else if (!key_id.equals(other.key_id)) {
            return false;
        }
        if (enabled == null) {
            if (other.enabled != null) {
                return false;
            }
        } else if (!enabled.equals(other.enabled)) {
            return false;
        }
        if (update_interval == null) {
            if (other.update_interval != null) {
                return false;
            }
        } else if (!update_interval.equals(other.update_interval)) {
            return false;
        }
        if (use_image_pixel == null) {
            if (other.use_image_pixel != null) {
                return false;
            }
        } else if (!use_image_pixel.equals(other.use_image_pixel)) {
            return false;
        }
        return true;
    }
}
