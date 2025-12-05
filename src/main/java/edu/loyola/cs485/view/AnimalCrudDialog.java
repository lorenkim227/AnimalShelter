package edu.loyola.cs485.view;
import edu.loyola.cs485.controller.AnimalService;
import edu.loyola.cs485.model.entity.Animal;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class AnimalCrudDialog extends JDialog {


    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton deleteButton;
    private JButton updateButton;
    private JList lstAnimalUI;

    public AnimalCrudDialog() {
        setContentPane(contentPane);
        setModal(true);
        //getRootPane().setDefaultButton(buttonOK);
        populateUI();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newClick();
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
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteClick();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClick();
            }
        });
    }

    private void newClick() {
        // add your code here
        AnimalInfoDialog dialog = new AnimalInfoDialog();
        dialog.pack();
        dialog.setVisible(true);

        populateUI();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void deleteClick() {
        try{
            AnimalService service = new AnimalService();
            Animal c = (Animal) lstAnimalUI.getSelectedValue();
            if (c != null) {
                service.deleteAnimal(c.getId());
                lstAnimalUI.clearSelection();

                // Repopulate the JList to get new data
                populateUI(); // fetch everything again from the DB
            }

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void populateUI() {
        try {
            AnimalService service = new AnimalService();
            List<Animal> lstdata = service.getAllAnimals();

            lstAnimalUI.setListData( lstdata.toArray() );

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void updateClick() {
        try{
            AnimalService service = new AnimalService();
            Animal c = (Animal) lstAnimalUI.getSelectedValue();
            if (c != null) {
                AnimalUpdateDialog dialog = new AnimalUpdateDialog(c.getId());
                dialog.pack();
                dialog.setVisible(true);
                populateUI();
            }
            else {
                AnimalUpdateDialog dialog = new AnimalUpdateDialog(c.getId());
                dialog.pack();
                dialog.setVisible(true);

                populateUI();
            }

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        AnimalCrudDialog dialog = new AnimalCrudDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
