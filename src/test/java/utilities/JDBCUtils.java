package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utilities.Constants.*;

public class JDBCUtils {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    /**
     * This method will establish connection with Oracle SQL data base
     */
    public static void establishConnection() {
        try {
            connection = DriverManager.getConnection(
                    PropertyUtils.getProperty(DBURL),
                    PropertyUtils.getProperty(DBUsername),
                    PropertyUtils.getProperty(DBPassword)
            );
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * This method will return list of map from the result of executed query
     *
     * @param query
     * @return
     * @throws SQLException
     */
    public static List<Map<String, Object>> runQuery(String query) throws SQLException {
        List<Map<String, Object>> data = new ArrayList<>();

        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ResultSetMetaData rMetaData = resultSet.getMetaData();

        while (resultSet.next()) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 1; i <= rMetaData.getColumnCount(); i++) {
                map.put(rMetaData.getColumnName(i), resultSet.getObject(rMetaData.getColumnName(i)));
            }
            data.add(map);
        }
        return data;
    }

    /**
     * This method will close connections to database
     *
     * @throws SQLException
     */
    public static void closeDatabase() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        if (statement != null) {
            connection.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }
}
