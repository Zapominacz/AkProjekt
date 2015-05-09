//package com.zapominacz.studia.akprojekt.instructions.aritmetic;
//
//import com.zapominacz.studia.akprojekt.instructions.Instruction;
//
//import java.util.ArrayList;
//
//public class Sub extends Instruction {
//
//    public Sub(){
//        arguments = new ArrayList<>();
//    }
//
//    public boolean execute(){
//        if(validArguments()){
//            Boolean[] src1 = registers.getRegisterValue(arguments.get(0));
//            Boolean[] src2 = registers.getRegisterValue(arguments.get(1));
//            Boolean[] dest = registers.getRegisterValue(arguments.get(2));
//            Boolean[] flags = registers.getRegisterValue("RFLAGS");
//            Boolean CARRY = flags[0], SIGN = Boolean.FALSE, OVERFLOW = Boolean.FALSE, ZERO = Boolean.TRUE;
//            for(int i=31; i>=0; i--) {
//                dest[i] = src1[i] ^ src2[i] ^ CARRY;
//                CARRY = (src1[i] & (!src2[i])) | ((!(src1[i] ^ src2[i])) & CARRY);
//                ZERO = ZERO & (!dest[i]);
//            }
//            if (dest[31] == Boolean.TRUE)
//                SIGN = Boolean.TRUE;
//            if (!CARRY.equals(dest[31]))
//                OVERFLOW = Boolean.TRUE;
//            flags[0] = CARRY;
//            flags[1] = SIGN;
//            flags[2] = OVERFLOW;
//            flags[3] = ZERO;
//            return true;
//        }
//        else
//            return false;
//    }
//
//    public boolean validArguments(){
//        return true;
//    }
//
//    public void addArgument(String argument){
//        arguments.add(argument);
//    }
//}
