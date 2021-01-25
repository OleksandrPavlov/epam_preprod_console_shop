package com.epam.preprod.Pavlov.creator.strategy.impl;


import com.epam.preprod.Pavlov.creator.impl.TransportCreator;
import com.epam.preprod.Pavlov.creator.strategy.ICreateStrategy;

import java.util.HashMap;
import java.util.Map;

public class CreateStrategyImpl implements ICreateStrategy {
    private Map<Integer, TransportCreator> creators;

    public CreateStrategyImpl() {
        creators = new HashMap<>();
    }

    @Override
    public TransportCreator getCreator(int userKey) {
        return creators.get(userKey);
    }

    @Override
    public void addCreator(int key, TransportCreator creator) {
        creators.put(key, creator);
    }
}
