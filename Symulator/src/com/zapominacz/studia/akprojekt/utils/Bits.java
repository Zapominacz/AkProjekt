package com.zapominacz.studia.akprojekt.utils;

import com.zapominacz.studia.akprojekt.model.Bit;

import java.util.Arrays;

public class Bits {

    public static Bit[] getBits(Bit[] bits, int fromInclusive, int toExclusive) {
        return Arrays.copyOfRange(bits, fromInclusive, toExclusive);
    }

    public static int parseInteger(Bit[] bits) {
        int result = 0;
        int exp = 1;
        for(Bit bit : bits) {
            if(bit.getBooleanValue()) {
                result+= exp;
            }
            exp *= 2;
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

    public static Bit[] parseBits(int number, int len) {
        Bit[] result = createBits(len);
        for(int i = 0; i < len; i++) {
            boolean high = number % 2 == 1;
            result[i] = Bit.getByValue(high);
            number /= 2;
        }
        return  result;
    }

    public static Bit[] parseBits(String line, int len) {
        Bit[] result = createBits(len);
        for(int i = 0; i < len; i++) {
            result[i] = Bit.getByValue(line.charAt(i) == '1');
        }
        return  result;
    }

    public static boolean equals(Bit[] bits1, Bit[] bits2) {
        boolean result = true;
        for(int i = 0; i < bits1.length; i++) {
            if(bits1[i] != bits2[i]) {
                result = false;
            }
        }
        return result;
    }

    public static Bit[] sub(Bit[] bits1, Bit[] bits2) {
        int len = bits1.length;
        Bit carry = Bit.LOW;
        Bit[] result = createBits(len);
        for(int i = 0; i < len; i++) {
            result[i] = bits1[i].xor(bits2[i]).xor(carry);
            carry = (bits1[i].and(bits2[i].neg())).or(((bits1[i].xor(bits2[i])).neg()).and(carry));
        }
        return result;
    }

    public static Bit[] reverse(Bit[] bits) {
        Bit[] result = Bits.createBits(bits.length);
        for(int i = 0; i < bits.length; i++) {
            result[i] = bits[bits.length - i - 1];
        }
        return result;
    }
}
