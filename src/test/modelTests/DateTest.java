package modelTests;

import model.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DateTest {

  Date d1;
  Date d2;
  Date d3;
  Date d4;

  @BeforeEach

  void runBefore() {
    d1 = new Date(10, 12, 2020);
    d2 = new Date(10, 13, 2020);
    d3 = new Date(11, 13, 2020);
    d4 = new Date(11, 13, 2021);
  }

  @Test
  void testAddDays() {
    d1 = new Date(10, 12 + 365, 2020);
    assertEquals(2021, d1.getYear());
    assertEquals(11, d1.getDay());
    assertEquals(10, d1.getMonth());

  }
  @Test
  void testAddMonths() {
    d1 = new Date(10 + 12 * 24, 12, 2020);
    assertEquals(2044, d1.getYear() );
    assertEquals(12, d1.getDay());
    assertEquals(10, d1.getMonth());
  }
  @Test
  void testIsAfter() {
    assertFalse(d1.isAfter(d2));
    assertTrue(d4.isAfter(d1));
  }



}

