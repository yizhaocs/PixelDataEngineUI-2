package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class AdobeDpkeyMappingDTO {
    private Integer adobe_segment_id; // int(11)

    private Integer dp_key_id; // int(11)

    public Integer getAdobe_segment_id() {
        return adobe_segment_id;
    }

    public void setAdobe_segment_id(Integer adobe_segment_id) {
        this.adobe_segment_id = adobe_segment_id;
    }

    public Integer getDp_key_id() {
        return dp_key_id;
    }

    public void setDp_key_id(Integer dp_key_id) {
        this.dp_key_id = dp_key_id;
    }


}
