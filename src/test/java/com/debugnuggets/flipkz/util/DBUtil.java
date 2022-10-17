package com.debugnuggets.flipkz.util;

import com.debugnuggets.flipkz.pages.ProductPage;
import com.mongodb.DB;
import org.openqa.selenium.WebDriver;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private static DBUtil instance;

    private DBUtil (){

    }

    public static DBUtil getInstance() {
        if (instance == null) {
            instance = new DBUtil();
        }
        return instance;
    }

    public void createConnection() {
        String dbUrl = "jdbc:postgresql://localhost:5432/testingFlipKz";
        String dbUsername = "postgres";
        String dbPassword = "123";
        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void executeQuery(String query) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(query);
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getColumnNames(String query) {
        executeQuery(query);
        List<String> columns = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            columns.add(rsmd.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }

    public List<Map<String, Object>> getQueryResultMap(String query) {
        createConnection();
        List<Map<String, Object>> rowList = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                Map<String, Object> colNameValueMap = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    colNameValueMap.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }
                rowList.add(colNameValueMap);
            }
        }
        catch(SQLException e){
                e.printStackTrace();
        }
            return rowList;
    }

    public List<List<Object>> getQueryResultList(String query) {
        executeQuery(query);
        List<List<Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                rowList.add(row);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowList;
    }

    public List<Object> getColumnData(String query, String column) {
        executeQuery(query);
        List<Object> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
           rsmd = resultSet.getMetaData();
           while (resultSet.next()) {
               rowList.add(resultSet.getObject(column));
           }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return rowList;
    }

    public void destroy() {
        try {
            if (resultSet != null)
            {
                resultSet.close();
            }
            if (statement != null)
            {statement.close();
            }
            if (connection != null)
            {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


