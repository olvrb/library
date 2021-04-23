package com.oliver.library.Application.GUIViews.Authentication;

import com.oliver.library.Application.Entities.User.User;
import com.oliver.library.Application.Services.ListenerServices;
import com.oliver.library.LibraryApplicationGUI;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class SignUpDialog extends JDialog {
    private JPanel contentPane;

    private JButton buttonOK;

    private JButton buttonCancel;

    private JTextField nameField;

    private JTextField ssnField;

    private JPasswordField passwordField;

    private JPasswordField passwordVerificationField;

    private LibraryApplicationGUI gui;


    public SignUpDialog(LibraryApplicationGUI gui) {
        this();
        this.gui = gui;
    }

    public SignUpDialog() {
        this.setContentPane(this.contentPane);
        this.setModal(true);
        this.getRootPane()
            .setDefaultButton(this.buttonOK);

        this.setUpListeners();

    }

    public static void main(String[] args) {
        JDialog dialog = new SignUpDialog();
        dialog.pack();
        dialog.setLocationByPlatform(true);
        dialog.setVisible(true);
    }

    private boolean validatePasswordField() {
        boolean valid = User.validatePassword(new String(this.passwordField.getPassword()));
        this.markFieldValid(this.passwordField, valid);
        return valid;
    }

    private void validateSsn() {
        
    }

    private void validatePasswordVerificationField(JPasswordField passwordVerificationField) {
        if (this.validatePasswordField()) {
            this.markFieldValid(passwordVerificationField,
                                Arrays.equals(this.passwordField.getPassword(),
                                              this.passwordVerificationField.getPassword()));
        }
    }

    private void markFieldValid(JTextComponent component, boolean valid) {
        this.setFieldBorder(component, valid ? Color.green : Color.RED);
    }

    private void setFieldBorder(JComponent component, Color c) {
        component.setBorder(BorderFactory.createLineBorder(c));
    }

    private void setUpListeners() {
        ListenerServices.addChangeListener(this.passwordField, e -> {
            this.validatePasswordField();
        });
        ListenerServices.addChangeListener(this.passwordVerificationField, e -> {
            this.validatePasswordVerificationField(this.passwordVerificationField);
        });

        this.buttonOK.addActionListener(e -> this.onOK());

        this.buttonCancel.addActionListener(e -> this.onCancel());

        // call onCancel() when cross is clicked
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                SignUpDialog.this.onCancel();
            }
        });

        // call onCancel() on ESCAPE
        this.contentPane.registerKeyboardAction(e -> this.onCancel(),
                                                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                                                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }


    private void onOK() {
        // add your code here
        this.dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        this.dispose();
    }
}
