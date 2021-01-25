package com.epam.preprod.Pavlov.service.impl;

import com.epam.preprod.Pavlov.entity.ShoppingCart;
import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.exception.UserInputException;
import com.epam.preprod.Pavlov.repository.ICartRepository;
import com.epam.preprod.Pavlov.repository.ProductRepository;
import com.epam.preprod.Pavlov.service.ICartService;
import com.epam.preprod.Pavlov.util.constants.ErrorMessageConstants;

public class CartServiceImpl<T extends Transport> implements ICartService {
    private ICartRepository cardRepository;
    private ProductRepository<T> productRepository;
    private LastAddedProductService<T> storage;

    public CartServiceImpl(ICartRepository cardRepository, ProductRepository<T> productRepository, LastAddedProductService<T> storage) {
        this.cardRepository = cardRepository;
        this.productRepository = productRepository;
        this.storage = storage;
    }

    @Override
    public void addProductToCart(int productId, int cartId) throws UserInputException {
        boolean productExist = productRepository.productExist(productId);
        if (!productExist) {
            throw new UserInputException(ErrorMessageConstants.UNKNOWN_IDENTIFIER);
        }
        T product = productRepository.getProductById(productId);
        ShoppingCart shc = cardRepository.getCartById(cartId);
        shc.addProduct(product);
        storage.addProduct(product);
    }

    @Override
    public void buyAllProductFromCart(int cartId) {
        ShoppingCart shoppingCard = getCartById(cartId);
        shoppingCard.removeAllProducts();
    }

    @Override
    public ShoppingCart getCartById(int cartId) {
        return cardRepository.getCartById(cartId);
    }
}
