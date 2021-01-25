package com.epam.preprod.Pavlov.command.impl;

import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.service.IProductService;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;

public class FiveLastProductCommand extends AbstractCommand {
    private IProductService<Transport> productService;

    public FiveLastProductCommand(ShopWriter shopWriter, ShopReader shopreader, IProductService<Transport> productService) {
        super(shopWriter, shopreader);
        this.productService = productService;
    }

    @Override
    public void execute() {
        getShopWriter().write(productService.getLastProductInfo());
    }


}
