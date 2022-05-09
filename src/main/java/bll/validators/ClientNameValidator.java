package bll.validators;

import model.Client;

import java.util.regex.Pattern;

/**
 * Checks if the given name is correct according to database standards.
 * @author Pinzariu Razvan-George
 */
public class ClientNameValidator implements Validator<Client>{
    private static final String NAME_PATTERN = "[A-Za-z]+[ ][A-Za-z]+";

    @Override
    public void validate(Client client) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if(!pattern.matcher(client.getName()).matches()){
            throw new IllegalArgumentException("Name is not valid!");
        }
    }
}
