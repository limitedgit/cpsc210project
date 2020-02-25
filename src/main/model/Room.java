package model;

//import persistence.Reader;
//import persistence.Saveable;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.io.Serializable;

// A class describing a room in a building
// a room has an id eg. 210
// a room can be booked for a certain time
public class Room implements /*Saveable,*/ Serializable {
    protected String name;
    protected int id;
    protected int floor;
    protected LinkedHashMap<Date, Booking> schedule;


    //EFFECTS: creates a new Room that does not need maintenance
    public Room(String name, int id, int floor) {
        this.name = name;
        this.id = id;
        this.floor = floor;
        this.schedule = new LinkedHashMap<>();
    }


    //EFFECTS: checks if the room is booked at a certain time (in hours) by comparing the
    // starting and ending times of currently current bookings in the schedule with the
    // the dateToCheck
    public Boolean isBookedAtDate(Date checkDateStartDate, Booking checkBook) {
        boolean result = false;

        //get the end time by adding duration
        Date checkDateEndDate = checkDateStartDate.addHours(checkBook.getTime(), checkBook.getDuration());

        //check each date
        for (Date dateStartDate : schedule.keySet()) {

            //the booking at the starting Date
            Booking booking = schedule.get(dateStartDate);

            //a variable that represents the end time by adding duration to the date
            Date dateEndDate =  dateStartDate.addHours(booking.getTime(), booking.getDuration());

            //boolean to represent if the date to check starts during a booked time
            Boolean startsDuringBookedDate = !dateStartDate.isAfter(checkDateStartDate)
                    && dateEndDate.isAfter(checkDateStartDate);

            //boolean to represent if the date to check ends during a booked time
            Boolean endsDuringBookedDate = (!dateStartDate.isAfter(checkDateEndDate)
                    && !dateStartDate.isEqual(checkDateEndDate))
                    && (dateEndDate.isAfter(checkDateEndDate) || dateEndDate.isEqual(checkDateEndDate));

            // if the a booking to be checked starts or ends during a booked time
            // then the time has already been booked
            if (startsDuringBookedDate || endsDuringBookedDate) {
                result = true;
            } else if (dateEndDate.isEqual(checkDateStartDate)) {

                //if it starts on the end day, check the hours
                if ((booking.getTime() + booking.getDuration()) % 24 >= checkBook.getTime()) {
                    result = true;
                }
            } else if (dateStartDate.isEqual(checkDateEndDate)) {

                // if it ends on a start day, check the hours
                if ((checkBook.getTime() + checkBook.getDuration()) % 24 >= booking.getTime()) {
                    result = true;
                }
            }
        }
        return result;
    }

    //REQUIRES: time is integer between 0 and 24
    //EFFECTS: checks if the room is booked at a certain time (in hours)
    public Boolean bookRoom(Date newDate, Booking newBook) {
        if (!isBookedAtDate(newDate, newBook)) {
            this.schedule.put(newDate, newBook);
            return true;
        }
        return false;
    }


    //EFFECTS: clears all Bookings
    public void removeBooking() {
        this.schedule = new LinkedHashMap<>();
    }


  //EFFECTS: returns room id
    public int getId() {
        return id;
    }

  //EFFECTS: returns floor of the room
    public int getFloor() {
        return floor;
    }

  //EFFECTS: returns name of the room
    public String getName() {
        return name;
    }


    //EFFECTS: returns room schedule
    public LinkedHashMap<Date, Booking> getSchedule() {
        return this.schedule;
    }


//    @Override
//    public void save(PrintWriter printWriter) {
//        printWriter.print(id);
//        printWriter.print(Reader.DELIMITER);
//        printWriter.print(name);
//        printWriter.print(Reader.DELIMITER);
//        printWriter.print(name);
//        printWriter.print(Reader.DELIMITER);
//
//p
//    }
}
