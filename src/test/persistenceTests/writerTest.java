package persistenceTests;

import model.Booking;
import model.Building;
import model.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Reader;
import persistence.Writer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class writerTest {

  Writer writer;
  Reader reader;
  Building b1;
  Building b2;

  @BeforeEach
  void runBefore() throws IOException {
    writer = new Writer("./data/TestBuilding.ser");
    reader = new Reader("./data/TestBuilding.ser");
    b1 = new Building();
  }

  @Test
  void testSaveFile() throws IOException, ClassNotFoundException {
    b1.addRoom("test", 1,3);
    Date date = new Date(1,1,2020);
    Booking book = new Booking(1,1,"tester");
    b1.bookRoom(1,date,book);
    writer.saveFile(b1);
    b2 = (Building) reader.loadObject();
    assertEquals(b2.getRooms().size(), b1.getRooms().size());
    assertEquals(b2.getRooms().get(0).getId(), b1.getRooms().get(0).getId());
    assertEquals(b2.getRooms().get(0).getName(), b1.getRooms().get(0).getName());
    assertEquals(b2.getRooms().get(0).getFloor(), b1.getRooms().get(0).getFloor());
    assertEquals(b2.getSchedule(1).size(), b1.getSchedule(1).size());

    for (Date date1: b1.getSchedule(1).keySet()) {
      for (Date date2 : b2.getSchedule(1).keySet()) {
        assertEquals(date1.getDay(),date2.getDay());
        assertEquals(date1.getMonth(),date2.getMonth());
        assertEquals(date1.getYear(),date2.getYear());
        assertEquals(date1.getYear(),date2.getYear());
        assertEquals(b1.getSchedule(1).get(date1).getTime(),b2.getSchedule(1).get(date2).getTime());
        assertEquals(b1.getSchedule(1).get(date1).getDuration(),b2.getSchedule(1).get(date2).getDuration());
        assertEquals(b1.getSchedule(1).get(date1).getBooker(),b2.getSchedule(1).get(date2).getBooker());
      }
    }
    writer.close();
  }
}
