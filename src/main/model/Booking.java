package model;

import java.io.Serializable;

//A class that describes a booking, a time and duration
//time is an hour between 0 and 24
public class Booking implements Serializable {
    private static final long serialVersionUID = 1L; //serialization id
    private int time;
    private int duration;
    private String booker;

    //REQUIRES: time and duration is between 0 and 24
    //EFFECTS: hour of booking is set to time, duration of booking is set to duration
    // and the name of the booker is set to booker
    public Booking(int time, int duration, String booker) {
        this.time = time;
        this.duration = duration;
        this.booker = booker;
    }

    //EFFECTS: returns the time of the booking
    public int getTime() {
        return time;
    }

    //EFFECTS: returns the duration of the booking
    public int getDuration() {
        return duration;
    }

    //EFFECTS: returns the name of the booker
    public String getBooker() {
        return booker;
    }
}
