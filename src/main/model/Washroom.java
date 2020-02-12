package model;

//a class that describes a washroom that cannot be booked
public class Washroom extends Room {

    Washroom(String name, int id, int floor) {
        super(name, id, floor);
    }

    @Override
    //EFFECTS: returns false as you cannot book a bathroom
    public Boolean bookRoom(int time, int duration, String booker, int month, int day, int year) {
        return false;
    }


}
