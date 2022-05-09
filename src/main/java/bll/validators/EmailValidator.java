package bll.validators;

import model.Client;

import java.util.regex.Pattern;

/**
 * Checks if the given email is correct according to database standards.
 * @author Pinzariu Razvan-George
 */
public class EmailValidator implements Validator<Client>{
    private static final String EMAIL_PATTERN = "[A-Za-z_]+[@](yahoo|gmail){1}(.com|.ro){1}";

    @Override
    public void validate(Client client) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if(!pattern.matcher(client.getEmail()).matches()){
            throw new IllegalArgumentException("Email is not valid!");
        }
    }
}
