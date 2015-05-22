package com.zapominacz.studia.akprojekt.instructions.arithmetic;

import com.zapominacz.studia.akprojekt.model.Bit;
import com.zapominacz.studia.akprojekt.model.Register;
import com.zapominacz.studia.akprojekt.utils.Bits;

public class Mul extends ArithmeticInstruction {

    @Override
    public void execute() {
        long src1 = Bits.parseLong(source1);
        long src2 = Bits.parseLong(source2);
        long res = src2 * src1;
        result = Bits.parseBits((int) res, Register.WORD_LEN);
        if(res == 0) {
            zero = Bit.HIGH;
        } else if(res < 0) {
            sign = Bit.HIGH;
        }
        if(Math.abs(res) > Integer.MAX_VALUE) {
            carry = Bit.HIGH;
        }
        //TODO overflow
        if(false) {
            overflow = Bit.HIGH;
        }
    }
}
