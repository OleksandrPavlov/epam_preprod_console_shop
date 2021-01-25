package com.epam.preprod.Pavlov.creator.strategy;

import com.epam.preprod.Pavlov.creator.impl.TransportCreator;

public interface ICreateStrategy {
    TransportCreator getCreator(int userKey);

    void addCreator(int key, TransportCreator creator);
}
