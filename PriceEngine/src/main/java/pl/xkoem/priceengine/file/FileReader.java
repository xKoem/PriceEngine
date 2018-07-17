package pl.xkoem.priceengine.file;

import pl.xkoem.priceengine.util.LoggerService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    private static final LoggerService logger = new LoggerService();

    public static List<String> readFile(String path) {
        List<String> strings = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new java.io.FileReader(new File(path)))) {
            String line;
            while ((line = reader.readLine()) != null && !line.equals("")) {
                    strings.add(line);
            }
        } catch (FileNotFoundException e) {
            logger.logError(FileReader.class, "Config file path " + path + " not found.");
        } catch (IOException e) {
            logger.logError(FileReader.class,"Cannot load config file " + path + ".");
        }

        return strings;
    }
}
