package bll.validators;

import model.Product;

/**
 * Checks if the given price is correct according to database standards.
 * @author Pinzariu Razvan-George
 */
public class ProductPriceValidator implements Validator<Product>{
    private static final double MIN_PRICE = 0.1;

    @Override
    public void validate(Product product) {
        if(product.getPrice() < MIN_PRICE){
            throw new IllegalArgumentException("The price is too low!");
        }
    }
}
