package com.zapominacz.studia.akprojekt.instructions.logical;

import com.zapominacz.studia.akprojekt.core.Memory;
import com.zapominacz.studia.akprojekt.core.Processor;
import com.zapominacz.studia.akprojekt.enums.RegisterSection;
import com.zapominacz.studia.akprojekt.instructions.Instruction;
import com.zapominacz.studia.akprojekt.model.Bit;
import com.zapominacz.studia.akprojekt.model.Register;
import com.zapominacz.studia.akprojekt.utils.Bits;

public abstract class LogicalInstruction extends Instruction {

    protected Bit[] result;
    protected Bit[] source1;
    protected Bit[] source2;
    protected Bit zero;

    public LogicalInstruction() {
        result = Bits.createBits(Register.WORD_LEN);
    }

    @Override
    public void parseArguments(Bit[] argument) {
        firstArgRegister = Bits.parseInteger(Bits.getBits(argument,
                RegisterSection.FIRST_REG_START.getIndex(), RegisterSection.FIRST_REG_END.getIndex()));
        secondArgRegister = Bits.parseInteger(Bits.getBits(argument,
                RegisterSection.SECOND_REG_START.getIndex(), RegisterSection.SECOND_REG_END.getIndex()));
        outputRegister = Bits.parseInteger(Bits.getBits(argument,
                RegisterSection.OUT_REG_START.getIndex(), RegisterSection.OUT_REG_END.getIndex()));
    }

    @Override
    public void loadArguments(Register[] registers) {
        zero = registers[Processor.FLAGS].getBits()[Processor.FLAG_ZERO];
        source1 = registers[firstArgRegister].getBits();
        source2 = registers[secondArgRegister].getBits();
    }

    @Override
    public void saveResult(Register[] registers) {
        if(Bits.parseInteger(result) == 0) {
            zero = Bit.HIGH;
        } else {
            zero = Bit.LOW;
        }
        registers[outputRegister].setRegisterValue(Bits.copy(result));
        registers[Processor.FLAGS].refreshView();
    }

    @Override
    public void saveResult(Memory memory) {}

    @Override
    public void loadArguments(Memory memory) {}
}
