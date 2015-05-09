package com.zapominacz.studia.akprojekt.instructions.manipulation;

import com.zapominacz.studia.akprojekt.core.Processor;
import com.zapominacz.studia.akprojekt.enums.RegisterSection;
import com.zapominacz.studia.akprojekt.instructions.Instruction;
import com.zapominacz.studia.akprojekt.memory.Memory;
import com.zapominacz.studia.akprojekt.model.Bit;
import com.zapominacz.studia.akprojekt.model.Register;
import com.zapominacz.studia.akprojekt.utils.Bits;

public abstract class RotationInstruction extends Instruction {

    protected Bit[] result;
    protected Bit[] source;
    protected Bit carry;

    public RotationInstruction() {
        result = Bits.createBits(Register.WORD_LEN);
    }

    @Override
    public void parseArguments(Bit[] argument) {
        firstArgRegister = Bits.parseInteger(Bits.getBits(argument,
                RegisterSection.FIRST_REG_START.getIndex(), RegisterSection.FIRST_REG_END.getIndex()));
        outputRegister = Bits.parseInteger(Bits.getBits(argument,
                RegisterSection.OUT_REG_START.getIndex(), RegisterSection.OUT_REG_END.getIndex()));
    }

    @Override
    public void loadArguments(Register[] registers) {
        carry = registers[Processor.FLAGS].getBits()[Processor.FLAG_CARRY];
        source = registers[firstArgRegister].getBits();
    }

    @Override
    public void saveResult(Register[] registers) {
        registers[outputRegister].setRegisterValue(result);
        registers[Processor.FLAGS].getBits()[Processor.FLAG_CARRY] = carry;
    }

    @Override
    public void saveResult(Memory memory) {}

    @Override
    public void loadArguments(Memory memory) {}
}
