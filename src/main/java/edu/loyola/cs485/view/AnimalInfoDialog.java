package edu.loyola.cs485.view;
import edu.loyola.cs485.controller.AnimalService;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;

public class AnimalInfoDialog extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtBreed;
    private JTextField txtDob;
    private JTextField txtColor;
    private JTextField txtSpecies;

    public AnimalInfoDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonCancel);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        AnimalService service = new AnimalService();
        String id = txtId.getText();
        String name = txtName.getText();
        String breed = txtBreed.getText();
        String dob = txtDob.getText();
        String color = txtColor.getText();
        String species = txtSpecies.getText();
        try {
            service.createAnimal(name, breed, color, species, dob);
            dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        dispose(); // dispose method from the superclass JDialog, closes the current dialog
    }

    public static void main(String[] args) {
        AnimalInfoDialog dialog = new AnimalInfoDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}