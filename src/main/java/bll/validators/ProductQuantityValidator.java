package bll.validators;

import model.Product;

/**
 * Checks if the given quantity is correct according to database standards.
 * @author Pinzariu Razvan-George
 */
public class ProductQuantityValidator implements Validator<Product>{
    private static final int MIN_QUANTITY = 1;

    @Override
    public void validate(Product product) {
        if(product.getQuantity() < MIN_QUANTITY){
            throw new IllegalArgumentException("At least one product required!");
        }
    }
}
