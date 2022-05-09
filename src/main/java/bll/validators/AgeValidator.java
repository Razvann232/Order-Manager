package bll.validators;

import model.Client;

/**
 * Checks if the given age is correct according to database standards.
 * @author Pinzariu Razvan-George
 */
public class AgeValidator implements Validator<Client>{
    private static final int MIN_AGE = 18;

    @Override
    public void validate(Client client) {
        if(client.getAge() < 0){
            throw new IllegalArgumentException("Age cannot be negative!");
        }
        if(client.getAge() < MIN_AGE){
            throw new IllegalArgumentException("You need to be over 18 to be a client!");
        }
    }
}
