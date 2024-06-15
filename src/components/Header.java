package components;
import java.awt.*;
import javax.swing.*;

/**
 *  basic header component
 *  
 *  @author David Wolf
 *  @version 2024-06-14
 */

public class Header extends JPanel{

    public JPanel main;

    public Header(String title, boolean backVisible){
        main = new JPanel();
        
        //set max size to be able to stack component vertically 
        main.setPreferredSize(new Dimension(0, 40));
        main.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        main.setAlignmentX(Component.CENTER_ALIGNMENT);

        //create label based on title
        JLabel mid = new JLabel(title, JLabel.CENTER);
        //main.setBackground(Color.BLUE);

        // Icon quiz = new ImageIcon(Header.class.getResource("/assets/quiz.png"));
        // System.out.println("Icon Width: " + quiz.getIconWidth());
        // System.out.println("Icon Height: " + quiz.getIconHeight());

        main.add(mid, JLabel.CENTER);
    }
}
