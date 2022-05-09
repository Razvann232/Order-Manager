package bll;

import bll.validators.*;
import dao.ClientDao;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Handles operations with the clients: search, deletion, insertion etc.
 * @author Pinzariu Razvan-George
 */
public class ClientBLL {

    private final List<Validator<Client>> validators;

    /**
     * Initializes the predefined validators for a Client object:
     * name, email, address and age
     */
    public ClientBLL(){
        validators = new ArrayList<>();
        validators.add(new ClientNameValidator());
        validators.add(new EmailValidator());
        validators.add(new AddressValidator());
        validators.add(new AgeValidator());
    }

    /**
     * Searches for a client in the database using the given id.
     * @param id
     * The id of the client.
     * @return
     * The found client. In case no client with the given id is found, a NoSuchElementException is thrown.
     */
    public Client findClientById(int id){
        Client cl = ClientDao.findById(id);
        if(cl == null){
            throw new NoSuchElementException("The client with id = " + id + "wasn't found!");
        }
        return cl;
    }

    /**
     * Validates and inserts a given client to the database.
     * @param cl
     * The given client
     * @return
     * The new id of the added client. In case of failure -1 is returned.
     */
    public int insertClient(Client cl){
        for(Validator<Client> v : validators){
            v.validate(cl);
        }
        return ClientDao.insert(cl);
    }

    /**
     * Deletes a given client from the database.
     * @param cl
     * The client to be deleted.
     * @return
     * True or false depending on whether the client was found or not.
     */
    public boolean deleteClient(Client cl){
        return ClientDao.delete(cl);
    }

    /**
     * Updates a client in the database.
     * @param cl
     * The client to be updated.
     * @param aux
     * An auxiliary client containing the new data. All its field will replace
     * those of the given client.
     * @return
     * True or false depending whether the update was successful.
     */
    public boolean updateClient(Client cl, Client aux){
        for(Validator<Client> v : validators){
            v.validate(aux);
        }
        return ClientDao.update(cl, aux);
    }

    /**
     * Selects all the clients from the database.
     * @return
     * A list of objects with all the clients available.
     */
    public List<Object> selectAll(){
        return ClientDao.selectAll();
    }

    /**
     * Counts the number of clients in the database.
     * @return
     * -1 in case of failure, the number of clients in case of success
     */
    public int countClients(){
        return ClientDao.countClients();
    }
}
