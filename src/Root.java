

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import components.Header;
import components.IntroductionMessage;
import components.QuestionPanel;
import components.Scoreboard;
import components.Settings;
import components.StartQuiz;
import data.FetchQuestions;
import data.Game;
import edu.kit.aifb.atks.opentdb4j.Question;
import edu.kit.aifb.atks.opentdb4j.QuestionType;
import functions.ButtonClickHandler;
import functions.Functions;
import functions.QuestionSetCallback;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

    Game currentGame;

    private JPanel dashboard;
    private JPanel gameScreen;

    private int highscore = 0;

    ButtonClickHandler onFinish;

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
        dashboard = createDashboard();

        canvas.add(dashboard, "Dashboard");


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

    public void callback(){
            
    }

    public JPanel createDashboard(){

        //create main panel with box layout to stack children components vertically
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        //create children components
        Header header = new Header("Start", false);
        Scoreboard scoreboard = new Scoreboard();
        Settings settings = new Settings();

        List<Game> previousGames = Functions.getGames();
        highscore = calcHighscore(previousGames);


        //create game button
        JButton startGame = new JButton("Quiz Starten");

        //add game button listener to start game session
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                List<Question> questions = FetchQuestions.fetch(Settings.getAmountQuestions(), QuestionType.MULTIPLE_CHOICE, Settings.getDifficulty(), null);
                currentGame  = new Game(Settings.getAmountQuestions());
                //callback to switch to game screen only when question have been set
                currentGame.setQuestions(questions, new QuestionSetCallback() {
                    @Override
                    public void onQuestionsSet(){
                        gameScreen = createGame();
                        canvas.add(gameScreen, "GameScreen");
                        cardLayout.show(canvas, "GameScreen");
                    }
                });
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

         // Create main panel with BoxLayout to stack children components vertically
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));

        // Create header
        Header header = new Header("Quiz", false);
        main.add(header.main);      
        List<Question> gameQuestions = currentGame.getQuestions();

        ButtonClickHandler onFinish = new ButtonClickHandler(){
            @Override
            public void handle() {
                System.out.println("finish");
                createPopUp();
                dashboard = createDashboard();
                canvas.add(dashboard, "Dashboard");
                cardLayout.show(canvas, "Dashboard");
            }
        };

        // Create and add question panel
        QuestionPanel currentQuestionPanel = new QuestionPanel(gameQuestions, currentGame, highscore, onFinish);
        main.add(currentQuestionPanel.main);


        // Re-add the header to ensure it's always present

        // Revalidate and repaint the panel to update the UI

        return main;
    }

    public void createPopUp(){
        JFrame popUp = new JFrame("ergebnis");
        popUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String message = "Leider konntest du den Highscore nicht kancken!";
        List<Game> newGames = Functions.getLastGame();
        if(highscore < newGames.get(0).getScore()){
            message = "Glückwunsch, du konntest den Highscore knacken";
        }
        JOptionPane.showMessageDialog(popUp, message, "Ergebnis", JOptionPane.INFORMATION_MESSAGE);
    }

    public int calcHighscore(List<Game> games){
        int score = 0;
        for(Game current : games){
            if(current.getScore() > score){
                score = current.getScore();
            }
        }
        return score;
    }
}
