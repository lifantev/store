package com.nedu.store.idgenerator;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("idGenerator")
@Scope("prototype")
public class SimpleIdGeneratorImpl implements IdGenerator {
    private long id;

    @Override
    public long getId() {
        return id++;
    }
}
