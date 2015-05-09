package com.zapominacz.studia.akprojekt.model;

import com.zapominacz.studia.akprojekt.util.Bit;

public class Register {

    public final static int WORD_LEN = 32;
    private Bit[] registerValue;

    public Bit[] getBits() {
        return registerValue;
    }

    public void setRegisterValue(Bit[] registerValue) {
        this.registerValue = registerValue;
    }
}
