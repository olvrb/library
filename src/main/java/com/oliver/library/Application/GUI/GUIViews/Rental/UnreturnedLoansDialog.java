package com.oliver.library.Application.GUI.GUIViews.Rental;

import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Entities.User.User;
import com.oliver.library.Application.GUI.GUIViews.Templates.GUIView;
import com.oliver.library.Application.GUI.LibraryApplicationGUI;

import javax.swing.*;

public class UnreturnedLoansDialog extends GUIView {
    private JPanel contentPane;

    private JList<RentalObject> unreturnedLoansList;

    private JTextArea infoArea;

    private DefaultListModel<RentalObject> unreturnedLoansListModel;


    private LibraryApplicationGUI gui;

    public UnreturnedLoansDialog(LibraryApplicationGUI gui) {
        this.gui = gui;

        this.setContentPane(this.contentPane);
        this.setModal(true);

        this.setUpListeners();
        this.setUpUnreturnedLoansList();
    }

    // Button and various component listeners
    private void setUpListeners() {
        this.unreturnedLoansList.addListSelectionListener(e -> {
            this.updateInfoArea();
        });
    }

    private void updateInfoArea() {
        // Get user that rented the object.
        User user = this.getSelectedObject()
                        .getMostRecentRental()
                        .getUser();
        this.infoArea.setText(String.format("Name: %s\nSSN: %s\n", user.getName(), user.getSsn()));
    }

    private RentalObject getSelectedObject() {
        return this.unreturnedLoansList.getSelectedValue();
    }

    // Set up list model and update its values
    private void setUpUnreturnedLoansList() {
        this.unreturnedLoansListModel = new DefaultListModel<>();
        this.setCurrentLoansToModel();
        this.updateCurrentLoans();
    }

    private void setCurrentLoansToModel() {
        this.unreturnedLoansList.setModel(this.unreturnedLoansListModel);
    }

    private void updateCurrentLoans() {
        this.unreturnedLoansListModel.removeAllElements();
        this.unreturnedLoansListModel.addAll(this.gui.getUnreturnedRentalObjects());
    }
}
