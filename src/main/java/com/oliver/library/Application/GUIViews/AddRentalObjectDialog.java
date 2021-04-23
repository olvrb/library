package com.oliver.library.Application.GUIViews;

import com.oliver.library.Application.Entities.Inventory.Book;
import com.oliver.library.LibraryApplicationGUI;

import javax.swing.*;
import java.awt.event.*;
import java.time.Year;
import java.util.Arrays;
import java.util.List;

public class AddRentalObjectDialog extends JDialog {
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

        this.setUpListeners();
        this.setUpSpecialInput();

    }

    public static void main(String[] args) {
        JDialog dialog = new AddRentalObjectDialog();
        dialog.pack();
        dialog.setLocationByPlatform(true);
        dialog.setVisible(true);
    }

    private void setUpSpecialInput() {
        this.hideSpecialInput();
    }

    private void setSpecialInputVisible(boolean visible) {
        this.setJComponentsVisible(this.bookComponents, visible);
        this.setJComponentsVisible(this.filmComponents, visible);
    }

    private void hideSpecialInput() {
        this.setSpecialInputVisible(false);
    }

    private void setJComponentsVisible(List<JComponent> components, boolean visible) {
        for (JComponent c : components) {
            c.setVisible(visible);
        }
        this.pack();
    }

    private void updateSpecialInput() {
        this.hideSpecialInput();
        // Since we're dealing with class *names*, we can't use genericism or polymorphism :(
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

    private void setUpListeners() {
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

    // TODO: do this for film and journal
    // TODO: add constructors to film and journal
    private void onOK() {
        // Since we're dealing with class *names*, we can't use genericism or polymorphism :(
        switch ((String)this.typeList.getSelectedItem()) {
            case "Book": {
                Book book = new Book(this.titleField.getText(),
                                     this.genreField.getText(),
                                     this.physicalLocationField.getText(),
                                     this.descriptionField.getText(),
                                     // TODO: catch error
                                     Year.of(Integer.parseInt(this.publicationYearField.getText())),
                                     this.isbnField.getText(),
                                     this.authorField.getText(),
                                     this.referenceBox.isSelected(),
                                     this.courseLiteratureBox.isSelected());
                this.gui.saveObject(book);
                break;
            }
            case "Film": {
                break;
            }
            case "Journal": {

            }
            default: {
                break;
            }
        }
        // add your code here
        this.dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        this.dispose();
    }
}
