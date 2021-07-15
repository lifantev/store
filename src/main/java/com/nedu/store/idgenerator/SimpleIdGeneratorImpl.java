package com.nedu.store.idgenerator;

public class SimpleIdGeneratorImpl implements IdGenerator {
    private long id;

    @Override
    public long getId() {
        return id++;
    }
}
