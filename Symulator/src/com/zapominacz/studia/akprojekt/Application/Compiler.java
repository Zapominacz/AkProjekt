package com.zapominacz.studia.akprojekt.application;

import com.zapominacz.studia.akprojekt.instructions.aritmetic.ADD;
import com.zapominacz.studia.akprojekt.instructions.aritmetic.SUB;
import com.zapominacz.studia.akprojekt.instructions.Instruction;
import com.zapominacz.studia.akprojekt.instructions.logical.AND;
import com.zapominacz.studia.akprojekt.instructions.logical.OR;
import com.zapominacz.studia.akprojekt.instructions.logical.XOR;
import com.zapominacz.studia.akprojekt.instructions.transport.CNST;
import com.zapominacz.studia.akprojekt.registers.Registers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian on 2015-04-19.
 *
 * CLASS ONLY FOR TESTING PURPOSES!
 */
public class Compiler {

    private static Compiler instance = null;
    private List<Instruction> instructions;
    private Registers registers;


    private Compiler(){
        instructions = new ArrayList<>();
        registers = Registers.getInstance();
    }

    public static Compiler getInstance(){
        if(instance == null){
            instance = new Compiler();
            return instance;
        }
        else
            return instance;
    }

    public String getRegisterHexValue(String registerName){
        String hexValue = "0x";
        Boolean[] value = registers.getRegisterValue(registerName);
        for(int i=0; i<32; i+=4){
            Integer binNumber = 0;
            for(int j=0; j<4; j++){
                if(value[i+j] == Boolean.TRUE)
                    binNumber += (int)Math.pow(2.0, 3.0 - j);
            }
            hexValue += Integer.toHexString(binNumber).toUpperCase();
        }
        return hexValue;
    }

    public Boolean addInstruction(String instruction){
        String mnemo[] = instruction.split(" ");
        if(mnemo[0].equals("ADD"))
            instructions.add(new ADD());
        else if(mnemo[0].equals("SUB"))
            instructions.add(new SUB());
        else if(mnemo[0].equals("AND"))
            instructions.add(new AND());
        else if(mnemo[0].equals("OR"))
            instructions.add(new OR());
        else if(mnemo[0].equals("XOR"))
            instructions.add(new XOR());
        else if(mnemo[0].equals("CNST"))
            instructions.add(new CNST());

        int i = 0;
        for(String temp: mnemo){
            if(i==0){
                i++;
                continue;
            }
            else {
                instructions.get(instructions.size() - 1).addArgument(temp);
            }
        }
        return true;
    }

    public void execute(){
        for(Instruction i: instructions)
            i.execute();
    }

}
