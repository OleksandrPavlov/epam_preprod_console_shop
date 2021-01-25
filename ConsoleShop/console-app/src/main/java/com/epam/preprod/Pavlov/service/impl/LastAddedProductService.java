package com.epam.preprod.Pavlov.service.impl;

import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.service.ILastAddedProductService;
import com.epam.preprod.Pavlov.storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class LastAddedProductService<T extends Transport> implements ILastAddedProductService<T> {
    private Storage<Integer, T> storage;

    public LastAddedProductService(int cashSize) {
        storage = new Storage<>(cashSize);
    }

    @Override
    public void addProduct(T product) {
        storage.put(product.getId(), product);
    }

    @Override
    public List<T> getAll() {
        List<T> list = new ArrayList<>(storage.values());
        return list;
    }
}
