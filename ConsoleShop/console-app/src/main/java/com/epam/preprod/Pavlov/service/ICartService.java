package com.epam.preprod.Pavlov.service;

import com.epam.preprod.Pavlov.entity.ShoppingCart;
import com.epam.preprod.Pavlov.exception.UserInputException;

public interface ICartService {
    void addProductToCart(int productId, int cardId) throws UserInputException;

    void buyAllProductFromCart(int cartId);

    ShoppingCart getCartById(int id);
}
