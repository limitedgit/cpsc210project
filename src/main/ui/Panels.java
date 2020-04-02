package ui;

import model.Booking;
import model.Building;
import model.Date;
import model.Room;

import javax.swing.*;

// creates the extra panels and actions for the app
public class Panels {

    Printer print = new Printer();
    private static final int SIZE = 16;
    GraphicsMenu mainWindow;
    Building b1;

    Panels(Building b1, GraphicsMenu mainWindow) {
        this.b1 = b1;
        this.mainWindow = mainWindow;
    }

    JPanel printPanel = new JPanel();
    JPanel addRoomPanel = new JPanel();
    JPanel removePanel = new JPanel();
    JPanel printBookingPanel = new JPanel();
    JPanel bookingPanel = new JPanel();
    JPanel clearBookingPanel = new JPanel();


  //EFFECTS: returns the panel that displays the screen to print room bookings
    public JPanel getPrintBookingPanel() {
        return printBookingPanel;
    }

  //EFFECTS: returns the panel that displays the screen to add rooms to the building
    public JPanel getAddRoomPanel() {
        return addRoomPanel;
    }

  //EFFECTS: returns the panel that displays the screen to book rooms
    public JPanel getBookingPanel() {
        return bookingPanel;
    }

  //EFFECTS: returns the panel that displays the screen to show current rooms
    public JPanel getPrintPanel() {
        return printPanel;
    }

  //EFFECTS: returns the panel that displays the screen to remove rooms
    public JPanel getRemovePanel() {
        return removePanel;
    }

  //EFFECTS: returns the panel that displays the screen to clear room bookings
    public JPanel getClearBookingPanel() {
        return clearBookingPanel;
    }

  //MODIFIES: this
  //EFFECTS: adds a new room from text fields
    public void addRoom() {
        JTextField name = (JTextField) addRoomPanel.getComponent(1);
        JTextField id = (JTextField) addRoomPanel.getComponent(3);
        JTextField floor = (JTextField) addRoomPanel.getComponent(5);
        b1.addRoom(name.getText(), Integer.parseInt(id.getText()), Integer.parseInt(floor.getText()));
    }

  //MODIFIES: this
  //EFFECTS: removes a room based on text field
    public void removeRoom() {
        JTextField id = (JTextField) removePanel.getComponent(1);
        b1.removeRoom(Integer.parseInt(id.getText()));
    }

  //MODIFIES: this
  //EFFECTS: book a room based on text field
    public void bookRoom() {
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
    public void printBooking() {
        JTextField idField = (JTextField) printBookingPanel.getComponent(1);
        int id = Integer.parseInt(idField.getText());
        printBookingPanel.removeAll();
        printBooking(id);
        addButton(printBookingPanel,"close","close");
        printBookingPanel.setVisible(true);
    }

  //MODIFIES: this
  //EFFECTS: adds labels with a given id to printBookingPanel
    public void printBooking(int id) {
        if (!b1.getSchedule(id).isEmpty()) {
            printBookingPanel.setVisible(false);
            for (Date dateStartDate : b1.getSchedule(id).keySet()) {
              //the booking at the starting Date
                Booking booking = b1.getSchedule(id).get(dateStartDate);

                JPanel panel = new JPanel();
                JPanel panel2 = new JPanel();

                addLabel(panel, print.printBookingStart(dateStartDate,booking));
                addLabel(panel2, print.printBookingEnd(dateStartDate,booking));

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
    public void clearBooking() {
        JTextField idField = (JTextField) clearBookingPanel.getComponent(1);
        int id = Integer.parseInt(idField.getText());
        b1.cancelBook(id);
    }

  //MODIFIES: mainWindow
  //EFFECTS: displays the current rooms of the building
    public void printRooms() {
        printPanel.removeAll();
        for (Room r: b1.getRooms()) {
            JPanel panel = new JPanel();
            addLabel(panel,print.printRoom(r));
            printPanel.add(panel);
        }
        addButton(printPanel,"close","close");
        mainWindow.getContentPane().add(printPanel);
        printPanel.setVisible(true);
    }


  //MODIFIES: mainWindow
  //EFFECTS: creates the addRoom panel
    public void initAddRooms() {
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

  //MODIFIES: mainWindow
  //EFFECTS: creates the removeRooms panel
    public void setRemovePanel() {
        addLabel(removePanel,"id of room to be removed");
        addTextField(removePanel);
        addButton(removePanel, "remove","enter");
        addButton(removePanel,"close","close");
    }

  //MODIFIES: mainWindow
  //EFFECTS: creates the booking panel
    public void setBookingPanel() {
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

  //MODIFIES: mainWindow
  //EFFECTS: creates the printBooking panel
    public void setPrintBookingPanel() {
        roomIdScreen(printBookingPanel, "printBook");
    }

  //MODIFIES: mainWindow
  //EFFECTS: creates the printBooking panel
    public void setClearBookingPanel() {
        roomIdScreen(clearBookingPanel, "clear");
    }

  //MODIFIES: mainWindow
  //EFFECTS: creates a room id accepting screen
    public void roomIdScreen(JPanel panel, String command) {
        panel.removeAll();
        addLabel(panel,"room id:");
        addTextField(panel);
        addButton(panel, command,"enter");
        addButton(panel,"close","close");
        mainWindow.add(panel);
    }


  //MODIFIES: JPanel
  //EFFECTS: adds a JButton to a panel
    public void addButton(JPanel panel,String command, String display) {
        JButton btn = new JButton(display);
        btn.addActionListener(mainWindow);
        btn.setActionCommand(command);
        panel.add(btn);
    }

  //MODIFIES: JPanel
  //EFFECTS: adds a JTextField to a panel
    public void addTextField(JPanel panel) {
        JTextField text = new JTextField(SIZE);
        panel.add(text);
    }

  //MODIFIES: JPanel
  //EFFECTS: adds a JLabel to a panel
    public void addLabel(JPanel panel, String text) {
        panel.add(new JLabel(text));
    }

}
