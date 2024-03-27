package DAO;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 * clasa este una generica, parametru T poate lua valoarea oricarui Obiect
 * se implementeaza metodele comune unei comenzi, produs si client
 * Este simplu sa utilizam aceasta clasa deoarece nu trebuie sa implementam separat toate aceste metode pentru fiecare obiect
 * @param <T>
 */


public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    public Class<T> getType() {
        return type;
    }

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     *metoda creeaz ao interogare de tip select dupa anumite criterii, date in camul field care poate lua orice valoare
     * @param field
     * @return sirul de caractere creat
     */
    public String createSelectQuery(String field) {
        StringBuilder str = new StringBuilder();
        str.append("SELECT ");
        str.append(" * ");
        str.append(" FROM ");
        str.append(type.getSimpleName());
        str.append(" WHERE " + field + " =?");
        return str.toString();
    }

    /**
     * metoda creeaza o interogare de tipul select care selecteaza toate datele dintr-un tabel
     * @return
     */
    public String createSelectAllQuery() {
        StringBuilder str = new StringBuilder();
        str.append("SELECT ");
        str.append(" * ");
        str.append(" FROM ");
        str.append(type.getSimpleName().toLowerCase());
        return str.toString();
    }

    /**
     * metoda creeaza o interogare de tipul delete care sterge din baza de date pe baza unor criterii date in campul field
     * @param field
     * @return
     */
    public String createDeleteQuery(String field) {
        StringBuilder str = new StringBuilder();
        str.append("DELETE FROM ");
        str.append(type.getSimpleName().toLowerCase());
        str.append(" WHERE " + field + " =?");
        return str.toString();
    }

    /**
     * metoda realizeaza conexiunea cu baza de date prin ConnectionFactory
     * se formeaza un rezultat cu interogarea
     * @return
     * se returneaza rezultatul respectiv
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * metoda cauta in baza d edate un obicet pe baza id-ului sau dat ca si paramatru
     * se creeaza un rezultat cu interogarea
     * @param id
     * @return
     * se returneaza obicetul gasit
     */

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("ID");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            System.out.println(statement.toString());
            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * aceasta metoda creeaza o lista care contine datele obiectului din parametrul dat
     * @param resultSet
     * @return
     */

    public List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * aceasta metoda sterge un obicet pe baza unei caracteristici date in campul value, in general id, dar se poate utiliza pentru orice camp
     * @param field
     * @param value
     */
    public void delete(String field, int value) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteQuery(field);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, value);
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