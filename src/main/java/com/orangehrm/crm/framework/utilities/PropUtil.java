package com.orangehrm.crm.framework.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class for handling property files.
 * Provides methods to read data from `.properties` files and load them into a {@link Properties} object.
 */
public class PropUtil {

    /**
     * Reads a `.properties` file and loads its key-value pairs into a {@link Properties} object.
     *
     * @param fileName The name of the properties file to be read. 
     *                 The file should be located in the `Config` directory under the user's working directory.
     * @return A {@link Properties} object containing the key-value pairs from the specified file.
     *         If the file is not found or an error occurs during reading, an empty {@link Properties} object is returned.
     * @throws FileNotFoundException If the specified file is not found.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public static Properties readData(String fileName) {
        Properties prop = new Properties();

        try {
            // Construct the file path using the user's working directory and the `Config` folder
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\Config\\" + fileName);

            // Load all the properties available within the file into the `prop` variable
            prop.load(fis);

        } catch (FileNotFoundException e) {
            // Print stack trace if the file is not found
            e.printStackTrace();
        } catch (IOException e) {
            // Print stack trace if an I/O error occurs
            e.printStackTrace();
        }

        return prop;
    }
}
