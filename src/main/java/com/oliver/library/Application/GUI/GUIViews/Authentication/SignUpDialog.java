package com.oliver.library.Application.GUI.GUIViews.Authentication;

import com.oliver.library.Application.Entities.User.GeneralUser;
import com.oliver.library.Application.Entities.User.User;
import com.oliver.library.Application.GUI.GUIViews.Templates.GUIView;
import com.oliver.library.Application.Services.ListenerService;
import com.oliver.library.Application.GUI.LibraryApplicationGUI;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class SignUpDialog extends GUIView {
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
        // Validate password, then mark corresponding field as valid.
        boolean valid = User.validatePassword(new String(this.passwordField.getPassword()));
        this.markFieldValid(this.passwordField, valid);
        return valid;
    }


    private boolean validatePasswordVerificationField() {
        // If both field are valid, and the main password field is valid, mark border accordingly.
        boolean valid = this.validatePasswordField() && Arrays.equals(this.passwordField.getPassword(),
                                                                      this.passwordVerificationField.getPassword());
        this.markFieldValid(this.passwordVerificationField, valid);
        return valid;
    }

    private boolean validateSsn() {
        // Validate ssn, then mark corresponding field as valid.
        boolean valid = User.validateSsn(this.ssnField.getText());
        this.markFieldValid(this.ssnField, valid);
        return valid;
    }


    // Button and various component listeners
    private void setUpListeners() {
        ListenerService.addChangeListener(this.passwordField, e -> {
            this.validatePasswordField();
        });
        ListenerService.addChangeListener(this.passwordVerificationField, e -> {
            this.validatePasswordVerificationField();
        });
        ListenerService.addChangeListener(this.ssnField, e -> {
            this.validateSsn();
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
        // Create user if fields are valid.
        // Close window if create user is successful.
        // This signup method can only create general users.
        // If a user wants student, employee, or researcher privileges, they will theoretically need to be changed by employees.
        if (this.validatePasswordVerificationField() && this.validateSsn()) {
            if (this.gui.createUser(new GeneralUser(this.nameField.getText(),
                                                    this.ssnField.getText(),
                                                    new String(this.passwordField.getPassword())))) {
                this.dispose();
            }
        }
    }

    private void onCancel() {
        this.dispose();
    }
}
