import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

//Created by ROHAN SINHA

public class notepad extends JFrame {

    ImageIcon im = new ImageIcon("Water.bmp");

    private JFrame f = new JFrame("Notepad"); // Create Frame
    private JPanel pnlNorth = new JPanel(); //North Quadrant
    private JPanel pnlSouth = new JPanel(); //South Quadrant


    private JMenuBar mb = new JMenuBar(); // Menu Bar
    private JMenu mnuFile = new JMenu("File"); // File Entry of Menu Bar
    private JMenuItem mnuItemQuit = new JMenuItem("Quit"); // Quit Sub item

    private JMenuItem mnuItemSave = new JMenuItem("Save");

    private JMenuItem mnuItemOpen = new JMenuItem("Open");

    private JMenu mnuHelp = new JMenu("Help"); //Help Menu Entry
    private JMenuItem mnuItemAbout = new JMenuItem("About"); //About Entry

    private JTextArea Ta = new JTextArea(10, 30);


    int fileSave;
    int fileOpen;
    JFileChooser sFile;
    JFileChooser oFile;


    public notepad(){



        pnlSouth.add(Ta);

        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(pnlNorth, BorderLayout.NORTH);
        f.getContentPane().add(pnlSouth,BorderLayout.SOUTH );
        //f.add(button);
f.addWindowListener(new ListenCloseWdw());

f.setJMenuBar(mb);

mnuItemQuit.addActionListener(new ListenMenuQuit());

mnuItemSave.addActionListener(new ListenMenuSave());

mnuItemOpen.addActionListener(new ListenMenuOpen());

//Build Menus
mnuFile.add(mnuItemQuit);//Create Quit Line
mnuFile.add(mnuItemSave);
mnuFile.add(mnuItemOpen);
mnuHelp.add(mnuItemAbout); //Create About Line
mb.add(mnuFile);        // Add Menu Items to form
mb.add(mnuHelp);




    }




    public class ListenCloseWdw extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            System.exit(0);

        }
    }

    public class ListenMenuQuit implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }   
    }

    public class ListenMenuSave implements ActionListener{
        public void actionPerformed(ActionEvent e){
            sFile();

            if (fileSave == JFileChooser.APPROVE_OPTION) {
                Ta.setText("");
                try {
                    Scanner scan = new Scanner(new FileReader(sFile.getSelectedFile().getPath()));
                    while(scan.hasNext())
                        Ta.append(scan.nextLine() + "\n");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }


        }
    }

    public class ListenMenuOpen implements ActionListener{
        public void actionPerformed(ActionEvent e){
            oFile();
            if (fileOpen == JFileChooser.APPROVE_OPTION) {
                Ta.setText("");
                try {
                    Scanner scan = new Scanner(new FileReader(oFile.getSelectedFile().getPath()));
                    while(scan.hasNext())
                        Ta.append(scan.nextLine() + "\n");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }

        }
    }

    public void sFile(){
        JFileChooser save = new JFileChooser();// Open up file chooser
        int option = save.showSaveDialog(this);
        fileSave= option;
        sFile = save;
    }

    public void oFile(){
        JFileChooser open = new JFileChooser();// Open up file chooser
        int option = open.showOpenDialog(this);
        fileOpen= option;
        oFile = open;

    }

    public void launchFrame(){
        //Display Frame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack(); // Adjusting panel to components for Display
        f.setVisible(true);
    }
    public static void main(String args[]){
        notepad gui = new notepad();
        gui.launchFrame();
    }
}
