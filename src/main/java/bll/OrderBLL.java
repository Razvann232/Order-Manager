package bll;

import dao.OrderDao;
import model.Order;
import model.Product;

import java.util.NoSuchElementException;

/**
 * Handles operations with the orders: search, deletion, insertion etc.
 * @author Pinzariu Razvan-George
 */
public class OrderBLL {
    /**
     * Searches for an order in the database using the given id.
     * @param id
     * The id of the order.
     * @return
     * The found order. In case no order with the given id is found, a NoSuchElementException is thrown.
     */
    public Order findOrderById(int id){
        Order order = OrderDao.findById(id);
        if(order == null){
            throw new NoSuchElementException("The order with id = " + id + "wasn't found!");
        }
        return order;
    }

    /**
     * Inserts a given order to the database.
     * @param order
     * The given order
     * @return
     * The new id of the added order. In case of failure -1 is returned.
     */
    public int insertOrder(Order order){
        return OrderDao.insert(order);
    }

    /**
     * Deletes a given order from the database.
     * @param order
     * The order to be deleted.
     * @return
     * True or false depending on whether the order was found or not.
     */
    public boolean deleteOrder(Order order){
        return OrderDao.delete(order);
    }

    /**
     * Adds a product to an order. The operation is done by inserting an element in the
     * junction table products_order in the database.
     * @param order
     * The order to which the product belongs.
     * @param pr
     * The purchased product.
     * @param quantity
     * Its given quantity.
     * @return
     * The new id of the product_order row, -1 in case of failure.
     */
    public int insertProductOrder(Order order, Product pr, int quantity){
        return OrderDao.insertProductOrder(order, pr, quantity);
    }

    /**
     * Generates a .txt file containing the products purchased, their quantity and respective price
     * and the price of the entire order.
     * @param order
     * The order whose bill is generated.
     * @return
     * True or not depending on whether the bill was generated successfully or not.
     */
    public boolean generateBill(Order order){
        return OrderDao.generateBill(order);
    }
}
