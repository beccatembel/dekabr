package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
    /***
     * This method read properties from files using FileInputStream
     * @param pathToPropertyFile
     * @return
     * @throws IOException
     */
    public static Properties readPropertyFile(String pathToPropertyFile) throws IOException {
        Properties properties = new Properties();
        File propFile = new File(pathToPropertyFile);
        FileInputStream fileInputStream = new FileInputStream(propFile);
        properties.load(fileInputStream);

        return properties;
    }

    /***
     * This method get Property Key from provided property file
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        try {
            Properties properties = readPropertyFile("src//test//resources//config.properties");
            String value = properties.getProperty(key);
            return value;
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }
}
