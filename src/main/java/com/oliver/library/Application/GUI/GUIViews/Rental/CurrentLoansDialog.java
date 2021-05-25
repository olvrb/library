package com.oliver.library.Application.GUI.GUIViews.Rental;

import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.GUI.GUIViews.Templates.GUIView;
import com.oliver.library.Application.GUI.LibraryApplicationGUI;

import javax.swing.*;

public class CurrentLoansDialog extends GUIView {
    private JPanel contentPane;

    private JButton returnButton;

    private JList<RentalObject> currentLoansList;

    private DefaultListModel<RentalObject> currentLoansListModel;


    private LibraryApplicationGUI gui;

    public CurrentLoansDialog(LibraryApplicationGUI gui) {
        this.gui = gui;

        this.setContentPane(this.contentPane);
        this.setModal(true);

        this.setUpListeners();
        this.setUpCurrentLoansList();
    }

    // Button and various component listeners
    private void setUpListeners() {
        this.returnButton.addActionListener(e -> CurrentLoansDialog.this.onOK());
    }

    private void handleReturn() {
        RentalObject obj = this.currentLoansList.getSelectedValue();
        if (obj != null) {
            this.gui.returnObject(obj.getId());
        } else {
            this.gui.showError("Select object to return.");
        }
        this.updateCurrentLoans();
    }

    // Set up list model and update its values
    private void setUpCurrentLoansList() {
        this.currentLoansListModel = new DefaultListModel<>();
        this.setCurrentLoansToModel();
        this.updateCurrentLoans();
    }

    private void setCurrentLoansToModel() {
        this.currentLoansList.setModel(this.currentLoansListModel);
    }

    private void updateCurrentLoans() {
        this.currentLoansListModel.removeAllElements();
        this.currentLoansListModel.addAll(this.gui.getCurrentUser()
                                                  .getCurrentRentalObjects());
    }

    private void onOK() {
        this.handleReturn();
    }
}
