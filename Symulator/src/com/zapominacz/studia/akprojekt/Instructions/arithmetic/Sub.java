package com.zapominacz.studia.akprojekt.instructions.arithmetic;

import com.zapominacz.studia.akprojekt.model.Bit;
import com.zapominacz.studia.akprojekt.model.Register;

public class Sub extends ArithmeticInstruction {

    public Sub(){
        super();
        isImmediate = false;
    }

    @Override
    public void execute(){
        Bit previousCarry  = Bit.LOW;
        for (int i = 0; i < Register.WORD_LEN; i++) {
            previousCarry = carry;
            result[i] = source2[i].xor(source1[i].xor(carry));
            carry = (source2[i].and(source1[i].neg())).or((source2[i].neg().xor(source1[i])).and(carry));
        }
        if (carry != previousCarry) {
            overflow = Bit.HIGH;
        }
    }
}
