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

public class MainView extends GUIView {
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

    private List<JComponent> signedInComponents = Arrays.asList(new JComponent[] {
            this.loanButton,
            this.signOutButton
    });

    private List<JComponent> adminComponents = Arrays.asList(new JComponent[] {
            this.removeObjectButton,
            this.addObjectButton
    });

    private List<JComponent> signedOutComponents = Arrays.asList(new JComponent[] {
            this.signInButton,
            this.signUpButton
    });

    public MainView(LibraryApplicationGUI gui) {
        this.gui = gui;

        this.setUpSpecialInput();
        this.setUpResultsList();
        this.setUpSpecialUI();
        this.setUpListeners();
    }

    @Override
    protected void setUpSpecialInput() {
        this.setJComponentsVisible(this.adminComponents, false);
        this.setJComponentsVisible(this.signedInComponents, false);
        this.specialInput.addAll(this.joinLists(this.signedInComponents,
                                                this.adminComponents,
                                                this.signedOutComponents));
    }

    public void setSignedInState(boolean signedIn, boolean isAdmin) {
        if (signedIn) {
            this.setJComponentsVisible(this.signedInComponents, true);
            this.setJComponentsVisible(this.signedOutComponents, false);
            if (isAdmin) this.setJComponentsVisible(this.adminComponents, true);
        } else {
            this.setJComponentsVisible(this.signedInComponents, false);
            this.setJComponentsVisible(this.adminComponents, false);
            this.setJComponentsVisible(this.signedOutComponents, true);
        }
    }

    private void setUpListeners() {
        this.addObjectButton.addActionListener(e -> {
            this.getGui()
                .showAddRentalObjectDialog();
        });
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

    }

    private void updateSearchResults() {
        this.resultsListModel.removeAllElements();
        this.currentResults = this.getGui()
                                  .search(this.searchField.getText());

        this.resultsListModel.addAll(this.currentResults);
    }

    private void loan() {
        RentalObject obj = this.resultsList.getSelectedValue();
        if (obj != null) {
            this.getGui()
                .loan(obj);
        } else {
            this.getGui()
                .showError("Select object to loan.");
        }
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


}
