package com.oliver.library.Application.GUIViews;

import com.oliver.library.LibraryApplicationGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView {
    private JPanel mainView;

    private JButton signUpButton;

    private JButton signInButton;

    private JTextField searchField;

    private JList resultsList;

    private JLabel titleLabel;

    private LibraryApplicationGUI gui;

    public MainView(LibraryApplicationGUI gui) {
        this.gui = gui;
        signUpButton.addActionListener(e -> {
            this.gui.showSignUpDialog();
        });

        signInButton.addActionListener(e -> {
            this.gui.showSignInDialog();
        });
    }

    public LibraryApplicationGUI getGui() {
        return gui;
    }

    public JPanel getMainView() {
        return mainView;
    }
}
