package com.epam.preprod.Pavlov.Initializer;

import com.epam.preprod.Pavlov.command.CommandRecognizer;
import com.epam.preprod.Pavlov.command.impl.AddProductCommand;
import com.epam.preprod.Pavlov.command.impl.AddProductToCartCommand;
import com.epam.preprod.Pavlov.command.impl.BuyAllProductFromCartCommand;
import com.epam.preprod.Pavlov.command.impl.CommandRecognizerImpl;
import com.epam.preprod.Pavlov.command.impl.FiveLastProductCommand;
import com.epam.preprod.Pavlov.command.impl.GetAllProductCommand;
import com.epam.preprod.Pavlov.command.impl.GetCartContentCommand;
import com.epam.preprod.Pavlov.command.impl.GetMostRelevantOrderToDate;
import com.epam.preprod.Pavlov.command.impl.GetOrdersForTimeRangeCommand;
import com.epam.preprod.Pavlov.command.impl.MakeOrderCommand;
import com.epam.preprod.Pavlov.command.impl.PrintMenuCommand;
import com.epam.preprod.Pavlov.command.impl.StopAppCommand;
import com.epam.preprod.Pavlov.creator.impl.ReflectTransportCreator;
import com.epam.preprod.Pavlov.creator.impl.TransportCreator;
import com.epam.preprod.Pavlov.creator.strategy.ICreateStrategy;
import com.epam.preprod.Pavlov.creator.strategy.impl.CreateStrategyImpl;
import com.epam.preprod.Pavlov.entity.PassengerCar;
import com.epam.preprod.Pavlov.entity.ShoppingCart;
import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.entity.Truck;
import com.epam.preprod.Pavlov.exception.UserInputException;
import com.epam.preprod.Pavlov.maker.IMaker;
import com.epam.preprod.Pavlov.maker.impl.ManualMaker;
import com.epam.preprod.Pavlov.maker.impl.RandomMaker;
import com.epam.preprod.Pavlov.repository.ICartRepository;
import com.epam.preprod.Pavlov.repository.IOrderRepository;
import com.epam.preprod.Pavlov.repository.ProductRepository;
import com.epam.preprod.Pavlov.repository.impl.CartRepositoryImpl;
import com.epam.preprod.Pavlov.repository.impl.OrderRepositoryImpl;
import com.epam.preprod.Pavlov.repository.impl.ProductRepositoryImpl;
import com.epam.preprod.Pavlov.service.ICartService;
import com.epam.preprod.Pavlov.service.IOrderService;
import com.epam.preprod.Pavlov.service.IProductService;
import com.epam.preprod.Pavlov.service.impl.CartServiceImpl;
import com.epam.preprod.Pavlov.service.impl.LastAddedProductService;
import com.epam.preprod.Pavlov.service.impl.OrderServiceImpl;
import com.epam.preprod.Pavlov.service.impl.ProductServiceImpl;
import com.epam.preprod.Pavlov.util.SerializeTransportUtils;
import com.epam.preprod.Pavlov.util.ValidateInputUtils;
import com.epam.preprod.Pavlov.util.constants.Constants;
import com.epam.preprod.Pavlov.util.constants.InvitationConstants;
import com.epam.preprod.Pavlov.util.constants.LocaleConstants;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;
import com.epam.preprod.Pavlov.util.io.impl.ConsoleShopReader;
import com.epam.preprod.Pavlov.util.io.impl.ConsoleShopWriter;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class Application {
    /*
    size of history
     */
    private static final int HISTORY_SIZE = 5;
    /*
    specified exit key we use to normal quite from app
     */
    private static final String EXIT_KEY = "0";
    /*
    Name of file we will extract transport from.
     */

    private static final String RESOURCE_NAME = "invitation";
    /*
    Path to file we read transport objects from
     */
    private final String PATH_TO_FILE = String.format("%s\\%s\\%s\\%s\\%s", "console-app", "src", "main", "files", "transport-repository.txt");
    /*
    stream we read user inputs from
     */
    private ShopReader reader;
    /*
    stream we write to
     */
    private ShopWriter writer;
    /*
    Object that will be used for recognizing
     */
    private CommandRecognizer commandRecognizer;
    /*
    Strategy that will be installed at the begin of program's work.
     */
    private ICreateStrategy strategy;
    /*
    Origin map of locals that accessible in this app
     */
    private HashMap<Integer, Locale> localeMap;
    /**
     * Product repository will be used for storing products
     */
    private ProductRepository<Transport> productRepository;
    /**
     * Cart repository will be used for storing user carts
     */
    private ICartRepository cartRepository;
    /**
     * Order repository will be used for storing user orders
     */
    private IOrderRepository<Transport> orderRepository;
    /**
     * Service to control history
     */
    private LastAddedProductService<Transport> storage;
    /**
     * cart service to manage user carts
     */
    private ICartService cartService;
    /**
     * order service to manage user orders
     */
    private IOrderService<Transport> orderService;
    /**
     * product service to manage all products
     */
    private IProductService<Transport> productService;


    public Application(InputStream inputStream, OutputStream outputStream) {
        this.reader = new ConsoleShopReader(inputStream);
        this.writer = new ConsoleShopWriter(outputStream);
        localeMap = new HashMap<>();
    }


    /**
     * This method starts entire application
     */
    public void start() {
        initApplication();
        String input = null;
        while (!EXIT_KEY.equals(input)) {
            try {
                commandRecognizer.recognizeAndExecute(Constants.PRINT_MENU_COMMAND);
                input = reader.readLine();
                commandRecognizer.recognizeAndExecute(input);
            } catch (UserInputException e) {
                writer.writeln(Constants.KEY_NOT_SUPPORTED_MESSAGE);
            }
        }
    }

    /**
     * This method sets current strategy by user's input. It takes strategy map we choose certain strategy from.
     *
     * @param strategy
     */
    public void chooseCreator(Map<Integer, ICreateStrategy> strategy) {
        writer.writeln(InvitationConstants.ENTER_INPUT_STRATEGY);
        writer.writeln(InvitationConstants.RANDOM_INPUT);
        writer.writeln(InvitationConstants.MANUAL_INPUT);
        int userInput = ValidateInputUtils.getValidOneZeroAnswer(writer, reader);
        this.strategy = strategy.get(userInput);
    }

    /**
     * This method provides comfortable interface to set up language. And returns code to define particular locale.
     *
     * @return code of locale
     */
    public int chooseLanguage() {
        writer.writeln(Constants.CHOOSE_LANGUAGE);
        writer.writeln(Constants.RUSSIAN);
        writer.writeln(Constants.ENGLISH);
        return ValidateInputUtils.getValidOneZeroAnswer(writer, reader);
    }

    /**
     * This method provides all transport objects from file.
     *
     * @return ArrayList of transport objects
     */
    public List<Transport> getAllTransportsFromFile() {
        try {
            return SerializeTransportUtils.extractTransports(PATH_TO_FILE);
        } catch (ClassNotFoundException e) {
            System.out.println(Constants.NO_ONE_SUITABLE_CLASS_IN_FILE_MESSAGE);
        }
        return null;
    }

    /**
     * This method assembles all parts of application together.
     */
    private void initApplication() {
        initLocaleMap();
        Integer languageCode = chooseLanguage();
        chooseCreator(buildStrategyMap(localeMap.get(languageCode)));
        initializationRepositoryLayer();
        initializationServiceLayer();
        initializationCommandRecognizer();
    }

    private void initLocaleMap() {
        localeMap.put(Constants.USER_INPUT_ZERO, LocaleConstants.RUSSIAN_LOCALE);
        localeMap.put(Constants.USER_INPUT_ONE, LocaleConstants.ENGLISH_LOCALE);

    }

    private Map<Integer, ICreateStrategy> buildStrategyMap(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
        IMaker randomMaker = new RandomMaker(writer, reader);
        IMaker manualMaker = new ManualMaker(writer, reader);
        TransportCreator randomPassengerCarCreator = new ReflectTransportCreator(randomMaker, resourceBundle, PassengerCar.class);
        TransportCreator manualPassengerCarCreator = new ReflectTransportCreator(manualMaker, resourceBundle, PassengerCar.class);
        TransportCreator manualTruckCreator = new ReflectTransportCreator(manualMaker, resourceBundle, Truck.class);
        TransportCreator randomTruckCreator = new ReflectTransportCreator(randomMaker, resourceBundle, Truck.class);
        ICreateStrategy randomStrategy = new CreateStrategyImpl();
        randomStrategy.addCreator(Constants.USER_INPUT_ZERO, randomPassengerCarCreator);
        randomStrategy.addCreator(Constants.USER_INPUT_ONE, randomTruckCreator);
        ICreateStrategy manualStrategy = new CreateStrategyImpl();
        manualStrategy.addCreator(Constants.USER_INPUT_ZERO, manualPassengerCarCreator);
        manualStrategy.addCreator(Constants.USER_INPUT_ONE, manualTruckCreator);
        Map<Integer, ICreateStrategy> strategyMap = new HashMap<>();
        strategyMap.put(Constants.USER_INPUT_ZERO, randomStrategy);
        strategyMap.put(Constants.USER_INPUT_ONE, manualStrategy);
        return strategyMap;
    }

    private void initializationRepositoryLayer() {
        productRepository = new ProductRepositoryImpl<>(getAllTransportsFromFile());
        cartRepository = new CartRepositoryImpl();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(Constants.DEFAULT_CARD_IDENTIFIER);
        cartRepository.addNewCard(shoppingCart);
        orderRepository = new OrderRepositoryImpl<>();
        storage = new LastAddedProductService<>(HISTORY_SIZE);
    }

    private void initializationServiceLayer() {
        cartService = new CartServiceImpl<>(cartRepository, productRepository, storage);
        orderService = new OrderServiceImpl<>(productRepository, orderRepository);
        productService = new ProductServiceImpl<Transport>(storage, productRepository);
    }

    private void initializationCommandRecognizer() {
        AddProductToCartCommand addProductToCartCommand = new AddProductToCartCommand(writer, reader, cartService);
        BuyAllProductFromCartCommand buyAllProductFromCartCommand = new BuyAllProductFromCartCommand(writer, reader, cartService);
        FiveLastProductCommand fiveLastProductCommand = new FiveLastProductCommand(writer, reader, productService);
        GetAllProductCommand getAllProductCommand = new GetAllProductCommand(writer, reader, productService);
        GetCartContentCommand getCartContentCommand = new GetCartContentCommand(writer, reader, cartService);
        MakeOrderCommand makeOrder = new MakeOrderCommand(writer, reader, orderService);
        GetOrdersForTimeRangeCommand getOrdersForTimeRange = new GetOrdersForTimeRangeCommand(writer, reader, orderService);
        GetMostRelevantOrderToDate getMostRelevantOrderToDate = new GetMostRelevantOrderToDate(writer, reader, orderService);
        PrintMenuCommand printMenuCommand = new PrintMenuCommand(writer, reader);
        AddProductCommand addProductCommand = new AddProductCommand(writer, reader, productService, strategy, PATH_TO_FILE);
        StopAppCommand stopAppCommand = new StopAppCommand(writer, reader, productService, PATH_TO_FILE);
        this.commandRecognizer = new CommandRecognizerImpl();
        commandRecognizer.addCommand(Constants.GET_ALL_PRODUCT_KEY, getAllProductCommand);
        commandRecognizer.addCommand(Constants.ADD_PRODUCT_TO_CART_KEY, addProductToCartCommand);
        commandRecognizer.addCommand(Constants.GET_CART_CONTENT, getCartContentCommand);
        commandRecognizer.addCommand(Constants.BUY_ALL_FROM_CART, buyAllProductFromCartCommand);
        commandRecognizer.addCommand(Constants.SHOW_LAST_FIVE, fiveLastProductCommand);
        commandRecognizer.addCommand(Constants.CREATE_ORDER, makeOrder);
        commandRecognizer.addCommand(Constants.GET_PRODUCTS_FOR_RANGE, getOrdersForTimeRange);
        commandRecognizer.addCommand(Constants.GET_MOST_RELEVANT_ORDER, getMostRelevantOrderToDate);
        commandRecognizer.addCommand(Constants.PRINT_MENU_COMMAND, printMenuCommand);
        commandRecognizer.addCommand(Constants.ADD_PRODUCT_COMMAND, addProductCommand);
        commandRecognizer.addCommand(Constants.STOP_APP_COMMAND, stopAppCommand);
    }
}
