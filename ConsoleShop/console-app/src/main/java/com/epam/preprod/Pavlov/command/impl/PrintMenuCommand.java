package com.epam.preprod.Pavlov.command.impl;

import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;

public class PrintMenuCommand extends AbstractCommand {

    public PrintMenuCommand(ShopWriter shopWriter, ShopReader shopreader) {
        super(shopWriter, shopreader);
    }

    @Override
    public void execute() {
        printMenu();
    }

    private void printMenu() {
        getShopWriter().writeln("--------------------------Меню------------------------------");
        getShopWriter().writeln("1. Вывести список товаров.");
        getShopWriter().writeln("2. Добавить товар в корзину.");
        getShopWriter().writeln("3. Посмотреть содержимое корзины.");
        getShopWriter().writeln("4. Купить все товары из корзины.");
        getShopWriter().writeln("5. Посмотреть информацию о 5 последних товарах.");
        getShopWriter().writeln("6. Оформить заказ.");
        getShopWriter().writeln("7. Посмотреть список заказов за заданый промежуток времени.");
        getShopWriter().writeln("8. Выбор заказа по ближайшей дате.");
        getShopWriter().writeln("9. Добавить новый транспорт.");
        getShopWriter().writeln("0. Выход.");
        getShopWriter().writeln("-----------------------------------------------------------------");
    }
}
