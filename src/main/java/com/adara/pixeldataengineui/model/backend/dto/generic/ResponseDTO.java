/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adara.pixeldataengineui.model.backend.dto.generic;

import org.springframework.http.HttpStatus;

import java.io.Serializable;


public class ResponseDTO extends BaseDTO implements Serializable {
    protected String message;
    protected HttpStatus statusCode;

    public ResponseDTO() {
    }


    public ResponseDTO(Object object, String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the statusCode
     */
    public HttpStatus getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
