package BLL;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import BLL.validators.validateQuantityForOrders;
import model.Product;
import BLL.validators.Validator;
import DAO.OrderDAO;
import model.Orders;

public class OrdersBll {

    private List<Validator<Orders>> validators;
    private OrderDAO orderDAO;

    public OrdersBll() {
        validators = new ArrayList<Validator<Orders>>();
        validators.add(new validateQuantityForOrders());

        orderDAO = new OrderDAO();
    }

    /**
     * metoda cauta o comanda dupa id-ul sau dat ca si parametru
     * daca rezultatul este null rezulta ca nu s-a gasit comanda cu id-ul respectiv
     * @param id
     * @return order
     * se returneaza obiectul gasit din baza de date
     */
    public Orders findOrderById(int id) {
        Orders order = orderDAO.findById(id);
        if (order == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return order;
    }

    /**
     * metoda preia toate comenzile din baza de date si formeaza o lista cu acestea
     * @return
     * se returneaza aceasta lista
     */
    public List<Orders> findAll(){
        OrderDAO dao = new OrderDAO();
        List<Orders> result = dao.findAll();
        return result;
    }

    /**
     * metoda sterge o comanda pe baza id-ului sau
     * @param id
     */
    public void deleteByID(int id) {
        OrderDAO dao = new OrderDAO();
        dao.delete("ID", id);
    }

    /**
     * metoda creeaza un obiect de tipul Produs si cauta id-ul acestuia pe baza id-ului primit ca si parametru
     * acesta este stocat in p
     * de asemenea, se face si pentru obiectul de tip Orders
     * avem nevoie de acestea pentru a accesa detalii despre comanda si produs
     * se verifica daca cantitatea pe care o dorim este mai mare decat cea posibila din produs, pentru a o putea utiliza
     * diferenta care trebuie scazuta se face intre vechea cantitate a comezii si cea noua
     * rezultat negativ => dorim o cantitate mai mare
     * rezultat pozitiv => dorim o cantitate mai mica
     * se modifca cantitatea produsului scazand sau adunand diferenta, si se modifica cantitatea comenzii cu cea primita ca si parametru
     * @param orderID
     * @param idClient
     * @param idProduct
     * @param quantity
     * @return
     * se returneaza true daca s-a efectuat modificarea si false daca cantitatea nu este suficienta pentru a se putea realiza
     */
    public boolean updateOrder(Integer orderID, Integer idClient, Integer idProduct, Integer quantity) {
        ProductBll bllProduct = new ProductBll();
        Product p = bllProduct.findProdusById(idProduct);
        OrdersBll obll =new OrdersBll();
        Orders orders = obll.findOrderById(orderID);
        if(quantity > p.getQuantity()){
            return false;
        }
        else {
            Integer oldQuantity = p.getQuantity();
            Integer differenceQuantity = orders.getQuantity() - quantity;
            Integer newQuantity = 0;
            if(differenceQuantity < 0) {
                newQuantity = oldQuantity + differenceQuantity;  //ar fi scadere doar ca rezultatul difference quantity e negativ si se schimba semnul
            } else if (differenceQuantity > 0) {
                newQuantity = oldQuantity + differenceQuantity; //aici e pozitiv, deci tot plus
            }
            validateNewQuantity(bllProduct, newQuantity, p);

            p.setQuantity(newQuantity);
            bllProduct.updateQuantity(idProduct, newQuantity);

            Orders o = new Orders(idClient, idProduct, quantity);
            for (Validator<Orders> v : validators) {
                v.validate(o);
            }
            orderDAO.updateOrder(orderID, idClient, idProduct, quantity);
            return true;
        }
    }

    /**
     * metoda insereaza o noua comanda noua, pe baza datelor date ca si parametrii
     * se verifica daca cantitatea dorita este una posibila, daca exista o cantitate suficienta de produse
     * @param idClient
     * @param idProduct
     * @param quantity
     * @return
     * se returneaza true daca s-a putut efectua modificarea si false daca cantitatea nu este suficienta
     */
    public boolean insertOrder(Integer idClient, Integer idProduct, Integer quantity) {
        ProductBll bllProduct = new ProductBll();
        Product p = bllProduct.findProdusById(idProduct);
        if(quantity > p.getQuantity()){
            return false;
        }
        else {
            Integer newQuantity = p.getQuantity() - quantity;
            validateNewQuantity(bllProduct, newQuantity, p);
            Orders o = new Orders(idClient, idProduct, quantity);
            for (Validator<Orders> v : validators) {
                v.validate(o);
            }
            orderDAO.insertOrder(idClient, idProduct, quantity);
            return true;
        }
    }

    /**
     * metoda este una "ajutatoare" pentru metoda de inserare
     * se verifica daca cantitatea ramasa a produsului este una pozitiva, atunci ii facem update
     * se verifica daca cantitatea ramasa a produsului este 0, atunci produsul trebuie sters
     * @param bllProduct
     * @param newQuantity
     * @param p
     */
    public void validateNewQuantity(ProductBll bllProduct, Integer newQuantity, Product p) {
        if(newQuantity>0) {
            bllProduct.updateByID(p.getID(), p.getName(), newQuantity, p.getPrice());
        }
        if(newQuantity == 0) {
            bllProduct.deleteByID(p.getID());
        }
    }

}