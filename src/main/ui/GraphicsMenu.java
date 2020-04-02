package ui;


import model.Building;
import persistence.Reader;
import persistence.Writer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

// creates the main graphic window for the menu

public class GraphicsMenu extends JFrame implements ActionListener {
    private Building b1;
    private Panels panels;
    private static final String BUILDING_SER = "./data/building.ser";
    JPanel menu = new JPanel();
    String[] menuStrings = new String[]{
        "print rooms",
        "add a room",
        "book a room",
        "remove a room",
        "clear booking",
        "print booking",
        "save",
        "quit"
    };
    LayoutManager layout = new GridLayout();


//EFFECTS: constructs the main window
    GraphicsMenu() {
        super("Building Reservation App");
        loadBuilding();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        menu.setLayout(layout);



        menu.add(new JLabel("please choose: "));
        for (int i = 0; i < menuStrings.length; i++) {
            JButton btn = new JButton(menuStrings[i]);
            btn.setActionCommand(menuStrings[i]);
            btn.addActionListener(this);
            menu.add(btn);
        }
        menu.setSize(300,300);
        this.getContentPane().add(menu);
        this.getContentPane().setLayout(layout);
        this.setSize(1000,250);
        panels = new Panels(b1, this);
        panels.initAddRooms();
        panels.setRemovePanel();
        panels.setBookingPanel();
        setVisible(true);
    }

  //MODIFIES: this
  //EFFECTS: responds to the actions performed by the users on the mian menu
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("print rooms")) {
            showPrintPanel();
        } else if (e.getActionCommand().equals("add a room")) {
            showAddRoomPanel();
        } else if (e.getActionCommand().equals("remove a room")) {
            showRemovePanel();
        } else if (e.getActionCommand().equals("book a room")) {
            showBookingPanel();
        } else if (e.getActionCommand().equals("print booking")) {
            showPrintBookingPanel();
        }  else if (e.getActionCommand().equals("quit")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("clear booking")) {
            showClearBooking();
        } else if (e.getActionCommand().equals("save")) {
            saveBuilding();
        } else {
            roomActions(e);
        }
    }

  //MODIFIES: this
  //EFFECTS: responds to the actions performed by the users in the panels
    public void roomActions(ActionEvent e) {
        if (e.getActionCommand().equals("addRoom")) {
            panels.addRoom();
        } else if (e.getActionCommand().equals("remove")) {
            panels.removeRoom();
        } else if (e.getActionCommand().equals("book")) {
            panels.bookRoom();
        } else if (e.getActionCommand().equals("printBook")) {
            panels.printBooking();
        } else if (e.getActionCommand().equals("close")) {
            showMenu();
        } else if (e.getActionCommand().equals("clear")) {
            panels.clearBooking();
        }

    }




    // MODIFIES: this
    // EFFECTS: initializes New Building
    private void init() {
        this.b1 = new Building();
    }

    // MODIFIES: this
    // EFFECTS: initializes New Building
    private void showRemovePanel() {
        menu.setVisible(false);
        this.add(panels.getRemovePanel());
    }


  // MODIFIES: this
  // EFFECTS: loads the printRooms screen
    private void showPrintPanel() {
        menu.setVisible(false);
        panels.printRooms();
    }

  // MODIFIES: this
  // EFFECTS: loads the printBooking screen
    private void showPrintBookingPanel() {
        menu.setVisible(false);
        panels.setPrintBookingPanel();
    }

  // MODIFIES: this
  // EFFECTS: loads the Booking screen
    private void showBookingPanel() {
        menu.setVisible(false);
        this.add(panels.getBookingPanel());
    }

    private void showClearBooking() {
        menu.setVisible(false);
        panels.setClearBookingPanel();
    }

    //MODIFIES: this
    //EFFECTS: removes all panels except for menu to show the menu
    private void showMenu() {
        this.remove(panels.getPrintBookingPanel());
        this.remove(panels.getRemovePanel());
        this.remove(panels.getPrintPanel());
        this.remove(panels.getAddRoomPanel());
        this.remove(panels.getBookingPanel());
        this.remove(panels.getClearBookingPanel());
        menu.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: shows the add room panel to the window
    private void showAddRoomPanel() {
        menu.setVisible(false);
        this.getContentPane().add(panels.addRoomPanel);
    }


  // MODIFIES: this
  // EFFECTS: loads accounts from ACCOUNTS_FILE, if that file exists;
  // otherwise initializes accounts with default values
    private void loadBuilding() {
        try {
            Reader reader = new Reader(BUILDING_SER);
            this.b1 = (Building) reader.loadObject();
            reader.close();
        } catch (FileNotFoundException f) {
            init();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Building class not found");
            c.printStackTrace();
            return;
        }

    }

  //EFFECTS: main method of the graphic application
    public static void main(String[] args) {
        new GraphicsMenu();
    }

    // EFFECTS: saves the building to a file
    private void saveBuilding() {
        try {
            Writer write = new Writer(BUILDING_SER);
            write.saveFile(b1);
            write.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

}



