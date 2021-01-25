package com.epam.preprod.Pavlov.command.impl;

import com.epam.preprod.Pavlov.creator.strategy.ICreateStrategy;
import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.service.IProductService;
import com.epam.preprod.Pavlov.util.ValidateInputUtils;
import com.epam.preprod.Pavlov.util.constants.InvitationConstants;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;

public class AddProductCommand extends AbstractCommand {
    private static final String SYSTEM_ERROR_MESSAGE = "System error was occur. Please try to add product again";
    private IProductService<Transport> service;
    private ICreateStrategy strategy;
    private String pathToFile;


    public AddProductCommand(ShopWriter shopWriter, ShopReader shopreader, IProductService<Transport> service, ICreateStrategy strategy, String pathToFile) {
        super(shopWriter, shopreader);
        this.service = service;
        this.strategy = strategy;
        this.pathToFile = pathToFile;
    }

    @Override
    public void execute() {
        getShopWriter().writeln(InvitationConstants.ENTER_TRANSPORT_NAME);
        getShopWriter().writeln(InvitationConstants.CHOOSE_PASSENGER_CAR);
        getShopWriter().writeln(InvitationConstants.CHOOSE_TRUCK);
        int userInput = ValidateInputUtils.getValidOneZeroAnswer(getShopWriter(), getShopReader());
        Transport transport = strategy.getCreator(userInput).create();
        if (transport != null) {
            service.addProduct(transport);
            service.loadProductsToFile(pathToFile);
        } else {
            getShopWriter().writeln(SYSTEM_ERROR_MESSAGE);
        }
    }
}
