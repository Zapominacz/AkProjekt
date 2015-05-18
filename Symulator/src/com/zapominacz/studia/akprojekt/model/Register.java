package com.zapominacz.studia.akprojekt.model;

import com.zapominacz.studia.akprojekt.utils.Bits;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register {

    public final static int WORD_LEN = 32;
    private Bit[] registerValue;
    private JTextField representation;

    public Register() {
        registerValue = Bits.createBits(WORD_LEN);
    }

    public Bit[] getBits() {
        return registerValue;
    }

    public void setRegisterValue(Bit[] registerValue) {
        this.registerValue = registerValue;
        representation.setText(Integer.toBinaryString(Bits.parseInteger(registerValue))); //TODO w translatorze
    }

    public JTextField getRepresentation() {
        return representation;
    }

    public void setRepresentation(JTextField representation) {
        this.representation = representation;
        this.representation.addActionListener(e -> {

        });
    }

    public void changeSystem(boolean hex) {

    }
}
