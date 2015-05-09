package com.zapominacz.studia.akprojekt.instructions.logical;

import com.zapominacz.studia.akprojekt.model.Register;

public class Or extends LogicalInstruction {

    @Override
    public void execute() {
        for(int i = 0; i < Register.WORD_LEN; i++) {
            result[i] = source1[i].or(source2[i]);
        }
    }
}
