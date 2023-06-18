package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.File;
import java.util.Objects;

public class FileManager {
    private final File directory;

    public FileManager(String directory) {
        this.directory = new File(directory);
    }

    public ArrayList<String> readFileContents(String fileName) {
        String path = "./resources/" + fileName;
        try {
            return new ArrayList<>(Files.readAllLines(Path.of(path)));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл отсутствует в нужной директории.");
            return new ArrayList<>();
        }
    }

    public ArrayList<String> getFileNames(String startingSymbol) {
        ArrayList<String> filesNames = new ArrayList<>();

        for (File item : Objects.requireNonNull(directory.listFiles())) {
            if (item.getName().startsWith(startingSymbol)) {
                filesNames.add(item.getName());
            }
        }
        return filesNames;
    }
}
