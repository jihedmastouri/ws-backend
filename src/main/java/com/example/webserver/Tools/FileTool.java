package com.example.webserver.Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileTool {

    static public String getContents(File aFile) {
        StringBuilder contents = new StringBuilder();
        try {
            try (BufferedReader input = new BufferedReader(new FileReader(aFile))) {
                String line = null; //not declared within while loop
                while ((line = input.readLine()) != null) {
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return contents.toString();
    }
}
