package com.epam.preprod.Pavlov.command.impl;

import com.epam.preprod.Pavlov.entity.ShoppingCart;
import com.epam.preprod.Pavlov.service.ICartService;
import com.epam.preprod.Pavlov.util.constants.Constants;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;

public class GetCartContentCommand extends AbstractCommand {
    private ICartService cartService;

    public GetCartContentCommand(ShopWriter shopWriter, ShopReader shopreader, ICartService cartService) {
        super(shopWriter, shopreader);
        this.cartService = cartService;
    }

    @Override
    public void execute() {
        ShoppingCart shoppingCard = cartService.getCartById(Constants.DEFAULT_CARD_IDENTIFIER);
        printCardContent(shoppingCard);
    }

    public void printCardContent(ShoppingCart shoppingCard) {
        getShopWriter().writeln(Constants.CART_CONTENT + " " + shoppingCard.getId());
        getShopWriter().write(shoppingCard.printCard());
    }
}
