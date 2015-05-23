package com.zapominacz.studia.akprojekt;

import com.zapominacz.studia.akprojekt.core.Memory;
import com.zapominacz.studia.akprojekt.core.Processor;
import com.zapominacz.studia.akprojekt.model.Register;
import com.zapominacz.studia.akprojekt.utils.Bits;
import com.zapominacz.studia.akprojekt.utils.MnemonicCodeTranslator;
import com.zapominacz.studia.akprojekt.utils.Registers;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class UserGuiActionsAdapter {

    private MnemonicCodeTranslator translator;
    private Path openedFile;
    private Processor processor;
    private Memory memory;
    private Register[] registers;
    private JLabel statusIndicator;
    private Map<Integer, Object> breakPoints;

    public UserGuiActionsAdapter() {
        breakPoints = new HashMap<>();
        translator = new MnemonicCodeTranslator();
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
        onTranslate(asmTextPane, codeTextPane);
        asmTextPane.removeAllLineHighlights();
        codeTextPane.removeAllLineHighlights();
        setStatusText("Uruchomiony");
        try {
            asmTextPane.addLineHighlight(0, Color.GREEN);
            codeTextPane.addLineHighlight(0, Color.GREEN);
        } catch (BadLocationException e) {
            setStatusText("Brak danych!");
        }
        processor.init(Bits.parseBits(Memory.CODE_BEGINNING, Register.WORD_LEN));
    }

    public void onNextLine(RSyntaxTextArea asmTextPane, RSyntaxTextArea codeTextPane) {
        processor.nextProcessorCycle();
        int debugLine = processor.getCurrentLine();
        System.out.println(debugLine);
        try {
            asmTextPane.removeAllLineHighlights();
            codeTextPane.removeAllLineHighlights();
            asmTextPane.addLineHighlight(debugLine, Color.GREEN);
            codeTextPane.addLineHighlight(debugLine, Color.GREEN);
        } catch (BadLocationException e) {
            setStatusText("Koniec programu");
        }

    }

    public void onBreakPointToggle(RSyntaxTextArea asmTextPane, RSyntaxTextArea codeTextPane) {
        try {
            int line = asmTextPane.getCaretLineNumber();
            Object tag = breakPoints.get(line);
            if (tag != null) {
                asmTextPane.removeLineHighlight(tag);
                breakPoints.remove(line);
            } else {
                breakPoints.put(line, asmTextPane.addLineHighlight(asmTextPane.getCaretLineNumber(), Color.RED));
            }

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void onInterrupt(RSyntaxTextArea asmTextPane, RSyntaxTextArea codeTextPane) {

    }

    public void onContinue(RSyntaxTextArea asmTextPane, RSyntaxTextArea codeTextPane) {
        int maxLine = asmTextPane.getLineCount();
        int line = processor.getCurrentLine();
        while(breakPoints.get(line) == null && line < maxLine) {
            onNextLine(asmTextPane, codeTextPane);
            line = processor.getCurrentLine();
        }
    }

    public void setStatusText(String statusText) {
        if(statusText != null) {
            statusIndicator.setText(statusText);
        }
    }

    public void setStatusIndicator(JLabel statusLabel) {
        this.statusIndicator = statusLabel;
    }

    public void onTranslate(RSyntaxTextArea asmTextPane, RSyntaxTextArea codeTextPane) {
        setStatusText("Przetłumaczono");
        translator.translateAssemblyCode(asmTextPane, codeTextPane);
    }
}
