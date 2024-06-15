

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import components.Header;
import components.IntroductionMessage;
import components.Scoreboard;
import components.Settings;
import components.StartQuiz;
import data.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class builds the main frame of the user inteface, makes the component scrollable and adds the different tabs to the navigation stack
 * 
 * 
 * @author David Wolf
 * @version 2024-06-12
 * 
 */

public class Root extends JFrame {

    private JFrame root;
    private CardLayout cardLayout;
    static JPanel canvas;

    public Root(){

        //create root component
        root = new JFrame();
        
        //set default behaviour and size of frame
        root.setSize(393,852);
        root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create navigation components
        cardLayout =  new CardLayout();
        canvas = new JPanel(cardLayout);

        //create tabs and add them to the stack
        JPanel dashboard = createDashboard();
        JPanel gameScreen = createGame();

        canvas.add(dashboard, "Dashboard");
        canvas.add(gameScreen, "GameScreen");

        //create scrollable component and disable horizontal scrolling
        JScrollPane scroll = new JScrollPane(canvas);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        cardLayout.show(canvas, "Dashboard");


        //add the scrollablecompinent to the main frame
        root.add(scroll);

        //make frame visible
        root.setVisible(true);

        //force revalidate and redraw
        root.revalidate();
        root.repaint();
    }

    public JPanel createDashboard(){

        //create main panel with box layout to stack children components vertically
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        //create children components
        Header header = new Header("Quiz App", false);
        Scoreboard scoreboard = new Scoreboard();
        Settings settings = new Settings();


        //create game button
        JButton startGame = new JButton("Quiz Starten");

        //add game button listener to start game session
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                cardLayout.show(canvas, "GameScreen");
            }
        });

        //game button alignment
        startGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        startGame.setMaximumSize(new Dimension(Integer.MAX_VALUE, startGame.getPreferredSize().height));

        //add all children coponents
        main.add(header.main);
        main.add(scoreboard.main);
        main.add(settings.main);
        main.add(startGame);

        return main;
    }

    public JPanel createGame(){
        JPanel main = new JPanel();
        JLabel label = new JLabel("Game");
        main.add(label);
        return main;
    }
}
