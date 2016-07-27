package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class LiverampDpMappingsDTO extends BaseDTO implements Serializable {
    private String dp_name; // varchar(80)
    private Integer dp_id; // int(11)
    private Long threshold_mb; // bigint(20)

    public LiverampDpMappingsDTO() {

    }

    public LiverampDpMappingsDTO(String dp_name, Integer dp_id, Long threshold_mb) {
        this.dp_name = dp_name;
        this.dp_id = dp_id;
        this.threshold_mb = threshold_mb;
    }

    public Long getThreshold_mb() {
        return threshold_mb;
    }

    public void setThreshold_mb(Long threshold_mb) {
        this.threshold_mb = threshold_mb;
    }

    public Integer getDp_id() {
        return dp_id;
    }

    public void setDp_id(Integer dp_id) {
        this.dp_id = dp_id;
    }

    public String getDp_name() {
        return dp_name;
    }

    public void setDp_name(String dp_name) {
        this.dp_name = dp_name;
    }

    @Override
    public String toString() {
        return "LiverampDpMappingsDTO [dp_name=" + dp_name + ", dp_id=" + dp_id
                + ", threshold_mb=" + threshold_mb + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((dp_name == null) ? 0 : dp_name.hashCode());
        result = prime * result + ((dp_id == null) ? 0 : dp_id.hashCode());
        result = prime * result + ((threshold_mb == null) ? 0 : threshold_mb.hashCode());
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
        LiverampDpMappingsDTO other = (LiverampDpMappingsDTO) obj;
        if (dp_name == null) {
            if (other.dp_name != null) {
                return false;
            }
        } else if (!dp_name.equals(other.dp_name)) {
            return false;
        }
        if (dp_id == null) {
            if (other.dp_id != null) {
                return false;
            }
        } else if (!dp_id.equals(other.dp_id)) {
            return false;
        }
        if (threshold_mb == null) {
            if (other.threshold_mb != null) {
                return false;
            }
        } else if (!threshold_mb.equals(other.threshold_mb)) {
            return false;
        }
        return true;
    }
}
