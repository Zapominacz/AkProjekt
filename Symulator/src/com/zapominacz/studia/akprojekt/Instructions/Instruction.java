package com.zapominacz.studia.akprojekt.instructions;

import com.zapominacz.studia.akprojekt.core.Memory;
import com.zapominacz.studia.akprojekt.model.Bit;
import com.zapominacz.studia.akprojekt.model.Register;

/**
 * Created by Sebastian on 2015-04-12.
 * Instruction abstract class, made for execution of instructions
 */
public abstract class Instruction {

    public final static int INSTRUCTION_LENGTH = 32;

    protected int outputRegister;
    protected int firstArgRegister;
    protected int secondArgRegister;

    public abstract void execute();
    public abstract void parseArguments(Bit[] argument);
    public abstract void loadArguments(Register[] registers);
    public abstract void saveResult(Register[] registers);
    public abstract void saveResult(Memory memory);
    public abstract void loadArguments(Memory memory);
}
