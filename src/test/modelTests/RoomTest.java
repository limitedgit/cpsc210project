package modelTests;

import model.Booking;
import model.Date;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {
  Room r1;
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

  }



  @Test
  void testBookRoom() {
    assertTrue(r1.bookRoom(d1, b1));
    assertFalse(r1.bookRoom(d1, b1));
    assertEquals(1, r1.getSchedule().size());
  }

  @Test
  void testIsBookedAtTime(){

      assertFalse(r1.isBookedAtDate(d1, b1));
      r1.bookRoom(d1,b1);
      assertTrue(r1.isBookedAtDate(d1, b1));
      r1.bookRoom(d2, b2);
      assertTrue(r1.isBookedAtDate(d2, b2));
      b1 = new Booking(10,56, "Felix");
      d2 = new Date(1,24,2020);
      r1.bookRoom(d1,b1);
      assertTrue(r1.isBookedAtDate(d2, b2));
  }

  @Test
  void testIsBookedAtEndStartTimes(){

    d2 = new Date(1,27,2008);
    b2 = new Booking(1,26, "Felix");

    d3 = new Date(1,26,2008);
    b3 = new Booking(2,24, "Felix");
    r1.bookRoom(d2,b2);
    assertTrue(r1.isBookedAtDate(d3, b3));
    d3 = new Date(1,28,2008);
    b3 = new Booking(1,24, "Felix");
    assertTrue(r1.isBookedAtDate(d3, b3));
    d3 = new Date(1,28,2008);
    b3 = new Booking(4,24, "Felix");
    assertFalse(r1.isBookedAtDate(d3, b3));
    b3 = new Booking(3,24, "Felix");
    assertTrue(r1.isBookedAtDate(d3, b3));
    d3 = new Date(1,26,2008);
    b3 = new Booking(0,24, "Felix");
    assertFalse(r1.isBookedAtDate(d3, b3));
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



