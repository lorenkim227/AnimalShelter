package edu.loyola.cs485.view;

import javax.swing.*;


public class MainFrame extends JFrame {
    // JFrame is the class for the Window Frame app

    public MainFrame() {
        super("CS485 - Animals in the Shelter");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        createMenuBar();
    }

    public void createMenuBar() {
        JMenuBar menuBar = new JMenuBar(); // A Frame can only have one menubar

        JMenu mnuFile = new JMenu("File"); // Each JMenu is th top menu option
        //Each menu will have many MenuItems
        JMenuItem mniClient = new JMenuItem("Animal CRUD");
        mniClient.addActionListener(e -> {
            //Code called when clicking on this menuitem
            createAnimalCrudDialog();
        });
        mnuFile.add(mniClient);
        mnuFile.addSeparator();

        JMenuItem mniExit = new JMenuItem("Exit");
        mniExit.addActionListener(e ->{
            System.exit(0);
        });
        mnuFile.add(mniExit);

        menuBar.add(mnuFile);
        this.setJMenuBar(menuBar);
    }

    public void createAnimalCrudDialog(){
        AnimalCrudDialog dialog = new AnimalCrudDialog();
        dialog.pack();
        dialog.setVisible(true);
    }

}
