package com.zapominacz.studia.akprojekt.enums;

public enum RegisterSection {
    OPCODE_START(27), OPCODE_END(32),
    OUT_REG_START(22), OUT_REG_END(27),
    FIRST_REG_START(17), FIRST_REG_END(22),
    SECOND_REG_START(12), SECOND_REG_END(17),
    IMMEDIATE_START(4), IMMEDIATE_END(12),
    COND_CODE_START(0), COND_CODE_END(4);

    private int index;

    private RegisterSection(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}