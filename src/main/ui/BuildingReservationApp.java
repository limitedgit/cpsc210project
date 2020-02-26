package ui;

import model.Booking;
import model.Building;
import model.Date;
import model.Room;
import persistence.Reader;
import persistence.Writer;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

//the UI app to be ran in main
public class BuildingReservationApp {

    private Scanner input;
    private Building b1;
    private static final String BUILDING_SER = "./data/building.ser";

    public BuildingReservationApp() {
        runApp();
    }

    //EFFECTS: displays the menu of options
    private void displayMenu() {
        System.out.println("\nPlease choose an option:");
        System.out.println("\tp -> print current rooms of building");
        System.out.println("\ta -> add a room to the current building");
        System.out.println("\tb -> book a room in the building");
        System.out.println("\tr -> remove a room in the building");
        System.out.println("\tc -> clear the bookings of a room");
        System.out.println("\tpb -> print the bookings of a room");
        System.out.println("\ts -> save the building to file");
        System.out.println("\tq -> exit program");
    }

    //EFFECTS: processes the user's command by taking in an input and calling the corresponding method
    private void processCommand(String option) {
        if (option.equals("p")) {
            printRooms();
        } else if (option.equals("a")) {
            addRooms();
        } else if (option.equals("b")) {
            bookRooms();
        } else if (option.equals("r")) {
            removeRooms();
        } else if (option.equals("c")) {
            cancelBooking();
        } else if (option.equals("pb")) {
            getBooking();
        } else if (option.equals("s")) {
            saveBuilding();
        } else {
            System.out.println("that is not an option");
        }
    }


    private void runApp() {
        boolean keepGoing = true;
        String option = null;
        input = new Scanner(System.in);

        loadBuilding();

        while (keepGoing) {
            displayMenu();
            option = input.next();
            option = option.toLowerCase();

            if (option.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(option);
            }
        }

        System.out.println("\nGoodbye!");
    }

    //EFFECTS: prints the rooms
    void printRooms() {
        for (Room r: b1.getRooms()) {
            System.out.println("name: " + r.getName() + " id: " + r.getId() + " floor " + r.getFloor());
        }
    }

    void bookRooms() {
        System.out.println("Enter the id of the room to be booked");
        int id = input.nextInt();
        input.nextLine(); //consumes new line
        System.out.println("Enter the month (as an integer) of the room to be booked");
        int month = input.nextInt();
        input.nextLine(); //consumes new line
        System.out.println("Enter the day of the room to be booked");
        int day = input.nextInt();
        input.nextLine(); //consumes new line
        System.out.println("Enter the year of the room to be booked");
        int year = input.nextInt();
        input.nextLine(); //consumes new line
        System.out.println("Enter the time (0 - 24) to be booked");
        int time = input.nextInt();
        input.nextLine(); //consumes new line
        System.out.println("Enter the duration to be booked");
        int duration = input.nextInt();
        input.nextLine();
        System.out.println("Enter the name to be booked under");
        String name = input.nextLine();
        Date d1 = new Date(month,day, year);
        Booking bk1 = new Booking(time, duration, name);
        b1.bookRoom(id, d1, bk1);

    }

    void cancelBooking() {
        System.out.println("Enter the id of the room to be removed");
        int id = input.nextInt();
        input.nextLine(); //consume new line
        b1.cancelBook(id);
    }

    void getBooking() {
        System.out.println("Enter the id of the room");
        int id = input.nextInt();
        input.nextLine(); //consume new line
        if (!b1.getSchedule(id).isEmpty()) {
            System.out.println("Booked:");
            for (Date dateStartDate : b1.getSchedule(id).keySet()) {
              //the booking at the starting Date
                Booking booking = b1.getSchedule(id).get(dateStartDate);
              //a variable that represents the end time by adding duration to the date
                Date dateEndDate =  dateStartDate.addHours(booking.getTime(), booking.getDuration());
                int endTime = ((booking.getTime() + booking.getDuration()) % 24);

                System.out.println("By " + booking.getBooker() + " from:");
                System.out.println(+ dateStartDate.getDay() + " "
                        + dateStartDate.getMonth() + " " + dateStartDate.getYear()
                        + " at " + booking.getTime() + ":00");
                System.out.println("To:");
                System.out.println(dateEndDate.getDay() + " "
                        + dateEndDate.getMonth() + " " + dateEndDate.getYear()
                        + " at " + endTime + ":00");
            }
        } else {
            System.out.println("that room has no schedule booked");
        }
    }


    void removeRooms() {
        System.out.println("Enter the id of the room to be removed");
        int id = input.nextInt();
        input.nextLine(); //consume new line
        b1.removeRoom(id);
    }

    void addRooms() {
        System.out.println("Enter an integer id for the room");
        int id = input.nextInt();
        input.nextLine(); //consume new line
        System.out.println("Enter which floor room is on");
        int floor = input.nextInt();
        input.nextLine(); //consume new line
        System.out.println("Enter the name of the room");
        String name = input.nextLine();
        b1.addRoom(name,id,floor);

    }

    // MODIFIES: this
    // EFFECTS: initializes New Building
    private void init() {
        this.b1 = new Building();
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




}
