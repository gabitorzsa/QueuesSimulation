package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManagerImpl implements FileManager {
    @Override
    public ArrayList<String> readFromFile(File file, String delimitator) throws IllegalArgumentException {
        ArrayList<String> fileResult = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                String[] splittedLine = nextLine.split(",");

                if(splittedLine.length > 0){
                    for (String value:splittedLine) {
                        fileResult.add(value);
                    }
                }else{
                    fileResult.add(nextLine);
                }
            }
            sc.close();
        } catch (IllegalArgumentException | FileNotFoundException ex) {
            throw new IllegalArgumentException("File does not exists!");
        }

        return fileResult;
    }

    @Override
    public void writeToFile(File file, String data) throws IllegalArgumentException {
        FileWriter fWriter;
        try {
            fWriter = new FileWriter(file.getAbsoluteFile(), true);
            fWriter.write(data);
            fWriter.close();
        } catch (Exception ex) {
            System.out.println("Error while trying to write to the file: \n" + ex.getMessage());
        }
    }
}
