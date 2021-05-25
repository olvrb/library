package com.oliver.library.Application.GUI.GUIViews.Templates;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GUIView extends JDialog {
    private List<JComponent> specialInput = new ArrayList<>();

    protected void addAllSpecialInput(Collection<JComponent> components) {
        this.specialInput.addAll(components);
    }

    // Generically merge an infinite number of lists to one.
    protected <T> List<T> joinLists(List<T>... args) {
        List<T> finalList = new ArrayList<>();
        for (List<T> l : args) {
            finalList.addAll(l);
        }
        return finalList;
    }

    // Set field border to green or red based on its validity
    protected void markFieldValid(JTextComponent component, boolean valid) {
        this.setFieldBorder(component, valid ? Color.green : Color.RED);
    }

    private void setFieldBorder(JComponent component, Color c) {
        component.setBorder(BorderFactory.createLineBorder(c));
    }

    // Initial setup
    protected void setUpSpecialInput() {
        this.hideSpecialInput();
    }

    protected void setSpecialInputVisible(List<JComponent> components, boolean visible) {
        this.setJComponentsVisible(components, visible);
    }

    protected void hideSpecialInput() {
        this.setSpecialInputVisible(this.specialInput, false);
    }

    // Set multiple components as visible or not
    protected void setJComponentsVisible(List<JComponent> components, boolean visible) {
        for (JComponent c : components) {
            c.setVisible(visible);
        }
        this.pack();
    }
}
