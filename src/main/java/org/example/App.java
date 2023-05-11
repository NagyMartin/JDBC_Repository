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
            preparedStatement = conn.prepareStatement("INSERT INTO public.accommodation " +
                    "(type, bed_type, max_guests, description) values (?, ?, ?, ?)");
            preparedStatement.setString(1, "hostel");
            preparedStatement.setString(2, "continental");
            preparedStatement.setInt(3, 2);
            preparedStatement.setString(4, "Hostel room for two guests with a continental sized bed.");
            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement("INSERT INTO room_fair (value, season)" +
                    "values (?, ?)");
            preparedStatement.setDouble(1,250);
            preparedStatement.setString(2,"Spring");
            preparedStatement.executeUpdate();

            preparedStatement = conn.prepareStatement("INSERT INTO public.accommodation_room_fair_relation " +
                    "(accommodation_id, room_fair_id) values (?, ?)");
            preparedStatement.setInt(1,1);
            preparedStatement.setInt(2,1);
            preparedStatement.executeUpdate();

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

