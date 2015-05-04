package com.zapominacz.studia.akprojekt.util;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

public class Conversions {

    public static int convertBooleanArrayToInteger(@NotNull Boolean[] array) {
        int result = 0;
        for(boolean bit : array) {
            result *= 2;
            if(bit) {
                result++;
            }
        }
        return result;
    }

    @NotNull
    public static String convertBooleanArrayToString(@NotNull Boolean[] array, int base, @Nullable CharSequence prefix) {
        StringBuilder result = new StringBuilder(prefix);
        int value = convertBooleanArrayToInteger(array);
        while(value > 0) {
            int currentValue = value % base;
            result.insert(0, Integer.toHexString(currentValue));
            value /= base;
        }
        return result.toString();
    }

    @NotNull
    public static Boolean[] convertIntegerToBooleanArray(long value, int arraySize) {
        Boolean[] result = new Boolean[arraySize];
        for(int i = 0; i < arraySize; i++) {
            result[i] = (value % 2 == 1);
            value /= 2;
        }
        return result;
    }

    @NotNull
    public static Boolean[] convertStringToBooleanArray(@NotNull String value, int base, int arraySize) {
        long val = Long.parseLong(value, base);
        return convertIntegerToBooleanArray(val, arraySize);
    }
}
