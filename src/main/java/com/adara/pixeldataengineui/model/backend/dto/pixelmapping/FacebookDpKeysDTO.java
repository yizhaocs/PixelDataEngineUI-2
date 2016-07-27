package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class FacebookDpKeysDTO  extends BaseDTO implements Serializable {
    private Integer key_id; // int(11)
    private Byte enabled; // tinyint(4)
    private Byte update_interval; // tinyint(4)
    private Boolean use_image_pixel; // tinyint(1)

    public FacebookDpKeysDTO(){

    }

    public FacebookDpKeysDTO(Integer key_id, Byte enabled, Byte update_interval, Boolean use_image_pixel){
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

}
