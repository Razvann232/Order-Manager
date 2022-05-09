package start;

import presentation.Controller;
import presentation.View;

/**
 * Initializes the controller and therefore runs the entire program.
 * @author Pinzariu Razvan-George
 */
public class Start {
    public static void main(String[] args) {
        Controller controller = new Controller(new View());
    }
}
