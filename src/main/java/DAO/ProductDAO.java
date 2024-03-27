package DAO;

import model.Client;
import model.Product;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class ProductDAO extends AbstractDAO<Product> {

    private String createInsertQuery() {
        String str = "INSERT INTO product (name, quantity, price) VALUES (?, ?, ?)";
        return str;
    }

    /**
     * metoda insereaza un produs pe baza datelor date ca si parametrii
     * @param name
     * @param quantity
     * @param price
     * @return
     */
    public Product insert(String name, int quantity, double price) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, quantity);
            statement.setDouble(3, price);
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

    private String createUpdateQuery() {
        return "UPDATE product SET name=?, quantity=?, price=? WHERE id=?";
    }


    /**
     * metoda modifica un produs pe baza id-ului sau
     * @param value
     * @param name
     * @param quantity
     * @param price
     * @return
     */
    public Product updateByID(Integer value, String name, Integer quantity, Double price) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdateQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, quantity);
            statement.setDouble(3, price);
            statement.setInt(4, value);
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


    /**
     * metoda modifica un produs
     * se primeste ca si parametru intregul obiect, iar modificarea se face oe baza parametrilor dati in setarea statementului
     * @param product
     */
    public void update(Product product) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdateQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getQuantity());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getID());
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