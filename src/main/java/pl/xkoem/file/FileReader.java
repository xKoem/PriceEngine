package pl.xkoem.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<String> readFile(String path) {
        List<String> strings = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new java.io.FileReader(new File(path)))) {
            String line;
            while ((line = reader.readLine()) != null && !line.equals("")) {
                    strings.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File path " + path + " not found.");
        } catch (IOException e) {
            System.out.println("Cannot load file " + path + ".");
        }

        return strings;
    }
}
