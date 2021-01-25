package com.epam.preprod.Pavlov.command.impl;

import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.service.IProductService;
import com.epam.preprod.Pavlov.util.constants.ErrorMessageConstants;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;

import java.util.List;

public class GetAllProductCommand extends AbstractCommand {
    private IProductService<Transport> productService;

    public GetAllProductCommand(ShopWriter shopWriter, ShopReader shopreader, IProductService<Transport> productService) {
        super(shopWriter, shopreader);
        this.productService = productService;
    }

    @Override
    public void execute() {
        List<Transport> list = productService.getAllProducts();
        if (list.size() > 0) {
            printAllProducts(list);
        } else {
            getShopWriter().writeln(ErrorMessageConstants.EMPTY_REPOSITORY);
        }
    }

    public void printAllProducts(List products) {
        if (products.size() == 0) {
            getShopWriter().writeln(ErrorMessageConstants.EMPTY_STORAGE);
        } else {
            getShopWriter().write(products);
        }
    }
}
