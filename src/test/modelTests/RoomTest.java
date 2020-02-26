package modelTests;

import model.Booking;
import model.Date;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {
  Room r1;
  Room r2;
  Room r3;
  Room r4;
  Date d1;
  Booking b1;
  Date d2;
  Booking b2;
  Date d3;
  Booking b3;

  @BeforeEach
  void runBefore() {
    r1 = new Room("Classroom", 100, 1);
    d1  = new Date(1,23,2020);
    b1 = new Booking(1,2, "Felix");
    d2 = new Date(1,27,2008);
    b2 = new Booking(1,2, "Felix");
    r2 = new Room("Classroom2", 100, 1);
    r3 = new Room("Classroom3", 100, 1);
    r4 = new Room("Classroom3", 100, 1);

  }



  @Test
  void testBookRoom() {
    assertTrue(r1.bookRoom(d1, b1));
    assertFalse(r1.bookRoom(d1, b1));
    assertEquals(1, r1.getSchedule().size());
  }

  @Test
  void testIsBookedAtTime(){
      //unbooked date
      assertFalse(r1.isBookedAtDate(d1, b1));

      //tests ending on same date and time
      r1.bookRoom(d1,b1);
      assertTrue(r1.isBookedAtDate(d1, b1));
      r1.bookRoom(d2, b2);
      assertTrue(r1.isBookedAtDate(d2, b2));

      //tests starting during a booked date
      d1  = new Date(1,23,2020);
      b1 = new Booking(10,56, "Felix");
      d2 = new Date(1,24,2020);
      r4.bookRoom(d1,b1);
      assertTrue(r4.isBookedAtDate(d2, b2));

      //tests starting during a booked date but ends on same day
      d2 = new Date(1,24,2020);
      b2 = new Booking(10,32, "Felix");
      assertTrue(r4.isBookedAtDate(d2, b2));


      //test ending during a booked date different end
      d1 = new Date(1,25,2022);
      b1 = new Booking(10,48, "Felix");
      b2 = new Booking(10,26, "Felix");
      d2 = new Date(1,24,2022);
      r2.bookRoom(d1,b1);
      assertTrue(r2.isBookedAtDate(d2, b2));


      //endsDuringBookedDate
      //test ending during a booked date same end
      d1 = new Date(1,25,2026);
      b1 = new Booking(10,2, "Felix");
      d2 = new Date(1,24,2026);
      b2 = new Booking(10,26, "Felix");

      r3.bookRoom(d1,b1);
      assertTrue(r3.isBookedAtDate(d2, b2));

  }

  @Test
  void testIsBookedAtEndStartTimes(){

    d2 = new Date(1,27,2008);
    b2 = new Booking(1,26, "Felix");
    d3 = new Date(1,26,2008);
    b3 = new Booking(2,24, "Felix");
    r1.bookRoom(d2,b2);

    //it is booked at the checkday end day
    assertTrue(r1.isBookedAtDate(d3, b3));


    d3 = new Date(1,26,2008);
    b3 = new Booking(0,24, "Felix");
    //it is not booked at the checkday end day
    assertFalse(r1.isBookedAtDate(d3, b3));


    d3 = new Date(1,28,2008);
    b3 = new Booking(1,24, "Felix");
    //it is booked at the checkday start day
    assertTrue(r1.isBookedAtDate(d3, b3));


    d3 = new Date(1,28,2008);
    b3 = new Booking(4,24, "Felix");
    //it is not booked at the checkday start date
    assertFalse(r1.isBookedAtDate(d3, b3));


    b3 = new Booking(3,24, "Felix");
    //it is booked at the check day start date
    assertTrue(r1.isBookedAtDate(d3, b3));


    d3 = new Date(1,26,2008);
    b3 = new Booking(0,24, "Felix");
    //it is not booked
    assertFalse(r1.isBookedAtDate(d3, b3));




    d2 = new Date(1,28,2008);
    b2 = new Booking(3,1, "Felix");
    r2.bookRoom(d2,b2);
    d3 = new Date(1,28,2008);
    b3 = new Booking(6,3, "Felix");
    assertFalse(r2.isBookedAtDate(d3, b3));
  }

  @Test
  void testGetFloor(){
      assertEquals(1, r1.getFloor());
      r1 = new Room ("newRoom", 100, 2);
      assertEquals(2, r1.getFloor());
  }

  @Test
  void testGetId(){
    assertEquals(100, r1.getId());
    r1 = new Room ("newRoom", 2123, 2);
    assertEquals(2123, r1.getId());
  }

  @Test
  void testGetName(){
    assertEquals("Classroom", r1.getName());
    r1 = new Room ("newRoom", 100, 2);
    assertEquals("newRoom", r1.getName());
  }


  @Test
  void testRemoveBooking(){
    r1.bookRoom(d1,b1);
    assertEquals(1, r1.getSchedule().size());
    r1.removeBooking();
    assertEquals(0, r1.getSchedule().size());
  }
}



