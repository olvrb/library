package com.oliver.library.Application.GUIViews;

import com.oliver.library.Application.Entities.Inventory.Book;
import com.oliver.library.Application.Entities.Inventory.Film;
import com.oliver.library.Application.Entities.Inventory.Journal;
import com.oliver.library.Application.Services.ListenerServices;
import com.oliver.library.LibraryApplicationGUI;
import net.bytebuddy.asm.Advice;

import javax.swing.*;
import java.awt.event.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddRentalObjectDialog extends BaseJDialog {
    private JPanel contentPane;

    private JButton buttonOK;

    private JButton buttonCancel;

    private JTextField titleField;

    private JTextField genreField;

    private JTextField physicalLocationField;

    private JTextField descriptionField;

    private JTextField publicationYearField;

    private JTextField isbnField;

    private JTextField ageLimitField;

    private JCheckBox referenceBox;

    private JCheckBox courseLiteratureBox;

    private JTextField productionCountryField;

    private JComboBox<String> typeList;

    private JTextField authorField;

    private JLabel titleLabel;

    private JLabel genreLabel;

    private JLabel physicalLocationLabel;

    private JLabel descriptionLabel;

    private JLabel publicationYearLabel;

    private JLabel isbnLabel;

    private JLabel ageLimitLabel;

    private JLabel authorLabel;

    private JLabel productionCountryLabel;

    private List<JComponent> bookComponents = Arrays.asList(new JComponent[] {
            this.isbnField,
            this.isbnLabel,
            this.publicationYearField,
            this.publicationYearLabel,
            this.referenceBox,
            this.courseLiteratureBox
    });

    private List<JComponent> filmComponents = Arrays.asList(new JComponent[] {
            this.productionCountryField,
            this.productionCountryLabel,
            this.ageLimitField,
            this.ageLimitLabel
    });


    private LibraryApplicationGUI gui;

    public AddRentalObjectDialog(LibraryApplicationGUI gui) {
        this();
        this.gui = gui;
    }

    public AddRentalObjectDialog() {
        this.setContentPane(this.contentPane);
        this.setModal(true);
        this.getRootPane()
            .setDefaultButton(this.buttonOK);

        this.setUpTypeList();
        this.setUpListeners();
        this.setUpSpecialInput();

    }

    public static void main(String[] args) {
        JDialog dialog = new AddRentalObjectDialog();
        dialog.pack();
        dialog.setLocationByPlatform(true);
        dialog.setVisible(true);
    }

    @Override
    protected void setUpSpecialInput() {
        this.addAllSpecialInput(this.joinLists(this.bookComponents, this.filmComponents));
        super.setUpSpecialInput();
    }

    private void updateSpecialInput() {
        this.hideSpecialInput();
        // Since we're dealing with class *names*, we can't use genericism or polymorphism :(
        // I don't believe it's possible to store classes in a JComboBox :(s
        // Journals also don't have any special fields.
        switch ((String)this.typeList.getSelectedItem()) {
            case "Book": {
                this.setJComponentsVisible(this.bookComponents, true);
                break;
            }
            case "Film": {
                this.setJComponentsVisible(this.filmComponents, true);
                break;
            }
            default: {
                break;
            }
        }
    }

    private void setUpTypeList() {
        this.typeList.insertItemAt("", 0);
        this.typeList.setSelectedIndex(0);
    }

    private boolean validateYear() {
        try {
            this.getYearValue();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void validateYearField() {
        // If field contains number and is a valid Java Year, mark field as valid. Else invalid.

        this.markFieldValid(this.publicationYearField, this.validateYear());
    }

    private Year getYearValue() {
        return Year.of(Integer.parseInt(this.publicationYearField.getText()));
    }


    private void setUpListeners() {
        ListenerServices.addChangeListener(this.publicationYearField, e -> {
            this.validateYearField();
        });
        this.typeList.addActionListener(e -> {
            this.updateSpecialInput();
        });

        this.buttonOK.addActionListener(e -> AddRentalObjectDialog.this.onOK());

        this.buttonCancel.addActionListener(e -> AddRentalObjectDialog.this.onCancel());

        // call onCancel() when cross is clicked
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                AddRentalObjectDialog.this.onCancel();
            }
        });

        // call onCancel() on ESCAPE
        this.contentPane.registerKeyboardAction(e -> AddRentalObjectDialog.this.onCancel(),
                                                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                                                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        boolean success = true;
        // Since we're dealing with class *names*, we can't use genericism or polymorphism :(
        switch ((String)this.typeList.getSelectedItem()) {
            case "Book": {
                // Only field which requires validation is year. We don't care if the other fields are empty 🤷‍
                if (this.validateYear()) {
                    Book book = new Book(this.titleField.getText(),
                                         this.genreField.getText(),
                                         this.physicalLocationField.getText(),
                                         this.descriptionField.getText(),
                                         // TODO: catch error
                                         this.getYearValue(),
                                         this.isbnField.getText(),
                                         this.authorField.getText(),
                                         this.referenceBox.isSelected(),
                                         this.courseLiteratureBox.isSelected());
                    this.gui.saveObject(book);
                } else {
                    success = false;
                }
                break;
            }
            case "Film": {
                Film film = new Film(this.titleField.getText(),
                                     this.genreField.getText(),
                                     this.physicalLocationField.getText(),
                                     this.descriptionField.getText(),
                                     this.authorField.getText(),
                                     this.ageLimitField.getText(),
                                     this.productionCountryField.getText());
                this.gui.saveObject(film);
                break;
            }
            case "Journal": {
                Journal journal = new Journal(this.titleField.getText(),
                                              this.genreField.getText(),
                                              this.physicalLocationField.getText(),
                                              this.descriptionField.getText(),
                                              this.authorField.getText());
                this.gui.saveObject(journal);
                break;
            }
            default: {
                break;
            }
        }
        if (success) {
            this.dispose();
        } else {
            this.gui.showError("Invalid field(s).");

        }
    }

    private void onCancel() {
        // add your code here if necessary
        this.dispose();
    }
}
