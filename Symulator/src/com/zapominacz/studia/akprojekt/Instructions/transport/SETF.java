package com.zapominacz.studia.akprojekt.instructions.transport;

import com.zapominacz.studia.akprojekt.core.Memory;
import com.zapominacz.studia.akprojekt.core.Processor;
import com.zapominacz.studia.akprojekt.enums.RegisterSection;
import com.zapominacz.studia.akprojekt.instructions.Instruction;
import com.zapominacz.studia.akprojekt.model.Bit;
import com.zapominacz.studia.akprojekt.model.Register;
import com.zapominacz.studia.akprojekt.utils.Bits;

public class SetF extends Instruction {

    private Bit[] source;

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
        source = registers[firstArgRegister].getBits();
    }

    @Override
    public void saveResult(Register[] registers) {
        registers[Processor.FLAGS].setRegisterValue(source);
    }

    @Override
    public void saveResult(Memory memory) {

    }

    @Override
    public void loadArguments(Memory memory) {

    }
}
