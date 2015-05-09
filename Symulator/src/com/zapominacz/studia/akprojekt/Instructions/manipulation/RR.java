package com.zapominacz.studia.akprojekt.instructions.manipulation;

import com.zapominacz.studia.akprojekt.model.Register;

public class Rr extends RotationInstruction {

    @Override
    public void execute(){
        for(int i = 0; i < Register.WORD_LEN; i++) {
            result[i] = source[(i - 1) % Register.WORD_LEN]; //TODO modula w Javie moze byc nei ten tegos
        }
        carry = source[0];
    }
}
