package com.zapominacz.studia.akprojekt.instructions.transport;

import com.zapominacz.studia.akprojekt.core.Memory;
import com.zapominacz.studia.akprojekt.enums.RegisterSection;
import com.zapominacz.studia.akprojekt.instructions.Instruction;
import com.zapominacz.studia.akprojekt.model.Bit;
import com.zapominacz.studia.akprojekt.model.Register;
import com.zapominacz.studia.akprojekt.utils.Bits;

public class LoadI extends Instruction {

    Bit[] result;
    Bit[] imm;

    @Override
    public void execute() {
        System.arraycopy(imm, 0, result, 0, 8);
    }

    @Override
    public void parseArguments(Bit[] argument) {
        outputRegister = Bits.parseInteger(Bits.getBits(argument,
                RegisterSection.OUT_REG_START.getIndex(), RegisterSection.OUT_REG_END.getIndex()));
        imm = Bits.getBits(argument, RegisterSection.IMMEDIATE_START.getIndex(), RegisterSection.IMMEDIATE_END.getIndex());
    }

    @Override
    public void loadArguments(Register[] registers) {
        result = registers[outputRegister].getBits();
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

    }
}
