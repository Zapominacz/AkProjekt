package com.zapominacz.studia.akprojekt.Registers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sebastian on 2015-04-12.
 * Registers Singleton
 */
public class Registers {
    private Map<String, Boolean[]> registers;
    private static Registers instance = null;

    private Registers(){
        registers = new HashMap<>();
        for(int i=0; i < 16; i++){
            registers.put("R" + Integer.toHexString(i), new Boolean[64]);
        }
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
}
