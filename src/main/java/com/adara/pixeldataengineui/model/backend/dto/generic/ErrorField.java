package com.adara.pixeldataengineui.model.backend.dto.generic;

public class ErrorField implements Cloneable {

    private String field;
    private String message;

    public ErrorField() {
    }

    public ErrorField(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message != null ? message.trim() : message;
    }

    @Override
    public String toString() {
        return "ErrorField [field=" + field + ", message=" + message + "]";
    }

    public ErrorField clone() {
        ErrorField clone = new ErrorField();
        clone.field = this.field;
        clone.message = this.message;
        return clone;
    }
}
