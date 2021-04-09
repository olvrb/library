package com.oliver.library;

import com.oliver.library.Application.Entities.User.User;
import com.oliver.library.Application.GUIViews.Authentication.SignInDialog;
import com.oliver.library.Application.GUIViews.Authentication.SignUpDialog;
import com.oliver.library.Application.GUIViews.MainView;
import com.oliver.library.Application.Services.UserAuthenticationService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.naming.AuthenticationException;
import javax.swing.*;
import java.awt.*;

@Controller
public class LibraryApplicationGUI {

    private MainView mainView;

    private JFrame mainFrame;

    private LibraryApplication control;

    @Autowired
    private UserAuthenticationService userService;

    public LibraryApplicationGUI(LibraryApplication control) {
        this.control = control;
        initializeUI();
    }

    public void showError(Exception error) {
        JOptionPane.showMessageDialog(this.mainFrame, error.getMessage());
    }

    public void setUserInfo(User user) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Not implemented");
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

    public void showSignInDialog() {
        this.showDialog(new SignInDialog(this));
    }

    public void showSignUpDialog() {
        this.showDialog(new SignUpDialog(this));
    }

    private void showDialog(JDialog dialog) {
        dialog.pack();
        dialog.setLocationByPlatform(true);
        dialog.setLocationRelativeTo(this.mainFrame);
        dialog.setVisible(true);
    }

    private Dimension getCenter() {
        return Toolkit.getDefaultToolkit()
                      .getScreenSize();
    }

    public boolean authenticateUser(String ssn, String pw) {
        try {
            this.control.setCurrentUser(userService.getAuthenticatedUser(ssn, pw));
        } catch (AuthenticationException e) {
            this.showError(e);
        }

        return false;
    }
}
