package com.oliver.library.Application.GUI.GUIViews.Rental;

import com.oliver.library.Application.GUI.GUIViews.Templates.GUIView;
import com.oliver.library.Application.GUI.LibraryApplicationGUI;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ReturnDialog extends GUIView {
    private JPanel contentPane;

    private JButton buttonOK;

    private JButton buttonCancel;

    private JTextField idField;

    private LibraryApplicationGUI gui;

    public ReturnDialog(LibraryApplicationGUI gui) {
        this();
        this.gui = gui;
    }

    public ReturnDialog() {
        this.setContentPane(this.contentPane);
        this.setModal(true);
        this.getRootPane()
            .setDefaultButton(this.buttonOK);

        this.setUpListeners();
    }

    public static void main(String[] args) {
        ReturnDialog dialog = new ReturnDialog();
        dialog.pack();
        dialog.setVisible(true);
    }

    public LibraryApplicationGUI getGui() {
        return this.gui;
    }

    // Button and various component listeners
    private void setUpListeners() {
        this.buttonOK.addActionListener(e -> ReturnDialog.this.onOK());

        this.buttonCancel.addActionListener(e -> ReturnDialog.this.onCancel());

        // call onCancel() when cross is clicked
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                ReturnDialog.this.onCancel();
            }
        });

        // call onCancel() on ESCAPE
        this.contentPane.registerKeyboardAction(e -> ReturnDialog.this.onCancel(),
                                                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                                                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        if (this.getGui()
                .returnObject(this.idField.getText())) {
            this.dispose();
        }
    }

    private void onCancel() {
        // add your code here if necessary
        this.dispose();
    }
}
