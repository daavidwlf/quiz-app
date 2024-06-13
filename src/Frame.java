

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import components.Header;
import components.IntroductionMessage;
import components.Scoreboard;

import java.awt.*;

/**
 * This class builds the main frame of the user inteface
 * 
 * @author David Wolf
 * @version 2024-06-12
 * 
 */

public class Frame extends JFrame {

    static JFrame frame;

    public Frame(){
        //create root frame
        frame = new JFrame();
        
        //set default size to size of iphone 15 screen to replicate a mobile app
        frame.setSize(393,852);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        //app components
        Header header = new Header(frame);
        Scoreboard scoreboard = new Scoreboard(frame);
        IntroductionMessage introductionMessage =  new IntroductionMessage();

        //content panel to nest all the components
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        contentPanel.add(header.main);
        contentPanel.add(introductionMessage.main);
        contentPanel.add(scoreboard.main);


        //create scrollable component and disable horizontal scrolling
        JScrollPane scroll = new JScrollPane(contentPanel);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        //add the scrollablecompinent to the main frame
        frame.add(scroll, BorderLayout.CENTER);

        //make frame visible
        frame.setVisible(true);

        //force revalidate and redraw
        frame.revalidate();
        frame.repaint();
    }
}
