package com.epam.preprod.Pavlov.entity;

public class ProductItem<T> {
    private T product;
    private int count;


    public ProductItem(T product, int i) {
        this.product = product;
        this.count = i;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getProduct() {
        return product;
    }

    public void setProduct(T product) {
        this.product = product;
    }


}
