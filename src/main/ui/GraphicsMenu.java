package ui;

import model.Building;
import model.Room;
import persistence.Reader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

// creates the main graphic window for the menu

public class GraphicsMenu extends JFrame implements ActionListener {
    private Building b1;
    private static final String BUILDING_SER = "./data/building.ser";
    JPanel menu = new JPanel();
    JPanel print = new JPanel();
    JPanel addRoom = new JPanel();
    JPanel remove = new JPanel();
    JPanel printBooking = new JPanel();
    static int SIZE = 50;
    String[] menuStrings = new String[]{
        "print rooms",
        "add a room",
        "book a room",
        "remove a room",
        "clear a room's bookings",
        "print a room booking",
        "save",
        "quit"
    };
    LayoutManager layout;


//EFFECTS: constructs the main window
    GraphicsMenu() {
        super("Building Reservation App");
        loadBuilding();
        makeLayout();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        menu.setLayout(layout);
        initAddRooms();


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
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("print rooms")) {
            menu.setVisible(false);
            printRooms();
        }
        if (e.getActionCommand().equals("add a room")) {
            menu.setVisible(false);
            this.getContentPane().add(addRoom);
        }
        if (e.getActionCommand().equals("close")) {
            this.remove(print);
            this.remove(addRoom);
            menu.setVisible(true);
        }
        if (e.getActionCommand().equals("addRoom")) {
            int id;
            int floor;
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes New Building
    private void init() {
        this.b1 = new Building();
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

    //MODIFIES: this
    //EFFECTS: displays the current rooms of the building
    private void printRooms() {
        print.removeAll();
        for (Room r: b1.getRooms()) {
            addLabel(print,"name: " + r.getName() + " id: " + r.getId() + " floor " + r.getFloor() + "\n");
        }
        addButton(print,"close","close");
        this.getContentPane().add(print);
        print.setVisible(true);
    }


  //MODIFIES: this
  //EFFECTS: creates the addRoom panel
    private void initAddRooms() {
        addLabel(addRoom,"name");
        addTextField(addRoom);
        addLabel(addRoom,"id");
        addTextField(addRoom);
        addLabel(addRoom,"floor");
        addTextField(addRoom);
        addButton(addRoom,"addRoom","enter");
        addButton(addRoom,"close","close");
    }


    private void makeLayout() {
        layout = new GridLayout();
    }

  //MODIFIES: JPanel
  //EFFECTS: adds a JButton to a panel
    private void addButton(JPanel panel,String command, String display) {
        JButton btn = new JButton(display);
        btn.addActionListener(this);
        btn.setActionCommand(command);
        panel.add(btn);
    }

  //MODIFIES: JPanel
  //EFFECTS: adds a JTextField to a panel
    private void addTextField(JPanel panel) {
        JTextField text = new JTextField();
        text.setColumns(SIZE);
        panel.add(text);
    }

  //MODIFIES: JPanel
  //EFFECTS: adds a JLabel to a panel
    private void addLabel(JPanel panel, String text) {
        panel.add(new JLabel(text));
    }

    public static void main(String[] args) {
        new GraphicsMenu();
    }

}



