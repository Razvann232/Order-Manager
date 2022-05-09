package bll.validators;

import model.Product;

import java.util.regex.Pattern;

/**
 * Checks if the given name is correct according to database standards.
 * @author Pinzariu Razvan-George
 */
public class ProductNameValidator implements Validator<Product> {
    private static final String NAME_PATTERN = "[A-Za-z ]+";

    @Override
    public void validate(Product product) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if(!pattern.matcher(product.getName()).matches()){
            throw new IllegalArgumentException("Name is not valid!");
        }
    }
}
