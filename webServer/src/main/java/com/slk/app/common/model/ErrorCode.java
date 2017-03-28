package com.slk.app.common.model;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enumeration of REST Error types.
 * 
 * @author Sohan Kumawat
 *
 *         Aug 3, 2016
 */
public enum ErrorCode {
    GLOBAL(2),

    AUTHENTICATION(10), JWT_TOKEN_EXPIRED(11),REFRESH_TOKEN_EXPIRED(12);
    
    private int errorCode;

    private ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {
        return errorCode;
    }
}
