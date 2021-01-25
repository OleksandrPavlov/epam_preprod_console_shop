package com.epam.preprod.Pavlov.command.impl;

import com.epam.preprod.Pavlov.entity.ShoppingCart;
import com.epam.preprod.Pavlov.service.ICartService;
import com.epam.preprod.Pavlov.util.constants.Constants;
import com.epam.preprod.Pavlov.util.constants.ErrorMessageConstants;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;


public class BuyAllProductFromCartCommand extends AbstractCommand {
    private ICartService cartService;

    public BuyAllProductFromCartCommand(ShopWriter shopWriter, ShopReader shopreader, ICartService cartService) {
        super(shopWriter, shopreader);
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        buyAllProductsFromCardPrint(cartService.getCartById(Constants.DEFAULT_CARD_IDENTIFIER));
        boolean yes = askYesNo();
        if (yes) {
            cartService.buyAllProductFromCart(Constants.DEFAULT_CARD_IDENTIFIER);
            getShopWriter().print(ErrorMessageConstants.ALL_PRODUCTS_PURCHASED);
            return;
        }
        getShopWriter().print(ErrorMessageConstants.OPERATION_CANCELED);
    }

    private void buyAllProductsFromCardPrint(ShoppingCart shoppingCard) {
        getShopWriter().writeln(Constants.BUY_ALL_PROD_ORDER);
        printCardContent(shoppingCard);
        getShopWriter().write(Constants.ORDER_SUM + shoppingCard.getSum() + System.lineSeparator());
    }

    private void printCardContent(ShoppingCart shoppingCard) {
        getShopWriter().writeln(Constants.CART_CONTENT + " " + shoppingCard.getId());
        getShopWriter().writeln(shoppingCard.printCard());
    }

    private boolean askYesNo() {
        getShopWriter().write(Constants.ADMIT_ACTION_YES_NO);
        String access = getShopReader().readLine();
        return access.equals(Constants.YES);
    }

}
