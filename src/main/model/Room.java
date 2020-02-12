package model;

import java.util.LinkedHashMap;
import java.util.Map;

// A class describing a room in a building
// a room has an id eg. 210
// a room can be booked for a certain time
public class Room {
    protected String name;
    protected int id;
    protected int floor;
    protected LinkedHashMap<Date, Booking> schedule;
    protected Boolean requiresMaintenance;


    //EFFECTS: creates a new Room that does not need maintenance
    Room(String name, int id, int floor) {
        this.name = name;
        this.id = id;
        this.floor = floor;
        this.schedule = new LinkedHashMap<>();
        this.requiresMaintenance = false;
    }

    //REQUIRES: time is integer between 0 and 24
    //EFFECTS: checks if the room is booked at a certain time (in hours) by comparing the
    // starting and ending times of currently current bookings in the schedule with the
    // the dateToCheck
    public Boolean isBookedAtTime(Date checkDateStartTime, Booking checkBook) {
        Boolean result = false;

        Date checkDateEndTime = checkDateStartTime.addHours(checkBook.getTime(), checkBook.getDuration());

        for (Date dateStartTime : schedule.keySet()) {
            Booking booking = schedule.get(dateStartTime);
            Date dateEndTime =  dateStartTime.addHours(booking.getTime(), booking.getDuration());

            Boolean startsDuringBookedTime = !dateStartTime.isAfter(checkDateStartTime)
                    && dateEndTime.isAfter(checkDateStartTime);

            Boolean endsDuringBookedTime = !dateStartTime.isAfter(checkDateEndTime)
                    && dateEndTime.isAfter(checkDateEndTime);

            // if the a booking to be checked starts or ends during a booked time
            // then the time has already been booked
            if (startsDuringBookedTime || endsDuringBookedTime) {
                result = true;
            }

        }
        return result;
    }

    //REQUIRES: time is integer between 0 and 24
    //EFFECTS: checks if the room is booked at a certain time (in hours)
    public Boolean bookRoom(int time, int duration, String booker, int month, int day, int year) {
        Booking newBook = new Booking(time, duration, booker);
        Date newDate = new Date(month, day, year);
        if (!isBookedAtTime(newDate, newBook)) {
            schedule.put(newDate, newBook);
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public int getFloor() {
        return floor;
    }

    public String getName() {
        return name;
    }

  //MODIFIES: this
    //EFFECTS: changes the maintenance status of a room
    public void changeMaintenceStatus() {
        this.requiresMaintenance = !this.requiresMaintenance;
    }


    public Boolean needsMaintenceStatus() {
        return requiresMaintenance;
    }


}
