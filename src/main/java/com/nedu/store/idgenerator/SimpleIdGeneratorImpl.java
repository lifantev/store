package com.nedu.store.idgenerator;

import org.springframework.stereotype.Component;

@Component("idGenerator")
public class SimpleIdGeneratorImpl implements IdGenerator {
    private long id;

    @Override
    public long getId() {
        return id++;
    }
}
