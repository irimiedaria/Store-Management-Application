package BLL.validators;

import model.Orders;

public class validateQuantityForOrders implements Validator<Orders> {
    /**
     * metoda verifica daca cantitatea comenzii este mai mare decat 0
     * cantitatea trebuie sa fie una pozitiva si diferita de 0
     * @param order
     */
    @Override
    public void validate(Orders order) {
        if (order.getQuantity() <= 0) {
            throw new IllegalArgumentException("Insufficient quantity");
        }
    }
}
