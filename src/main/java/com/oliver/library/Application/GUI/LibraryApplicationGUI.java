package com.oliver.library.Application.GUI;

import com.oliver.library.Application.Entities.Abstract.Rental;
import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.Entities.User.User;
import com.oliver.library.Application.Exceptions.InvalidLoanException;
import com.oliver.library.Application.Exceptions.RentalObjectRentedException;
import com.oliver.library.Application.GUI.GUIViews.Authentication.SignInDialog;
import com.oliver.library.Application.GUI.GUIViews.Authentication.SignUpDialog;
import com.oliver.library.Application.GUI.GUIViews.EditRentalObjectDialog;
import com.oliver.library.Application.GUI.GUIViews.MainView;
import com.oliver.library.Application.GUI.GUIViews.Rental.CurrentLoansDialog;
import com.oliver.library.Application.GUI.GUIViews.Rental.ReturnDialog;
import com.oliver.library.Application.GUI.GUIViews.Rental.UnreturnedLoansDialog;
import com.oliver.library.Application.Services.DataServices.AdminService;
import com.oliver.library.Application.Services.DataServices.LibraryService;
import com.oliver.library.Application.Services.DataServices.UserRentalService;
import com.oliver.library.Application.Services.DataServices.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.naming.AuthenticationException;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LibraryApplicationGUI {

    private MainView mainView;

    private JFrame mainFrame;

    private User currentUser;

    @Autowired
    private UserService userService;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private UserRentalService userRentalService;

    @Autowired
    private AdminService adminService;


    public LibraryApplicationGUI() {
        this.initializeUI();
    }

    public void showError(Exception error) {
        this.showError(error.getMessage());
    }

    public void showError(String s) {
        this.quickMessageDialog(s);
    }

    public void quickMessageDialog(String s) {
        JOptionPane.showMessageDialog(this.mainFrame, s);
    }

    private void initializeUI() {
        Dimension size = this.getWindowSize();

        this.mainView = new MainView(this);
        this.mainFrame = new JFrame("Library");
        this.mainFrame.setContentPane(this.mainView.getMainView());
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize((int)size.getWidth(), (int)size.getHeight());
        this.setCentered();
        // this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }

    @PostConstruct
    private void init() {
        this.mainView.init();
    }

    private void setCentered() {
        Dimension dim = this.getScreenDimension();

        this.mainFrame.setLocation(dim.width / 2 - this.mainFrame.getSize().width / 2,
                                   dim.height / 2 - this.mainFrame.getSize().height / 2);
    }

    public JDialog showAddRentalObjectDialog() {
        return this.showDialog(new EditRentalObjectDialog(this));
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void showSignUpDialog() {
        this.showDialog(new SignUpDialog(this));
    }

    public void showReturnDialog() {
        this.showDialog(new ReturnDialog(this));
    }

    public JDialog showSignInDialog() {
        return this.showDialog(new SignInDialog(this));
    }

    public void showCurrentLoansDialog() {
        this.showDialog(new CurrentLoansDialog(this));
    }

    public void showUnreturnedLoansDialog() {
        this.showDialog(new UnreturnedLoansDialog(this));
    }


    // Update gui to match signed in state.
    public void signInOk(User user) {
        this.setCurrentUser(user);
        this.mainView.setSignedInState(true, user.isAdmin());
        this.mainView.updateUserInfo(user);
    }


    // Standard procedure for showing a dialog.
    private JDialog showDialog(JDialog dialog) {
        dialog.pack();
        dialog.setLocationRelativeTo(this.mainFrame);
        dialog.setVisible(true);
        return dialog;
    }

    private Dimension getScreenDimension() {
        return Toolkit.getDefaultToolkit()
                      .getScreenSize();
    }

    // Calculate width and height to cover about half of the screen. 0.5 is just an arbitrary number that I found works well.
    private Dimension getWindowSize() {
        Dimension screen = this.getScreenDimension();
        double baseRatio = 0.5;
        return new Dimension((int)(screen.getWidth() * baseRatio), (int)(screen.getHeight() * baseRatio));
    }


    public boolean authenticateUser(String ssn, String pw) {
        // Try to auth user with provided credentials. If fails, show error
        try {
            User user = this.userService.getAuthenticatedUser(ssn, pw);
            this.signInOk(user);
            return true;
        } catch (AuthenticationException e) {
            this.showError(e);
            return false;
        }
    }

    public boolean createUser(User u) {
        // Catch duplicate SSN exception. Not very safe, allows for easier brute force but 🤷‍
        try {
            this.userService.createUser(u);
        } catch (DataIntegrityViolationException e) {
            this.showError("User is already registered.");
            return false;
        }
        return true;
    }

    public boolean saveObject(RentalObject obj) {
        this.libraryService.save(obj);
        return true;
    }

    // Null out current user, update user info, and set correct state.
    public void signOut() {
        this.setCurrentUser(null);
        this.mainView.updateUserInfo(null);
        this.mainView.setSignedInState(false, false);
    }

    public List<RentalObject> searchBy(String searchString, String property) {
        // Get RentalObject by respective properties.
        // Default case is empty list.
        switch (property) {
            case "title": {
                return this.libraryService.getRentalObjectsByTitle(searchString);
            }
            case "author": {
                return this.libraryService.getRentalObjectsByAuthor(searchString);
            }
            case "isbn": {
                return this.libraryService.getRentalObjectsByIsbn(searchString);
            }
            case "genre": {
                return this.libraryService.getRentalObjectsByGenre(searchString);
            }
            default: {
                return new ArrayList<>();
            }
        }
    }

    // Old search method
    @Deprecated
    public List<RentalObject> search(String searchString) {
        return this.libraryService.getRentalObjectsByTitle(searchString);
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

    // Can edit information if is signed in and user is admin.
    public boolean canEdit() {
        return this.signedIn() && this.getCurrentUser()
                                      .isAdmin();
    }

    public boolean signedIn() {
        return this.getCurrentUser() != null;
    }


    // TODO: Merge loan and reserve into loanOrReserve.
    public void loan(RentalObject object) {
        // TODO: Format better?

        // Try and loan object and show confirmation (receipt). Else show error.
        try {
            Rental r = this.userRentalService.loan(this.getCurrentUser(), object);
            this.quickMessageDialog(String.format("%s (id: %s) rented from\n%s \nuntil \n%s.",
                                                  object.getTitle(),
                                                  object.getId(),
                                                  r.getStartDate(),
                                                  r.getReturnDate()
                                                   .toString()));

            this.refreshUser();
        } catch (InvalidLoanException e) {
            this.showError(e);
        }
    }

    public void reserve(RentalObject object) {
        // Try and reserve object and show confirmation (receipt). Else show error.
        try {
            Rental r = this.userRentalService.reserve(this.getCurrentUser(), object, object.getNextRentDate());
            this.quickMessageDialog(String.format("%s (id: %s) reserved from\n%s \nuntil \n%s.",
                                                  object.getTitle(),
                                                  object.getId(),
                                                  r.getStartDate(),
                                                  r.getReturnDate()
                                                   .toString()));
            this.refreshUser();
        } catch (InvalidLoanException e) {
            this.showError(e);
        }
    }

    public boolean returnObject(RentalObject obj) {
        // If no rental is found, show error.
        try {
            this.userRentalService.markRentalStatusForRentalObject(obj, true);
            this.quickMessageDialog(String.format("Returned object %s (%s)", obj.getTitle(), obj.getId()));
            this.refreshUser();
        } catch (NotFoundException e) {
            this.showError(e);
            return false;
        }
        return true;
    }

    // Method overload.
    public boolean returnObject(String id) {
        return this.returnObject(this.userRentalService.getRentalObjectById(id));
    }


    public void edit(RentalObject obj) {
        this.showDialog(new EditRentalObjectDialog(this, obj));
    }

    // To be called after changing user rentals in some way, as it is not updated automatically in the currentUser object.
    public void refreshUser() {
        this.userService.refreshUserRentals(this.getCurrentUser());
    }

    public List<RentalObject> getUnreturnedRentalObjects() {
        return this.libraryService.getUnreturnedRentalObjects();
    }
}
