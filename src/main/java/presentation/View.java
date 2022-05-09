package presentation;

import model.Client;
import model.Product;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A view is a user interface that displays data and enables user to modify it.
 * This predefined view is that of a warehouse manager.
 *
 * @author Pinzariu Razvan-George
 */
public class View {
    private final JFrame f_main = new JFrame("Warehouse Manager");  // graphical elements of the interface

    // =========================================================================== Client operations
    private final JLabel l_clientOperations = new JLabel("Client operations", SwingConstants.CENTER);
    private final JComboBox<Client> cb_clientList = new JComboBox<Client>();
    private final JLabel l_clientName = new JLabel("Client name:", SwingConstants.CENTER);
    private final JLabel l_clientAddress = new JLabel("Client address:", SwingConstants.CENTER);
    private final JLabel l_clientEmail = new JLabel("Client email:", SwingConstants.CENTER);
    private final JLabel l_clientAge = new JLabel("Client age:", SwingConstants.CENTER);
    private final JTextField tf_clientName = new JTextField();
    private final JTextField tf_clientAddress = new JTextField();
    private final JTextField tf_clientEmail = new JTextField();
    private final JTextField tf_clientAge = new JTextField();
    private final JButton b_addClient = new JButton("Add client");
    private final JButton b_deleteClient = new JButton("Delete client");
    private final JButton b_editClient = new JButton("Edit client");
    private final JButton b_viewClients = new JButton("View Clients");
    private JTable t_clients = null;
    private final JPanel p_clients = new JPanel();

    // =========================================================================== Product operations
    private final JLabel l_productOperations = new JLabel("Product operations", SwingConstants.CENTER);
    private final JComboBox<Product> cb_productList = new JComboBox<Product>();
    private final JLabel l_productName = new JLabel("Product name:", SwingConstants.CENTER);
    private final JLabel l_productQuantity = new JLabel("Product quantity:", SwingConstants.CENTER);
    private final JLabel l_productWeight = new JLabel("Product weight:", SwingConstants.CENTER);
    private final JLabel l_productPrice = new JLabel("Product price:", SwingConstants.CENTER);
    private final JTextField tf_productName = new JTextField();
    private final JTextField tf_productQuantity = new JTextField();
    private final JTextField tf_productWeight = new JTextField();
    private final JTextField tf_productPrice = new JTextField();
    private final JButton b_addProduct = new JButton("Add product");
    private final JButton b_deleteProduct = new JButton("Delete product");
    private final JButton b_editProduct = new JButton("Edit product");
    private final JButton b_viewProducts = new JButton("View products");
    private JTable t_products = null;
    private final JPanel p_products = new JPanel();

    // =========================================================================== Product orders
    private final JLabel l_productOrders = new JLabel("Product Orders", SwingConstants.CENTER);
    private final JComboBox<Client> cb_clientList1 = new JComboBox<>();
    private final JComboBox<Product> cb_productList1 = new JComboBox<>();
    private final JLabel l_quantity = new JLabel("Quantity:", SwingConstants.CENTER);
    private final JLabel l_client = new JLabel("Client:", SwingConstants.CENTER);
    private final JLabel l_product = new JLabel("Product:", SwingConstants.CENTER);
    private final JTextField tf_quantity = new JTextField();
    private final JButton b_placeOrder = new JButton("Place order");
    private final JButton b_addToOrder = new JButton("Add");
    private final JPanel p_orders = new JPanel();

    // =========================================================================== Output
    private final JTextField tf_output = new JTextField("Output message");
    private final JTextField tf_list = new JTextField("Order List");
    private final JPanel p_outputs = new JPanel();

    // =========================================================================== Others
    private final JPanel p_main = new JPanel();
    private final GridBagLayout ly_main = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();
    private final Border border = new LineBorder(Color.lightGray, 3);
    private final Font font = new Font("Courier", Font.BOLD,15);
    private final Insets insets = new Insets(1 ,2,1,2);

    /**
     * Initializes a predefined view of a warehouse manager.
     */
    public View(){
        gbc.insets = insets;
        p_main.setBorder(border);

        l_clientOperations.setFont(font);
        l_productOperations.setFont(font);
        l_productOrders.setFont(font);

        tf_clientName.setHorizontalAlignment(SwingConstants.CENTER);    // Settings just for aesthetic purposes
        tf_clientAddress.setHorizontalAlignment(SwingConstants.CENTER);
        tf_clientEmail.setHorizontalAlignment(SwingConstants.CENTER);
        tf_clientAge.setHorizontalAlignment(SwingConstants.CENTER);
        tf_productName.setHorizontalAlignment(SwingConstants.CENTER);
        tf_productQuantity.setHorizontalAlignment(SwingConstants.CENTER);
        tf_productWeight.setHorizontalAlignment(SwingConstants.CENTER);
        tf_productPrice.setHorizontalAlignment(SwingConstants.CENTER);
        tf_quantity.setHorizontalAlignment(SwingConstants.CENTER);
        tf_output.setHorizontalAlignment(SwingConstants.CENTER);
        tf_list.setHorizontalAlignment(SwingConstants.CENTER);
        tf_output.setFont(new Font("Courier", Font.BOLD,12));
        tf_list.setFont(new Font("Courier", Font.BOLD,12));

        tf_output.setEditable(false);
        tf_list.setEditable(false);

        p_clients.setBorder(border);
        p_orders.setBorder(border);
        p_products.setBorder(border);
        p_outputs.setBorder(border);

        p_clients.setLayout(ly_main);
        p_clients.add(l_clientOperations, setConstraints(gbc, 0, 0, 1 ,2));
        p_clients.add(cb_clientList, setConstraints(gbc, 0, 1, 1 ,2));
        p_clients.add(l_clientName, setConstraints(gbc, 0, 2, 1 ,1));
        p_clients.add(l_clientAddress, setConstraints(gbc, 0, 3, 1 ,1));
        p_clients.add(l_clientEmail, setConstraints(gbc, 0, 4, 1 ,1));
        p_clients.add(l_clientAge, setConstraints(gbc, 0, 5, 1,1));
        p_clients.add(tf_clientName, setConstraints(gbc, 1, 2, 1,1));
        p_clients.add(tf_clientAddress, setConstraints(gbc, 1, 3, 1,1));
        p_clients.add(tf_clientEmail, setConstraints(gbc, 1, 4, 1,1));
        p_clients.add(tf_clientAge, setConstraints(gbc, 1, 5, 1,1));
        p_clients.add(b_addClient, setConstraints(gbc, 0, 6, 1,2));
        p_clients.add(b_editClient, setConstraints(gbc, 0, 7, 1,2));
        p_clients.add(b_deleteClient, setConstraints(gbc, 0, 8, 1,2));
        p_clients.add(b_viewClients, setConstraints(gbc, 0, 9, 1,2));

        p_products.setLayout(ly_main);
        p_products.add(l_productOperations, setConstraints(gbc, 0, 0, 1 ,2));
        p_products.add(cb_productList,setConstraints(gbc, 0,1,1,2));
        p_products.add(l_productName,setConstraints(gbc, 0,2,1,1));
        p_products.add(l_productQuantity,setConstraints(gbc, 0,3,1,1));
        p_products.add(l_productWeight, setConstraints(gbc, 0,4,1,1));
        p_products.add(l_productPrice,setConstraints(gbc, 0,5,1,1));
        p_products.add(tf_productName,setConstraints(gbc, 1,2,1,1));
        p_products.add(tf_productQuantity,setConstraints(gbc, 1,3,1,1));
        p_products.add(tf_productWeight,setConstraints(gbc, 1,4,1,1));
        p_products.add(tf_productPrice,setConstraints(gbc, 1,5,1,1));
        p_products.add(b_addProduct,setConstraints(gbc, 0,6,1,2));
        p_products.add(b_editProduct,setConstraints(gbc, 0,7,1,2));
        p_products.add(b_deleteProduct,setConstraints(gbc, 0,8,1,2));
        p_products.add(b_viewProducts,setConstraints(gbc, 0,9,1,2));

        p_orders.setLayout(ly_main);
        p_orders.add(l_productOrders, setConstraints(gbc, 0, 0, 1 ,2));
        p_orders.add(l_client,setConstraints(gbc, 0,1,1,1));
        p_orders.add(l_product,setConstraints(gbc, 0,2,1,1));
        p_orders.add(cb_clientList1,setConstraints(gbc, 1,1,1,1));
        p_orders.add(cb_productList1,setConstraints(gbc, 1,2,1,1));
        p_orders.add(l_quantity,setConstraints(gbc, 0,3,1,2));
        p_orders.add(tf_quantity,setConstraints(gbc, 0,4,1,2));
        p_orders.add(b_addToOrder,setConstraints(gbc, 0,5,1,2));
        p_orders.add(b_placeOrder,setConstraints(gbc, 0,6,1,2));

        p_outputs.setLayout(ly_main);
        p_outputs.add(tf_output,setConstraints(gbc, 0,0,1,2));
        p_outputs.add(tf_list,setConstraints(gbc, 0,1,1,2));

        insets.set(0,0,0,0);
        p_main.setLayout(ly_main);
        p_main.add(p_clients, setConstraints(gbc,0,0,2,1));
        p_main.add(p_products, setConstraints(gbc,1,0,2,1));
        p_main.add(p_orders, setConstraints(gbc,2,0,1,1));
        p_main.add(p_outputs, setConstraints(gbc,2,1,1,1));

        f_main.setSize(1400,600);
        f_main.setContentPane(p_main);
        f_main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f_main.setVisible(true);
    }

    /**
     * Sets specific parameters to a given GridBagConstraints object.
     * @param gbc
     * The given constraints.
     * @param x
     * Vertical position in Cartesian system.
     * @param y
     * Horizontal position in Cartesian system.
     * @param height
     * Height of component in number of cells occupied.
     * @param width
     * Width of component in number of cells occupied.
     */
    private GridBagConstraints setConstraints(GridBagConstraints gbc,int x, int y, int height, int width){
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridheight = height;
        gbc.gridwidth = width;
        gbc.fill = GridBagConstraints.BOTH;
        return gbc;
    }

    //==========================================================================================Clients
    protected String[] getClientData(){ //ges all the data from the client at once
        String[] data = new String[4];
        data[0] = tf_clientName.getText();
        data[1] = tf_clientAddress.getText();
        data[2] = tf_clientEmail.getText();
        data[3] = tf_clientAge.getText();
        return data;
    }

    protected void setClientData(String[] s){   //sets all the data of the client at once
        tf_clientName.setText(s[0]);
        tf_clientAddress.setText(s[1]);
        tf_clientEmail.setText(s[2]);
        tf_clientAge.setText(s[3]);
    }

    protected void addAddClientListener(ActionListener e){
        b_addClient.addActionListener(e);
    }

    protected void addEditClientListener(ActionListener e){
        b_editClient.addActionListener(e);
    }

    protected void addDeleteClientListener(ActionListener e){
        b_deleteClient.addActionListener(e);
    }

    protected void addViewClientListener(ActionListener e){
        b_viewClients.addActionListener(e);
    }

    protected void addClientListListener(ActionListener e){
        cb_clientList.addActionListener(e);
    }

    protected void addClientToList(Client cl){  // adds a client to both lists
        cb_clientList.addItem(cl);
        cb_clientList1.addItem(cl);
    }

    protected Client getSelectedClient(){
        return (Client) cb_clientList.getSelectedItem();
    }

    protected void deleteClientFromList(Client cl){ // removes a client from both lists
        cb_clientList.removeItem(cl);
        cb_clientList1.removeItem(cl);
    }

    protected void emptyLists(){    // empties all lists(for refresh)
        cb_clientList.removeAllItems();
        cb_clientList1.removeAllItems();
        cb_productList.removeAllItems();
        cb_productList1.removeAllItems();
    }

    /**
     * Creates a separate frame with a JTable containing all the clients
     * information
     * @param data
     * Data to be stored in the rows of the table.
     * @param columns
     * Name of each column of the table.
     */
    protected void setClientTable(String [][] data, String[] columns){
        t_clients = new JTable(data, columns);
        JFrame f_clientTable = new JFrame();
        f_clientTable.add(new JScrollPane(t_clients));
        f_clientTable.setSize(750,300);
        f_clientTable.setVisible(true);
    }

    //==========================================================================================Products
    protected String[] getProductData(){    // gets all product data at once
        String[] data = new String[4];
        data[0] = tf_productName.getText();
        data[1] = tf_productQuantity.getText();
        data[2] = tf_productWeight.getText();
        data[3] = tf_productPrice.getText();
        return data;
    }

    protected void setProductData(String[] s){  // sets all product data at once
        tf_productName.setText(s[0]);
        tf_productQuantity.setText(s[1]);
        tf_productWeight.setText(s[2]);
        tf_productPrice.setText(s[3]);
    }

    protected void addAddProductListener(ActionListener e){
        b_addProduct.addActionListener(e);
    }

    protected void addEditProductListener(ActionListener e){
        b_editProduct.addActionListener(e);
    }

    protected void addDeleteProductListener(ActionListener e){
        b_deleteProduct.addActionListener(e);
    }

    protected void addViewProductListener(ActionListener e){
        b_viewProducts.addActionListener(e);
    }

    protected void addProductToList(Product pr) {   // adds a product to both lists
        cb_productList.addItem(pr);
        cb_productList1.addItem(pr);
    }

    protected void addProductListListener(ActionListener e) {
        cb_productList.addActionListener(e);
    }

    protected Product getSelectedProduct(){
        return (Product) cb_productList.getSelectedItem();
    }

    protected Client getSelectedClient1(){
        return (Client) cb_clientList1.getSelectedItem();
    }

    protected Product getSelectedProduct1(){
        return (Product) cb_productList1.getSelectedItem();
    }

    protected void deleteProductFromList(Product pr){   // removes a product from both lists
        cb_productList.removeItem(pr);
        cb_productList1.removeItem(pr);
    }

    /**
     * Creates a separate frame with a JTable containing all the products
     * information
     * @param data
     * Data to be stored in the rows of the table.
     * @param columns
     * Name of each column of the table.
     */
    protected void setProductTable(String [][] data, String[] columns){
        t_products = new JTable(data, columns);
        JFrame f_productTable = new JFrame();
        f_productTable.add(new JScrollPane(t_products));
        f_productTable.setSize(750,300);
        f_productTable.setVisible(true);
    }

    //==========================================================================================Orders

    protected void setQuantity(String s){
        tf_quantity.setText(s);
    }

    protected String getQuantity(){
        return tf_quantity.getText();
    }

    protected void addPlaceOrderListener(ActionListener e){
        b_placeOrder.addActionListener(e);
    }

    protected void addAddToOrderListener(ActionListener e){
        b_addToOrder.addActionListener(e);
    }

    protected void addClientList1Listener(ActionListener e){
        cb_clientList1.addActionListener(e);
    }

    protected void addProductList1Listener(ActionListener e) {
        cb_productList1.addActionListener(e);
    }

    protected void setComboBoxUsable(boolean b){
        cb_clientList1.setEnabled(b);
    }

    //==========================================================================================Output
    protected void setOutputText(String s){
        tf_output.setText(s);
    }

    protected void appendList(String s){    // adds a text to the end of the text field
        tf_list.setText(tf_list.getText() + s);
    }

    protected void setList(String s){
        tf_list.setText(s);
    }
}
