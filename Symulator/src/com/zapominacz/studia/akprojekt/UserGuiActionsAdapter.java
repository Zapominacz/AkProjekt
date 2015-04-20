package com.zapominacz.studia.akprojekt;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class UserGuiActionsAdapter {

    private Path openedFile;

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

    public void onRunProgram(RSyntaxTextArea asmTextPane, RSyntaxTextArea codeTextPane) {
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
