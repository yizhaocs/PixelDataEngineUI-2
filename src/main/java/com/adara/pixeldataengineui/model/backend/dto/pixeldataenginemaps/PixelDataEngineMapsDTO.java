package com.adara.pixeldataengineui.model.backend.dto.pixeldataenginemaps;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class PixelDataEngineMapsDTO extends BaseDTO implements Serializable {
    private String map_name;
    private String table_name;


    public PixelDataEngineMapsDTO() {

    }

    public PixelDataEngineMapsDTO(String map_name, String table_name) {
        this.map_name = map_name;
        this.table_name = table_name;
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

    @Override
    public String toString() {
        return "PixelDataEngineMapsDTO [map_name=" + map_name + ", table_name=" + table_name
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((map_name == null) ? 0 : map_name.hashCode());
        result = prime * result + ((table_name == null) ? 0 : table_name.hashCode());
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

        return true;
    }

}
