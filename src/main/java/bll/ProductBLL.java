package bll;

import bll.validators.*;
import dao.ProductDao;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Handles operations with the products: search, deletion, insertion etc.
 * @author Pinzariu Razvan-George
 */
public class ProductBLL {

    private final List<Validator<Product>> validators;

    /**
     * Initializes the predefined validators for a Product object:
     * name, price, quantity and weight
     */
    public ProductBLL(){
        validators = new ArrayList<>();
        validators.add(new ProductNameValidator());
        validators.add(new ProductPriceValidator());
        validators.add(new ProductQuantityValidator());
        validators.add(new ProductWeightValidator());
    }

    /**
     * Searches for a product in the database using the given id.
     * @param id
     * The id of the product.
     * @return
     * The found product. In case no product with the given id is found, a NoSuchElementException is thrown.
     */
    public Product findProductById(int id){
        Product pr = ProductDao.findById(id);
        if(pr == null){
            throw new NoSuchElementException("The Product with id = " + id + "wasn't found!");
        }
        return pr;
    }

    /**
     * Validates and inserts a given product to the database.
     * @param pr
     * The given product
     * @return
     * The new id of the added product. In case of failure -1 is returned.
     */
    public int insertProduct(Product pr){
        for(Validator<Product> v : validators){
            v.validate(pr);
        }
        return ProductDao.insert(pr);
    }

    /**
     * Deletes a given product from the database.
     * @param pr
     * The product to be deleted.
     * @return
     * True or false depending on whether the product was found or not.
     */
    public boolean deleteProduct(Product pr){
        return ProductDao.delete(pr);
    }

    /**
     * Updates a product in the database.
     * @param pr
     * The product to be updated.
     * @param aux
     * An auxiliary product containing the new data. All its field will replace
     * those of the given product.
     * @return
     * True or false depending whether the update was successful.
     */
    public boolean updateProduct(Product pr, Product aux){
        for(Validator<Product> v : validators){
            v.validate(aux);
        }
        return ProductDao.update(pr, aux);
    }

    /**
     * Selects all the products from the database.
     * @return
     * A list of objects with all the products available.
     */
    public List<Object> selectAll(){
        return ProductDao.selectAll();
    }

    /**
     * Counts the number of products in the database.
     * @return
     * -1 in case of failure, the number of products in case of success
     */
    public int countProducts(){
        return ProductDao.countProducts();
    }
}
