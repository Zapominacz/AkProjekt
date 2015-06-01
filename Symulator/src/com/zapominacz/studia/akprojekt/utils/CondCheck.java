package com.zapominacz.studia.akprojekt.utils;

import com.zapominacz.studia.akprojekt.model.Bit;

public class CondCheck {

    public static boolean isAlways(Bit[] cond) {
        return cond[0].and(cond[1].and(cond[2].and(cond[3]))).getBooleanValue();
    }

    public static boolean isNever(Bit[] cond) {
        return cond[0].neg().and(cond[1].and(cond[2].and(cond[3]))).getBooleanValue();
    }

    public static boolean isZero(Bit[] cond) {
        return cond[0].and(cond[1].neg().and(cond[2].neg().and(cond[3].neg()))).getBooleanValue();
    }

    public static boolean isNotZero(Bit[] cond) {
        return cond[0].and(cond[1].and(cond[2].neg().and(cond[3].neg()))).getBooleanValue();
    }

    public static boolean isOverflow(Bit[] cond) {
        return cond[0].neg().and(cond[1].and(cond[2].neg().and(cond[3].neg()))).getBooleanValue();
    }

    public static boolean isNotOverflow(Bit[] cond) {
        return cond[0].and(cond[1].and(cond[2].neg().and(cond[3].neg()))).getBooleanValue();
    }

    public static boolean isNegative(Bit[] cond) {
        return cond[0].neg().and(cond[1].neg().and(cond[2].and(cond[3].neg()))).getBooleanValue();
    }

    public static boolean isNotNegative(Bit[] cond) {
        return cond[0].and(cond[1].neg().and(cond[2].and(cond[3].neg()))).getBooleanValue();
    }

    public static boolean isPositive(Bit[] cond) {
        return cond[0].neg().and(cond[1].and(cond[2].and(cond[3].neg()))).getBooleanValue();
    }

    public static boolean isNotPositive(Bit[] cond) {
        return cond[0].and(cond[1].and(cond[2].and(cond[3].neg()))).getBooleanValue();
    }

    public static boolean isCarry(Bit[] cond) {
        return cond[0].neg().and(cond[1].neg().and(cond[2].neg().and(cond[3]))).getBooleanValue();
    }

    public static boolean isNotCarry(Bit[] cond) {
        return cond[0].and(cond[1].neg().and(cond[2].neg().and(cond[3]))).getBooleanValue();
    }

    public static boolean isHigher(Bit[] cond) {
        return cond[0].neg().and(cond[1].and(cond[2].neg().and(cond[3]))).getBooleanValue();
    }

    public static boolean isNotHigher(Bit[] cond) {
        return cond[0].and(cond[1].and(cond[2].neg().and(cond[3]))).getBooleanValue();
    }

    public static boolean isLower(Bit[] cond) {
        return cond[0].neg().and(cond[1].neg().and(cond[2].and(cond[3]))).getBooleanValue();
    }

    public static boolean isNotLower(Bit[] cond) {
        return cond[0].and(cond[1].neg().and(cond[2].and(cond[3]))).getBooleanValue();
    }



    public static boolean check(Bit[] flags, Bit[] cond) {
        if(isAlways(cond)) {
            return true;
        } else if(isNever(cond)) {
            return false;
        } else if(isCarry(cond)) {
            return flags[3] == Bit.HIGH;
        } else if(isNotCarry(cond)) {
            return flags[3] != Bit.HIGH;
        } else if(isZero(cond)) {
            return flags[0] == Bit.HIGH;
        } else if(isNotZero(cond)) {
            return flags[0] != Bit.HIGH;
        } else if(isOverflow(cond)) {
            return flags[1] == Bit.HIGH;
        } else if(isNotOverflow(cond)) {
            return flags[1] != Bit.HIGH;
        } else if(isNegative(cond)) {
            return flags[2] == Bit.HIGH && flags[0] != Bit.HIGH;
        } else if(isNotNegative(cond)) {
            return flags[2] != Bit.HIGH || flags[0] == Bit.HIGH;
        } else if(isLower(cond)) {
            return flags[2] == Bit.HIGH && flags[0] != Bit.HIGH && flags[1] != Bit.HIGH;
        } else if(isNotLower(cond)) {
            return flags[2] != Bit.HIGH || flags[0] == Bit.HIGH && flags[1] != Bit.HIGH;
        } else if(isHigher(cond)) {
            return flags[2] != Bit.HIGH && flags[0] != Bit.HIGH && flags[1] != Bit.HIGH;
        } else if(isNotHigher(cond)) {
            return flags[2] == Bit.HIGH || flags[0] == Bit.HIGH && flags[1] != Bit.HIGH;
        } else if(isPositive(cond)) {
            return flags[2] != Bit.HIGH && flags[0] != Bit.HIGH;
        } else if(isNotPositive(cond)) {
            return flags[2] == Bit.HIGH || flags[0] == Bit.HIGH;
        }
        return true;
    }
}
