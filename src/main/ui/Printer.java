package ui;

import model.Booking;
import model.Date;
import model.Room;


// a class that formats strings
public class Printer {



//EFFECTS: format the name, floor, and id into a string
    public String printRoom(Room r) {
        return "name: " + r.getName() + " id: "
            + r.getId() + " floor " + r.getFloor() + "\n";
    }


    //EFFECTS: formats the starting date and time of a booking into a string
    public String printBookingStart(Date dateStartDate, Booking booking) {
        return "Booked by " + booking.getBooker() + " from:" + dateStartDate.getDay() + " "
                  + dateStartDate.getMonth() + " " + dateStartDate.getYear()
                  + " at " + booking.getTime() + ":00";
    }

  //EFFECTS: formats the ending date and time of a booking into a string
    public String printBookingEnd(Date dateStartDate, Booking booking) {
      //a variable that represents the end time by adding duration to the date
        Date dateEndDate = dateStartDate.addHours(booking.getTime(), booking.getDuration());
        int endTime = ((booking.getTime() + booking.getDuration()) % 24);
        return "To:" + dateEndDate.getDay() + " "
              +                           + dateEndDate.getMonth() + " " + dateEndDate.getYear()
              + " "                          + " at " + endTime + ":00";
    }
}
