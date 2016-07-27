package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * Created by yzhao on 4/18/16.
 */
public class KruxDpkeyDTO  extends BaseDTO implements Serializable {
    private String krux_segment_id;
    private Integer dp_key_id;

    public KruxDpkeyDTO(){

    }

    public KruxDpkeyDTO(String krux_segment_id, Integer dp_key_id){
        this.krux_segment_id = krux_segment_id;
        this.dp_key_id = dp_key_id;
    }

    public String getKrux_segment_id() {
        return krux_segment_id;
    }

    public void setKrux_segment_id(String krux_segment_id) {
        this.krux_segment_id = krux_segment_id;
    }

    public Integer getDp_key_id() {
        return dp_key_id;
    }

    public void setDp_key_id(Integer dp_key_id) {
        this.dp_key_id = dp_key_id;
    }

    @Override
    public String toString() {
        return "KruxDpkeyDTO [krux_segment_id=" + krux_segment_id + ", dp_key_id=" + dp_key_id
               + "]";
    }

}
