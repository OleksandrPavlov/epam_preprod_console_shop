package com.epam.preprod.Pavlov.util.io.impl;

import com.epam.preprod.Pavlov.util.io.ShopWriter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;

public class ConsoleShopWriter extends ShopWriter {
    private static final String IO_EXCEPTION_MESSAGE="There was an io exception. Probably stream you writing to is not available to write in. ";
    public ConsoleShopWriter(OutputStream outputStream) {
        super(outputStream);
    }

    @Override
    public void printMenu() {
        writeln("--------------------------Меню------------------------------");
        writeln("1. Вывести список товаров.");
        writeln("2. Добавить товар в корзину.");
        writeln("3. Посмотреть содержимое корзины.");
        writeln("4. Купить все товары из корзины.");
        writeln("5. Посмотреть информацию о 5 последних товарах.");
        writeln("6. Оформить заказ.");
        writeln("7. Посмотреть список заказов за заданый промежуток времени.");
        writeln("8. Выбор заказа по ближайшей дате.");
        writeln("0. Выход.");
        writeln("-----------------------------------------------------------------");
    }


    @Override
    public void print(String message) {
        BufferedWriter localeBufferedWriter = getBufferedWriter();
        try {
            localeBufferedWriter.write(message + System.lineSeparator());
        } catch (IOException e) {
            System.out.println(IO_EXCEPTION_MESSAGE);
        }
    }
}
