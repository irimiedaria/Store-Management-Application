package BLL;
import model.Product;
import DAO.ProductDAO;
import BLL.validators.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBll {

    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    public ProductBll() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new validateQuantityForProduct());
        validators.add(new validatePriceForProduct());
        productDAO = new ProductDAO();
    }

    /**
     * metoda cauta un produs dupa id-ul dat ca si parametru
     * daca nu se gaseste id-ul produsului in baza de date se arunca o exceptie
     * @param id
     * @return
     * se returneaza obiectul daca acesta a fost gasit
     */
    public Product findProdusById(int id) {
        Product produs = productDAO.findById(id);
        if (produs == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return produs;
    }

    /**
     * metoda cauta toate produsele din baza de date si creeaza o lista cu acestea
     * @return
     * se returneaza lista creata
     */
    public List<Product> findAll(){
        List<Product> result = productDAO.findAll();
        return result;
    }

    /**
     * metoda sterge un produs pe baza id-ului dat ca si parametru
     * @param id
     */
    public void deleteByID(Integer id) {
        ProductDAO dao = new ProductDAO();
        dao.delete("ID", id);
    }

    /**
     * metoda insereaza un nou produs pe baza datelor date ca si parametrii
     * se verifica daca cantitatea si pretul sunt unele valide
     * @param name
     * @param quantity
     * @param price
     */
    public void insert(String name, int quantity, double price) {
        Product product = new Product(name, quantity, price);
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        productDAO.insert(name,quantity,price);
    }

    /**
     * se face schimbarea pe baza id-ului
     * valoarea value va reprezenta id-ul in acest caz, dar poate prelua si alte valori
     * @param value
     * @param name
     * @param quantity
     * @param price
     */
    public void updateByID(Integer value, String name, Integer quantity, Double price) {
        Product p = new Product(name, quantity, price);
        for (Validator<Product> v : validators) {
            v.validate(p);
        }
        productDAO.updateByID(value, name,quantity, price);
    }

    /**
     * metoda face schimbarea numelui unui produs pe baza parametrului dat
     * @param productId
     * @param newName
     */
    public void updateName(int productId, String newName) {
        Product product = productDAO.findById(productId);
        if (product != null) {
            product.setName(newName);
            productDAO.update(product);
        }
    }

    /**
     * metoda face modificarea cantitatii pe baza parametrului dat
     * se verifica daca cantitatea dorita este una valida
     * @param productId
     * @param newQuantity
     */
    public void updateQuantity(int productId, int newQuantity) {
        Product product = productDAO.findById(productId);
        product.setQuantity(newQuantity);

        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        if (product != null) {
            productDAO.update(product);
        }
    }

    /**
     * metoda face schimbarea pretului produsului pe baza id-ului dat ca si parametru
     * se verifica daca pretul are o valoare valida
     * @param productId
     * @param newPrice
     */
    public void updatePrice(int productId, double newPrice) {
        Product product = productDAO.findById(productId);
        product.setPrice(newPrice);

        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        if (product != null) {
            productDAO.update(product);
        }
    }
}