package org.example;

import junit.framework.TestCase;


import java.sql.*;

public class AppTest extends TestCase {
    public static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/mydb";
    static final String USER = "postgres";
    static final String PASS = "root";
    public void testApp() throws ClassNotFoundException {
        Connection conn;
        Statement stmt;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = """
                    SELECT type, value
                    FROM public.accommodation
                    JOIN accommodation_room_fair_relation ON public.accommodation.id = accommodation_room_fair_relation.accommodation_id
                    JOIN room_fair ON accommodation_room_fair_relation.room_fair_id = room_fair.id;""";

            ResultSet resultSet = stmt.executeQuery(sql);
            boolean ioCheck = false;
            while(resultSet.next()) {
                String accommodationType = resultSet.getString("type");
                double roomValue = resultSet.getDouble("value");
                if(!(accommodationType == null) && roomValue!=0){
                    ioCheck = true;
                }
                System.out.println("Accommodation type is: " + accommodationType + ", the cost for the accommodation is:  " + roomValue);
            }

            assertTrue(ioCheck);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
