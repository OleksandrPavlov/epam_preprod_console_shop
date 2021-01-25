package com.epam.preprod.Pavlov.util;


import com.epam.preprod.Pavlov.util.constants.Constants;
import com.epam.preprod.Pavlov.util.constants.ErrorMessageConstants;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;
import org.apache.commons.lang3.math.NumberUtils;

public class ValidateInputUtils {
    private ValidateInputUtils() {

    }

    public static int getValidOneZeroAnswer(ShopWriter customWriter, ShopReader customReader) {
        String input;
        int number;
        while (true) {
            input = customReader.readLine();
            if (!NumberUtils.isDigits(input)) {
                invalidInput(customWriter);
                continue;
            }
            number = Integer.parseInt(input);
            if (number > 1 || number < 0) {
                invalidInput(customWriter);
                continue;
            }
            break;
        }
        return number;
    }


    public static int getValidId(ShopWriter writer, ShopReader reader) {
        String input;
        int number;
        while (true) {
            input = reader.readLine();
            if (!NumberUtils.isDigits(input)) {
                invalidInput(writer);
                continue;
            }
            number = Integer.parseInt(input);
            if (number < 0) {
                invalidInput(writer);
                continue;
            }
            break;
        }
        return number;
    }

    public static String getValidName(ShopWriter writer, ShopReader reader) {
        String input;
        while (true) {
            input = reader.readLine();
            if (!input.matches("\\w{2,}")) {
                invalidInput(writer);
                continue;
            }
            break;
        }
        return input;
    }

    public static double getPositiveDouble(ShopWriter writer, ShopReader reader) {
        String input;
        double number;
        while (true) {
            input = reader.readLine();
            if (!NumberUtils.isCreatable(input)) {
                invalidInput(writer);
                continue;
            }
            number = Double.parseDouble(input);
            break;
        }
        return number;
    }

    private static void invalidInput(ShopWriter writer) {
        writer.writeln(ErrorMessageConstants.INVALID_VALUE);
        writer.writeln(Constants.REPEAT);
    }

}
