package database;

import java.sql.*;

public class DatabaseConnection {

    public static void main(String[] args) throws SQLException {

        /**
         * In order to connect to the database, we need our URL, username, password and query
         * NOTE: This can be the interview question
         */


        String url = "jdbc:oracle:thin:@techglobal.cup7q3kvh5as.us-east-2.rds.amazonaws.com:1521/TGDEVQA";
        String username = "saeed";
        String password = "$saeed123!";
        String query = "select * from employees";

        // Create a connection to the database with the parameters stored
        Connection connection = DriverManager.getConnection(url, username, password);

        System.out.println("Database connection is successful");


        // Statement keeps the connection between DB and Automation
        // To send queries
        Statement statement = connection.createStatement();

        // ResultSet is sending the query to the DB and get the result
        ResultSet resultSet = statement.executeQuery(query);



        // ResultSetMetaData gives the information about the table.
        // You can't simply print the column values, we need to call them with iterations
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();


        System.out.println("Number of columns: " + resultSetMetaData.getColumnCount());
        System.out.println("Name of columns: " + resultSetMetaData.getColumnName(1));

        while(resultSet.next()){
            System.out.println(resultSet.getString("FIRST_NAME"));
            System.out.println(resultSet.getString("LAST_NAME"));
        }


    }
}
