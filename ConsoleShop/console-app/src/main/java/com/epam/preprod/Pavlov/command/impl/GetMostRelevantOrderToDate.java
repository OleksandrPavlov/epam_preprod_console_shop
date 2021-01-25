package com.epam.preprod.Pavlov.command.impl;

import com.epam.preprod.Pavlov.entity.Order;
import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.service.IOrderService;
import com.epam.preprod.Pavlov.util.constants.Constants;
import com.epam.preprod.Pavlov.util.constants.ErrorMessageConstants;
import com.epam.preprod.Pavlov.util.constants.InvitationConstants;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class GetMostRelevantOrderToDate extends AbstractCommand {
    private static final String CUSTOM_TIME_PATTERN = "dd.MM.yyyy HH:mm:ss";
    private IOrderService<Transport> orderService;

    public GetMostRelevantOrderToDate(ShopWriter shopWriter, ShopReader shopreader, IOrderService<Transport> orderService) {
        super(shopWriter, shopreader);
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        LocalDateTime localDateTime;
        getShopWriter().writeln(InvitationConstants.CHOOSING_ORDER_FOR_RELEVANT_DATE);
        getShopWriter().write(InvitationConstants.INPUT_DATE + "(" + CUSTOM_TIME_PATTERN + ")");

        try {
            localDateTime = readTime();
        } catch (DateTimeParseException ex) {
            getShopWriter().writeln(ErrorMessageConstants.INVALID_DATE_FORMAT);
            return;
        }
        Order<Transport> order = orderService.theMostRelevantOrder(localDateTime);
        if (order == null) {
            getShopWriter().writeln(Constants.NOTHING_TO_DATE);
            return;
        }
        getShopWriter().write(order);
    }

    private LocalDateTime readTime() {
        LocalDateTime localDateTime;
        String date = getShopReader().readLine();
        localDateTime = LocalDateTime.from(DateTimeFormatter.ofPattern(CUSTOM_TIME_PATTERN).parse(date));

        return localDateTime;
    }


}
