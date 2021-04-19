package com.oliver.library.Application.GUIViews.Authentication;

import com.oliver.library.Application.GUIViews.MainView;
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
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        if (this.gui.authenticateUser(this.ssnField.getText(), new String(this.passwordField.getPassword()))) {
            this.gui.signInOk();
            dispose();
        } else {
            this.passwordField.setText("");
        }
        // add your code here
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
