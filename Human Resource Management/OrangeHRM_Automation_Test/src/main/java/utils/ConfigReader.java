package main.java.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    // Static block executes only once when the class is loaded
    static {
        try {
            FileInputStream fis = new FileInputStream("config.properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("Exception while loading config.properties: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to fetch value by key
    public static String get(String key) {
        return properties.getProperty(key);
    }

    // Method to fetch value with default if not found
    public static String getOrDefault(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}

