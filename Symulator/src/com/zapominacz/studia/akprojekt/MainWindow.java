package com.zapominacz.studia.akprojekt;

import com.zapominacz.studia.akprojekt.Application.MnemonicCodeTranslator;
import com.zapominacz.studia.akprojekt.Application.Compiler;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;

/**
 * @author Zapominacz
 */
public class MainWindow extends javax.swing.JFrame {

    private RTextScrollPane asmScrollPane;
    private RSyntaxTextArea asmTextPane;
    private javax.swing.JMenuItem breakMenuItem;
    private RTextScrollPane codeScrollPane;
    private RSyntaxTextArea codeTextPane;
    private javax.swing.JMenuItem continueMenuItem;
    private javax.swing.JTabbedPane editorPane;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu programMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem nextMenuItem;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JTextField r0Field;
    private javax.swing.JLabel r0Label;
    private javax.swing.JTextField r1Field;
    private javax.swing.JLabel r1Label;
    private javax.swing.JTextField r2Field;
    private javax.swing.JLabel r2Label;
    private javax.swing.JTextField r3Field;
    private javax.swing.JLabel r3Label;
    private javax.swing.JTextField r4Field;
    private javax.swing.JLabel r4Label;
    private javax.swing.JTextField r5Field;
    private javax.swing.JLabel r5Label;
    private javax.swing.JTextField r6Field;
    private javax.swing.JLabel r6Label;
    private javax.swing.JTextField r7Field;
    private javax.swing.JLabel r7Label;
    private javax.swing.JTextField r8Field;
    private javax.swing.JLabel r8Label;
    private javax.swing.JTextField r9Field;
    private javax.swing.JLabel r9Label;
    private javax.swing.JTextField rAField;
    private javax.swing.JLabel rALabel;
    private javax.swing.JTextField rBField;
    private javax.swing.JLabel rBLabel;
    private javax.swing.JTextField rCField;
    private javax.swing.JLabel rCLabel;
    private javax.swing.JTextField rDField;
    private javax.swing.JLabel rDLabel;
    private javax.swing.JTextField rEField;
    private javax.swing.JLabel rELabel;
    private javax.swing.JTextField rFField;
    private javax.swing.JLabel rFLabel;
    private javax.swing.JScrollPane regScrollPane;
    private javax.swing.JMenuItem runMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JComboBox showInSystemComboBox;
    private javax.swing.JLabel showInSystemLabel;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JMenuItem stopMenuItem;
    private JMenuItem translateMenuItem;
    private MnemonicCodeTranslator translator;
    private Compiler compiler;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        compiler = Compiler.getInstance();
        initComponents();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        statusLabel = new javax.swing.JLabel();
        splitPane = new javax.swing.JSplitPane();
        editorPane = new javax.swing.JTabbedPane();


        asmTextPane = new RSyntaxTextArea(20, 60);
        asmTextPane.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_ASSEMBLER_X86);
        asmTextPane.setCodeFoldingEnabled(true);
        asmScrollPane = new RTextScrollPane(asmTextPane);


        codeTextPane = new RSyntaxTextArea(20, 60);
        codeTextPane.setCodeFoldingEnabled(true);
        codeScrollPane = new RTextScrollPane(codeTextPane);


        regScrollPane = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        r0Label = new javax.swing.JLabel();
        r0Field = new javax.swing.JTextField();
        r1Label = new javax.swing.JLabel();
        r1Field = new javax.swing.JTextField();
        r2Label = new javax.swing.JLabel();
        r2Field = new javax.swing.JTextField();
        r3Label = new javax.swing.JLabel();
        r3Field = new javax.swing.JTextField();
        r4Field = new javax.swing.JTextField();
        r4Label = new javax.swing.JLabel();
        r5Field = new javax.swing.JTextField();
        r5Label = new javax.swing.JLabel();
        r6Label = new javax.swing.JLabel();
        r6Field = new javax.swing.JTextField();
        r7Field = new javax.swing.JTextField();
        r7Label = new javax.swing.JLabel();
        r8Field = new javax.swing.JTextField();
        r8Label = new javax.swing.JLabel();
        r9Label = new javax.swing.JLabel();
        r9Field = new javax.swing.JTextField();
        rAField = new javax.swing.JTextField();
        rALabel = new javax.swing.JLabel();
        rBLabel = new javax.swing.JLabel();
        rBField = new javax.swing.JTextField();
        rCField = new javax.swing.JTextField();
        rCLabel = new javax.swing.JLabel();
        rDField = new javax.swing.JTextField();
        rDLabel = new javax.swing.JLabel();
        rEField = new javax.swing.JTextField();
        rELabel = new javax.swing.JLabel();
        rFLabel = new javax.swing.JLabel();
        rFField = new javax.swing.JTextField();
        showInSystemLabel = new javax.swing.JLabel();
        showInSystemComboBox = new javax.swing.JComboBox();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        programMenu = new javax.swing.JMenu();
        runMenuItem = new javax.swing.JMenuItem();
        translateMenuItem = new javax.swing.JMenuItem();
        nextMenuItem = new javax.swing.JMenuItem();
        breakMenuItem = new javax.swing.JMenuItem();
        stopMenuItem = new javax.swing.JMenuItem();
        continueMenuItem = new javax.swing.JMenuItem();
        translator = new MnemonicCodeTranslator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Projekt AK");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("mainFrame"); // NOI18N

        statusLabel.setText("Wszystko OK");

        splitPane.setDividerLocation(400);
        splitPane.setDividerSize(1);

        editorPane.addTab("asembler", asmScrollPane);

        editorPane.addTab("kod", codeScrollPane);

        splitPane.setLeftComponent(editorPane);

        r0Label.setText("R0x0");

        r0Field.setText(compiler.getRegisterHexValue("R0"));

        r1Label.setText("R0x1");

        r1Field.setText(compiler.getRegisterHexValue("R1"));

        r2Label.setText("R0x2");

        r2Field.setText(compiler.getRegisterHexValue("R2"));

        r3Label.setText("R0x3");

        r3Field.setText(compiler.getRegisterHexValue("R3"));

        r4Field.setText(compiler.getRegisterHexValue("R7"));

        r4Label.setText("R0x7");

        r5Field.setText(compiler.getRegisterHexValue("R6"));

        r5Label.setText("R0x6");

        r6Label.setText("R0x5");

        r6Field.setText(compiler.getRegisterHexValue("R5"));

        r7Field.setText(compiler.getRegisterHexValue("R4"));

        r7Label.setText("R0x4");

        r8Field.setText(compiler.getRegisterHexValue("RF"));

        r8Label.setText("R0xF");

        r9Label.setText("R0xE");

        r9Field.setText(compiler.getRegisterHexValue("RE"));

        rAField.setText(compiler.getRegisterHexValue("RD"));

        rALabel.setText("R0xC");

        rBLabel.setText("R0xD");

        rBField.setText(compiler.getRegisterHexValue("RC"));

        rCField.setText(compiler.getRegisterHexValue("RB"));

        rCLabel.setText("R0xA");

        rDField.setText(compiler.getRegisterHexValue("RA"));

        rDLabel.setText("R0xB");

        rEField.setText(compiler.getRegisterHexValue("R9"));

        rELabel.setText("R0x9");

        rFLabel.setText("R0x8");

        rFField.setText(compiler.getRegisterHexValue("R8"));

        showInSystemLabel.setText("Reprezentacja:");

        showInSystemComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"HEX", "DEC"}));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(r4Label)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(r4Field, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(r5Label)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(r5Field, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(r6Label)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(r6Field, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(r7Label)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(r7Field, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(r3Label)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(r3Field, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(r2Label)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(r2Field, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(r1Label)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(r1Field, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(r0Label)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(r0Field, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(r8Label)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(r8Field, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(r9Label)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(r9Field, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(rBLabel)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(rAField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(rALabel)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(rBField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(rDLabel)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(rCField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(rCLabel)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(rDField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(rELabel)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(rEField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(rFLabel)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(rFField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(showInSystemLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(showInSystemComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(showInSystemLabel)
                                        .addComponent(showInSystemComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(r0Label)
                                        .addComponent(r0Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(r1Label)
                                        .addComponent(r1Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(r2Label)
                                        .addComponent(r2Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(r3Label)
                                        .addComponent(r3Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(r7Label)
                                        .addComponent(r7Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(r6Label)
                                        .addComponent(r6Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(r5Label)
                                        .addComponent(r5Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(r4Label)
                                        .addComponent(r4Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rFLabel)
                                        .addComponent(rFField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rELabel)
                                        .addComponent(rEField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rCLabel)
                                        .addComponent(rDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rDLabel)
                                        .addComponent(rCField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rALabel)
                                        .addComponent(rBField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rBLabel)
                                        .addComponent(rAField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(r9Label)
                                        .addComponent(r9Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(r8Label)
                                        .addComponent(r8Field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        regScrollPane.setViewportView(jPanel1);

        splitPane.setRightComponent(regScrollPane);

        fileMenu.setText("Plik");
        fileMenu.setToolTipText("");
        fileMenu.setActionCommand("Plik");

        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveAsMenuItem.setText("Zapisz jako...");
        fileMenu.add(saveAsMenuItem);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setText("Zapisz");
        saveMenuItem.setToolTipText("");
        saveMenuItem.setActionCommand("Zapisz");
        fileMenu.add(saveMenuItem);

        menuBar.add(fileMenu);

        programMenu.setText("Program");
        programMenu.setToolTipText("");

        translateMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        translateMenuItem.setText("Translate");
        translateMenuItem.addActionListener(e -> translator.translateAssemblyCode(asmTextPane, codeTextPane));
        programMenu.add(translateMenuItem);

        runMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        runMenuItem.setText("Uruchom");
        programMenu.add(runMenuItem);

        nextMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        nextMenuItem.setText("Nast�pna linia");
        programMenu.add(nextMenuItem);

        breakMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        breakMenuItem.setText("Toggle breakpoint");
        programMenu.add(breakMenuItem);

        stopMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        stopMenuItem.setText("Zatrzymaj");
        programMenu.add(stopMenuItem);

        continueMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        continueMenuItem.setText("Kontynuuj");
        programMenu.add(continueMenuItem);

        menuBar.add(programMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                                        .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(splitPane)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusLabel)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>
    // End of variables declaration                   
}
