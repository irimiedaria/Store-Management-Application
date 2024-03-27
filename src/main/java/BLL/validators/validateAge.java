package BLL.validators;
import model.Client;

public class validateAge implements Validator<Client> {
    /**
     * metoda verifica daca varsta clientului primit ca si parametru este mai mare decat 0 (pentru a fi una posibila)
     * De asemenea verifica daca este mai mica decat 100, varsta aleasa ca si maxim realist
     * @param c
     */
    public void validate(Client c) {
        if (c.getAge() <= 0 || c.getAge() > 100) {
            throw new IllegalArgumentException("Age not possible or too old!");
        }
    }

}