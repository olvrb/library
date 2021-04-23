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

    private JButton addObjectButton;

    private JButton removeObjectButton;

    private LibraryApplicationGUI gui;

    private List<RentalObject> currentResults;

    public MainView(LibraryApplicationGUI gui) {
        this.gui = gui;


        this.setUpResultsList();
        this.setUpSpecialUI();
        this.setUpListeners();

    }

    private void setUpListeners() {
        this.signUpButton.addActionListener(e -> {
            this.getGui()
                .showSignUpDialog();
        });

        this.signInButton.addActionListener(e -> {
            this.getGui()
                .showSignInDialog();
        });
        this.signOutButton.addActionListener(e -> {
            this.getGui()
                .signOut();
        });

        ListenerServices.addChangeListener(this.searchField, e -> this.updateSearchResults());

        this.loanButton.addActionListener(e -> {
            this.loan();
        });

        this.removeObjectButton.addActionListener(e -> {
            this.removeObject();
        });
    }

    private void removeObject() {
        this.getGui()
            .removeRentalObject(this.resultsList.getSelectedValue());
        this.updateSearchResults();
    }

    private void setUpResultsList() {
        this.resultsListModel = new DefaultListModel<>();
        this.setSearchResultsToModel();
    }

    private void setUpSpecialUI() {
        this.signOutButton.setVisible(false);
        this.loanButton.setVisible(false);
        this.addObjectButton.setVisible(false);
        this.removeObjectButton.setVisible(false);
    }

    private void updateSearchResults() {
        this.resultsListModel.removeAllElements();
        this.currentResults = this.getGui()
                                  .search(this.searchField.getText());

        this.resultsListModel.addAll(this.currentResults);
    }

    private void loan() {
        this.getGui()
            .loan(this.resultsList.getSelectedValue());
    }

    private void setSearchResultsToModel() {
        this.resultsList.setModel(this.resultsListModel);
    }

    public LibraryApplicationGUI getGui() {
        return this.gui;
    }

    public JPanel getMainView() {
        return this.mainView;
    }

    public void updateUserInfo(User user) {
        if (user == null) this.currentUserInfo.setText("");
        else this.currentUserInfo.setText(String.format("Logged in as:\nName: %s\nSSN: %s",
                                                        user.getName(),
                                                        user.getSsn()));
    }

    public void setSignedInState(boolean signedIn, boolean isAdmin) {
        this.signUpButton.setVisible(!signedIn);
        this.signInButton.setVisible(!signedIn);
        this.signOutButton.setVisible(signedIn);
        this.loanButton.setVisible(signedIn);

        this.removeObjectButton.setVisible(signedIn && isAdmin);
        this.addObjectButton.setVisible(signedIn && isAdmin);
    }
}
