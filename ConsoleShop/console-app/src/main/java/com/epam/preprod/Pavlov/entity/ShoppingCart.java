package com.epam.preprod.Pavlov.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ShoppingCart<T extends Transport> {
    private HashMap<Integer, ProductItem<T>> card;
    private int id;

    public ShoppingCart() {
        card = new HashMap<>();
    }

    public void addProduct(T product) {
        ProductItem<T> productItem = card.get(product.getId());
        if (Objects.nonNull(productItem)) {
            productItem.setCount(productItem.getCount() + 1);
            return;
        }
        card.put(product.getId(), new ProductItem(product, 1));
    }


    public void removeProduct(T product) {
        ProductItem<T> productItem = card.get(product.getId());
        if (Objects.nonNull(productItem)) {
            if (productItem.getCount() == 1) {
                card.remove(product.getId());
                return;
            }
            productItem.setCount(productItem.getCount() - 1);
        }
    }


    public List<ProductItem> getAllProducts() {
        List<ProductItem> productItemList = new ArrayList();
        for (Map.Entry<Integer, ProductItem<T>> entry : card.entrySet()) {
            productItemList.add(entry.getValue());
        }
        return productItemList;
    }


    public String printCard() {
        StringBuilder stringBuilder = new StringBuilder();
        Set<Map.Entry<Integer, ProductItem<T>>> set = card.entrySet();
        List<ProductItem<T>> list = set.stream().map(value -> value.getValue()).collect(Collectors.toList());
        list.forEach((value) -> {
            stringBuilder.append(" + " + value.getProduct().toString() + " кол-во: " + value.getCount() + System.lineSeparator());
        });
        return stringBuilder.toString();
    }


    public double getSum() {
        Set<Map.Entry<Integer, ProductItem<T>>> set = card.entrySet();
        double count = 0;
        for (Map.Entry<Integer, ProductItem<T>> entry : set) {
            double tempPrice = entry.getValue().getProduct().getPrice();
            tempPrice *= entry.getValue().getCount();
            count += tempPrice;
        }
        return count;
    }


    public void removeAllProducts() {
        card = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
