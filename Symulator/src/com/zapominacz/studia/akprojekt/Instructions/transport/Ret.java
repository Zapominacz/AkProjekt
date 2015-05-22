package com.zapominacz.studia.akprojekt.instructions.transport;

import com.zapominacz.studia.akprojekt.core.Memory;
import com.zapominacz.studia.akprojekt.core.Processor;
import com.zapominacz.studia.akprojekt.instructions.Instruction;
import com.zapominacz.studia.akprojekt.model.Bit;
import com.zapominacz.studia.akprojekt.model.Register;
import com.zapominacz.studia.akprojekt.utils.Bits;

public class Ret extends Instruction {

    private int sp;
    Bit[] result;

    @Override
    public void execute() {

    }

    @Override
    public void parseArguments(Bit[] argument) {

    }

    @Override
    public void loadArguments(Register[] registers) {
        sp = Bits.parseInteger(registers[Processor.STACK_POINTER].getBits());
    }

    @Override
    public void saveResult(Register[] registers) {
        registers[Processor.PC].setRegisterValue(result);
        registers[Processor.STACK_POINTER].setRegisterValue(Bits.parseBits(sp + 4, Register.WORD_LEN));
    }

    @Override
    public void saveResult(Memory memory) {

    }

    @Override
    public void loadArguments(Memory memory) {
        result = memory.loadFromMemory(sp);
    }
}