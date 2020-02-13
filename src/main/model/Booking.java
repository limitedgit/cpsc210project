package model;

//A class that describes a booking, a time and duration
//time is an hour between 0 and 24
//duration is in hours between 0 and MAXBOOKTIME
public class Booking {

    private int time;
    private int duration;
    private String booker;

    //REQUIRES: time and duration is between 0 and 24
    public Booking(int time, int duration, String booker) {
        this.time = time;
        this.duration = duration;
        this.booker = booker;
    }

    public int getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }

    public String getBooker() {
        return booker;
    }
}
