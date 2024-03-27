package BLL;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import BLL.validators.validateAge;
import BLL.validators.Validator;
import DAO.ClientDAO;
import model.Client;

public class ClientBll {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    public ClientBll() {
        validators = new ArrayList<Validator<Client>>();
        validators.add(new validateAge());

        clientDAO = new ClientDAO();
    }

    /**
     * metoda cauta un client dupa id-ul sau dat ca si parametru
     * @param id
     * @return client
     * metoda returneaza clientul daca acesta a fost gasit
     */
    public Client findClientById(int id) {
        Client client = clientDAO.findById(id);
        if (client == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return client;
    }

    /**
     * metoda preia toti clientii din baza de date prin intermediul obicetului de tip ClientDAO
     * creeaza o lista cu toti acesti clienti
     * @return result
     * returneaza lista de clienti formata
     */
    public List<Client> findAll(){
        ClientDAO dao = new ClientDAO();
        List<Client> result = dao.findAll();
        return result;
    }

    /**
     * metoda sterge un client pe baza id-ului sau dat ca si parametru
     * creeaza un obicet de tipul ClientDAO pentru a-l putea sterge din baza de date
     * @param id
     */
    public void deleteByID(int id) {
        ClientDAO dao = new ClientDAO();
        dao.delete("ID", id);
    }

    /**
     * metoda insereaza un client pe baza numelui si varstei care sunt date ca si parametrii
     * nu este necesar sa inseram id-ul deoarece incrementul se face automat din baza de date
     * de asemenea, se verifica validatorii pentru ca varsta sa fie una valida
     * @param name
     * @param age
     */
    public void insert(String name, Integer age) {
        Client client = new Client(name, age);
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        clientDAO.insert(name,age);
    }

    /**
     * metoda schimba numele clientului pe baza id-ului sau dat ca si parametru
     * metoda cauta daca id-ul clientului exista, iar daca acesta este diferit de null il seteaza cu noua valoare
     * @param clientId
     * @param newName
     */
    public void updateName(int clientId, String newName) {
        Client client = clientDAO.findById(clientId);

        if (client != null) {
            client.setName(newName);
            clientDAO.update(client);
        }
    }

    /**
     * metoda schimba varsta clientului pe baza id-ului sau dat ca si parametru
     * se fac validari astfel incat sa se verifice ca varsta ce urmeaza sa fie schimbata este una valida
     * se cauta daca id-ul clientului exista, iar daca este diferit de null se face modificarea varstei
     * @param clientId
     * @param newAge
     */
    public void updateAge(int clientId, int newAge) {
        Client client = clientDAO.findById(clientId);
        client.setAge(newAge);

        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        if (client != null) {
            clientDAO.update(client);
        }
    }
}