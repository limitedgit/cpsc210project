package modelTests;

import model.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DateTest {

  Date d1;

  @BeforeEach

  void runBefore() {
    d1 = new Date(10, 12, 2020);
  }

  @Test
  void testAddDays() {
    d1 = new Date(10, 12 + 365, 2020);
    assertEquals(2021, d1.getYear());
    assertEquals(11, d1.getDay());
    assertEquals(10, d1.getMonth());

  }

  void testAddMonths() {
    d1 = new Date(10 + 12 * 24, 12, 2020);
    assertEquals(2044, d1.getYear() );
    assertEquals(12, d1.getDay());
    assertEquals(10, d1.getMonth());
  }



}

