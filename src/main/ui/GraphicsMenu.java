package ui;

import model.Booking;
import model.Building;
import model.Date;
import model.Room;
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
    private static final String BUILDING_SER = "./data/building.ser";
    JPanel menu = new JPanel();
    JPanel printPanel = new JPanel();
    JPanel addRoomPanel = new JPanel();
    JPanel removePanel = new JPanel();
    JPanel printBookingPanel = new JPanel();
    JPanel bookingPanel = new JPanel();
    JPanel clearBookingPanel = new JPanel();
    static int SIZE = 16;
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
        initAddRooms();
        setRemovePanel();
        setBookingPanel();


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
            printRooms();
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

    public void roomActions(ActionEvent e) {
        if (e.getActionCommand().equals("addRoom")) {
            addRoom();
        } else if (e.getActionCommand().equals("remove")) {
            removeRoom();
        } else if (e.getActionCommand().equals("book")) {
            bookRoom();
        } else if (e.getActionCommand().equals("printBook")) {
            printBooking();
        } else if (e.getActionCommand().equals("close")) {
            showMenu();
        } else if (e.getActionCommand().equals("clear")) {
            clearBooking();
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
        this.add(removePanel);
    }

  // MODIFIES: this
  // EFFECTS: loads the printBooking screen
    private void showPrintBookingPanel() {
        menu.setVisible(false);
        setPrintBookingPanel();
    }


  // MODIFIES: this
  // EFFECTS: loads the Booking screen
    private void showBookingPanel() {
        menu.setVisible(false);
        this.add(bookingPanel);
    }

    private void showClearBooking() {
        menu.setVisible(false);
        setClearBookingPanel();
    }

    //MODIFIES: this
    //EFFECTS: removes all panels except for menu to show the menu
    private void showMenu() {
        this.remove(printBookingPanel);
        this.remove(removePanel);
        this.remove(printPanel);
        this.remove(addRoomPanel);
        this.remove(bookingPanel);
        this.remove(clearBookingPanel);
        menu.setVisible(true);
    }





    //MODIFIES: this
    //EFFECTS: adds a new room from text fields
    private void addRoom() {
        JTextField name = (JTextField) addRoomPanel.getComponent(1);
        JTextField id = (JTextField) addRoomPanel.getComponent(3);
        JTextField floor = (JTextField) addRoomPanel.getComponent(5);
        b1.addRoom(name.getText(), Integer.parseInt(id.getText()), Integer.parseInt(floor.getText()));
    }

    //MODIFIES: this
    //EFFECTS: removes a room based on text field
    private void removeRoom() {
        JTextField id = (JTextField) removePanel.getComponent(1);
        b1.removeRoom(Integer.parseInt(id.getText()));
    }

    //MODIFIES: this
    //EFFECTS: book a room based on text field
    private void bookRoom() {
        JTextField id = (JTextField) bookingPanel.getComponent(1);
        JTextField day = (JTextField) bookingPanel.getComponent(3);
        JTextField month = (JTextField) bookingPanel.getComponent(5);
        JTextField year = (JTextField) bookingPanel.getComponent(7);
        JTextField time = (JTextField) bookingPanel.getComponent(9);
        JTextField duration = (JTextField) bookingPanel.getComponent(11);
        JTextField name = (JTextField) bookingPanel.getComponent(13);
        Date date = new Date(Integer.parseInt(day.getText()),
                    Integer.parseInt(month.getText()),Integer.parseInt(year.getText()));
        Booking book = new Booking(Integer.parseInt(time.getText()),
                Integer.parseInt(duration.getText()), name.getText());
        b1.bookRoom(Integer.parseInt(id.getText()), date, book);
    }

  //MODIFIES: this
  //EFFECTS: adds labels to the printBookingPanel to show the bookings of a room
    private void printBooking() {
        JTextField idField = (JTextField) printBookingPanel.getComponent(1);
        int id = Integer.parseInt(idField.getText());
        printBookingPanel.removeAll();
        printBooking(id);
        addButton(printBookingPanel,"close","close");
        printBookingPanel.setVisible(true);

    }

  //MODIFIES: this
  //EFFECTS: adds labels with a given id to printBookingPanel
    private void printBooking(int id) {
        if (!b1.getSchedule(id).isEmpty()) {
            printBookingPanel.setVisible(false);
            for (Date dateStartDate : b1.getSchedule(id).keySet()) {
              //the booking at the starting Date
                Booking booking = b1.getSchedule(id).get(dateStartDate);
                //a variable that represents the end time by adding duration to the date
                Date dateEndDate = dateStartDate.addHours(booking.getTime(), booking.getDuration());
                int endTime = ((booking.getTime() + booking.getDuration()) % 24);
                JPanel panel = new JPanel();
                addLabel(panel,"Booked by " + booking.getBooker() + " from:");
                addLabel(panel,dateStartDate.getDay() + " "
                          + dateStartDate.getMonth() + " " + dateStartDate.getYear()
                         + " at " + booking.getTime() + ":00");
                JPanel panel2 = new JPanel();
                addLabel(panel2,"To:" + dateEndDate.getDay() + " "
                         +                           + dateEndDate.getMonth() + " " + dateEndDate.getYear()
                         + " "                          + " at " + endTime + ":00");
                printBookingPanel.add(panel);
                printBookingPanel.add(panel2);
            }
        } else {
            printBookingPanel.setVisible(false);
            addLabel(printBookingPanel, "room is not booked");
        }
    }


  //MODIFIES: this
  //EFFECTS: clears the booking of a room with id from textfield
    private void clearBooking() {
        JTextField idField = (JTextField) clearBookingPanel.getComponent(1);
        int id = Integer.parseInt(idField.getText());
        b1.cancelBook(id);
    }



    //MODIFIES: this
    //EFFECTS: shows the add room panel to the window
    private void showAddRoomPanel() {
        menu.setVisible(false);
        this.getContentPane().add(addRoomPanel);
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
        menu.setVisible(false);
        printPanel.removeAll();
        for (Room r: b1.getRooms()) {
            JPanel panel = new JPanel();
            addLabel(panel,"name: " + r.getName() + " id: "
                    + r.getId() + " floor " + r.getFloor() + "\n");
            printPanel.add(panel);
        }
        addButton(printPanel,"close","close");
        this.getContentPane().add(printPanel);
        printPanel.setVisible(true);
    }


  //MODIFIES: this
  //EFFECTS: creates the addRoom panel
    private void initAddRooms() {
        addRoomPanel.removeAll();
        addLabel(addRoomPanel,"name");
        addTextField(addRoomPanel);
        addLabel(addRoomPanel,"id");
        addTextField(addRoomPanel);
        addLabel(addRoomPanel,"floor");
        addTextField(addRoomPanel);
        addButton(addRoomPanel,"addRoom","enter");
        addButton(addRoomPanel,"close","close");
    }

    //MODIFIES: this
    //EFFECTS: creates the removeRooms panel
    private void setRemovePanel() {
        addLabel(removePanel,"id of room to be removed");
        addTextField(removePanel);
        addButton(removePanel, "remove","enter");
        addButton(removePanel,"close","close");
    }

  //MODIFIES: this
  //EFFECTS: creates the booking panel
    private void setBookingPanel() {
        addLabel(bookingPanel,"id:");
        addTextField(bookingPanel);
        addLabel(bookingPanel,"month:");
        addTextField(bookingPanel);
        addLabel(bookingPanel,"day:");
        addTextField(bookingPanel);
        addLabel(bookingPanel,"year:");
        addTextField(bookingPanel);
        addLabel(bookingPanel,"time:");
        addTextField(bookingPanel);
        addLabel(bookingPanel,"dur.:");
        addTextField(bookingPanel);
        addLabel(bookingPanel,"name:");
        addTextField(bookingPanel);
        addButton(bookingPanel, "book","enter");
        addButton(bookingPanel,"close","close");
    }

  //MODIFIES: this
  //EFFECTS: creates the printBooking panel
    private void setPrintBookingPanel() {
        roomIdScreen(printBookingPanel, "printBook");
    }

  //MODIFIES: this
  //EFFECTS: creates the printBooking panel
    private void setClearBookingPanel() {
        roomIdScreen(clearBookingPanel, "clear");
    }

  //MODIFIES: this
  //EFFECTS: creates a room id accepting screen
    private void roomIdScreen(JPanel panel, String command) {
        panel.removeAll();
        addLabel(panel,"room id:");
        addTextField(panel);
        addButton(panel, command,"enter");
        addButton(panel,"close","close");
        this.add(panel);
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
        JTextField text = new JTextField(SIZE);
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



