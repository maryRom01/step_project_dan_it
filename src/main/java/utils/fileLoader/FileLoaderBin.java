package utils.fileLoader;

import entity.Flight;

import java.io.*;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

public class FileLoaderBin {

    public void writeFile(String filePath, Set<Flight> data) throws IOException {
        File file = new File(filePath);
        final ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(file.toPath()));
        data.forEach(object -> {
            try {
                oos.writeObject(object);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        });
        oos.close();
    }

    public Set<Flight> readFile(String filePath) throws IOException {
        File file = new File(filePath);
        Set<Flight> flights = new HashSet<>();
        final ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(file.toPath()));
        while (true) {
            try {
                Flight obj = (Flight) ois.readObject();
                flights.add(obj);
            } catch (EOFException | ClassNotFoundException e) {
                break;
            }
        }
        ois.close();
        return flights;
    }
}
