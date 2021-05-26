package com.oliver.library.Application.GUI.GUIViews;

import com.oliver.library.Application.Entities.Inventory.Book;
import com.oliver.library.Application.Entities.Inventory.Film;
import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Entities.User.User;
import com.oliver.library.Application.GUI.GUIViews.Templates.GUIView;
import com.oliver.library.Application.Services.ListenerService;
import com.oliver.library.Application.GUI.LibraryApplicationGUI;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

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

    private JButton returnButton;

    private JButton editObjectButton;

    private JComboBox searchProperty;

    private JTextArea infoArea;

    private JButton currentLoansButton;

    private JButton viewUnreturnedObjectsButton;

    private LibraryApplicationGUI gui;

    private List<RentalObject> currentResults;

    private List<JComponent> signedInComponents = Arrays.asList(new JComponent[] {
            this.loanButton,
            this.signOutButton,
            this.currentLoansButton
    });

    private List<JComponent> adminComponents = Arrays.asList(new JComponent[] {
            this.viewUnreturnedObjectsButton,
            this.removeObjectButton,
            this.addObjectButton,
            this.editObjectButton
    });

    private List<JComponent> signedOutComponents = Arrays.asList(new JComponent[] {
            this.signInButton,
            this.signUpButton
    });

    public MainView(LibraryApplicationGUI gui) {
        this.gui = gui;

        this.setUpSpecialInput();
        this.setUpResultsList();
        this.setUpListeners();
    }

    public void init() {
        this.updateSearchResults();
    }

    // Only show signedUpComponents, and none of the other special inputs.
    @Override
    protected void setUpSpecialInput() {
        this.setJComponentsVisible(this.adminComponents, false);
        this.setJComponentsVisible(this.signedInComponents, false);
        this.addAllSpecialInput(this.joinLists(this.signedInComponents,
                                               this.adminComponents,
                                               this.signedOutComponents));
    }

    // Show and hide buttons based on signed in state: signedIn, isAdmin
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


    // Button and various component listeners
    private void setUpListeners() {
        this.viewUnreturnedObjectsButton.addActionListener(e -> {
            this.getGui()
                .showUnreturnedLoansDialog();
        });

        this.currentLoansButton.addActionListener(e -> {
            this.getGui()
                .showCurrentLoansDialog();
            this.updateSearchResults();
        });

        this.editObjectButton.addActionListener(e -> {
            this.editObject();
            this.updateSearchResults();
        });

        this.returnButton.addActionListener(e -> {
            this.getGui()
                .showReturnDialog();
            this.updateSearchResults();
        });

        this.addObjectButton.addActionListener(e -> {
            this.getGui()
                .showAddRentalObjectDialog();
            this.updateSearchResults();
        });
        this.signUpButton.addActionListener(e -> {
            this.getGui()
                .showSignUpDialog();
            this.updateSearchResults();
        });

        this.signInButton.addActionListener(e -> {
            this.getGui()
                .showSignInDialog();
            this.updateSearchResults();
        });
        this.signOutButton.addActionListener(e -> {
            this.getGui()
                .signOut();
            this.updateSearchResults();
        });

        ListenerService.addChangeListener(this.searchField, e -> this.updateSearchResults());

        this.loanButton.addActionListener(e -> {
            this.loanObject();
            this.updateSearchResults();
        });

        this.removeObjectButton.addActionListener(e -> {
            this.removeObject();
            this.updateSearchResults();
        });

        this.resultsList.addListSelectionListener(e -> {
            this.updateInfoArea(this.resultsList.getSelectedValue());
        });
    }

    // Update info area with selected objects information
    private void updateInfoArea(RentalObject object) {
        if (object != null) {
            // First set information relevant for all object types
            this.infoArea.setText(String.format(
                    "Type: %s\nTitle: %s\nGenre: %s\nPhysical Location: %s\nDescription: %s\nAuthor: %s\n",
                    // Bad practice but works for now.
                    object.getClass()
                          .getSimpleName(),
                    object.getTitle(),
                    object.getGenre(),
                    object.getPhysicalLocation(),
                    object.getDescription(),
                    object.getAuthor()));

            // Then add on specific information for Book or Film objects
            if (object instanceof Book) {
                this.infoArea.append(String.format(
                        "Publication year: %s\nISBN: %s\nReference Literature: %s\nCourse Literature: %s\n",
                        ((Book)object).getPublicationYear(),
                        ((Book)object).getISBN(),
                        ((Book)object).isReference(),
                        ((Book)object).isCourseLiterature()));
            } else if (object instanceof Film) {
                this.infoArea.append(String.format("Age Limit: %s\nProduction Country: %s\n",
                                                   ((Film)object).getAgeLimit(),
                                                   ((Film)object).getProductionCountry()));
            }
        }
    }

    // Edit object button
    private void editObject() {
        // Get selected object and show edit dialog.
        RentalObject obj = this.resultsList.getSelectedValue();
        if (obj != null) {
            this.getGui()
                .edit(obj);
        } else {
            this.getGui()
                .showError("Select object to edit.");
        }
    }


    private void removeObject() {
        RentalObject obj = this.resultsList.getSelectedValue();

        if (obj != null) {
            // Remove selected object.
            this.getGui()
                .removeRentalObject(this.resultsList.getSelectedValue());
            this.updateSearchResults();
        } else {
            this.getGui()
                .showError("Select object to remove.");
        }
    }

    // Set up list model
    private void setUpResultsList() {
        this.resultsListModel = new DefaultListModel<>();
        this.setSearchResultsToModel();
    }

    // Remove current results and refetch results based on search string
    private void updateSearchResults() {
        this.resultsListModel.removeAllElements();
        this.currentResults = this.getGui()
                                  .searchBy(this.searchField.getText(),
                                            this.searchProperty.getSelectedItem()
                                                               .toString()
                                                               .toLowerCase());
        this.resultsListModel.addAll(this.currentResults);
    }

    private void loanObject() {
        // Get selected object. If it's already rented, we reserve it. Else we can freely loan it.
        RentalObject obj = this.resultsList.getSelectedValue();
        if (obj != null) {
            if (obj.isRented()) {
                this.getGui()
                    .reserve(obj);
            } else {
                this.getGui()
                    .loan(obj);
            }
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

    // Update current user info.
    public void updateUserInfo(User user) {
        if (user == null) this.currentUserInfo.setText("");
        else this.currentUserInfo.setText(String.format("Logged in as:\nName: %s\nSSN: %s",
                                                        user.getName(),
                                                        user.getSsn()));
    }
}
