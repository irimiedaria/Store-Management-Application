package DAO;

import model.Client;
import model.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import connection.ConnectionFactory;
import BLL.ProductBll;

public class OrderDAO extends AbstractDAO<Orders>{

    private String createUpdateQuery() {
        return "UPDATE orders SET quantity=? WHERE id=?";
    }

    /**
     * metoda face modificarea unei comenzi pe baza datelor date ca si parametru
     * teoretic programul modifica doar valoarea cantitatii, restul datelor raman la fel dar ar avea functionalitatea de a modifica si alte campuri
     * @param value
     * @param idClient
     * @param idProduct
     * @param quantity
     */
    public void updateOrder(Integer value, Integer idClient, Integer idProduct, Integer quantity) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, value);
            statement.setInt(2, idClient);
            statement.setInt(3, idProduct);
            statement.setInt(4, quantity);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    private String createInsertQuery() {
        return "INSERT INTO orders(idClient, idProdus, quantity) VALUES (?,?,?)" ;
    }

    /**
     * metoda insereaza o noua comanda pe baza campurilor date ca si parametrii
     * @param idClient
     * @param idProdus
     * @param quantity
     * @return
     */
    public Orders insertOrder(Integer idClient, Integer idProdus, Integer quantity) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, idClient);
            statement.setInt(2, idProdus);
            statement.setInt(3, quantity);
            System.out.println(statement);
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



}