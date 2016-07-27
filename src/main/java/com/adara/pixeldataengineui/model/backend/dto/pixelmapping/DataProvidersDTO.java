package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class DataProvidersDTO extends BaseDTO implements Serializable {
    private Integer dp_id; // int(11)
    private String name; // varchar(64)
    private Boolean sync_facebook; // tinyint(1)

    public DataProvidersDTO() {

    }

    public DataProvidersDTO(Integer dp_id, String name, Boolean sync_facebook) {
        this.dp_id = dp_id;
        this.name = name;
        this.sync_facebook = sync_facebook;
    }

    public Boolean getSync_facebook() {
        return sync_facebook;
    }

    public void setSync_facebook(Boolean sync_facebook) {
        this.sync_facebook = sync_facebook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDp_id() {
        return dp_id;
    }

    public void setDp_id(Integer dp_id) {
        this.dp_id = dp_id;
    }

    @Override
    public String toString() {
        return "DataProvidersDTO [id=" + dp_id + ", name=" + name
                + ", sync_facebook=" + sync_facebook + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((dp_id == null) ? 0 : dp_id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((sync_facebook == null) ? 0 : sync_facebook.hashCode());
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
        DataProvidersDTO other = (DataProvidersDTO) obj;
        if (dp_id == null) {
            if (other.dp_id != null) {
                return false;
            }
        } else if (!dp_id.equals(other.dp_id)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }

        if (sync_facebook == null) {
            if (other.sync_facebook != null) {
                return false;
            }
        } else if (!sync_facebook.equals(other.sync_facebook)) {
            return false;
        }

        return true;
    }
}
