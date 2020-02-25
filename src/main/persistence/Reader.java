//package persistence;
//
//import model.Room;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//// A reader that can read building data from a file
//public class Reader {
//    public static final String DELIMITER = ",";
//
//    // EFFECTS: returns a list of accounts parsed from file; throws
//    // IOException if an exception is raised when opening / reading from file
//    public static List<Room> readAccounts(File file) throws IOException {
//        List<String> fileContent = readFile(file);
//        return parseContent(fileContent);
//    }
//
//    // EFFECTS: returns content of file as a list of strings, each string
//    // containing the content of one row of the file
//    private static List<String> readFile(File file) throws IOException {
//        return Files.readAllLines(file.toPath());
//    }
//
//    // EFFECTS: returns a list of accounts parsed from list of strings
//    // where each string contains data for one account
//    private static List<Room> parseContent(List<String> fileContent) {
//        List<Room> rooms = new ArrayList<>();
//
//        for (String line : fileContent) {
//            ArrayList<String> lineComponents = splitString(line);
//            rooms.add(parseRooms(lineComponents));
//        }
//
//        return rooms;
//    }
//
//    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
//    private static ArrayList<String> splitString(String line) {
//        String[] splits = line.split(DELIMITER);
//        return new ArrayList<>(Arrays.asList(splits));
//    }
//
//    // REQUIRES: components has size 4 where element 0 represents the
//    // id of the next account to be constructed, element 1 represents
//    // the id, elements 2 represents the name and element 3 represents
//    // the balance of the account to be constructed
//    // EFFECTS: returns an account constructed from components
//    private static Room parseRooms(List<String> components) {
//        int id = Integer.parseInt(components.get(1));
//        String name = components.get(2);
//        int floor = Integer.parseInt(components.get(3));
//        return new Room(name, id, floor);
//    }
//}
