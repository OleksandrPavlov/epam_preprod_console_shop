package com.epam.preprod.Pavlov.service;


import java.util.List;

public interface IProductService<T> {
    List<T> getAllProducts();

    List<T> getLastProductInfo();

    void addProduct(T product);

    void loadProductsToFile(String fileName);

}
