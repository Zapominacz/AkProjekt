package com.zapominacz.studia.akprojekt.core;

import com.zapominacz.studia.akprojekt.enums.RegisterSection;
import com.zapominacz.studia.akprojekt.instructions.Instruction;
import com.zapominacz.studia.akprojekt.memory.Memory;
import com.zapominacz.studia.akprojekt.model.Register;
import com.zapominacz.studia.akprojekt.model.Bit;
import com.zapominacz.studia.akprojekt.utils.Bits;

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

    public Processor(Bit[] firstInstructionPointer, Register[] registers, Memory memory) {
        this.registers = registers;
        this.memory = memory;
        loadAvailableInstructions();
        initStackPointer();
        insertPointerToPC(firstInstructionPointer);
        loadNextInstruction();
    }

    private void loadAvailableInstructions() {
        //TODO
    }

    private void initStackPointer() {
        registers[STACK_POINTER].setRegisterValue(Bits.parseBits(Memory.STACK_BEGINNING, Register.WORD_LEN));
    }

    public void nextProcessorCycle() {
        loadNextInstructionPointer();
        currentInstruction.loadArguments(registers);
        currentInstruction.loadArguments(memory);
        currentInstruction.execute();
        currentInstruction.saveResult(registers);
        currentInstruction.saveResult(memory);
        loadNextInstruction();
    }

    private void loadNextInstructionPointer() {
        Bit[] increment = Bits.createBits(Register.WORD_LEN);
        increment[0] = Bit.LOW;
        registers[PC].setRegisterValue(Bits.add(registers[PC].getBits(), increment));
    }

    private void insertPointerToPC(Bit[] instructionPointer) {
        registers[PC].setRegisterValue(instructionPointer);
    }

    private void loadNextInstruction() {
        Bit[] instructionCode = registers[PC].getBits();
        int instructionNumber = Bits.parseInteger(Bits.getBits(instructionCode,
                RegisterSection.OPCODE_START.getIndex(), RegisterSection.OPCODE_END.getIndex()));
        currentInstruction = availableInstructions.get(instructionNumber);
        currentInstruction.parseArguments(instructionCode);
    }

}
