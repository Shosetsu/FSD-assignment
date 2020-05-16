package com.fsd.emart.common.exception;

public class BizException extends RuntimeException {

    protected BizException(String message) {
        super(message);
    }

    /** UID */
    private static final long serialVersionUID = 5910174507347726630L;
}
