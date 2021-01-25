package com.epam.preprod.Pavlov.command.impl;

import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.exception.CheckoutException;
import com.epam.preprod.Pavlov.service.IOrderService;
import com.epam.preprod.Pavlov.util.constants.Constants;
import com.epam.preprod.Pavlov.util.constants.ErrorMessageConstants;
import com.epam.preprod.Pavlov.util.constants.InvitationConstants;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class MakeOrderCommand extends AbstractCommand {
    private static final String CUSTOM_TIME_PATTERN = "dd.MM.yyyy HH:mm:ss";
    private IOrderService<Transport> orderService;

    public MakeOrderCommand(ShopWriter shopWriter, ShopReader shopreader, IOrderService<Transport> orderService) {
        super(shopWriter, shopreader);
        this.orderService = orderService;
    }

    @Override
    public void execute() {
        List<Integer> list;
        LocalDateTime localDateTime;
        try {
            list = getIdentifierList();

            getShopWriter().write(InvitationConstants.INPUT_DATE + "(" + CUSTOM_TIME_PATTERN + ")");
            localDateTime = readDateTime();
        } catch (NumberFormatException | DateTimeParseException ex) {
            if (ex instanceof NumberFormatException) {
                getShopWriter().writeln(Constants.CHECKOUT_CL + " " + ErrorMessageConstants.INVALID_PRODUCT_ID);
                return;
            }
            getShopWriter().writeln(Constants.CHECKOUT_CL + " " + ErrorMessageConstants.INVALID_DATE_FORMAT);
            return;
        }
        try {
            orderService.submitAnOrder(list, localDateTime);
        } catch (CheckoutException e) {
            getShopWriter().writeln(Constants.CHECKOUT_CL + " " + ErrorMessageConstants.INVALID_PRODUCT_ID);
            return;
        }
        getShopWriter().writeln(Constants.CHECKOUT_SC);
    }

    private List<Integer> getIdentifierList() {
        List<Integer> list = new ArrayList<Integer>();
        getShopWriter().writeln(Constants.CHECKOUT_TBL);
        getShopWriter().writeln(InvitationConstants.CHECKOUT_INPUT_IDENTIFIERS);
        String input = null;
        while (!Constants.ADMIT.equals(input) && !Constants.REJECT.equals(input)) {
            getShopWriter().write("->>");
            input = getShopReader().readLine();
            if (!"+".equals(input) && !"-".equals(input)) {
                list.add(Integer.valueOf(input));
            }
        }
        return list;
    }

    private LocalDateTime readDateTime() {
        LocalDateTime localDateTime = null;
        String date = getShopReader().readLine();
        localDateTime = LocalDateTime.from(DateTimeFormatter.ofPattern(CUSTOM_TIME_PATTERN).parse(date));
        return localDateTime;
    }

}
