package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Query {
    Connection conn = null;
    PreparedStatement preparedStatement = null;

    public void addAccommodationType(String type, String bed_type, int max_guests, String description) throws SQLException {
        preparedStatement = conn.prepareStatement("INSERT INTO public.accommodation " +
                "(type, bed_type, max_guests, description) values (?, ?, ?, ?)");
        preparedStatement.setString(1, type);
        preparedStatement.setString(2, bed_type);
        preparedStatement.setInt(3, max_guests);
        preparedStatement.setString(4, description);
        preparedStatement.executeUpdate();
    }

    public void addRoomFair(Integer value, String season) throws SQLException {
        preparedStatement = conn.prepareStatement("INSERT INTO room_fair (value, season)" +
                "values (?, ?)");
        preparedStatement.setDouble(1,value);
        preparedStatement.setString(2,season);
        preparedStatement.executeUpdate();
    }

    public void addAccommodationRoomFairRelation(int accommodation_id, int room_fair_id) throws SQLException {
        preparedStatement = conn.prepareStatement("INSERT INTO public.accommodation_room_fair_relation " +
                "(accommodation_id, room_fair_id) values (?, ?)");
        preparedStatement.setInt(1,accommodation_id);
        preparedStatement.setInt(2,room_fair_id);
        preparedStatement.executeUpdate();
    }

}
