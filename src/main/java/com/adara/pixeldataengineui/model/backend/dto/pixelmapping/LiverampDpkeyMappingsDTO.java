package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class LiverampDpkeyMappingsDTO extends BaseDTO implements Serializable {
    private Long liveramp_segment_id; // bigint(20)
    private Integer dp_key_id; // int(11)
    private String value; // varchar(80)

    public LiverampDpkeyMappingsDTO() {

    }

    public LiverampDpkeyMappingsDTO(Long liveramp_segment_id, Integer dp_key_id, String value) {
        this.liveramp_segment_id = liveramp_segment_id;
        this.dp_key_id = dp_key_id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getDp_key_id() {
        return dp_key_id;
    }

    public void setDp_key_id(Integer dp_key_id) {
        this.dp_key_id = dp_key_id;
    }

    public Long getLiveramp_segment_id() {
        return liveramp_segment_id;
    }

    public void setLiveramp_segment_id(Long liveramp_segment_id) {
        this.liveramp_segment_id = liveramp_segment_id;
    }

    @Override
    public String toString() {
        return "LiverampDpkeyMappingsDTO [liveramp_segment_id=" + liveramp_segment_id + ", dp_key_id=" + dp_key_id
                + ", value=" + value + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((liveramp_segment_id == null) ? 0 : liveramp_segment_id.hashCode());
        result = prime * result + ((dp_key_id == null) ? 0 : dp_key_id.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        LiverampDpkeyMappingsDTO other = (LiverampDpkeyMappingsDTO) obj;
        if (liveramp_segment_id == null) {
            if (other.liveramp_segment_id != null) {
                return false;
            }
        } else if (!liveramp_segment_id.equals(other.liveramp_segment_id)) {
            return false;
        }
        if (dp_key_id == null) {
            if (other.dp_key_id != null) {
                return false;
            }
        } else if (!dp_key_id.equals(other.dp_key_id)) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }
}
