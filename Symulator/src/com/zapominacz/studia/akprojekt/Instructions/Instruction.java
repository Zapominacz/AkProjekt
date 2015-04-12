package com.zapominacz.studia.akprojekt.Instructions;

import com.zapominacz.studia.akprojekt.Registers.Registers;

import java.util.List;

/**
 * Created by Sebastian on 2015-04-12.
 * Instruction abstract class, made for execution of instructions
 */
public abstract class Instruction {
    protected List<String> arguments;
    protected static Registers registers = Registers.getInstance();

    public abstract boolean execute();
    public abstract boolean validArguments();
    public abstract void addArgument(String argument);
}
