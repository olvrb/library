package com.oliver.library;

import com.oliver.library.Application.Entities.Abstract.Rental;
import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Entities.User.User;
import com.oliver.library.Application.Exceptions.InvalidLoanException;
import com.oliver.library.Application.Exceptions.RentalObjectRentedException;
import com.oliver.library.Application.GUIViews.Authentication.SignInDialog;
import com.oliver.library.Application.GUIViews.Authentication.SignUpDialog;
import com.oliver.library.Application.GUIViews.MainView;
import com.oliver.library.Application.Services.AdminService;
import com.oliver.library.Application.Services.LibraryService;
import com.oliver.library.Application.Services.UserAuthenticationService;
import com.oliver.library.Application.Services.UserRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.naming.AuthenticationException;
import javax.swing.*;
import java.awt.*;
import java.util.List;

@Controller
public class LibraryApplicationGUI {

    private MainView mainView;

    private JFrame mainFrame;

    private LibraryApplication control;

    @Autowired
    private UserAuthenticationService userService;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private UserRentalService userRentalService;

    @Autowired
    private AdminService adminService;

    public LibraryApplicationGUI(LibraryApplication control) {
        this.control = control;
        this.initializeUI();
    }

    public void showError(Exception error) {
        this.quickMessageDialog(error.getMessage());
    }

    public void quickMessageDialog(String s) {
        JOptionPane.showMessageDialog(this.mainFrame, s);
    }


    private void initializeUI() {
        this.mainView = new MainView(this);
        this.mainFrame = new JFrame("Library");
        this.mainFrame.setContentPane(this.mainView.getMainView());
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(500, 700);
        this.setCentered();
        // mainFrame.pack();
        this.mainFrame.setVisible(true);
    }

    private void setCentered() {
        Dimension dim = this.getCenter();

        this.mainFrame.setLocation(dim.width / 2 - this.mainFrame.getSize().width / 2,
                                   dim.height / 2 - this.mainFrame.getSize().height / 2);
    }

    public JDialog showSignInDialog() {
        return this.showDialog(new SignInDialog(this));
    }

    public User getCurrentUser() {
        return this.control.getCurrentUser();
    }

    public void showSignUpDialog() {
        this.showDialog(new SignUpDialog(this));
    }

    // Update gui to match signed in state.
    public void signInOk(User user) {
        this.mainView.setSignedInState(true, user.isAdmin());
    }


    private JDialog showDialog(JDialog dialog) {
        dialog.pack();
        dialog.setLocationByPlatform(true);
        dialog.setLocationRelativeTo(this.mainFrame);
        dialog.setVisible(true);
        return dialog;
    }

    private Dimension getCenter() {
        return Toolkit.getDefaultToolkit()
                      .getScreenSize();
    }

    public boolean authenticateUser(String ssn, String pw) {
        try {
            User user = this.userService.getAuthenticatedUser(ssn, pw);
            this.control.setCurrentUser(user);
            this.mainView.updateUserInfo(user);
            this.signInOk(user);
            return true;
        } catch (AuthenticationException e) {
            this.showError(e);
            return false;
        }
    }

    public boolean saveObject(RentalObject obj) {
        this.libraryService.save(obj);
    }

    public void signOut() {
        this.control.setCurrentUser(null);
        this.mainView.updateUserInfo(null);
        this.mainView.setSignedInState(false, false);
    }

    public List<RentalObject> search(String searchString) {
        return this.libraryService.getAvailableRentalObjects(searchString);
    }

    // Remove object if user is logged in and is admin.
    public void removeRentalObject(RentalObject object) {
        if (this.canEdit()) {
            try {
                this.adminService.removeRentalObject(object);
            } catch (RentalObjectRentedException e) {
                this.showError(e);
            }
        }
    }

    public boolean canEdit() {
        return this.control.signedIn() && this.control.getCurrentUser()
                                                      .isAdmin();
    }

    public void loan(RentalObject object) {
        // TODO: Format better?
        try {
            Rental r = this.userRentalService.loan(this.getCurrentUser(), object);
            this.quickMessageDialog(String.format("%s (id: %s) rented from %s until %s.",
                                                  object.getTitle(),
                                                  object.getId(),
                                                  r.getStartDate(),
                                                  r.getReturnDate()
                                                   .toString()));
        } catch (InvalidLoanException e) {
            this.showError(e);
        }
    }
}
