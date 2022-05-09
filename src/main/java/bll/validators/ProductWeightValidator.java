package bll.validators;

import model.Product;

/**
 * Checks if the given weight is correct according to database standards.
 * @author Pinzariu Razvan-George
 */
public class ProductWeightValidator implements Validator<Product>{
    private static final int MIN_WEIGHT = 0;
    private static final int MAX_WEIGHT = 1000;

    @Override
    public void validate(Product product) {
        if(product.getWeight() < MIN_WEIGHT){
            throw new IllegalArgumentException("Invalid weight!");
        }
        if(product.getWeight() > MAX_WEIGHT){
            throw new IllegalArgumentException("The product is too heavy!");
        }
    }
}
