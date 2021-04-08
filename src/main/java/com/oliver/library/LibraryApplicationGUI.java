package com.oliver.library;

import com.oliver.library.Application.Entities.User.User;
import com.oliver.library.Application.GUIViews.MainView;
import jdk.jshell.spi.ExecutionControl;

import javax.swing.*;
import java.awt.*;

public class LibraryApplicationGUI {

    private MainView mainView;

    private JFrame mainFrame;

    private LibraryApplication control;

    public LibraryApplicationGUI(LibraryApplication control) {
        this.control = control;
        initializeUI();
    }

    public void setUserInfo(User user) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Not implemented");
    }

    private void initializeUI() {
        this.mainView = new MainView();
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

    private Dimension getCenter() {
        return Toolkit.getDefaultToolkit()
                      .getScreenSize();
    }
}
