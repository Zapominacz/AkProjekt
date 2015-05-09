package com.zapominacz.studia.akprojekt.util;

import java.util.Arrays;

public class Bits {

    public static Bit[] getBits(Bit[] bits, int fromInclusive, int toExclusive) {
        return Arrays.copyOfRange(bits, fromInclusive, toExclusive);
    }

    public static int parseInteger(Bit[] bits) {
        int result = 0;
        for(Bit bit : bits) {
            result *= 2;
            if(bit.getBooleanValue()) {
                result++;
            }
        }
        return result;
    }

    public static Bit[] createBits(int len) {
        Bit[] result = new Bit[len];
        for(int i = 0; i < len; i++) {
            result[i] = Bit.LOW;
        }
        return result;
    }

    public static Bit[] add(Bit[] bits1, Bit[] bits2) {
        int len = bits1.length;
        Bit carry = Bit.LOW;
        Bit[] result = createBits(len);
        for(int i = 0; i < len; i++) {
            result[i] = bits1[i].xor(bits2[i]).xor(carry);
            carry = (bits1[i].and(bits2[i])).or((bits1[i].xor(bits2[i])).and(carry));
        }
        return result;
    }
}
