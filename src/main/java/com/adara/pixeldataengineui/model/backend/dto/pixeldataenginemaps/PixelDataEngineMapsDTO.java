package com.adara.pixeldataengineui.model.backend.dto.pixeldataenginemaps;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class PixelDataEngineMapsDTO extends BaseDTO implements Serializable {
    private String map_name;
    private String table_name;
    private String description;
    private String version;
    private String modification_ts;


    public PixelDataEngineMapsDTO() {

    }

    public PixelDataEngineMapsDTO(String map_name, String table_name, String description) {
        this.map_name = map_name;
        this.table_name = table_name;
        this.description = description;
    }

    public String getMap_name() {
        return map_name;
    }

    public void setMap_name(String map_name) {
        this.map_name = map_name;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getModification_ts() {
        return modification_ts;
    }

    public void setModification_ts(String modification_ts) {
        this.modification_ts = modification_ts;
    }

    @Override
    public String toString() {
        return "PixelDataEngineMapsDTO [map_name=" + map_name + ", table_name=" + table_name + ", description=" + description + ", version=" + version + ", modification_ts=" + modification_ts
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((map_name == null) ? 0 : map_name.hashCode());
        result = prime * result + ((table_name == null) ? 0 : table_name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
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
        PixelDataEngineMapsDTO other = (PixelDataEngineMapsDTO) obj;
        if (map_name == null) {
            if (other.map_name != null) {
                return false;
            }
        } else if (!map_name.equals(other.map_name)) {
            return false;
        }
        if (table_name == null) {
            if (other.table_name != null) {
                return false;
            }
        } else if (!table_name.equals(other.table_name)) {
            return false;
        }

        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }

        return true;
    }

}
