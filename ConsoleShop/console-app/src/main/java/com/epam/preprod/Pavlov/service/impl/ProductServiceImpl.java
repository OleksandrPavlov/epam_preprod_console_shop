package com.epam.preprod.Pavlov.service.impl;

import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.repository.ProductRepository;
import com.epam.preprod.Pavlov.service.ILastAddedProductService;
import com.epam.preprod.Pavlov.service.IProductService;
import com.epam.preprod.Pavlov.util.SerializeTransportUtils;

import java.util.List;

public class ProductServiceImpl<T extends Transport> implements IProductService<T> {
    private ILastAddedProductService<T> tLastAddedProductService;
    private ProductRepository<T> productRepository;

    public ProductServiceImpl(ILastAddedProductService<T> tLastAddedProductService, ProductRepository<T> productRepository) {
        this.tLastAddedProductService = tLastAddedProductService;
        this.productRepository = productRepository;
    }

    @Override
    public List<T> getLastProductInfo() {
        return tLastAddedProductService.getAll();
    }

    @Override
    public void addProduct(T product) {
        productRepository.addNewProduct(product);
    }

    @Override
    public void loadProductsToFile(String fileName) {
        SerializeTransportUtils.insertList(fileName, productRepository.getAllProducts(), false);
    }

    @Override
    public List<T> getAllProducts() {
        return productRepository.getAllProducts();
    }


}
