import functions.Functions;

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
        Functions.validateCSVFile();
    }
}
