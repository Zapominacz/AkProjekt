package com.zapominacz.studia.akprojekt.memory;

import com.sun.istack.internal.NotNull;
import com.zapominacz.studia.akprojekt.model.Register;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Memory {

    //public final static int MEMORY_LEN = 32;
    public final static int MEMORY_BYTES = 4;
    public final static int CLUSTER_LEN = 4;

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
        int cluster = (int) (address / MEMORY_BYTES);
        int rem = (int) (address % MEMORY_BYTES);
        byte[] result;
        if(rem == 0) {
            result = memoryMap.get(cluster);
        } else {
            result = new byte[MEMORY_BYTES];
            byte[] c1 = memoryMap.get(cluster);
            byte[] c2 = memoryMap.get(cluster + 1);
            if(c1 == null) {
                memoryMap.put(cluster, new byte[]{0, 0, 0, 0});
            }
            if(c2 == null) {
                memoryMap.put(cluster + 1, new byte[] {0,0,0,0});
            }
            System.arraycopy(c1, rem, result, 0, CLUSTER_LEN  - rem);
            System.arraycopy(c2, 0, result, CLUSTER_LEN  - rem, rem);
        }
        return result;
    }

    void writeToMemory(long address, @NotNull byte[] value) {
        if(value.length != Register.WORD_LEN) {
            throw new UnsupportedOperationException("Tylko 32 bity");
        } else {
            int cluster = (int) (address / MEMORY_BYTES);
            int rem = (int) (address % MEMORY_BYTES);
            if(rem != 0) {
                byte[] c1 = memoryMap.get(cluster);
                byte[] c2 = memoryMap.get(cluster + 1);
                if(c1 == null) {
                    c1 =  new byte[]{0, 0, 0, 0};
                }
                if(c2 == null) {
                    c2 =  new byte[]{0, 0, 0, 0};
                }
                System.arraycopy(value, 0, c1, rem, CLUSTER_LEN  - rem);
                System.arraycopy(value, CLUSTER_LEN  - rem, c2, 0, rem);
            } else {
                memoryMap.put(cluster, value);
            }
        }
    }

}
