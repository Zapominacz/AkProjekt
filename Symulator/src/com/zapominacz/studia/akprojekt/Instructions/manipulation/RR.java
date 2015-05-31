package com.zapominacz.studia.akprojekt.instructions.manipulation;

import com.zapominacz.studia.akprojekt.model.Register;

public class Rr extends RotationInstruction {

    @Override
    public void execute(){
        result[Register.WORD_LEN - 1] = carry;
        System.arraycopy(source, 1, result, 0, Register.WORD_LEN - 1);
        carry = source[0];
    }
}
