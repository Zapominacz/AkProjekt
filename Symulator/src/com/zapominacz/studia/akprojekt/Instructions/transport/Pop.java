package com.zapominacz.studia.akprojekt.instructions.transport;

import com.zapominacz.studia.akprojekt.core.Memory;
import com.zapominacz.studia.akprojekt.core.Processor;
import com.zapominacz.studia.akprojekt.enums.RegisterSection;
import com.zapominacz.studia.akprojekt.instructions.Instruction;
import com.zapominacz.studia.akprojekt.model.Bit;
import com.zapominacz.studia.akprojekt.model.Register;
import com.zapominacz.studia.akprojekt.utils.Bits;

public class Pop extends Instruction {

    Bit[] address;
    Bit[] result;

    @Override
    public void execute() {

    }

    @Override
    public void parseArguments(Bit[] argument) {
        outputRegister = Bits.parseInteger(Bits.getBits(argument,
                RegisterSection.OUT_REG_START.getIndex(), RegisterSection.OUT_REG_END.getIndex()));
    }

    @Override
    public void loadArguments(Register[] registers) {
        address = registers[Processor.STACK_POINTER].getBits();
    }

    @Override
    public void saveResult(Register[] registers) {
        registers[Processor.STACK_POINTER].setRegisterValue(Bits.add(address, Bits.parseBits(1, Register.WORD_LEN)));
        registers[outputRegister].setRegisterValue(result);
    }

    @Override
    public void saveResult(Memory memory) {

    }

    @Override
    public void loadArguments(Memory memory) {
        result = memory.loadFromMemory(address);
    }
}
