package ru.corruptzero;

import java.io.FileWriter;
import java.io.IOException;

public class FW {

    public void write(String str) {
        try (FileWriter writer = new FileWriter("task.csv", true)) {
            writer.write(str);
            writer.append('\n');
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
