package com.epam.preprod.Pavlov.repository.impl;

import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepositoryImpl<T extends Transport> implements ProductRepository<T> {
    private List<T> productList;

    public ProductRepositoryImpl(List<T> productList) {
        this.productList = productList;
    }

    public ProductRepositoryImpl() {
        productList = new ArrayList<>();
    }

    @Override
    public void addNewProduct(T product) {
        productList.add(product);
    }

    @Override
    public void removeProduct(T product) {
        productList.remove(product);
    }

    @Override
    public List<T> getAllProducts() {
        return productList;
    }

    @Override
    public void setProductList(List<T> list) {
        this.productList = list;
    }

    @Override
    public boolean productExist(int id) {
        boolean productExist = productList.stream().map(value -> value.getId()).anyMatch(value -> value == id);
        return productExist;
    }

    @Override
    public T getProductById(int id) {
        List<T> product = productList.stream().filter(value -> value.getId() == id).collect(Collectors.toList());
        if (product.size() == 0) {
            return null;
        }
        return product.get(0);
    }
}
