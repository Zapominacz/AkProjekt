package com.zapominacz.studia.akprojekt.instructions;

import com.zapominacz.studia.akprojekt.registers.Registers;
import com.zapominacz.studia.akprojekt.util.Bit;

import java.util.List;

/**
 * Created by Sebastian on 2015-04-12.
 * Instruction abstract class, made for execution of instructions
 */
public abstract class Instruction {

    public final static int INSTRUCTION_LENGTH = 32;

    protected int outputRegister;
    protected int firstArgRegister;
    protected int secondArgRegister;
    protected static Registers registers = Registers.getInstance();

    public abstract void execute();
    public abstract void parseArguments(Bit[] argument);
}
