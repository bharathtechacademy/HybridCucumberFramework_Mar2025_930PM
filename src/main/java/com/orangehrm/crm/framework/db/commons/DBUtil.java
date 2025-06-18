package com.orangehrm.crm.framework.db.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.orangehrm.crm.framework.utilities.PropUtil;

/**
 * Utility class for database operations.
 * Provides methods to connect to a database, execute queries, and retrieve data in various formats.
 *
 * <p>This class is designed to simplify database interactions by offering reusable methods
 * for fetching raw data and converting it into a structured format such as a list of maps.</p>
 *
 * <p>It uses JDBC to connect to a PostgreSQL database and execute SQL queries.</p>
 */
public class DBUtil {
	
    public static Properties prop = PropUtil.readData("Config.properties");

    /**
     * Establishes a connection to the database and executes the given SQL query.
     * Returns the raw data as a {@link ResultSet}.
     *
     * <p>This method uses the PostgreSQL JDBC driver to connect to the database.
     * The connection details (URL, username, and password) are hardcoded for simplicity.</p>
     *
     * @param query The SQL query to be executed.
     * @return A {@link ResultSet} containing the raw data retrieved from the database.
     * @throws SQLException If a database access error occurs or the query is invalid.
     */
    public static ResultSet getData(String query) throws SQLException {
        ResultSet dataSet = null;

        // Database connection details
        String db_url = prop.getProperty("DB_URL");
        String db_user = prop.getProperty("DB_USER");
        String db_password = prop.getProperty("DB_PASS");

        // Establish a connection to the database
        Connection connection = DriverManager.getConnection(db_url, db_user, db_password);

        // Execute the query and retrieve the raw data
        dataSet = connection.createStatement().executeQuery(query);

        return dataSet;
    }

    /**
     * Executes the given SQL query and converts the retrieved data into a list of maps.
     * Each map represents a row, with column names as keys and column values as values.
     *
     * <p>This method first fetches the raw data using the {@link #getData(String)} method.
     * It then iterates through the rows and columns of the {@link ResultSet} to build the list of maps.</p>
     *
     * @param query The SQL query to be executed.
     * @return A list of maps, where each map contains key-value pairs representing column names and their corresponding values.
     * @throws SQLException If a database access error occurs or the query is invalid.
     */
    public static List<Map<String, String>> readData(String query) throws SQLException {
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();

        // Get the raw data from the database
        ResultSet dataSet = getData(query);

        // Iterate through the rows of the ResultSet
        while (dataSet.next()) { // Verify if the next row is available and move to it
            Map<String, String> rowData = new HashMap<String, String>();

            // Loop through all columns in the current row
            for (int c = 1; c <= dataSet.getMetaData().getColumnCount(); c++) {
                // Get the column name and value
                String columnName = dataSet.getMetaData().getColumnName(c);
                String columnValue = dataSet.getString(c);

                // Add the column name and value to the map
                rowData.put(columnName, columnValue);
            }

            // Add the map to the list
            dataList.add(rowData);
        }

        return dataList;
    }
}
