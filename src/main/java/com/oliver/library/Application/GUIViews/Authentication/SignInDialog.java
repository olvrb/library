package com.oliver.library.Application.GUIViews.Authentication;

import com.oliver.library.LibraryApplicationGUI;

import javax.swing.*;
import java.awt.event.*;

public class SignInDialog extends JDialog {
    private JPanel contentPane;

    private JButton buttonOK;

    private JButton buttonCancel;

    private JTextField ssnField;

    private JPasswordField passwordField;

    private LibraryApplicationGUI gui;

    public SignInDialog(LibraryApplicationGUI gui) {
        this.gui = gui;
        this.setContentPane(this.contentPane);
        this.setModal(true);
        this.getRootPane()
            .setDefaultButton(this.buttonOK);

        this.setUpListeners();
    }

    private void setUpListeners() {
        this.buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignInDialog.this.onOK();
            }
        });

        this.buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignInDialog.this.onCancel();
            }
        });

        // call onCancel() when cross is clicked
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                SignInDialog.this.onCancel();
            }
        });

        // call onCancel() on ESCAPE
        this.contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignInDialog.this.onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        if (this.gui.authenticateUser(this.ssnField.getText(), new String(this.passwordField.getPassword()))) {
            this.dispose();
        } else {
            this.passwordField.setText("");
        }
        // add your code here
    }

    private void onCancel() {
        // add your code here if necessary
        this.dispose();
    }
}
