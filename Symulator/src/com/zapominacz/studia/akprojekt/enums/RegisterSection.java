package com.zapominacz.studia.akprojekt.enums;

/**
 * Beginnig inclusive, ending exclusive
 */
public enum RegisterSection {
    OPCODE_START(0), OPCODE_END(5),
    OUT_REG_START(5), OUT_REG_END(10),
    FIRST_REG_START(10), FIRST_REG_END(15),
    SECOND_REG_START(15), SECOND_REG_END(20),
    IMMEDIATE_START(20), IMMEDIATE_END(28),
    COND_CODE_START(28), COND_CODE_END(32);

    private int index;

    private RegisterSection(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
