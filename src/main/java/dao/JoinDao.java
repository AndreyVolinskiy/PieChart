package dao;

import data.Database;
import model.JoinTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Andrey Volinskiy on 13.02.2018.
 */
public class JoinDao {

    private static final String GETALL = "SELECT orders.id, orders.order_name, orders.completed, clients.name, clients.phone\n" +
            "FROM clients JOIN orders ON clients.id=orders.client_id";
    private static final String GET_BY_COMPLETED = "SELECT count(id) AS count FROM orders WHERE orders.completed = 1";
    private static final String GET_BY_INCOMPLETED = "SELECT count(id) AS count FROM orders WHERE orders.completed = 0";

    public int countOfCompleted() {
        int count = 0;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_COMPLETED)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(count);
        return count;
    }

    public int countOfIncompleted() {
        int count = 0;
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_INCOMPLETED)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getInt("count");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<JoinTable> getAll() {
        List<JoinTable> list = new LinkedList<>();
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(GETALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                JoinTable joinTable = new JoinTable();
                joinTable.setId(resultSet.getLong("id"));
                joinTable.setOrderName("'" + resultSet.getString("order_name") + "'");
                joinTable.setCompleted(resultSet.getBoolean("completed"));
                joinTable.setClientName(resultSet.getString("name"));
                joinTable.setClientPhone(resultSet.getString("phone"));
                list.add(joinTable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
