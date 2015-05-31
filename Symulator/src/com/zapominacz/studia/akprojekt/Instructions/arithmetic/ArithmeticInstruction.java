package com.zapominacz.studia.akprojekt.instructions.arithmetic;

import com.zapominacz.studia.akprojekt.core.Memory;
import com.zapominacz.studia.akprojekt.core.Processor;
import com.zapominacz.studia.akprojekt.enums.RegisterSection;
import com.zapominacz.studia.akprojekt.instructions.Instruction;
import com.zapominacz.studia.akprojekt.model.Bit;
import com.zapominacz.studia.akprojekt.model.Register;
import com.zapominacz.studia.akprojekt.utils.Bits;

public abstract class ArithmeticInstruction extends Instruction {

    protected Bit[] result;
    protected Bit[] source1;
    protected Bit[] source2;
    protected Bit zero;
    protected Bit carry;
    protected Bit sign;
    protected Bit overflow;
    protected boolean isImmediate;

    @Override
    public void saveResult(Memory memory) {}

    @Override
    public void loadArguments(Memory memory) {}

    public ArithmeticInstruction() {
        result = Bits.createBits(Register.WORD_LEN);
    }

    @Override
    public void loadArguments(Register[] registers) {
        carry = registers[Processor.FLAGS].getBits()[Processor.FLAG_CARRY];
        sign = registers[Processor.FLAGS].getBits()[Processor.FLAG_SIGN];
        zero = registers[Processor.FLAGS].getBits()[Processor.FLAG_ZERO];
        overflow = registers[Processor.FLAGS].getBits()[Processor.FLAG_OVERFLOW];
        source1 = registers[firstArgRegister].getBits();
        if(!isImmediate) {
            source2 = registers[secondArgRegister].getBits();
        }
    }

    @Override
    public void saveResult(Register[] registers) {
        if(Bits.parseInteger(result) == 0) {
            zero = Bit.HIGH;
        } else {
            zero = Bit.LOW;
        }
        if(result[31] == Bit.HIGH) {
            sign = Bit.HIGH;
        } else {
            sign = Bit.LOW;
        }
        registers[outputRegister].setRegisterValue(Bits.copy(result));
        Bit[] flag = registers[Processor.FLAGS].getBits();
        flag[Processor.FLAG_CARRY] = carry;
        flag[Processor.FLAG_SIGN] = sign;
        flag[Processor.FLAG_ZERO] = zero;
        flag[Processor.FLAG_OVERFLOW] = overflow;
        registers[Processor.FLAGS].refreshView();
    }

    @Override
    public void parseArguments(Bit[] argument) {
        firstArgRegister = Bits.parseInteger(Bits.getBits(argument,
                RegisterSection.FIRST_REG_START.getIndex(), RegisterSection.FIRST_REG_END.getIndex()));
        if(isImmediate) {
            source2 = Bits.parseBits(Bits.parseInteger(Bits.getBits(argument,
                    RegisterSection.IMMEDIATE_START.getIndex(), RegisterSection.IMMEDIATE_END.getIndex())), Register.WORD_LEN);
        } else {
            secondArgRegister = Bits.parseInteger(Bits.getBits(argument,
                    RegisterSection.SECOND_REG_START.getIndex(), RegisterSection.SECOND_REG_END.getIndex()));
        }
        outputRegister = Bits.parseInteger(Bits.getBits(argument,
                RegisterSection.OUT_REG_START.getIndex(), RegisterSection.OUT_REG_END.getIndex()));
    }

}
