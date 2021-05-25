package com.oliver.library.Application.GUI.GUIViews;

import com.oliver.library.Application.Entities.Inventory.Book;
import com.oliver.library.Application.Entities.Inventory.Film;
import com.oliver.library.Application.Entities.Inventory.Journal;
import com.oliver.library.Application.Entities.Inventory.RentalObject;
import com.oliver.library.Application.GUI.GUIViews.Templates.GUIView;
import com.oliver.library.Application.Services.ListenerService;
import com.oliver.library.Application.GUI.LibraryApplicationGUI;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Year;
import java.util.Arrays;
import java.util.List;


public class EditRentalObjectDialog extends GUIView {
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

    private RentalObject oldObject;

    public EditRentalObjectDialog(LibraryApplicationGUI gui) {
        this();
        this.gui = gui;
    }

    public EditRentalObjectDialog() {
        this.setContentPane(this.contentPane);
        this.setModal(true);
        this.getRootPane()
            .setDefaultButton(this.buttonOK);

        this.setUpTypeList();
        this.setUpListeners();
        this.setUpSpecialInput();

    }

    public EditRentalObjectDialog(LibraryApplicationGUI gui, RentalObject obj) {
        this(gui);

        this.oldObject = obj;

        this.titleField.setText(obj.getTitle());
        this.genreField.setText(obj.getGenre());
        this.physicalLocationField.setText(obj.getPhysicalLocation());
        this.descriptionField.setText(obj.getDescription());

        // Fill fields with class specific attributes, and select correct typeList index.
        if (obj instanceof Book) {
            this.publicationYearField.setText(((Book)obj).getPublicationYear()
                                                         .toString());
            this.isbnField.setText(((Book)obj).getISBN());
            this.referenceBox.setSelected(((Book)obj).isReference());
            this.courseLiteratureBox.setSelected(((Book)obj).isCourseLiterature());
            this.typeList.setSelectedIndex(1);
        } else if (obj instanceof Film) {
            this.productionCountryField.setText(((Film)obj).getProductionCountry());
            this.ageLimitField.setText(((Film)obj).getAgeLimit());
            this.typeList.setSelectedIndex(2);
        } else {
            this.typeList.setSelectedIndex(3);
        }
    }

    public static void main(String[] args) {
        JDialog dialog = new EditRentalObjectDialog();
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
        // Since we're dealing with class *names*, we can't use generic methods or polymorphism :(
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

    // Insert empty item at the top
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


    // Button and various component listeners
    private void setUpListeners() {
        ListenerService.addChangeListener(this.publicationYearField, e -> {
            this.validateYearField();
        });
        this.typeList.addActionListener(e -> {
            this.updateSpecialInput();
        });

        this.buttonOK.addActionListener(e -> EditRentalObjectDialog.this.onOK());

        this.buttonCancel.addActionListener(e -> EditRentalObjectDialog.this.onCancel());

        // call onCancel() when cross is clicked
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                EditRentalObjectDialog.this.onCancel();
            }
        });

        // call onCancel() on ESCAPE
        this.contentPane.registerKeyboardAction(e -> EditRentalObjectDialog.this.onCancel(),
                                                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                                                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        boolean success = true;
        RentalObject newObject = null;
        // Since we're dealing with class *names*, we can't use generic methods or polymorphism :(
        switch ((String)this.typeList.getSelectedItem()) {
            case "Book": {
                // Only field which requires validation is year. We don't care if the other fields are empty 🤷‍
                if (this.validateYear()) {
                    newObject = new Book(this.titleField.getText(),
                                         this.genreField.getText(),
                                         this.physicalLocationField.getText(),
                                         this.descriptionField.getText(),
                                         this.getYearValue(),
                                         this.isbnField.getText(),
                                         this.authorField.getText(),
                                         this.referenceBox.isSelected(),
                                         this.courseLiteratureBox.isSelected());
                } else {
                    success = false;
                }
                break;
            }
            case "Film": {
                newObject = new Film(this.titleField.getText(),
                                     this.genreField.getText(),
                                     this.physicalLocationField.getText(),
                                     this.descriptionField.getText(),
                                     this.authorField.getText(),
                                     this.ageLimitField.getText(),
                                     this.productionCountryField.getText());
                break;
            }
            case "Journal": {
                newObject = new Journal(this.titleField.getText(),
                                        this.genreField.getText(),
                                        this.physicalLocationField.getText(),
                                        this.descriptionField.getText(),
                                        this.authorField.getText());
                break;
            }
            default: {
                break;
            }
        }
        if (success && newObject != null) {
            // If existing object exists, inherit its id.
            if (this.oldObject != null) newObject.setId(this.oldObject.getId());
            this.gui.saveObject(newObject);
            this.dispose();
        } else {
            this.gui.showError("Invalid field(s).");
        }
    }

    private void onCancel() {
        this.dispose();
    }
}
