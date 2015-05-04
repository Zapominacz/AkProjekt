package com.zapominacz.studia.akprojekt.model;

import com.zapominacz.studia.akprojekt.util.Conversions;

public class Register {

    public final static int WORD_LEN = 32;

    Boolean[] value;
    private int registerNumber;

    public int getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(int registerNumber) {
        this.registerNumber = registerNumber;
    }

    public int getRegisterValue() {
        return Conversions.convertBooleanArrayToInteger(value);
    }

    public void setRegisterValue(String value, int base) {
        this.value = Conversions.convertStringToBooleanArray(value, base, WORD_LEN);
    }

}
