package model;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.List;

// a class describing a Building in the campus
public class Building {
    String name; //the name of the building
    int totalRooms; //the total number of rooms in the building, cannot be exceeded
    List<Room> rooms; //should be a list of rooms, cannot exceed totalRooms


  // Requires: rooms is greater than 0
  // Modifies: this
  // Effects: initiates a building with a given number of rooms.
    Building(String name, int rooms) {
        this.name = name;
        this.totalRooms = rooms;
        this.rooms = new LinkedList();
    }


    // Modifies: this
    // Effects: adds a room to the building's rooms list
    public void addRoom(String name, int id, int floor) {
        rooms.add(new Room(name, id, floor));
    }


    // Effects: returns the current list of rooms
    public List<Room> getRooms() {
        return rooms;
    }

    // Effects: returns the current list of rooms that require Maintence
    public List<Room> getMaintenanceList() {
        List<Room> maintenceList = new LinkedList();
        for (Room r : rooms) {
            if (r.requiresMaintenance) {
                maintenceList.add(r);
            }
        }
        return maintenceList;
    }




}
