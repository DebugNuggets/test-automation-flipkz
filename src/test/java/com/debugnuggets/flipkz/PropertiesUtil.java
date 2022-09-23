package com.debugnuggets.flipkz;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private Properties properties;
    private static PropertiesUtil instance;

    private PropertiesUtil() {

    }

    public static PropertiesUtil getInstance() {
        if (instance == null) {
            instance = new PropertiesUtil();
        }
        return instance;
    }

    public Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
        }
        try(FileInputStream fileInputStream = new FileInputStream("application.properties");) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return properties;
    }

}
