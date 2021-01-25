package com.epam.preprod.Pavlov.repository.impl;


import com.epam.preprod.Pavlov.entity.ShoppingCart;
import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.repository.ICartRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CartRepositoryImpl<T extends Transport> implements ICartRepository {
    private List<ShoppingCart> card;

    public CartRepositoryImpl() {
        card = new ArrayList<>();
    }


    @Override
    public void addNewCard(ShoppingCart shoppingCard) {
        card.add(shoppingCard);
    }

    @Override
    public void removeCard(ShoppingCart shoppingCard) {
        card.remove(shoppingCard);
    }

    @Override
    public ShoppingCart getCartById(int id) {
        List<ShoppingCart> shoppingCart = card.stream().filter(value -> value.getId() == id).collect(Collectors.toList());
        return shoppingCart.size() > 0 ? shoppingCart.get(0) : null;
    }

}
