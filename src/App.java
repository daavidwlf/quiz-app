import controller.Functions;
import view.Root;

/**
 * This is the entry class which conatains the main method
 * 
 * 
 * @author David Wolf
 * @version 2024-06-12
 */

public class App {
    public static void main(String[] args) throws Exception {
        new Root();
        //create csv file with header if non existent
        Functions.validateCSVFile();
    }
}
