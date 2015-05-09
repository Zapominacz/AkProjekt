package com.zapominacz.studia.akprojekt.instructions.manipulation;

import com.zapominacz.studia.akprojekt.core.Processor;
import com.zapominacz.studia.akprojekt.enums.RegisterSection;
import com.zapominacz.studia.akprojekt.instructions.Instruction;
import com.zapominacz.studia.akprojekt.memory.Memory;
import com.zapominacz.studia.akprojekt.model.Bit;
import com.zapominacz.studia.akprojekt.model.Register;
import com.zapominacz.studia.akprojekt.utils.Bits;

public class Rl extends RotationInstruction {

    @Override
    public void execute(){
        for(int i = 0; i < Register.WORD_LEN; i++) {
            result[i] = source[(i + 1) % Register.WORD_LEN]; //TODO modula w Javie moze byc nei ten tegos
        }
        carry = source[Register.WORD_LEN - 1];
    }
}
