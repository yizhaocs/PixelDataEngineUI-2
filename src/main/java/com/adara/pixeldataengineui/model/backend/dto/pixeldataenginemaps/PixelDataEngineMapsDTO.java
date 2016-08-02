package com.adara.pixeldataengineui.model.backend.dto.pixeldataenginemaps;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class PixelDataEngineMapsDTO extends BaseDTO implements Serializable {
    private String value;
    private String mapped_value;


    public PixelDataEngineMapsDTO() {

    }

    public PixelDataEngineMapsDTO(String value, String mapped_value) {
        this.value = value;
        this.mapped_value = mapped_value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PixelDataEngineMapsDTO [value=" + value + ", mapped_value=" + mapped_value
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        result = prime * result + ((mapped_value == null) ? 0 : mapped_value.hashCode());
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
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        if (mapped_value == null) {
            if (other.mapped_value != null) {
                return false;
            }
        } else if (!mapped_value.equals(other.mapped_value)) {
            return false;
        }

        return true;
    }

}
