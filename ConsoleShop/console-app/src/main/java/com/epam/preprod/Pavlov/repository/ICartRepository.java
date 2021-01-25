package com.epam.preprod.Pavlov.repository;

import com.epam.preprod.Pavlov.entity.ShoppingCart;

public interface ICartRepository {
    void addNewCard(ShoppingCart iShoppingCard);

    void removeCard(ShoppingCart iShoppingCard);

    ShoppingCart getCartById(int id);
}
