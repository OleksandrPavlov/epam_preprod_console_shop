package com.epam.preprod.Pavlov.repository;

import java.util.List;

public interface ProductRepository<P> {
    void addNewProduct(P product);

    void removeProduct(P product);

    List<P> getAllProducts();

    void setProductList(List<P> list);

    boolean productExist(int id);

    P getProductById(int id);
}
