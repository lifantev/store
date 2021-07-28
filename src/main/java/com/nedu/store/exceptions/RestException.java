package com.nedu.store.exceptions;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class RestException extends Exception {
    private RestExceptionEnum error;
    private Date date = new Date();

    public RestException(Throwable throwable) {
        super(throwable);
        this.error = RestExceptionEnum.ERR_SYSTEM_INTERNAL;
    }

    public RestException(RestExceptionEnum error) {
        this.error = error;
    }

    @Override
    @SneakyThrows
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> restResult= new TreeMap<>();

        restResult.put("date", date.toString());
        restResult.put("code", error.name());
        restResult.put("message", error.getMessage());

        return objectMapper.writeValueAsString(restResult);
    }
}
