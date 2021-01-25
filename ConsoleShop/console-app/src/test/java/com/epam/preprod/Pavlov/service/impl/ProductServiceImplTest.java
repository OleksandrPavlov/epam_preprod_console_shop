package com.epam.preprod.Pavlov.service.impl;

import com.epam.preprod.Pavlov.entity.PassengerCar;
import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.repository.ProductRepository;
import com.epam.preprod.Pavlov.service.IProductService;
import com.epam.preprod.Pavlov.util.SerializeTransportUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class ProductServiceImplTest {
    private static final int HISTORY_SIZE = 5;
    private static final PassengerCar PC1 = new PassengerCar("Renault", 6789, 5, 4506, 5044, 170, 1.6, 6);
    private static final PassengerCar PC2 = new PassengerCar("Cherry", 6799, 5, 6780, 5044, 170, 1.6, 6);
    private static final PassengerCar PC3 = new PassengerCar("Mersedes", 6709, 5, 10123, 5044, 170, 1.6, 6);
    private static final PassengerCar PC4 = new PassengerCar("Galushka", 6719, 5, 45678, 5044, 170, 1.6, 6);
    private static final PassengerCar PC5 = new PassengerCar("Niagar", 6739, 5, 7900, 5044, 170, 1.6, 6);
    private static final PassengerCar PC6 = new PassengerCar("Volga", 6734, 5, 7900, 5044, 170, 1.6, 6);
    private static final PassengerCar PC7 = new PassengerCar("Zabava", 6733, 5, 7900, 5044, 170, 1.6, 6);
    private static final String PATH_TO_FILE = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "test.txt";
    private ProductRepository<Transport> productRepository;

    private LastAddedProductService<Transport> storage;

    private IProductService<Transport> productService;

    private <T> ProductRepository<T> createProductRepositoryMock() {
        return (ProductRepository<T>) mock(ProductRepository.class);
    }


    @Before
    public void setUp() throws Exception {
        productRepository = createProductRepositoryMock();
        //Initialization of storage and filling it
        storage = new LastAddedProductService<>(HISTORY_SIZE);
        storage.addProduct(PC1);
        storage.addProduct(PC2);
        storage.addProduct(PC3);
        storage.addProduct(PC4);
        storage.addProduct(PC5);
        storage.addProduct(PC6);
        storage.addProduct(PC7);
        productService = new ProductServiceImpl<>(storage, productRepository);
    }

    @Test
    public void shouldBeCorrectlyProductListWhenGetLastProductInfoCalled() {
        Object[] expectedArray = new PassengerCar[]{PC3, PC4, PC5, PC6, PC7};
        List<Transport> list = productService.getLastProductInfo();
        Object[] actualArray = list.toArray();
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void shouldBeCorrectListOfProductsWhenGetAllProductsCalledOnFilledRepository() {
        List<Transport> expectedList = new ArrayList<>();
        expectedList.add(PC1);
        expectedList.add(PC2);
        expectedList.add(PC3);
        expect(productRepository.getAllProducts()).andReturn(expectedList);
        replay(productRepository);
        Object[] expectedArray = new PassengerCar[]{PC1, PC2, PC3};
        List<Transport> transportList = productService.getAllProducts();
        Object[] actualArray = transportList.toArray();
        verify();
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void shouldBeCorrectOrderOfMethodInvokeWhenAddNewProductCalled() {
        productRepository.addNewProduct(PC1);
        expectLastCall();
        replay(productRepository);
        productService.addProduct(PC1);
        verify(productRepository);
    }

    @Test
    public void shouldBeCorrectListOfProductsWhenExtractTransportCalled() throws ClassNotFoundException {
        List<Transport> listToLoad = Stream.of(PC1, PC2, PC3).collect(Collectors.toList());
        expect(productRepository.getAllProducts()).andReturn(listToLoad);
        replay(productRepository);
        productService.loadProductsToFile(PATH_TO_FILE);
        List<Transport> actualList = SerializeTransportUtils.extractTransports(PATH_TO_FILE);
        assertThat(actualList, is(listToLoad));
        verify(productRepository);
        File tempFile = new File(PATH_TO_FILE);
        tempFile.delete();
    }
}