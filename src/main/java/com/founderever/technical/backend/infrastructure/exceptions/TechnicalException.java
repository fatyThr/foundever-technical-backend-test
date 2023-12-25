package com.founderever.technical.backend.infrastructure.exceptions;

import lombok.Getter;

@Getter
public class TechnicalException extends RuntimeException {

    private final String code;
    private final Object[] params;

    public TechnicalException(String code, Object... params) {
        super();
        this.code = code;
        this.params = params;
    }

}
