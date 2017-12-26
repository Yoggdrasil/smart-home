package ru.sbt.mipt.oop.SmartHomeDir;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HomeReader {
    public static SmartHome readFromJSON(String fileName) throws IOException {
        // читает архитектуру дома из файла
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(fileName)));

        return gson.fromJson(json, SmartHome.class);
    }
}
