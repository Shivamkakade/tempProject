package project.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    final static String configFilePath = System.getProperty("user.dir") + "/src/main/resources/config.properties";

    public static String getProperty(final String propertyName) {
        String propertyValue;
        try {
            FileInputStream fileInputStream = new FileInputStream(configFilePath);
            Properties properties = new Properties();
            properties.load(fileInputStream);
            propertyValue = properties.getProperty(propertyName);
        } catch (Exception e) {
            throw new RuntimeException(propertyName + "Not found in file");
        }
        return propertyValue;

    }
}
