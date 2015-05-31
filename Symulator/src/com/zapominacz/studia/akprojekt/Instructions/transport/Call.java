package com.zapominacz.studia.akprojekt.instructions.transport;

import com.zapominacz.studia.akprojekt.core.Memory;
import com.zapominacz.studia.akprojekt.core.Processor;
import com.zapominacz.studia.akprojekt.enums.RegisterSection;
import com.zapominacz.studia.akprojekt.instructions.Instruction;
import com.zapominacz.studia.akprojekt.model.Bit;
import com.zapominacz.studia.akprojekt.model.Register;
import com.zapominacz.studia.akprojekt.utils.Bits;

public class Call extends Instruction {

    int src;
    Bit[] returnAddress;
    Bit[] callAddress;
    int sp;

    @Override
    public void execute() {

    }

    @Override
    public void parseArguments(Bit[] argument) {
        src = Bits.parseInteger(Bits.getBits(argument, RegisterSection.FIRST_REG_START.getIndex(), RegisterSection.FIRST_REG_END.getIndex()));
    }

    @Override
    public void loadArguments(Register[] registers) {
        int nextPC = Bits.parseInteger(registers[Processor.PC].getBits());
        returnAddress = Bits.parseBits(nextPC, Register.WORD_LEN);
        sp = Bits.parseInteger(registers[Processor.STACK_POINTER].getBits()) - 4;
        callAddress = registers[src].getBits();
    }

    @Override
    public void saveResult(Register[] registers) {
        registers[Processor.PC].setRegisterValue(Bits.copy(callAddress));
        registers[Processor.STACK_POINTER].setRegisterValue(Bits.parseBits(sp, Register.WORD_LEN));
    }

    @Override
    public void saveResult(Memory memory) {
        memory.writeToMemory(sp, returnAddress);
    }

    @Override
    public void loadArguments(Memory memory) {

    }
}