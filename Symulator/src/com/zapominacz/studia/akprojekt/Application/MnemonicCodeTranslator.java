package com.zapominacz.studia.akprojekt.application;

import com.zapominacz.studia.akprojekt.enums.RegisterSection;
import com.zapominacz.studia.akprojekt.util.InputNumberFormat;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.text.BadLocationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class MnemonicCodeTranslator {

    private static final String TRANSLATE_OPCODE_TABLE_PATH = "translateOpcodesTable.txt";
    private static final String TRANSLATE_NUBER_BASE_TABLE_PATH = "numberFormatRegexTable.txt";
    private static final String TRANSLATE_REGISTERS_TABLE_PATH = "translateRegisters.txt";
    private static final String TRANSLATE_COND_TABLE_PATH = "translateConditionalTable.txt";
    private static final String COMMAND_BASE = "00000000000000000000000000001111";

    private static final String IMMEDIATE_ARGUMENT =  "%IMM";
    private static final String FIRST_REGISTER_ARGUMENT =  "%IN1";
    private static final String SECOND_REGISTER_ARGUMENT =  "%IN2";
    private static final String OUT_REGISTER_ARGUMENT =  "%OUT";

    private static final int OPCODE =  1;
    private static final int MNEMONIC =  0;
    private static final int COND_MNEMONIC =  0;
    private static final int COND_CODE =  1;

    private Map<String, String[]> opCodeMap;
    private Map<String, String> condCodeMap;
    private Map<String, String> registersMap;
    private List<InputNumberFormat> numberBase;

    public MnemonicCodeTranslator() {
        try {
            loadNumberBases();
            loadRegistersMap();
            loadCondCodeMap();
            loadOpCodeMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadNumberBases() throws IOException {
        numberBase = new LinkedList<>();
        Path translateTableFile = Paths.get(TRANSLATE_NUBER_BASE_TABLE_PATH);
        Iterator<String> tableIterator = Files.lines(translateTableFile).iterator();
        while (tableIterator.hasNext()) {
            String[] lineData = tableIterator.next().split("\\s+");
            numberBase.add(new InputNumberFormat(lineData[0], Integer.parseInt(lineData[1]),
                    lineData[2].equals("B"), Integer.parseInt(lineData[3])));
        }
    }

    private void loadOpCodeMap() throws IOException {
        opCodeMap = new HashMap<>();
        Path translateTableFile = Paths.get(TRANSLATE_OPCODE_TABLE_PATH);
        Iterator<String> tableIterator = Files.lines(translateTableFile).iterator();
        while (tableIterator.hasNext()) {
            String[] lineData = tableIterator.next().split("\\s+");
            opCodeMap.put(lineData[MNEMONIC], Arrays.copyOfRange(lineData, OPCODE, lineData.length));
        }
    }

    private void loadRegistersMap() throws IOException {
        registersMap = new HashMap<>();
        Path translateTableFile = Paths.get(TRANSLATE_REGISTERS_TABLE_PATH);
        Iterator<String> tableIterator = Files.lines(translateTableFile).iterator();
        while (tableIterator.hasNext()) {
            String[] lineData = tableIterator.next().split("\\s+");
            registersMap.put(lineData[MNEMONIC], lineData[OPCODE]);
        }
    }

    private void loadCondCodeMap() throws IOException {
        condCodeMap = new HashMap<>();
        Path translateTableFile = Paths.get(TRANSLATE_COND_TABLE_PATH);
        Iterator<String> tableIterator = Files.lines(translateTableFile).iterator();
        while (tableIterator.hasNext()) {
            String[] lineData = tableIterator.next().split("\\s+");
            condCodeMap.put(lineData[COND_MNEMONIC], lineData[COND_CODE]);
        }
    }

    public void translateAssemblyCode(RSyntaxTextArea asmTextPane, RSyntaxTextArea codeTextPane) {
        clearTranslateTextPane(codeTextPane);
        String lastLine = null;
        try {
            int line = 0;
            for (String asmLine : asmTextPane.getText().split("\\n")) {
                line++;
                lastLine = asmLine;
                if(asmLine.matches("\\s*")) {
                    insertNewLine(codeTextPane);
                    continue;
                }
                StringBuilder result = new StringBuilder(COMMAND_BASE);
                String[] command = asmLine.toUpperCase().split("\\s+");
                String[] code = opCodeMap.get(command[MNEMONIC]);
                if(code == null) {
                    System.out.println("Błąd w lini" + line + ": " + lastLine);
                    return;
                }
                translateOpcode(result, code[0]);

                for (int i = 1; i < command.length; i++) {
                    String reg = registersMap.get(command[i]);
                    if (reg != null) {
                        insertRegisterIntoCommand(result, reg, code[i]);
                    } else {
                        parseImmediateArgument(result, command[i]);
                    }
                }

                String cond = condCodeMap.get(code[code.length - 1]);
                if (cond != null) {
                    insertCondCode(result, cond);
                }
                formatCode(result);
                codeTextPane.insert(result.toString(), length(codeTextPane));
                insertNewLine(codeTextPane);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Błąd w lini: " + lastLine);
            e.printStackTrace();
        }
    }

    private void formatCode(StringBuilder result) {
        result.insert(RegisterSection.COND_CODE_START.getIndex(), '\t');
        result.insert(RegisterSection.IMMEDIATE_START.getIndex(), '\t');
        result.insert(RegisterSection.SECOND_REG_START.getIndex(), '\t');
        result.insert(RegisterSection.FIRST_REG_START.getIndex(), '\t');
        result.insert(RegisterSection.OUT_REG_START.getIndex(), '\t');
    }

    private void insertCondCode(StringBuilder result, String cond) {
        result.replace(RegisterSection.COND_CODE_START.getIndex(), RegisterSection.COND_CODE_END.getIndex(), cond);
    }

    private void parseImmediateArgument(StringBuilder builder, String immediate) {
        String result = null;
        for(InputNumberFormat baseFormat : numberBase) {
            if(immediate.matches(baseFormat.getRegex())) {
                result = baseFormat.convertStringNumberToBinaryString(immediate);
                if(result.length() > 8) {
                    result = result.substring(result.length() - 8);
                }
                break;
            }
        }
        builder.replace(RegisterSection.IMMEDIATE_START.getIndex() + (8 - result.length()), RegisterSection.IMMEDIATE_END.getIndex(), result);
    }

    private void insertRegisterIntoCommand(StringBuilder result, String register, String code) {
        switch (code) {
            case FIRST_REGISTER_ARGUMENT:
                result.replace(RegisterSection.FIRST_REG_START.getIndex(), RegisterSection.FIRST_REG_END.getIndex(), register);
                break;
            case SECOND_REGISTER_ARGUMENT:
                result.replace(RegisterSection.SECOND_REG_START.getIndex(), RegisterSection.SECOND_REG_END.getIndex(), register);
                break;
            case OUT_REGISTER_ARGUMENT:
                result.replace(RegisterSection.OUT_REG_START.getIndex(), RegisterSection.OUT_REG_END.getIndex(), register);
                break;
        }
    }

    private void clearTranslateTextPane(RSyntaxTextArea codeTextPane) {
        try {
            codeTextPane.getDocument().remove(0, codeTextPane.getDocument().getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    private void translateOpcode(StringBuilder result, String opcode) {
        result.replace(RegisterSection.OPCODE_START.getIndex(), RegisterSection.OPCODE_END.getIndex(), opcode);
    }

    private static void insertNewLine(RSyntaxTextArea codeTextPane) {
        codeTextPane.insert("\n", length(codeTextPane));
    }

    private static int length(RSyntaxTextArea codeTextPane) {
        return codeTextPane.getDocument().getLength();
    }


}
