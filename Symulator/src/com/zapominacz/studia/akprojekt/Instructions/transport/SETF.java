package com.zapominacz.studia.akprojekt.instructions.transport;

import com.zapominacz.studia.akprojekt.instructions.Instruction;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2015-04-13.
 * GET FLAGS instruction
 */
public class SETF extends Instruction {
    public SETF(){
        arguments = new ArrayList<>();
    }

    public boolean execute(){
        if(validArguments()){
            Boolean[] src = registers.getRegisterValue(arguments.get(0));
            Boolean[] dest = registers.getRegisterValue("RFLAGS");
            for(int i=0; i<32; i++)
                dest[i] = src[i];
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
