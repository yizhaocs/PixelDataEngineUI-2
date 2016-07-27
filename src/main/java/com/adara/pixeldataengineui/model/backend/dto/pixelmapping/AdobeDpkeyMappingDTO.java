package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class AdobeDpkeyMappingDTO  extends BaseDTO implements Serializable {
    private Integer adobe_segment_id; // int(11)

    private Integer dp_key_id; // int(11)


    public AdobeDpkeyMappingDTO(){

    }

    public AdobeDpkeyMappingDTO(Integer adobe_segment_id, Integer dp_key_id){
        this.adobe_segment_id = adobe_segment_id;
        this.dp_key_id = dp_key_id;
    }

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

    @Override
    public String toString() {
        return "AdobeDpkeyMappingDTO [adobe_segment_id=" + adobe_segment_id + ", dp_key_id=" + dp_key_id + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((adobe_segment_id == null) ? 0 : adobe_segment_id.hashCode());
        result = prime * result
                + ((dp_key_id == null) ? 0 : dp_key_id.hashCode());

        return result;
    }
}
