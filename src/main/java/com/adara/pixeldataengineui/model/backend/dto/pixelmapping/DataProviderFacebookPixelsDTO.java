package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class DataProviderFacebookPixelsDTO {
    private Integer dp_id; // int(11)
    private Long facebook_pixel_id; // bigint(20)


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


}
