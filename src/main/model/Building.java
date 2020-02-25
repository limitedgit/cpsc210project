package model;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;


// a class describing a Building in the campus
public class Building implements Serializable {
    private static final long serialVersionUID = 1L; //serialization id
    int totalRooms; //the total number of rooms in the building
    List<Room> rooms; //should be a list of rooms, cannot exceed totalRooms


  // Requires: rooms is greater than 0
  // Modifies: this
  // Effects: initiates a building with a given number of rooms.
    public Building() {
        this.rooms = new LinkedList();
        this.totalRooms = rooms.size();

    }


    // Modifies: this
    // Effects: adds a room to the building's rooms list
    public void addRoom(String name, int id, int floor) {
        rooms.add(new Room(name, id, floor));
        totalRooms += 1;
    }

  // Modifies: this
  // Effects: removes a room from the building's rooms list
    public void removeRoom(int id) {
        for (Room r : rooms) {
            if (r.getId() == id) {
                rooms.remove(r);
                totalRooms -= 1;
            }
        }
    }


    // Effects: returns the current list of rooms
    public List<Room> getRooms() {
        return rooms;
    }

  // Modifies: this
  // Effects: books a room in the building's rooms list
  // with the given date and booking if it matches the given id
    public Boolean bookRoom(int id, Date d, Booking b) {
        for (int r = 0; r < rooms.size(); r++) {
            if (rooms.get(r).getId() == id) {
                if (rooms.get(r).bookRoom(d,b)) {
                    return true;
                }
            }
        }
        return false;

    }


    // Modifies: this
    // Effects: books a room in the building's rooms list
    // with the given date and booking if it matches the given id
    public void cancelBook(int id) {
        for (int r = 0; r < rooms.size(); r++) {
            if (rooms.get(r).getId() == id) {
                rooms.get(r).removeBooking();
            }
        }
    }


    // Effects: returns the current number of rooms
    public int getTotalRooms() {
        return totalRooms;
    }

  // Effects: returns schedule of a room with the given id
  // if no schedule is found, return null
    public LinkedHashMap<Date,Booking> getSchedule(int id) {
        for (Room r : rooms) {
            if (r.getId() == id) {
                return r.getSchedule();
            }
        }
        return new LinkedHashMap<Date, Booking>();
    }

}
