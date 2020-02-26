package persistence;

import java.io.*;


// a class that writes data to a file
public class Writer {
    FileOutputStream fileOut;
    ObjectOutputStream out;


    //MODIFIES: this
    //EFFECTS: initializes a new FileOuput and ObjectOutput
    public Writer(String fileName) throws IOException {
        fileOut = new FileOutputStream(fileName);
        out = new ObjectOutputStream(fileOut);
    }


    //EFFECTS: writes an Object to a file
    public void saveFile(Object object) throws IOException {
        out.writeObject(object);
    }


    //EFFECTS: closes the writer
    public void  close() throws IOException {
        fileOut.close();
        out.close();
    }
}
