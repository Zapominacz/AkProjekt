package com.zapominacz.studia.akprojekt.core;

import com.zapominacz.studia.akprojekt.enums.RegisterSection;
import com.zapominacz.studia.akprojekt.instructions.Instruction;
import com.zapominacz.studia.akprojekt.memory.Memory;
import com.zapominacz.studia.akprojekt.model.Register;
import com.zapominacz.studia.akprojekt.util.Bit;
import com.zapominacz.studia.akprojekt.util.Bits;

import java.util.Map;

public class Processor {

    /* Rejestry specjalne */
    private final static int PC = 31;
    private final static int FLAGS = 30;
    private final static int STACK_POINTER = 28;
    private final static int CURRENT_INSTRUCTION = 32;
    private final static int VALUE_BUF = 33;

    private Instruction currentInstruction;
    private Map<Integer, Instruction> availableInstructions;
    private Register[] registers;
    private Memory memory;

    public Processor(Bit[] firstInstructionPointer, Register[] registers, Memory memory) {
        this.registers = registers;
        this.memory = memory;

        insertPointerToPC(firstInstructionPointer);
        loadNextInstruction();
    }

    public void nextProcessorCycle() {
        loadNextInstructionPointer();
        currentInstruction.loadArguments(registers);
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
        currentInstruction.parseArguments(Bits.getBits(instructionCode,
                RegisterSection.OPCODE_END.getIndex(), Instruction.INSTRUCTION_LENGTH));
    }

}
