package com.nedu.store.idgenerator;

public class SimpleIdGeneratorImpl implements IdGenerator {
    private long id = 0;

    @Override
    public long getId() {
        return id++;
    }
}
