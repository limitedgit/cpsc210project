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

  @BeforeEach
  void runBefore() {
    r1 = new Room("Classroom", 100, 1);
    d1  = new Date(1,23,2020);
    b1 = new Booking(1,2, "Felix");
  }


  @Test
  void testIsBookedAtTime(){
      r1.isBookedAtTime(d1, b1);
      assertFalse(r1.isBookedAtTime(d1, b1));
      assertTrue(r1.bookRoom(1, 200,"Felix", 1, 22, 2020));
      r1.isBookedAtTime(d1, b1);
      for (Date d : r1.getSchedule().keySet()) {
          System.out.println(d.getMonth() + " " + " " + d.getDay()+ " " + d.getYear());
          System.out.println(r1.getSchedule().get(d));
      }
      assertTrue(r1.isBookedAtTime(d1, b1));

  }
}
