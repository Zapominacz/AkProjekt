package com.zapominacz.studia.akprojekt.core;

import com.zapominacz.studia.akprojekt.enums.RegisterSection;
import com.zapominacz.studia.akprojekt.instructions.Instruction;
import com.zapominacz.studia.akprojekt.instructions.logical.And;
import com.zapominacz.studia.akprojekt.instructions.logical.Or;
import com.zapominacz.studia.akprojekt.instructions.logical.Xor;
import com.zapominacz.studia.akprojekt.instructions.manipulation.Rl;
import com.zapominacz.studia.akprojekt.instructions.manipulation.Rr;
import com.zapominacz.studia.akprojekt.instructions.transport.*;
import com.zapominacz.studia.akprojekt.model.Bit;
import com.zapominacz.studia.akprojekt.model.Register;
import com.zapominacz.studia.akprojekt.utils.Bits;

import java.util.HashMap;
import java.util.Map;

public class Processor {

    /* pozycja CSOZ */
    public final static int FLAG_CARRY = 3;
    public final static int FLAG_SIGN = 2;
    public final static int FLAG_OVERFLOW = 1;
    public final static int FLAG_ZERO = 0;

    /* Rejestry specjalne */
    public final static int PC = 31;
    public final static int FLAGS = 30;
    public final static int STACK_POINTER = 28;
    public final static int CURRENT_INSTRUCTION = 32;
    public final static int VALUE_BUF = 33;

    private Instruction currentInstruction;
    private Map<Integer, Instruction> availableInstructions;
    private Register[] registers;
    private Memory memory;

    public Processor(Register[] registers, Memory memory) {
        this.registers = registers;
        this.memory = memory;
    }

    private void initStackPointer() {
        registers[STACK_POINTER].setRegisterValue(Bits.parseBits(Memory.STACK_BEGINNING, Register.WORD_LEN));
    }

    public void init(Bit[] firstInstructionPointer) {
        loadAvailableInstructions();
        initStackPointer();
        insertPointerToPC(firstInstructionPointer);
    }

    public void nextProcessorCycle() {
        loadNextInstruction();
        currentInstruction.loadArguments(registers);
        currentInstruction.loadArguments(memory);
        currentInstruction.execute();
        currentInstruction.saveResult(registers);
        currentInstruction.saveResult(memory);
        loadNextInstructionPointer();
    }

    private void loadNextInstructionPointer() {
        Bit[] increment = Bits.parseBits(4, Register.WORD_LEN);
        registers[PC].setRegisterValue(Bits.add(registers[PC].getBits(), increment));
    }

    private void insertPointerToPC(Bit[] instructionPointer) {
        registers[PC].setRegisterValue(instructionPointer);
    }

    private void loadNextInstruction() {
        Bit[] instructionCode = memory.loadFromMemory(registers[PC].getBits());
        if(Bits.parseInteger(instructionCode) != 0) {
            int instructionNumber = Bits.parseInteger(Bits.getBits(instructionCode,
                    RegisterSection.OPCODE_START.getIndex(), RegisterSection.OPCODE_END.getIndex()));
            currentInstruction = availableInstructions.get(instructionNumber);
            currentInstruction.parseArguments(instructionCode);
        }
    }

    public void loadAvailableInstructions() {
        availableInstructions = new HashMap<>();
        //TODO numery
//        availableInstructions.put(0, new Add());
//        availableInstructions.put(1, new AddI());
//        availableInstructions.put(2, new Sub());
//        availableInstructions.put(3, new SubI());
//        availableInstructions.put(4, new MulI());
//        availableInstructions.put(5, new Mul());
//        availableInstructions.put(6, new IMuh());
//        availableInstructions.put(7, new Muh());


        availableInstructions.put(8, new Or());
        availableInstructions.put(9, new Xor());
        availableInstructions.put(10, new And());
        availableInstructions.put(14, new Rr());
        availableInstructions.put(15, new Rl());
        availableInstructions.put(19, new Push());
        availableInstructions.put(20, new Pop());
//        availableInstructions.put(31, new Itrp());
        availableInstructions.put(27, new GetPc());
        availableInstructions.put(28, new SetPc());
//        availableInstructions.put(29, new Call());
//        availableInstructions.put(30, new Ret());
        availableInstructions.put(21, new LoadI());
        availableInstructions.put(22, new Load());
        availableInstructions.put(25, new Store());
        availableInstructions.put(26, new Copy());
        availableInstructions.put(23, new GetF());
        availableInstructions.put(24, new SetF());
    }

    public int getCurrentLine() {
        return (Bits.parseInteger(registers[PC].getBits()) - 2000) / 4;
    }
}
