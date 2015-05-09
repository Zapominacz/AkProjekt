package com.zapominacz.studia.akprojekt.memory;

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

    Map<Bit[], Bit[]> memoryMap;
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
    public Bit[] loadFromMemory(@NotNull Bit[] address) {
        Bit[] cluster = Bits.getBits(address, 0, CLUSTER_LEN);
        Bit[] rem = Bits.getBits(address, CLUSTER_LEN, MEMORY_LEN);
        Bit[] result;
        int remainder = Bits.parseInteger(rem);
        if(remainder == 0) {
            result = memoryMap.get(cluster);
            if(result == null) {
                result = Bits.createBits(MEMORY_LEN);
            }
        } else {
            result = Bits.createBits(MEMORY_LEN);
            Bit[] increment = Bits.createBits(CLUSTER_LEN);
            increment[0] = Bit.LOW;
            Bit[] c1 = memoryMap.get(cluster);
            Bit[] c2 = memoryMap.get(Bits.add(cluster, increment));
            if(c1 == null) {
                c1 = Bits.createBits(MEMORY_LEN);
            }
            if(c2 == null) {
                c2 = Bits.createBits(MEMORY_LEN);
            }
            System.arraycopy(c1, remainder, result, 0, CLUSTER_LEN  - remainder);
            System.arraycopy(c2, 0, result, remainder, remainder);
        }
        return result;
    }

    public void writeToMemory(@NotNull Bit[] address, @NotNull Bit[] value) {
        Bit[] cluster = Bits.getBits(address, 0, CLUSTER_LEN);
        Bit[] rem = Bits.getBits(address, CLUSTER_LEN, MEMORY_LEN);
        int remainder = Bits.parseInteger(rem);
        if(remainder != 0) {
            Bit[] increment = Bits.createBits(CLUSTER_LEN);
            increment[0] = Bit.LOW;
            Bit[] nextCluster = Bits.add(cluster, increment);
            Bit[] c1 = memoryMap.get(cluster);
            Bit[] c2 = memoryMap.get(nextCluster);
            if(c1 == null) {
                c1 =  Bits.createBits(MEMORY_LEN);
            }
            if(c2 == null) {
                c2 =  Bits.createBits(MEMORY_LEN);
            }
            System.arraycopy(value, 0, c1, remainder, CLUSTER_LEN  - remainder);
            System.arraycopy(value, CLUSTER_LEN  - remainder, c2, 0, remainder);
            memoryMap.put(cluster, c1);
            memoryMap.put(nextCluster, c2);
        } else {
            memoryMap.put(cluster, value);
        }
    }

}
