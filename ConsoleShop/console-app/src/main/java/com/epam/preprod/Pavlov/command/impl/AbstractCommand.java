package com.epam.preprod.Pavlov.command.impl;

import com.epam.preprod.Pavlov.command.ICommand;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;

public abstract class AbstractCommand implements ICommand {
    private ShopWriter shopWriter;
    private ShopReader shopReader;

    public AbstractCommand(ShopWriter shopWriter, ShopReader shopreader) {
        this.shopWriter = shopWriter;
        this.shopReader = shopreader;
    }

    public ShopWriter getShopWriter() {
        return shopWriter;
    }

    public void setShopWriter(ShopWriter shopWriter) {
        this.shopWriter = shopWriter;
    }

    public ShopReader getShopReader() {
        return shopReader;
    }

    public void setShopReader(ShopReader shopReader) {
        this.shopReader = shopReader;
    }



}
