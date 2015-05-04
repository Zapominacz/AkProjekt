package com.zapominacz.studia.akprojekt.instructions.transport;

import com.zapominacz.studia.akprojekt.instructions.Instruction;

import java.util.ArrayList;

/**
 * Created by Sebastian on 2015-04-13.
 * XOR logical instruction
 */
public class COPY_imm extends Instruction {
    public COPY_imm(){
        arguments = new ArrayList<>();
    }

    public boolean execute(){
        if(validArguments()){
            String hexValue = arguments.get(0);
            Boolean[] dest = registers.getRegisterValue(arguments.get(1));
            Long value = Long.parseLong(hexValue, 16);
            for(int i=31; i>=0; i--){
                if(value % 2 == 0)
                    dest[i] = Boolean.FALSE;
                else
                    dest[i] = Boolean.TRUE;
                value /= 2;
            }
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
