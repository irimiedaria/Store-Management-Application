package BLL.validators;

import model.Product;
public class validatePriceForProduct implements Validator<Product> {
    /**
     * metoda verifica daca pretul produsului primit ca si parametru este mai mare decat 0
     * pretul trebuie sa fie unul pozitiv si diferit de 0
     * @param product
     */
    @Override
    public void validate(Product product) {
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Price should be positive");
        }
    }
}
