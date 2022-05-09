package bll.validators;

import model.Client;

import java.util.regex.Pattern;

/**
 * Checks if the given address is correct according to database standards.
 * @author Pinzariu Razvan-George
 */
public class AddressValidator implements Validator<Client> {
    private static final String ADDRESS_PATTERN = "(Str. )[a-zA-Z ]+( Nr. )[0-9]+";

    @Override
    public void validate(Client client) {
        Pattern pattern = Pattern.compile(ADDRESS_PATTERN);
        if(!pattern.matcher(client.getAddress()).matches()){
            throw new IllegalArgumentException("Address is not valid!");
        }
    }
}
