package components;
import java.awt.*;
import javax.swing.*;

public class Header{

    public JPanel main;

    JLabel left;
    JLabel mid;
    JLabel right;

    public Header(JFrame frame){
        main = new JPanel(new GridLayout(1,3));
        main.setPreferredSize(new Dimension(frame.getWidth(), 100));
        //main.setBackground(Color.BLUE);

        // Icon quiz = new ImageIcon(Header.class.getResource("/assets/quiz.png"));
        // System.out.println("Icon Width: " + quiz.getIconWidth());
        // System.out.println("Icon Height: " + quiz.getIconHeight());

        left = new JLabel();
        mid = new JLabel("Quiz App", JLabel.CENTER);
        right =  new JLabel();

        main.add(left);
        main.add(mid);
        main.add(right);
    }
}
