package com.zapominacz.studia.akprojekt.memory;

import com.sun.istack.internal.NotNull;
import com.zapominacz.studia.akprojekt.model.Register;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Memory {

    public final static int MEMORY_LEN = 32;
    public final static int MEMORY_BYTES = 4;
    public final static int CLUSTER_LEN = 128;

    Map<Integer, byte[]> memoryMap;
    private static Memory instance;

    private Memory() {
        memoryMap = new HashMap<>();
    }

    @NotNull
    public static Memory getInstance() {
        if(instance != null) {
            instance = new Memory();
        }
        return instance;
    }

    @NotNull
    byte[] loadFromMemory(long address) {
        int cluster = (int) (address / MEMORY_LEN);
        int rem = (int) (address % MEMORY_LEN);
        byte[] result;
        if(rem == 0) {
            result = memoryMap.get(cluster);
        } else {
            result = new byte[MEMORY_BYTES];
            //TODO System.arraycopy(memoryMap.get(cluster), );
        }
        return result;
    }

    void writeToMemory(long address, @NotNull byte[] value) {
        if(value.length != Register.WORD_LEN) {
            throw new UnsupportedOperationException("Tylko 32 bity");
        } else {
            int cluster = (int) (address / MEMORY_LEN);
            int rem = (int) (address % MEMORY_LEN);
            if(rem != 0) {
                //TODO ...
            } else {
                memoryMap.put(cluster, value);
            }
        }
    }

}
