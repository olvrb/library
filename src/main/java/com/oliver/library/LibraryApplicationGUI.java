package com.oliver.library;

import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Entities.User.User;
import com.oliver.library.Application.Exceptions.InvalidLoanException;
import com.oliver.library.Application.GUIViews.Authentication.SignInDialog;
import com.oliver.library.Application.GUIViews.Authentication.SignUpDialog;
import com.oliver.library.Application.GUIViews.MainView;
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

    public LibraryApplicationGUI(LibraryApplication control) {
        this.control = control;
        initializeUI();
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
        mainFrame.setContentPane(this.mainView.getMainView());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 700);
        this.setCentered();
        // mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void setCentered() {
        Dimension dim = getCenter();

        mainFrame.setLocation(dim.width / 2 - mainFrame.getSize().width / 2,
                              dim.height / 2 - mainFrame.getSize().height / 2);
    }

    public JDialog showSignInDialog() {
        return this.showDialog(new SignInDialog(this));
    }

    public User getCurrentUser() {
        return control.getCurrentUser();
    }

    public void showSignUpDialog() {
        this.showDialog(new SignUpDialog(this));
    }

    // Update gui to match signed in state.
    public void signInOk() {
        mainView.setSignedInState(true);
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
            User user = userService.getAuthenticatedUser(ssn, pw);
            this.control.setCurrentUser(user);
            mainView.updateUserInfo(user);
            return true;
        } catch (AuthenticationException e) {
            this.showError(e);
            return false;
        }
    }

    public void signOut() {
        this.control.setCurrentUser(null);
        mainView.updateUserInfo(null);
        this.mainView.setSignedInState(false);
    }

    public List<RentalObject> search(String searchString) {
        return libraryService.getAvailableRentalObjects(searchString);
    }

    public void loan(RentalObject object) {
        try {
            this.userRentalService.loan(this.getCurrentUser(), object);
            this.quickMessageDialog(String.format("%s rented until %s.", object.getTitle(), object));
        } catch (InvalidLoanException e) {
            this.showError(e);
        }
    }
}
