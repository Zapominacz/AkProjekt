package com.zapominacz.studia.akprojekt.Application;

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
    private Map<String, String> opCodeMap;

    public MnemonicCodeTranslator() {
        try {
            loadOpCodeMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadOpCodeMap() throws IOException {
        opCodeMap = new HashMap<>();
        Path translateTableFile = Paths.get("translateTable.txt");
        Iterator<String> tableIterator = Files.lines(translateTableFile).iterator();
        while (tableIterator.hasNext()) {
            String[] lineData = tableIterator.next().split(" ");
            opCodeMap.put(lineData[0].trim(), lineData[1].trim());
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
            String[] command = asmLine.toUpperCase().split("\\s+");
            String opCode = opCodeMap.get(command[0]);
            if(opCode == null) throw new UnsupportedMnemonicException("Nie ma takiego mnemonika: " + command[0]);
            codeTextPane.insert(opCodeMap.get(command[0]), length(codeTextPane));
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
