package com.adara.pixeldataengineui.model.backend.dto.generic;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.Collection;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_DEFAULT)
public class BaseDTO extends ResourceSupport {
    protected Boolean isSelected = null;
    protected Collection<ErrorField> errors = new ArrayList<ErrorField>();
    protected Collection<String> fieldNames = new ArrayList<String>();
    @Deprecated
    protected boolean updateCrExtFlag = false;    // default to false
    protected String transactionCode;

    public BaseDTO() {
    }

    protected BaseDTO(BaseDTO other) {
        setBaseDTO(other);
    }

    public void setBaseDTO(BaseDTO other) {
        this.isSelected = other.isSelected;
        for (ErrorField error : other.errors) {
            this.errors.add(error.clone());
        }
        this.fieldNames.addAll(fieldNames);
    }

    @Deprecated
    @JsonIgnore
    public boolean isUpdateCrExtFlag() {
        return updateCrExtFlag;
    }

    @Deprecated
    public void setUpdateCrExtFlag(boolean updateCrExtFlag) {
        this.updateCrExtFlag = updateCrExtFlag;
    }

    public Collection<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(Collection<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public Collection<ErrorField> getErrors() {
        return errors;
    }

    public void setErrors(Collection<ErrorField> errors) {
        this.errors = errors;
    }

    public void addFieldError(String path, String message) {
        ErrorField errorField = new ErrorField();
        errorField.setField(path);
        errorField.setMessage(message);
        errors.add(errorField);
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    @Override
    public String toString() {
        return "BaseDTO [errors=" + errors + ", fieldNames=" + fieldNames
                + ", transactionCode=" + transactionCode + "]";
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((errors == null) ? 0 : errors.hashCode());
        result = prime * result + ((fieldNames == null) ? 0 : fieldNames.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !super.equals(obj)) { // Spring resource support cannot take obj as null
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BaseDTO other = (BaseDTO) obj;
        if (errors == null) {
            if (other.errors != null) {
                return false;
            }
        } else if (!errors.equals(other.errors)) {
            return false;
        }
        if (fieldNames == null) {
            if (other.fieldNames != null) {
                return false;
            }
        } else if (!fieldNames.equals(other.fieldNames)) {
            return false;
        }
        return true;
    }

}
