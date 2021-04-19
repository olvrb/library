package com.oliver.library.Application.GUIViews;

import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Entities.User.User;
import com.oliver.library.Application.Services.ListenerServices;
import com.oliver.library.LibraryApplicationGUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView {
    private JPanel mainView;

    private JButton signUpButton;

    private JButton signInButton;

    private JTextField searchField;

    private JList<RentalObject> resultsList;

    private DefaultListModel<RentalObject> resultsListModel;

    private JLabel titleLabel;

    private JTextArea currentUserInfo;

    private JButton signOutButton;

    private JButton loanButton;

    private LibraryApplicationGUI gui;

    private List<RentalObject> currentResults;

    public MainView(LibraryApplicationGUI gui) {
        this.gui = gui;
        this.resultsListModel = new DefaultListModel<>();
        this.setSearchResultsToModel();

        this.signOutButton.setVisible(false);
        this.loanButton.setVisible(false);

        signUpButton.addActionListener(e -> {
            this.gui.showSignUpDialog();
        });

        signInButton.addActionListener(e -> {
            this.gui.showSignInDialog();
        });
        signOutButton.addActionListener(e -> {
            this.gui.signOut();
        });

        ListenerServices.addChangeListener(searchField, e -> updateSearchResults(searchField.getText()));

        loanButton.addActionListener(e -> {
            this.loan();
        });
    }

    private void updateSearchResults(String searchString) {
        this.resultsListModel.removeAllElements();

        System.out.printf("Text updated: %s\n", searchString);

        this.currentResults = gui.search(searchString);

        this.resultsListModel.addAll(currentResults);
    }

    private void loan() {
        this.gui.loan(this.resultsList.getSelectedValue());
    }

    private void setSearchResultsToModel() {
        this.resultsList.setModel(this.resultsListModel);
    }

    public LibraryApplicationGUI getGui() {
        return gui;
    }

    public JPanel getMainView() {
        return mainView;
    }

    public void updateUserInfo(User user) {
        if (user == null) this.currentUserInfo.setText("");
        else this.currentUserInfo.setText(String.format("Logged in as:\nName: %s\nSSN: %s",
                                                        user.getName(),
                                                        user.getSsn()));
    }

    public void setSignedInState(boolean signedIn) {
        this.signUpButton.setVisible(!signedIn);
        this.signInButton.setVisible(!signedIn);
        this.signOutButton.setVisible(signedIn);
        this.loanButton.setVisible(signedIn);
    }
}
