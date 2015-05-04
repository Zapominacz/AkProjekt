package com.zapominacz.studia.akprojekt.instructions.transport;

import com.zapominacz.studia.akprojekt.instructions.Instruction;
import com.zapominacz.studia.akprojekt.util.Conversions;

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
            String value = arguments.get(0);
            String prefix = value.substring(0, 3);
            if(prefix.compareTo("#0b") == 0)
                registers.setRegisterValue((arguments.get(1)), Conversions.convertStringToBooleanArray(value.substring(3), 2, 32));
            else if(prefix.compareTo("#0q") == 0)
                registers.setRegisterValue((arguments.get(1)), Conversions.convertStringToBooleanArray(value.substring(3), 8, 32));
            else if(prefix.compareTo("#0d") == 0)
                registers.setRegisterValue((arguments.get(1)), Conversions.convertStringToBooleanArray(value.substring(3), 10, 32));
            else if(prefix.compareTo("#0x") == 0)
                registers.setRegisterValue((arguments.get(1)), Conversions.convertStringToBooleanArray(value.substring(3), 16, 32));
            else
                return false;
            /*Long value = Long.parseLong(hexValue, 16);
            for(int i=31; i>=0; i--){
                if(value % 2 == 0)
                    dest[i] = Boolean.FALSE;
                else
                    dest[i] = Boolean.TRUE;
                value /= 2;
            }*/
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
