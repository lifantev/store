package com.nedu.store.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RestExceptionEnum {
    ERR_SYSTEM_INTERNAL("Unexpected system exception"),
    ERR_001("");

    private String message;
}
