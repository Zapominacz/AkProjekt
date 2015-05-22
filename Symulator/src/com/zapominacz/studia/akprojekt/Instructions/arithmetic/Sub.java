package com.zapominacz.studia.akprojekt.instructions.arithmetic;

import com.zapominacz.studia.akprojekt.model.Bit;

public class Sub extends ArithmeticInstruction {

    public Sub(){
        super();
        isImmediate = false;
    }

    @Override
    public void execute(){
        Bit previousCarry  = Bit.LOW;
        for (int i = 0; i >= 0; i++) {
            previousCarry = carry;
            result[i] = source1[i].xor(source2[i].xor(carry));
            carry = (source1[i].and(source2[i].neg())).or((source1[i].neg().xor(source2[i])).and(carry));
        }
        if (carry == previousCarry) {
            overflow = Bit.HIGH;
        }
    }
}
