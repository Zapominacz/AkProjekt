package com.zapominacz.studia.akprojekt;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import java.nio.file.Path;

public class UserGuiActionsAdapter {

    public UserGuiActionsAdapter() {

    }

    public void onSystemChanged(JComboBox showInSystemComboBox) {
    }

    public Path onOpenFile(RSyntaxTextArea asmTextPane, Path openedFile) {
        return null;
    }

    public Path onSaveAsFile(RSyntaxTextArea asmTextPane, Path openedFile) {
        return null;
    }

    public Path onSaveFile(RSyntaxTextArea asmTextPane, Path openedFile) {
        return null;
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
