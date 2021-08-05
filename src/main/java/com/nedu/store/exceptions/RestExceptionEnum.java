package com.nedu.store.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RestExceptionEnum {
    ERR_SYSTEM_INTERNAL("Unexpected system exception"),
    ERR_001("Wrong user password or login"),
    ERR_002("Unknown product or user"),
    ERR_003("Unknown user"),
    ERR_004("Unknown product"),
    ERR_005("Empty product list"),
    ERR_006("Weak password"),
    ERR_007("Username already exists"),
    ERR_008("Basket showing error");

    private String message;
}
