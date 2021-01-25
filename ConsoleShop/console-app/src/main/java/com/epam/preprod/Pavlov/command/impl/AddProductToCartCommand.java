package com.epam.preprod.Pavlov.command.impl;

import com.epam.preprod.Pavlov.exception.UserInputException;
import com.epam.preprod.Pavlov.service.ICartService;
import com.epam.preprod.Pavlov.util.constants.Constants;
import com.epam.preprod.Pavlov.util.constants.ErrorMessageConstants;
import com.epam.preprod.Pavlov.util.constants.InvitationConstants;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;

public class AddProductToCartCommand extends AbstractCommand {
    private ICartService cartService;

    public AddProductToCartCommand(ShopWriter shopWriter, ShopReader shopreader, ICartService cartService) {
        super(shopWriter, shopreader);
        this.cartService = cartService;
    }

    @Override
    public void execute() {

        getShopWriter().write(InvitationConstants.INSERT_PRODUCT_ID);
        String userInput = "";
        userInput = getShopReader().readLine();
        try {
            Integer userInputInteger = Integer.valueOf(userInput);
            cartService.addProductToCart(userInputInteger, Constants.DEFAULT_CARD_IDENTIFIER);
            getShopWriter().writeln(Constants.PRODUCT_INSERTION_SUC);
        } catch (NumberFormatException | UserInputException ex) {
            if (ex instanceof UserInputException) {
                getShopWriter().writeln(ErrorMessageConstants.UNKNOWN_IDENTIFIER);
            } else {
                getShopWriter().writeln(ErrorMessageConstants.INVALID_PRODUCT_ID);
            }
        }
    }

}
