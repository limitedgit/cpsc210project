package persistence;

import java.io.*;

// a class that reads in data from a file
public class Reader {

    FileInputStream fileIn;
    ObjectInputStream in;

    //MODIFIES: this
    //EFFECTS: initializes a new FileInput and ObjectInput
    public Reader(String fileName) throws IOException {
        fileIn = new FileInputStream(fileName);
        in = new ObjectInputStream(fileIn);
    }

    //REQUIRES: in has been initialized
    //EFFECTS: returns the current object in the ObjectInputStream
    public Object loadObject() throws IOException, ClassNotFoundException {
        return in.readObject();
    }


    //EFFECTS: closes the Reader
    public void  close() throws IOException {
        fileIn.close();
        in.close();
    }
}
