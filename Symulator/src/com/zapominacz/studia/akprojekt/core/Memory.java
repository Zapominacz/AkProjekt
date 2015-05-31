package com.zapominacz.studia.akprojekt.core;

import com.sun.istack.internal.NotNull;
import com.zapominacz.studia.akprojekt.model.Bit;
import com.zapominacz.studia.akprojekt.utils.Bits;

import java.util.HashMap;
import java.util.Map;

public class Memory {

    public final static int STACK_BEGINNING = 3000;
    public final static int CODE_BEGINNING = 2000;
    public final static int VAR_BEGINNING = 1000;

    public final static int MEMORY_LEN = 32;
    public final static int CLUSTER_LEN = 28;

    Map<Integer, Bit[]> memoryMap;
    private static Memory instance;

    private Memory() {
        memoryMap = new HashMap<>();
    }

    @NotNull
    public static Memory getInstance() {
        if(instance == null) {
            instance = new Memory();
        }
        return instance;
    }

    @NotNull
    public Bit[] loadFromMemory(@NotNull Bit[] address) {
        int realAddress = Bits.parseInteger(address);
        return loadFromMemory(realAddress);
    }

    public void writeToMemory(@NotNull Bit[] address, @NotNull Bit[] value) {
        int realAddress = Bits.parseInteger(address);
        writeToMemory(realAddress, value);
    }

    public void writeToMemory(@NotNull int realAddress, @NotNull Bit[] value) {
        int cluster = realAddress / 4;
        int remainder = realAddress % 4;
        if(remainder != 0) {
            Bit[] c1 = memoryMap.get(cluster);
            Bit[] c2 = memoryMap.get(cluster + 4);
            if(c1 == null) {
                c1 =  Bits.createBits(MEMORY_LEN);
            }
            if(c2 == null) {
                c2 =  Bits.createBits(MEMORY_LEN);
            }
            remainder *= 8;
            System.arraycopy(value, 0, c1, remainder, MEMORY_LEN  - remainder);
            System.arraycopy(value, MEMORY_LEN  - remainder, c2, 0, remainder);
            memoryMap.put(cluster, c1);
            memoryMap.put(cluster + 4, c2);
        } else {
            memoryMap.put(cluster, value);
        }
    }

    @NotNull
    public Bit[] loadFromMemory(int realAddress) {
        int cluster = realAddress / 4;
        int remainder = realAddress % 4;
        Bit[] result;
        if(remainder == 0) {
            result = memoryMap.get(cluster);
            if(result == null) {
                result = Bits.createBits(MEMORY_LEN);
            }
        } else {
            result = Bits.createBits(MEMORY_LEN);
            Bit[] c1 = memoryMap.get(cluster);
            Bit[] c2 = memoryMap.get(cluster + 4);
            if(c1 == null) {
                c1 = Bits.createBits(MEMORY_LEN);
            }
            if(c2 == null) {
                c2 = Bits.createBits(MEMORY_LEN);
            }
            remainder *= 8;
            System.arraycopy(c1, remainder, result, 0, MEMORY_LEN  - remainder);
            System.arraycopy(c2, 0, result, remainder, remainder);
        }
        return result;
    }
}
