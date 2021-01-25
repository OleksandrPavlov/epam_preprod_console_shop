package com.epam.preprod.Pavlov.service;

import java.util.List;

public interface ILastAddedProductService<T> {
    void addProduct(T product);

    List<T> getAll();
}
