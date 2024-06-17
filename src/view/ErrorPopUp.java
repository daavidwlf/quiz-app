package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * error pop up in case something went wrong reading/wrtiting the csv file or fetching the quesions
 * 
 * @author David Wolf
 * @version 17-06-2024
 */

public class ErrorPopUp {
    public ErrorPopUp(){
            JFrame popUp = new JFrame("ergebnis");
            popUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            String message = "Bei schreiben oder Lesen der Daten ist etwas schief gelaufen!";
            JOptionPane.showMessageDialog(popUp, message, "Ergebnis", JOptionPane.INFORMATION_MESSAGE);
    
    }
}
