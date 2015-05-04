package com.zapominacz.studia.akprojekt.registers;

import com.zapominacz.studia.akprojekt.model.Register;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sebastian on 2015-04-12.
 * registers Singleton
 */
public class Registers {

    public final static int REGISTERS = 32;

    private Map<String, Boolean[]> registers;
    private static Registers instance = null;

    private Registers(){
        registers = new HashMap<>();
        for(int i=0; i < REGISTERS; i++){
            registers.put("R" + Integer.toHexString(i).toUpperCase(), new Boolean[64]);
        }
        reset();
    }

    public static Registers getInstance(){
        if(instance == null){
            instance = new Registers();
            return instance;
        }
        else
            return instance;
    }

    public Boolean[] getRegisterValue(String registerName){
        return registers.get(registerName);
    }

    public void setRegisterValue(String registerName, Boolean[] registerValue){
        registers.put(registerName, registerValue);
    }

    public boolean validRegisterName(String registerName){
        return getRegisterValue(registerName) != null;
    }

    public void reset(){
        for(int i =0; i<REGISTERS; i++){
            Boolean[] src = getRegisterValue("R" + Integer.toHexString(i).toUpperCase());
            for(int j=0; j < Register.WORD_LEN;j++)
                src[j] = Boolean.FALSE;
        }
    }
}
