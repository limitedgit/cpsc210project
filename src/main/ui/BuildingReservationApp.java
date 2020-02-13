package ui;

import model.Booking;
import model.Building;
import model.Date;
import model.Room;

import java.util.Scanner;

public class BuildingReservationApp {

    private Scanner input;
    private Building b1 = new Building();

    public BuildingReservationApp() {
        runApp();
    }

    private void displayMenu() {
        System.out.println("\nPlease choose an option:");
        System.out.println("\tp -> print current rooms of building");
        System.out.println("\ta -> add a room to the current building");
        System.out.println("\tb -> book a room in the building");
        System.out.println("\tr -> remove a room in the building");
        System.out.println("\tc -> clear the bookings of a room");
        System.out.println("\tq -> exit program");
    }

    private void processCommand(String option) {
        switch (option) {
            case "p" :
                printRooms();
                break;
            case "a":
                addRooms();
                break;
            case "b":
                bookRooms();
                break;
            case "r":
                removeRooms();
                break;
            case "c":
                cancelBooking();
                break;
            default:
                System.out.println("that is not an option");
                break;
        }

    }

    private void runApp() {
        boolean keepGoing = true;
        String option = null;
        input = new Scanner(System.in);

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




}
