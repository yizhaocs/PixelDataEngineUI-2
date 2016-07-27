package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class DataProviderFacebookPixelsDTO extends BaseDTO implements Serializable {
    private Integer dp_id; // int(11)
    private Long facebook_pixel_id; // bigint(20)

    public DataProviderFacebookPixelsDTO(){

    }

    public DataProviderFacebookPixelsDTO(Integer dp_id, Long facebook_pixel_id){
        this.dp_id = dp_id;
        this.facebook_pixel_id = facebook_pixel_id;
    }

    public Integer getDp_id() {
        return dp_id;
    }

    public void setDp_id(Integer dp_id) {
        this.dp_id = dp_id;
    }

    public Long getFacebook_pixel_id() {
        return facebook_pixel_id;
    }

    public void setFacebook_pixel_id(Long facebook_pixel_id) {
        this.facebook_pixel_id = facebook_pixel_id;
    }

    @Override
    public String toString() {
        return "DataProviderFacebookPixelsDTO [dp_id=" + dp_id + ", facebook_pixel_id=" + facebook_pixel_id + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((dp_id == null) ? 0 : dp_id.hashCode());
        result = prime * result
                + ((facebook_pixel_id == null) ? 0 : facebook_pixel_id.hashCode());

        return result;
    }
}
