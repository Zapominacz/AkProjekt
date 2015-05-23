package com.zapominacz.studia.akprojekt;

import com.zapominacz.studia.akprojekt.utils.Registers;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;

public class MainWindow extends JFrame implements StatusChangeInterface {



    private RSyntaxTextArea codeTextPane;
    private RSyntaxTextArea asmTextPane;

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu programMenu;
    private JMenuItem breakMenuItem;
    private JMenuItem continueMenuItem;
    private JMenuItem nextMenuItem;
    private JMenuItem openMenuItem;
    private JMenuItem stopMenuItem;
    private JMenuItem translateMenuItem;
    private JMenuItem runMenuItem;
    private JMenuItem saveAsMenuItem;
    private JMenuItem saveMenuItem;

    private JLabel[] registerLabels;

    private RTextScrollPane asmScrollPane;
    private RTextScrollPane codeScrollPane;
    private JScrollPane regScrollPane;
    private JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private JLabel statusLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;

    private UserGuiActionsAdapter guiActionsAdapter;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        guiActionsAdapter = new UserGuiActionsAdapter();
        initComponents();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException ex ) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    private void initComponents() {

        statusLabel = new JLabel();
        jLabel1 = new javax.swing.JLabel("Rejestry");
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel("Asembler");
        jLabel3 = new javax.swing.JLabel("Kod maszynowy");


        asmTextPane = new RSyntaxTextArea(20, 60);
        asmTextPane.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_ASSEMBLER_X86);
        asmTextPane.setCodeFoldingEnabled(true);
        asmScrollPane = new RTextScrollPane(asmTextPane);


        codeTextPane = new RSyntaxTextArea(20, 60);
        codeTextPane.setCodeFoldingEnabled(true);
        codeScrollPane = new RTextScrollPane(codeTextPane);


        regScrollPane = new JScrollPane();
        jPanel1 = new JPanel();

        registerLabels = new JLabel[Registers.REGISTERS];
        for(int i = 0; i < Registers.REGISTERS; i++) {
            String registerName = "R" + Integer.toHexString(i).toUpperCase();
            registerLabels[i] = new JLabel(registerName);
            guiActionsAdapter.connectTextFieldWithRegister(i, new JTextField("0"));
        }

        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        openMenuItem = new JMenuItem();
        saveAsMenuItem = new JMenuItem();
        saveMenuItem = new JMenuItem();
        programMenu = new JMenu();
        runMenuItem = new JMenuItem();
        translateMenuItem = new JMenuItem();
        nextMenuItem = new JMenuItem();
        breakMenuItem = new JMenuItem();
        stopMenuItem = new JMenuItem();
        continueMenuItem = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Projekt AK");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("mainFrame"); // NOI18N

        guiActionsAdapter.setStatusIndicator(statusLabel);
        statusLabel.setText("Wszystko OK");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        GroupLayout.ParallelGroup registerGroup = jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
        for(int i = 0; i < Registers.REGISTERS; i++) {
            registerGroup = registerGroup.addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(registerLabels[i])
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(guiActionsAdapter.getRegister(i).getRepresentation(), GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE));
        }
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(registerGroup))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        GroupLayout.SequentialGroup registerVerticalGroup = jPanel1Layout.createSequentialGroup()
                .addContainerGap();
        for(int i = 0; i < Registers.REGISTERS; i++) {
            registerVerticalGroup = registerVerticalGroup
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(registerLabels[i])
                            .addComponent(guiActionsAdapter.getRegister(i).getRepresentation(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
        }
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(registerVerticalGroup
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        regScrollPane.setViewportView(jPanel1);

        fileMenu.setText("Plik");
        fileMenu.setToolTipText("");
        fileMenu.setActionCommand("Plik");

        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMenuItem.setText("Open");
        openMenuItem.addActionListener(e -> guiActionsAdapter.onOpenFile(asmTextPane, MainWindow.this));
        fileMenu.add(openMenuItem);

        saveAsMenuItem.setText("Zapisz jako...");
        fileMenu.add(saveAsMenuItem);
        saveAsMenuItem.addActionListener(e -> guiActionsAdapter.onSaveAsFile(asmTextPane, MainWindow.this));

        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setText("Zapisz");
        saveMenuItem.setToolTipText("");
        saveMenuItem.setActionCommand("Zapisz");
        saveMenuItem.addActionListener(e -> guiActionsAdapter.onSaveFile(asmTextPane, MainWindow.this));
        fileMenu.add(saveMenuItem);

        menuBar.add(fileMenu);

        programMenu.setText("Program");
        programMenu.setToolTipText("");

        translateMenuItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        translateMenuItem.setText("Translate");
        translateMenuItem.addActionListener(e -> guiActionsAdapter.onTranslate(asmTextPane, codeTextPane));
        programMenu.add(translateMenuItem);

        runMenuItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        runMenuItem.setText("Uruchom");
        runMenuItem.addActionListener(e -> guiActionsAdapter.onRunProgram(asmTextPane, codeTextPane));
        programMenu.add(runMenuItem);

        nextMenuItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        nextMenuItem.setText("Nastêpna linia");
        nextMenuItem.addActionListener(e -> guiActionsAdapter.onNextLine(asmTextPane, codeTextPane));
        programMenu.add(nextMenuItem);

        breakMenuItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        breakMenuItem.setText("Toggle breakpoint");
        breakMenuItem.addActionListener(e -> guiActionsAdapter.onBreakPointToggle(asmTextPane, codeTextPane));
        programMenu.add(breakMenuItem);

        stopMenuItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        stopMenuItem.setText("Zatrzymaj");
        stopMenuItem.addActionListener(e -> guiActionsAdapter.onInterrupt(asmTextPane, codeTextPane));
        programMenu.add(stopMenuItem);

        continueMenuItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        continueMenuItem.setText("Kontynuuj");
        continueMenuItem.addActionListener(e -> guiActionsAdapter.onContinue(asmTextPane, codeTextPane));
        programMenu.add(continueMenuItem);

        menuBar.add(programMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addGap(0, 435, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(asmScrollPane)
                                                        .addComponent(codeScrollPane))
                                                .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(asmScrollPane)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(codeScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(regScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(regScrollPane))
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    @Override
    public void changeStatus(String msg) {
        statusLabel.setText(msg);
    }
    // End of variables declaration                   
}
