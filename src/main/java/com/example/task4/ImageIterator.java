package com.example.task4;

import javafx.scene.image.Image;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;

public class ImageIterator implements Iterator {
    private int current = 0;
    private final String filetopic;

    public ImageIterator(String filetopic) {
        this.filetopic = filetopic;
    }

    private Image getImage(int index) {
        try {
            String filename = Paths.get("src/main/resources/img/" + filetopic + index + ".jpg").toUri().toString();

            URL url = new URL(filename);
            URLConnection connection = url.openConnection();
            connection.connect();

            return new Image(filename);
        } catch (Exception e) {
            current = 1;
            return getImage(1);
        }
    }

    @Override
    public boolean hasNext(int i) {
        getImage(current + i);
        return true;
    }

    @Override
    public Object next() {
        current++;
        return getImage(current);
    }

    @Override
    public Object preview() {
        if (current != 1){
            current--;
        }
        System.out.println(current);
        return getImage(current);
    }
}