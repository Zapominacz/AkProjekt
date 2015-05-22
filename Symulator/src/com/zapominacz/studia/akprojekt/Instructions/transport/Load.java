package com.zapominacz.studia.akprojekt.instructions.transport;

import com.zapominacz.studia.akprojekt.core.Memory;
import com.zapominacz.studia.akprojekt.enums.RegisterSection;
import com.zapominacz.studia.akprojekt.instructions.Instruction;
import com.zapominacz.studia.akprojekt.model.Bit;
import com.zapominacz.studia.akprojekt.model.Register;
import com.zapominacz.studia.akprojekt.utils.Bits;

public class Load extends Instruction {

    Bit[] address;
    Bit[] result;

    @Override
    public void execute() {

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
        address = Bits.add(registers[firstArgRegister].getBits(),  registers[secondArgRegister].getBits());
    }

    @Override
    public void saveResult(Register[] registers) {
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
