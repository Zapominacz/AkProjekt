package com.zapominacz.studia.akprojekt.util;

public enum Bit {
    HIGH(true), LOW(false);

    private boolean value;

    private Bit(boolean value) {
        this.value = value;
    }

    public boolean getBooleanValue() {
        return value;
    }

    public Bit getByValue(boolean high) {
        if(high) {
            return HIGH;
        } else {
            return LOW;
        }
    }

    public Bit xor(Bit bit) {
        return getByValue(value ^ bit.value);
    }

    public Bit and(Bit bit) {
        return getByValue(value & bit.value);
    }

    public Bit or(Bit bit) {
        return getByValue(value | bit.value);
    }
}
