package com.epam.preprod.Pavlov.command.impl;

import com.epam.preprod.Pavlov.entity.Order;
import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.service.IOrderService;
import com.epam.preprod.Pavlov.util.constants.ErrorMessageConstants;
import com.epam.preprod.Pavlov.util.constants.InvitationConstants;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class GetOrdersForTimeRangeCommand extends AbstractCommand {
    private static final String CUSTOM_TIME_PATTERN = "dd.MM.yyyy HH:mm:ss";
    private IOrderService<Transport> orderService;

    public GetOrdersForTimeRangeCommand(ShopWriter shopWriter, ShopReader shopreader, IOrderService<Transport> orderService) {
        super(shopWriter, shopreader);
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        LocalDateTime localDateTimeBefore = null;
        LocalDateTime localDateTimeAfter = null;
        getShopWriter().writeln(InvitationConstants.ORDER_CHOOSING);
        getShopWriter().write(InvitationConstants.TIME_BEFORE + "(" + CUSTOM_TIME_PATTERN + ") ->>");
        try {
            localDateTimeBefore = readTime();
            getShopWriter().write(InvitationConstants.TIME_AFTER + "(" + CUSTOM_TIME_PATTERN + ") ->>");
            localDateTimeAfter = readTime();
        } catch (DateTimeParseException ex) {
            getShopWriter().writeln(ErrorMessageConstants.INVALID_DATE_FORMAT);
            return;
        }
        //validation of date format
        if (!DateTimeIsValid(localDateTimeBefore, localDateTimeAfter)) {
            getShopWriter().writeln(ErrorMessageConstants.INVALID_DATE_RANGE);
            return;
        }
        List<Order<Transport>> list = orderService.OrderListForRange(localDateTimeBefore, localDateTimeAfter);
        getShopWriter().write(list);
    }

    private boolean DateTimeIsValid(LocalDateTime localDateTimeBefore, LocalDateTime localDateTimeAfter) {
        if (localDateTimeBefore.compareTo(localDateTimeAfter) > 0) {
            return false;
        }
        return true;
    }

    private LocalDateTime readTime() {
        LocalDateTime localDateTime = null;
        String date = getShopReader().readLine();
        localDateTime = LocalDateTime.from(DateTimeFormatter.ofPattern(CUSTOM_TIME_PATTERN).parse(date));
        return localDateTime;
    }
}
