package com.zapominacz.studia.akprojekt.Instructions.manipulation;

import com.zapominacz.studia.akprojekt.instructions.Instruction;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2015-04-13.
 * RL manipulation instruction
 */
public class RR extends Instruction {
    public RR(){
        arguments = new ArrayList<>();
    }

    public boolean execute(){
        if(validArguments()){
            Boolean[] src = registers.getRegisterValue(arguments.get(0));
            Boolean[] dest = registers.getRegisterValue(arguments.get(1));
            dest[0] = src[31];
            for(int i=1; i<32; i++)
                dest[i] = src[i-1];
            return true;
        }
        else
            return false;
    }

    public boolean validArguments(){
        return true;
    }

    public void addArgument(String argument){
        arguments.add(argument);
    }
}
