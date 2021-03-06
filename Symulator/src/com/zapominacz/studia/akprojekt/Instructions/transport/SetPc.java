package com.zapominacz.studia.akprojekt.instructions.transport;

import com.zapominacz.studia.akprojekt.core.Memory;
import com.zapominacz.studia.akprojekt.core.Processor;
import com.zapominacz.studia.akprojekt.enums.RegisterSection;
import com.zapominacz.studia.akprojekt.instructions.Instruction;
import com.zapominacz.studia.akprojekt.model.Bit;
import com.zapominacz.studia.akprojekt.model.Register;
import com.zapominacz.studia.akprojekt.utils.Bits;
import com.zapominacz.studia.akprojekt.utils.CondCheck;

//TODO restrykcje na rejestry
public class SetPc extends Instruction {

    private Bit[] cond;
    private Bit[] source;
    private Bit[] flags;

    @Override
    public void execute() {

    }

    @Override
    public void parseArguments(Bit[] argument) {
        firstArgRegister = Bits.parseInteger(Bits.getBits(argument,
                RegisterSection.FIRST_REG_START.getIndex(), RegisterSection.FIRST_REG_END.getIndex()));
        cond = Bits.getBits(argument,
                RegisterSection.COND_CODE_START.getIndex(), RegisterSection.COND_CODE_END.getIndex());
    }

    @Override
    public void loadArguments(Register[] registers) {
        source = Bits.copy(registers[firstArgRegister].getBits());
        flags = Bits.getBits(registers[Processor.FLAGS].getBits(), Processor.FLAG_ZERO, Processor.FLAG_CARRY + 1);
    }

    @Override
    public void saveResult(Register[] registers) {
        if(CondCheck.check(flags, cond)) {
            registers[Processor.PC].setRegisterValue(Bits.copy(source));
        }
    }

    @Override
    public void saveResult(Memory memory) {

    }

    @Override
    public void loadArguments(Memory memory) {

    }
}
