package ui;

import model.Booking;
import model.Date;
import model.Room;

public class Printer {




    public String printRoom(Room r) {
        return "name: " + r.getName() + " id: "
            + r.getId() + " floor " + r.getFloor() + "\n";
    }

    public String printBookingStart(Date dateStartDate, Booking booking) {

        return "Booked by " + booking.getBooker() + " from:" + dateStartDate.getDay() + " "
                  + dateStartDate.getMonth() + " " + dateStartDate.getYear()
                  + " at " + booking.getTime() + ":00";
    }

    public String printBookingEnd(Date dateStartDate, Booking booking) {

      //a variable that represents the end time by adding duration to the date
        Date dateEndDate = dateStartDate.addHours(booking.getTime(), booking.getDuration());
        int endTime = ((booking.getTime() + booking.getDuration()) % 24);
        return "To:" + dateEndDate.getDay() + " "
              +                           + dateEndDate.getMonth() + " " + dateEndDate.getYear()
              + " "                          + " at " + endTime + ":00";
    }
}
