package modelTests;

import model.Building;
import model.Booking;
import model.Date;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BuildingTest {

  Building b1;

  @BeforeEach
  void runBefore(){
    b1 = new Building();
  }


  @Test
  void testRoomList(){
    assertEquals(0, b1.getRooms().size());
    b1.addRoom("tab", 100, 1);
    assertEquals(1, b1.getRooms().size());
    assertEquals("tab", b1.getRooms().get(0).getName());
  }

  @Test
  void testGetRoomNumber(){
    assertEquals(0, b1.getTotalRooms());
    b1.addRoom("tab", 100, 1);
    assertEquals( 1, b1.getTotalRooms());
  }

  @Test
  void testAddRooms(){
    assertEquals(0, b1.getRooms().size());
    b1.addRoom("tab", 100, 1);
    assertEquals(1, b1.getRooms().size());
    assertEquals("tab", b1.getRooms().get(0).getName());
  }

  @Test
  void testRemoveRooms(){
    assertEquals(0, b1.getRooms().size());
    b1.addRoom("tab", 100, 1);
    assertEquals(1, b1.getRooms().size());
    assertEquals("tab", b1.getRooms().get(0).getName());
    b1.removeRoom(200);
    assertEquals(1, b1.getRooms().size());
    b1.removeRoom(100);
    assertEquals(0, b1.getRooms().size());
  }
@Test
  void testBookRoom(){
    b1.addRoom("tab", 100, 1);
    Date d1 = new Date(10, 10, 2010);
    Booking book = new Booking(1,1, "felix");
    b1.bookRoom(200,d1,book);
    assertFalse(b1.getRooms().get(0).isBookedAtDate(d1, book));
    b1.bookRoom(100,d1,book);
    assertTrue(b1.getRooms().get(0).isBookedAtDate(d1, book));
    b1.bookRoom(100,d1,book);
    assertFalse(b1.bookRoom(100,d1,book));
  }
@Test
  void testCancelBook(){
    b1.addRoom("tab", 100, 1);
    Date d1 = new Date(10, 10, 2010);
    Booking book = new Booking(1,1, "felix");
    b1.bookRoom(100,d1,book);
    assertTrue(b1.getRooms().get(0).isBookedAtDate(d1, book));
    b1.cancelBook(200);
    assertTrue(b1.getRooms().get(0).isBookedAtDate(d1, book));
    b1.cancelBook(100);
    assertFalse(b1.getRooms().get(0).isBookedAtDate(d1, book));
  }
  @Test
  void testGetSchedule(){
    b1.addRoom("tab", 100, 1);
    Date d1 = new Date(10, 10, 2010);
    Booking book = new Booking(1,1, "felix");
    b1.bookRoom(100,d1,book);
    assertEquals(b1.getRooms().get(0).getSchedule(), b1.getSchedule(100));
    assertEquals(0,b1.getSchedule(101).size());
  }





}

