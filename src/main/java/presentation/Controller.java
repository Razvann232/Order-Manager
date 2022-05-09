package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Handles the user request and modifies
 * the appropriate view as a response.
 *
 * @author Pinzariu Razvan-George
 */
public class Controller {
    private final ClientBLL clientBLL = new ClientBLL();
    private final ProductBLL productBLL = new ProductBLL();
    private final OrderBLL orderBLL = new OrderBLL();

    private final View view;

    private boolean newOrder = true;
    private Order currentOrder = null;

    public Controller(View view){
        this.view = view;
        updateCb(); //adds the clients and products to the combo boxes

        view.addClientListListener(e -> {   // sets the fields of client data with the data of the selected client
            Client cl = view.getSelectedClient();
            if(cl != null){
                String[] data = {cl.getName(), cl.getAddress(),cl.getEmail(), String.valueOf(cl.getAge())};
                view.setClientData(data);
            }
        });

        view.addProductListListener(e -> {  // sets the fields of client data with the data of the selected product
            Product pr = view.getSelectedProduct();
            if (pr != null){
                String[] data = {pr.getName(), String.valueOf(pr.getQuantity()), String.valueOf(pr.getWeight()), String.valueOf(pr.getPrice())};
                view.setProductData(data);
            }
        });

        view.addClientList1Listener(e -> {  // sets the fields of client data with the data of the selected client (the other combo box)
            Client cl = view.getSelectedClient1();
            if(cl != null){
                String[] data = {cl.getName(), cl.getAddress(),cl.getEmail(), String.valueOf(cl.getAge())};
                view.setClientData(data);
            }
        });

        view.addProductList1Listener(e -> {  // sets the fields of client data with the data of the selected product (the other combo box)
            Product pr = view.getSelectedProduct1();
            if(pr != null){
                String[] data = {pr.getName(), String.valueOf(pr.getQuantity()), String.valueOf(pr.getWeight()), String.valueOf(pr.getPrice())};
                view.setProductData(data);
                view.setQuantity("Insert desired quantity - maximum " + String.valueOf(pr.getQuantity()));
            }
        });

        view.addAddClientListener(e ->{ // add client button
            String[] data = view.getClientData();
            try{
                Client cl = new Client(data[0],data[1], data[2], Integer.parseInt(data[3]));
                cl.setId(clientBLL.insertClient(cl));
                if(cl.getId() != -1){
                view.setOutputText("Client added successfully!");
                view.addClientToList(cl);
                } else {
                    view.setOutputText("Client could not be added!");
                }
            } catch(NumberFormatException e1){
                view.setOutputText("Wrong client age!");
            } catch (IllegalArgumentException e2){
                view.setOutputText(e2.getMessage());
            }
        });

        view.addEditClientListener(e -> {   // edit client button
            String[] data = view.getClientData();
            try{
                Client cl = view.getSelectedClient();
                Client aux = new Client(data[0],data[1], data[2], Integer.parseInt(data[3]));
                if(clientBLL.updateClient(cl, aux)){
                    updateCb();
                    view.setOutputText("Client updated successfully!");
                } else {
                    view.setOutputText("Client could not be updated!");
                }
            } catch(NumberFormatException e1){
                view.setOutputText("Wrong client age!");
            } catch (IllegalArgumentException e2){
                view.setOutputText(e2.getMessage());
            }
        });

        view.addDeleteClientListener(e -> { // delete client button
            Client cl = view.getSelectedClient();
            if(clientBLL.deleteClient(cl)){
                view.setOutputText("Client deleted successfully!");
                view.deleteClientFromList(cl);
            } else {
                view.setOutputText("Client could not be deleted");
            }
        });

        view.addAddProductListener(e->{ // add product button
            String[] data = view.getProductData();
            try{
                Product pr = new Product(data[0],Integer.parseInt(data[1]), Float.parseFloat(data[2]), Float.parseFloat(data[3]));
                pr.setId(productBLL.insertProduct(pr));
                if(pr.getId() != -1){
                view.setOutputText("Product added successfully!");
                view.addProductToList(pr);
                } else {
                    view.setOutputText("Product could not be added!");
                }
            } catch(NumberFormatException e1){
                view.setOutputText("Wrong numeric data!");
            } catch (IllegalArgumentException e2){
                view.setOutputText(e2.getMessage());
            }
        });

        view.addEditProductListener(e -> {  // edit product button
            String[] data = view.getProductData();
            try{
                Product pr = view.getSelectedProduct();
                Product aux = new Product(data[0],Integer.parseInt(data[1]), Float.parseFloat(data[2]), Float.parseFloat(data[3]));
                if(productBLL.updateProduct(pr, aux)){
                    updateCb();
                    view.setOutputText("Product updated successfully!");
                } else {
                    view.setOutputText("Product could not be updated!");
                }
            } catch(NumberFormatException e1){
                view.setOutputText("Wrong numeric data!");
            } catch (IllegalArgumentException e2){
                view.setOutputText(e2.getMessage());
            }
        });

        view.addDeleteProductListener(e -> {    // delete product button
            Product pr = view.getSelectedProduct();
            if(productBLL.deleteProduct(pr)){
                view.setOutputText("Product deleted successfully!");
                view.deleteProductFromList(pr);
            } else {
                view.setOutputText("Product could not be deleted");
            }
        });

        view.addAddToOrderListener(e->{ // add to order button
            Product pr = view.getSelectedProduct1();
            Client cl = view.getSelectedClient1();

            if(newOrder) {
                currentOrder = new Order(cl.getId());
                currentOrder.setOrderId(orderBLL.insertOrder(currentOrder));
                newOrder = false;
                view.setList("Order " + currentOrder.getOrderId() + " : ");
            }

            try{
                int quantity = Integer.parseInt(view.getQuantity());
                if(quantity>pr.getQuantity()){
                    view.setOutputText("Quantity exceeds warehouse limits!");
                } else{
                    orderBLL.insertProductOrder(currentOrder, pr, quantity);
                    view.setComboBoxUsable(false);
                    view.appendList(" " + pr.getName() + "(" + quantity + "), ");
                    view.setOutputText("Product added successfully!");
                }
            } catch(NumberFormatException e1){
                view.setOutputText("Wrong numeric data!");
            }
        });

        view.addPlaceOrderListener(e->{ // place order button
            newOrder = true;
            view.setComboBoxUsable(true);
            view.setOutputText("Order placed successfully!");
            view.setList("Order list");
            updateCb();
            currentOrder.setPrice(orderBLL.findOrderById(currentOrder.getOrderId()).getPrice());
            orderBLL.generateBill(currentOrder);
        });

        view.addViewClientListener(e -> {   // generates the table with the clients (view clients button)
            generateTable(clientBLL.selectAll());
        });

        view.addViewProductListener(e -> {  // generates the table with the products (view products button)
            generateTable(productBLL.selectAll());
        });

    }

    /**
     * Generates the data for a JTable using a generic list of object received
     * from a previous query. It can be used for any class.
     * The method then uses setClientTable() from view to display the table.
     * @param list
     * The list of object it receives.
     */
    private void generateTable(List<Object> list) {
        Object o = list.get(0); // the first object - for finding constructors
        Class c = o.getClass(); // the class of the object
        
        String[] columnNames = new String[c.getDeclaredFields().length];    // array containing column names
        String[][] data = new String[list.size()][c.getDeclaredFields().length];    // matrix of strings containing data to be inserted in rows
        int i = 0, j = 0;
        for(Field field: c.getDeclaredFields()){    //  finding the fields of the object in order to generate column headers
            columnNames[i] = field.getName();;
            i++;
        }
        i = 0;
        for(Object ob: list){   // go through each object from the list
            for(Field field: c.getDeclaredFields()){    // for each of its field
                field.setAccessible(true);
                try {
                    data[i][j] = field.get(ob).toString();  // populate the rows
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                j++;
            }
            j=0;
            i++;
        }
        view.setClientTable(data, columnNames); // set the table
    }

    /**
     *  Updates the combo boxes whenever a new object is added, edited or deleted.
     */
    private void updateCb(){
        view.emptyLists();

        int nrClients = clientBLL.countClients();  //   gets the number of clients
        for(int i=1;i<=nrClients;i++){
            try{
                Client cl = clientBLL.findClientById(i);
                view.addClientToList(cl);
            } catch (NoSuchElementException e){
                nrClients++;
            }
        }

        int nrProducts = productBLL.countProducts();    //  gets the number of products
        for(int i=1;i<=nrProducts;i++){
            try{
                Product pr = productBLL.findProductById(i);
                view.addProductToList(pr);
            } catch (NoSuchElementException e){
               nrProducts++;
            }
        }
    }
}
