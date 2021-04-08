package com.oliver.library.Application.GUIViews;

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

    public MainView() {
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("clicked");
            }
        });

    }

    public JPanel getMainView() {
        return mainView;
    }
}
