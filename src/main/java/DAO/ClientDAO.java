package DAO;
import model.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import connection.ConnectionFactory;

public class ClientDAO extends AbstractDAO<Client> {

    private String createInsertQuery() {
        String str = "INSERT INTO client (name, age) VALUES (?, ?)";
        return str;
    }

    /**
     * metoda insereaza un client cu numele si varsta date ca si parametrii
     * id-ul nu este necesar sa fie specificat deoarece se incrementeaza automat in baza de date
     * @param name
     * @param age
     * @return
     * se returneaza un obicet de tipul client
     */
    public Client insert(String name, Integer age) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private String createUpdateQuery(String field) {
        String str = "UPDATE client SET name=?, age=? WHERE " + field + "=?";
        return str;
    }

    /**
     * se face modificarea unui client
     * @param client
     */
    public void update(Client client) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdateQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, client.getName());
            statement.setInt(2, client.getAge());
            statement.setInt(3, client.getID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}