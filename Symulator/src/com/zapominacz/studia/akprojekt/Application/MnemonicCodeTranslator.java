package com.zapominacz.studia.akprojekt.application;

import com.zapominacz.studia.akprojekt.exceptions.UnsupportedMnemonicException;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.text.BadLocationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MnemonicCodeTranslator {

    private String[] asmLines;
    private Map<String, String[]> opCodeMap;

    public MnemonicCodeTranslator() {
        try {
            loadOpCodeMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadOpCodeMap() throws IOException {
        opCodeMap = new HashMap<>();
        Path translateTableFile = Paths.get("translateOpcodesTable.txt");
        Iterator<String> tableIterator = Files.lines(translateTableFile).iterator();
        while (tableIterator.hasNext()) {
            String[] lineData = tableIterator.next().split("\\s+");
            opCodeMap.put(lineData[0], lineData);
        }
    }

    public void translateAssemblyCode(RSyntaxTextArea asmTextPane, RSyntaxTextArea codeTextPane)
            throws UnsupportedMnemonicException {
        try {
            codeTextPane.getDocument().remove(0, codeTextPane.getDocument().getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        asmLines = asmTextPane.getText().split("\\n");
        for(String asmLine : asmLines) {
            StringBuilder result = new StringBuilder("00000000000000000000000000001111");
            String reg;
            String[] command = asmLine.toUpperCase().split("\\s+");
            String[] code = opCodeMap.get(command[0]);
            result.replace(0,4, code[1]);
            try {
                for (int i = 1; i < command.length; i++) {
                    switch (code[i + 1]) {
                        case "%IMM":
                            int immediate = Integer.decode(command[i]);
                            result.replace(20,28,Integer.toBinaryString(immediate));
                            break;
                        case "%IN1":
                            reg = command[i].substring(2);
                            result.replace(15, 20, Integer.toBinaryString(Integer.parseInt(reg, 16)));
                            break;
                        case "%IN2":
                            reg = command[i].substring(2);
                            result.replace(10,15,Integer.toBinaryString(Integer.parseInt(reg, 16)));
                            break;
                        case "%OUT":
                            reg = command[i].substring(2);
                            result.replace(5,10,Integer.toBinaryString(Integer.parseInt(reg, 16)));
                            break;
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                throw new UnsupportedOperationException("Nie ma takiej operacji: " + asmLine);
            }
            if(code == null) throw new UnsupportedMnemonicException("Nie ma takiego mnemonika: " + command[0]);
            codeTextPane.insert(result.toString(), length(codeTextPane));
            insertNewLine(codeTextPane);
        }
    }

    private static void insertNewLine(RSyntaxTextArea codeTextPane) {
        codeTextPane.insert("\n", length(codeTextPane));
    }

    private static int length(RSyntaxTextArea codeTextPane) {
        return codeTextPane.getDocument().getLength();
    }


}
