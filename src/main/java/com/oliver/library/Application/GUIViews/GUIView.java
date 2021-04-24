package com.oliver.library.Application.GUIViews;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUIView extends JDialog {
    protected List<JComponent> specialInput = new ArrayList<>();

    // Generically merge an infinite number of lists to one.
    protected <T> List<T> joinLists(List<T>... args) {
        List<T> finalList = new ArrayList<>();
        for (List<T> l : args) {
            finalList.addAll(l);
        }
        return finalList;
    }

    protected void markFieldValid(JTextComponent component, boolean valid) {
        this.setFieldBorder(component, valid ? Color.green : Color.RED);
    }

    protected void setFieldBorder(JComponent component, Color c) {
        component.setBorder(BorderFactory.createLineBorder(c));
    }

    protected void setUpSpecialInput() {
        this.hideSpecialInput();
    }

    protected void setSpecialInputVisible(List<JComponent> components, boolean visible) {
        this.setJComponentsVisible(components, visible);
    }

    protected void hideSpecialInput() {
        this.setSpecialInputVisible(this.specialInput, false);
    }

    protected void setJComponentsVisible(List<JComponent> components, boolean visible) {
        for (JComponent c : components) {
            c.setVisible(visible);
        }
        this.pack();
    }
}