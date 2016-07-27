package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class DeriveComboPixelDTO  extends BaseDTO implements Serializable {
    private Integer dp_key_id; // int(11)

    private Integer advertiser_id; // int(11)

    private Integer cp_id; // int(11)

    public DeriveComboPixelDTO(){

    }

    public DeriveComboPixelDTO(Integer dp_key_id, Integer advertiser_id, Integer cp_id){
        this.dp_key_id = dp_key_id;
        this.advertiser_id = advertiser_id;
        this.cp_id = cp_id;
    }

    public Integer getDp_key_id() {
        return dp_key_id;
    }

    public void setDp_key_id(Integer dp_key_id) {
        this.dp_key_id = dp_key_id;
    }

    public Integer getAdvertiser_id() {
        return advertiser_id;
    }

    public void setAdvertiser_id(Integer advertiser_id) {
        this.advertiser_id = advertiser_id;
    }

    public Integer getCp_id() {
        return cp_id;
    }

    public void setCp_id(Integer cp_id) {
        this.cp_id = cp_id;
    }

    @Override
    public String toString() {
        return "DeriveComboPixelDTO [dp_key_id=" + dp_key_id + ", advertiser_id=" + advertiser_id
                + ", cp_id=" + cp_id + "]";
    }

}
