package com.zapominacz.studia.akprojekt.util;

public class InputNumberFormat {
    private String regex;
    private int base;
    private int trimCount;
    private boolean fromLeft;

    public InputNumberFormat(String regex, int base, boolean fromLeft, int trimCount) {
        this.regex = regex;
        this.base = base;
        this.fromLeft = fromLeft;
        this.trimCount = trimCount;
    }

    public String convertStringNumberToBinaryString(String formattedNumber) {
        if(fromLeft) {
            formattedNumber = formattedNumber.substring(trimCount);
        } else {
            formattedNumber = formattedNumber.substring(0, trimCount);
        }
        formattedNumber = Integer.toUnsignedString(Integer.parseInt(formattedNumber, base), 2);
        return formattedNumber;
    }

    public String getRegex() {
        return regex;
    }
}
