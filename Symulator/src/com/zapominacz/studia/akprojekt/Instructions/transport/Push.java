package com.zapominacz.studia.akprojekt.instructions.transport;

import com.zapominacz.studia.akprojekt.core.Memory;
import com.zapominacz.studia.akprojekt.core.Processor;
import com.zapominacz.studia.akprojekt.enums.RegisterSection;
import com.zapominacz.studia.akprojekt.instructions.Instruction;
import com.zapominacz.studia.akprojekt.model.Bit;
import com.zapominacz.studia.akprojekt.model.Register;
import com.zapominacz.studia.akprojekt.utils.Bits;

public class Push extends Instruction {

    int address;
    Bit[] data;

    @Override
    public void execute() {

    }

    @Override
    public void parseArguments(Bit[] argument) {
        firstArgRegister = Bits.parseInteger(Bits.getBits(argument,
                RegisterSection.FIRST_REG_START.getIndex(), RegisterSection.FIRST_REG_END.getIndex()));
    }

    @Override
    public void loadArguments(Register[] registers) {
        address = Bits.parseInteger(registers[Processor.STACK_POINTER].getBits()) - 4;
        data = registers[outputRegister].getBits();
    }

    @Override
    public void saveResult(Register[] registers) {
        registers[Processor.STACK_POINTER].setRegisterValue(Bits.parseBits(address, Register.WORD_LEN));
    }

    @Override
    public void saveResult(Memory memory) {
        memory.writeToMemory(address, Bits.copy(data));
    }

    @Override
    public void loadArguments(Memory memory) {

    }
}
