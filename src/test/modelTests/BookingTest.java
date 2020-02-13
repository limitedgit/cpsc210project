package modelTests;

import model.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {
  Booking b1;

  @BeforeEach
  void runBefore() {
    b1 = new Booking(12, 12, "Fredericka");
  }

  @Test
  void testGetDuration(){
    assertEquals(12, b1.getDuration());
  }

  @Test
  void testGetTime(){
    assertEquals(12, b1.getTime());
  }

  @Test
  void testGetBooker(){
    assertEquals("Fredericka", b1.getBooker());
  }

}
