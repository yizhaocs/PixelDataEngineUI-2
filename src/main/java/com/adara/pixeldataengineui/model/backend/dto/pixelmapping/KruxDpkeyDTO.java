package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * Created by yzhao on 4/18/16.
 */
public class KruxDpkeyDTO extends BaseDTO implements Serializable {
    private String krux_segment_id;
    private Integer dp_key_id;

    public KruxDpkeyDTO() {

    }

    public KruxDpkeyDTO(String krux_segment_id, Integer dp_key_id) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((krux_segment_id == null) ? 0 : krux_segment_id.hashCode());
        result = prime * result + ((dp_key_id == null) ? 0 : dp_key_id.hashCode());
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
        KruxDpkeyDTO other = (KruxDpkeyDTO) obj;
        if (krux_segment_id == null) {
            if (other.krux_segment_id != null) {
                return false;
            }
        } else if (!krux_segment_id.equals(other.krux_segment_id)) {
            return false;
        }
        if (dp_key_id == null) {
            if (other.dp_key_id != null) {
                return false;
            }
        } else if (!dp_key_id.equals(other.dp_key_id)) {
            return false;
        }
        return true;
    }
}
