package com.epam.preprod.Pavlov.command.impl;

import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.service.IProductService;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;

public class StopAppCommand extends AbstractCommand {
    private IProductService<Transport> service;
    private String fileName;

    public StopAppCommand(ShopWriter shopWriter, ShopReader shopreader, IProductService<Transport> service, String fileName) {
        super(shopWriter, shopreader);
        this.service = service;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        service.loadProductsToFile(fileName);
    }
}
