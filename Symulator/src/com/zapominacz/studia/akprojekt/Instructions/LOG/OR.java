package com.zapominacz.studia.akprojekt.Instructions.LOG;

import com.zapominacz.studia.akprojekt.Instructions.Instruction;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2015-04-13.
 * OR logical instruction
 */
public class OR extends Instruction {
    public OR(){
        arguments = new ArrayList<>();
    }

    public boolean execute(){
        if(validArguments()){
            Boolean[] src1 = registers.getRegisterValue(arguments.get(0));
            Boolean[] src2 = registers.getRegisterValue(arguments.get(1));
            Boolean[] dest = registers.getRegisterValue(arguments.get(2));
            for(int i=0; i<64; i++)
                dest[i] = src1[i] | src2[i];
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
