package com.zapominacz.studia.akprojekt.utils;

public class Conversions {

    public static String toHexString(int val) {
        return Integer.toUnsignedString(val, 16).toUpperCase();
    }

    public static String toDecString(int val) {
        return Integer.toUnsignedString(val, 10).toUpperCase();
    }

    public static String addPrefix(String prefix, String val) {
        return new StringBuilder(prefix).append(val).toString();
    }

    public static String removePrefix(String prefix, String val) {
        return new StringBuilder(val).delete(0, prefix.length()).toString();
    }

    public static int toNumber(String text, int i) {
        return Integer.parseUnsignedInt(text, i);
    }
}
