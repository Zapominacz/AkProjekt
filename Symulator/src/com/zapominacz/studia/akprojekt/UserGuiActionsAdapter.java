package com.zapominacz.studia.akprojekt;

import com.zapominacz.studia.akprojekt.core.Processor;
import com.zapominacz.studia.akprojekt.memory.Memory;
import com.zapominacz.studia.akprojekt.model.Register;
import com.zapominacz.studia.akprojekt.utils.Bits;
import com.zapominacz.studia.akprojekt.utils.Registers;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class UserGuiActionsAdapter {

    private Path openedFile;
    private Processor processor;
    private Memory memory;
    private Register[] registers;
    private boolean hex = true;

    public UserGuiActionsAdapter() {
        openedFile = null;
        registers = new Register[Registers.REGISTERS];
        for(int i = 0; i < Registers.REGISTERS; i++) {
            registers[i] = new Register();
        }
        memory = Memory.getInstance();
        processor = new Processor(registers, memory);
    }

    public Register getRegister(int number) {
        return registers[number];
    }

    public void connectTextFieldWithRegister(int register, JTextField textField) {
        registers[register].setRepresentation(textField);
    }

    public void onSystemChanged(JComboBox showInSystemComboBox) {
        hex = showInSystemComboBox.getSelectedIndex() == 0;
        for(Register register : registers) {
            register.changeSystem(hex);
        }
    }

    public void onOpenFile(RSyntaxTextArea asmTextPane, JFrame parent) {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(parent);
        File file = fileChooser.getSelectedFile();
        if(file != null) {
            openedFile = file.toPath();
            byte[] bytes = new byte[0];
            try {
                bytes = Files.readAllBytes(openedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            asmTextPane.setText(new String(bytes, Charset.forName("UTF-8")));
        }
    }

    public void onSaveAsFile(RSyntaxTextArea asmTextPane, JFrame parent) {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(parent);
        File file = fileChooser.getSelectedFile();
        if(file != null) {
            openedFile = file.toPath();
            try {
                Files.write(openedFile, asmTextPane.getText().getBytes(), StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onSaveFile(RSyntaxTextArea asmTextPane, JFrame parent) {
        if(openedFile == null) {
            onSaveAsFile(asmTextPane, parent);
        } else {
            try {
                Files.write(openedFile, asmTextPane.getText().getBytes(), StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void onRunProgram(RSyntaxTextArea asmTextPane, RSyntaxTextArea codeTextPane) {
        processor.init(Bits.parseBits(Processor.CURRENT_INSTRUCTION, Register.WORD_LEN));
//        compiler.clearCompiler();
//
//        try {
//            for (int i = 0; i < asmTextPane.getLineCount(); i++) {
//                Integer startOffset = asmTextPane.getLineStartOffset(i), endOffset = asmTextPane.getLineEndOffset(i);
//                String temp = asmTextPane.getText(startOffset, endOffset-startOffset);
//                temp = temp.replaceAll("(\\r|\\n)", "");
//                compiler.addInstruction(temp);
//            }
//        }
//        catch(Exception e){
//            //Do nothing
//        }
//        compiler.execute();
//        for(int i = 0; i < 32; i++) {
//            String registerName = "R" + Integer.toHexString(i).toUpperCase();
//            textFields[i].setText(compiler.getRegisterHexValue(registerName));
//        }
    }

    public void onNextLine(RSyntaxTextArea asmTextPane, RSyntaxTextArea codeTextPane) {
        processor.nextProcessorCycle();
    }

    public void onBreakPointToggle(RSyntaxTextArea asmTextPane, RSyntaxTextArea codeTextPane) {
    }

    public void onInterrupt(RSyntaxTextArea asmTextPane, RSyntaxTextArea codeTextPane) {
    }

    public void onContinue(RSyntaxTextArea asmTextPane, RSyntaxTextArea codeTextPane) {

    }
}
