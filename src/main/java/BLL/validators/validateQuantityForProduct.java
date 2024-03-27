package BLL.validators;

import model.Product;

public class validateQuantityForProduct implements Validator<Product>{
    /**
     * metoda verifica daca cantitatea produsului primit ca si parametru este mai mare decat 0
     * cantitatea trebuie sa fie pozitiva si diferita de 0
     * @param p
     */
    public void validate(Product p) {
        if (p.getQuantity() <= 0) {
            throw new IllegalArgumentException("Insufficient quantity");
        }
    }
}