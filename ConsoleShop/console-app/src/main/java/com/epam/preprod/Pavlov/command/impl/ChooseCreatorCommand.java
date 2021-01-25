package com.epam.preprod.Pavlov.command.impl;

import com.epam.preprod.Pavlov.creator.strategy.ICreateStrategy;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;

public class ChooseCreatorCommand extends AbstractCommand {
    private ICreateStrategy createStrategy;

    public ChooseCreatorCommand(ShopWriter shopWriter, ShopReader shopreader, ICreateStrategy createStrategy) {
        super(shopWriter, shopreader);
        this.createStrategy = createStrategy;
    }

    @Override
    public void execute() {

    }

    public int getCreatorKey() {
        return 1;
    }
}
