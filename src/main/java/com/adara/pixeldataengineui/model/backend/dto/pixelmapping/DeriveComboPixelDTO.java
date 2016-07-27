package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class DeriveComboPixelDTO extends BaseDTO implements Serializable {
    private Integer dp_key_id; // int(11)

    private Integer advertiser_id; // int(11)

    private Integer cp_id; // int(11)

    public DeriveComboPixelDTO() {

    }

    public DeriveComboPixelDTO(Integer dp_key_id, Integer advertiser_id, Integer cp_id) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((dp_key_id == null) ? 0 : dp_key_id.hashCode());
        result = prime * result + ((advertiser_id == null) ? 0 : advertiser_id.hashCode());
        result = prime * result + ((cp_id == null) ? 0 : cp_id.hashCode());
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
        DeriveComboPixelDTO other = (DeriveComboPixelDTO) obj;
        if (dp_key_id == null) {
            if (other.dp_key_id != null) {
                return false;
            }
        } else if (!dp_key_id.equals(other.dp_key_id)) {
            return false;
        }
        if (advertiser_id == null) {
            if (other.advertiser_id != null) {
                return false;
            }
        } else if (!advertiser_id.equals(other.advertiser_id)) {
            return false;
        }
        if (cp_id == null) {
            if (other.cp_id != null) {
                return false;
            }
        } else if (!cp_id.equals(other.cp_id)) {
            return false;
        }
        return true;
    }
}
