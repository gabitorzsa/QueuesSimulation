package file;

import java.io.File;
import java.util.ArrayList;

public interface FileManager {
     ArrayList<String> readFromFile(File file, String delimitator) throws IllegalArgumentException;
     void writeToFile(File file, String data)  throws IllegalArgumentException;
}
