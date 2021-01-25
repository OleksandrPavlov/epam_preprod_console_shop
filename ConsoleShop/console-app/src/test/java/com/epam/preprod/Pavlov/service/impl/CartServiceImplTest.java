package com.epam.preprod.Pavlov.service.impl;


import com.epam.preprod.Pavlov.entity.PassengerCar;
import com.epam.preprod.Pavlov.entity.ProductItem;
import com.epam.preprod.Pavlov.entity.ShoppingCart;
import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.exception.UserInputException;
import com.epam.preprod.Pavlov.repository.ICartRepository;
import com.epam.preprod.Pavlov.repository.ProductRepository;
import com.epam.preprod.Pavlov.service.ICartService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

public class CartServiceImplTest {
    private static final int HISTORY_SIZE = 5;
    private static final int CART_ID = 1212312;
    private static final PassengerCar PC1 = new PassengerCar("Renault", 6789, 5, 4506, 5044, 170, 1.6, 6);
    private ProductRepository<Transport> productRepository;
    private ICartRepository cartRepository;
    private LastAddedProductService<Transport> storage;
    private ShoppingCart shoppingCart;
    private ICartService cartService;

    @Before
    public void setUp() throws Exception {
        storage = new LastAddedProductService<>(HISTORY_SIZE);
        productRepository = getProductRepository();
        cartRepository = mock(ICartRepository.class);
        shoppingCart = new ShoppingCart();
        shoppingCart.setId(CART_ID);
        cartService = new CartServiceImpl<>(cartRepository, productRepository, storage);
    }

    @Test
    public void shouldAddNewProductToCartWhenCartServiceCalled() throws UserInputException {
        int productId = PC1.getId();
        expect(productRepository.productExist(productId)).andReturn(true);
        expect(productRepository.getProductById(productId)).andReturn(PC1);
        expect(cartRepository.getCartById(CART_ID)).andReturn(shoppingCart);
        replay(productRepository, cartRepository);
        cartService.addProductToCart(productId, CART_ID);
        List<ProductItem> actualListOfProductsFromCart = shoppingCart.getAllProducts();
        assertEquals(PC1, actualListOfProductsFromCart.get(0).getProduct());
        verify(productRepository, cartRepository);
    }

    @Test(expected = UserInputException.class)
    public void shoulBeThrownAnExceptionWhenCartServiceWithWrongIdentifierWillBeCalled() throws UserInputException {
        int productId = PC1.getId();
        expect(productRepository.productExist(productId)).andReturn(false);
        replay(productRepository);
        cartService.addProductToCart(productId, CART_ID);
        verify(productRepository);
    }

    @Test
    public void shouldBeCleanCartWhenWeBuyAllProducts() {
        expect(cartRepository.getCartById(CART_ID)).andReturn(shoppingCart);
        replay(cartRepository);
        cartService.buyAllProductFromCart(CART_ID);
        assertEquals(0, shoppingCart.getAllProducts().size());
        verify(cartRepository);
    }


    private ProductRepository<Transport> getProductRepository() {
        return (ProductRepository<Transport>) mock(ProductRepository.class);
    }

}