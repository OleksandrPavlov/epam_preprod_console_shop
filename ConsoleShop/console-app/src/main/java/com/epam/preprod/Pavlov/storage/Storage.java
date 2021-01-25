package com.epam.preprod.Pavlov.storage;


import java.util.LinkedHashMap;
import java.util.Map;

public class Storage<Long, V> extends LinkedHashMap<Long, V> {

    private int maxEntries;

    public Storage(int max) {
        super();
        maxEntries = max;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > maxEntries;
    }

}
