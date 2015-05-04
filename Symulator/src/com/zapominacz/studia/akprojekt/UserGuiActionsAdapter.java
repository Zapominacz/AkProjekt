package com.zapominacz.studia.akprojekt;

import com.zapominacz.studia.akprojekt.application.Compiler;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class UserGuiActionsAdapter {

    private Path openedFile;
    //for refactioring:
    private Compiler compiler = Compiler.getInstance();

    public UserGuiActionsAdapter() {
        openedFile = null;
    }

    public void onSystemChanged(JComboBox showInSystemComboBox) {
    }

    public void onOpenFile(RSyntaxTextArea asmTextPane, JFrame parent) {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(parent);
        openedFile = fileChooser.getSelectedFile().toPath();
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(openedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        asmTextPane.setText(new String(bytes, Charset.forName("UTF-8")));
    }

    public void onSaveAsFile(RSyntaxTextArea asmTextPane, JFrame parent) {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(parent);
        openedFile = fileChooser.getSelectedFile().toPath();
        try {
            Files.write(openedFile, asmTextPane.getText().getBytes(), StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
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

    //for refactoring - textfields update!
    public void onRunProgram(RSyntaxTextArea asmTextPane, RSyntaxTextArea codeTextPane, JTextField[] textFields) {
        try {
            for (int i = 0; i < asmTextPane.getLineCount(); i++) {
                Integer startOffset = asmTextPane.getLineStartOffset(i), endOffset = asmTextPane.getLineEndOffset(i);
                String temp = asmTextPane.getText(startOffset, endOffset-startOffset);
                compiler.addInstruction(temp);
            }
        }
        catch(Exception e){
            //Do nothing
        }
        compiler.execute();
        for(int i = 0; i < 16; i++) {
            String registerName = "R" + Integer.toHexString(i).toUpperCase();
            textFields[i].setText(compiler.getRegisterHexValue(registerName));
        }
    }

    public void onNextLine(RSyntaxTextArea asmTextPane, RSyntaxTextArea codeTextPane) {
        
    }

    public void onBreakPointToggle(RSyntaxTextArea asmTextPane, RSyntaxTextArea codeTextPane) {
    }

    public void onInterrupt(RSyntaxTextArea asmTextPane, RSyntaxTextArea codeTextPane) {
    }

    public void onContinue(RSyntaxTextArea asmTextPane, RSyntaxTextArea codeTextPane) {

    }
}
