package org.example;


import java.sql.*;

public class App {
    public static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/mydb";
    static final String USER = "postgres";
    static final String PASS = "root";

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Query queryStarter = new Query();
            queryStarter.addAccommodationType("hostel", "continental", 2,
                    "Hostel room for two guests with a continental sized bed.");
            queryStarter.addRoomFair(250, "Summer");
            queryStarter.addAccommodationRoomFairRelation(1, 1);

        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}

