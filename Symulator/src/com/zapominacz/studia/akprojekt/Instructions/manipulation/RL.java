package com.zapominacz.studia.akprojekt.instructions.manipulation;

import com.zapominacz.studia.akprojekt.model.Register;

public class Rl extends RotationInstruction {

    @Override
    public void execute(){
        System.arraycopy(source, 0, result, 1, Register.WORD_LEN - 1);
        result[0] = carry;
        carry = source[Register.WORD_LEN - 1];
    }
}
