package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.ButtonClickHandler;
import controller.Functions;
import edu.kit.aifb.atks.opentdb4j.Question;
import model.Game;


/**
 * This class is used to mount the questions and anseres on the game screen
 * 
 * @author David Wolf
 * @version 17-06-2024
 * 
 */
public class QuestionPanel {
    List<Question> gameQuestions;
    Question currentQuestion;
    public JPanel main;

    private JTextArea question;
    private List<String> answers;

    boolean isQuestionSelected = false;
    int selectedQuestion = 100;

    JButton cont;
    JButton finish;

    int questionIndex = 0;

    JPanel currentQuestionPanel;

    JLabel progressLabel;
    JLabel highscoreLabel;
    JLabel scoreLabel;

    int currentScore = 0;


    /**
     * this method creates the quesion panel cotaining the questions and all the available answers
     * 
     * @return JPanel
     */
    private JPanel createQuestionPanel(){
        //creat basic box with boxlayout
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        
        //create answer panels
        JPanel answersPanel = new JPanel();
        answersPanel.setLayout(new GridLayout(4,1));
        answers = new ArrayList<>();

        //get all incorrect answers and append the correct answer
        for(String x: currentQuestion.incorrectAnswers()){
            answers.add(x);
        };
        answers.add(currentQuestion.correctAnswer());

        //randomize the answers to avoid having the right answer alwys on the last button
        Collections.shuffle(answers);

        //craetea buttons with answers
        JButton a0 = new JButton(answers.get(0).toString());
        JButton a1 = new JButton(answers.get(1).toString());
        JButton a2 = new JButton(answers.get(2).toString());
        JButton a3 = new JButton(answers.get(3).toString());

        // because of grid layout the set size gets adopted by all the other buttons too
        a0.setPreferredSize(new Dimension(100, 200));

        //style question
        question = new JTextArea(currentQuestion.question().toString());
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setEditable(false);
        question.setOpaque(false);

        //i decided against locating this action listner into controller because it needs to much referneces to other components
        a0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    //set selected button color to blue and rest black
                    a0.setForeground(Color.BLUE);
                    a1.setForeground(Color.BLACK);
                    a2.setForeground(Color.BLACK);
                    a3.setForeground(Color.BLACK);

                    isQuestionSelected = true;

                    //set selectedQuesion to quesion index
                    selectedQuestion = 0;

                    //enable continue button 
                    cont.setEnabled(true);
                    cont.setForeground(Color.BLACK);

                    //enable finish button 
                    finish.setEnabled(true);
                    finish.setForeground(Color.BLACK);
            }
        });

        //i decided against locating this action listner into controller because it needs to much referneces to other components
        a1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    //set selected button color to blue and rest black
                    a0.setForeground(Color.BLACK);
                    a1.setForeground(Color.BLUE);
                    a2.setForeground(Color.BLACK);
                    a3.setForeground(Color.BLACK);
    
                    isQuestionSelected = true;

                    //set selectedQuesion to quesion index
                    selectedQuestion = 1;

                    //enable continue button 
                    cont.setEnabled(true);
                    cont.setForeground(Color.BLACK);

                    //enable finish button 
                    finish.setEnabled(true);
                    finish.setForeground(Color.BLACK);
            }
        });

        //i decided against locating this action listner into controller because it needs to much referneces to other components
        a2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    //set selected button color to blue and rest black
                    a0.setForeground(Color.BLACK);
                    a1.setForeground(Color.BLACK);
                    a2.setForeground(Color.BLUE);
                    a3.setForeground(Color.BLACK);

                    isQuestionSelected = true;

                    //set selectedQuesion to quesion index
                    selectedQuestion = 2;

                    //enable continue button
                    cont.setEnabled(true);
                    cont.setForeground(Color.BLACK);

                    //enable finish button
                    finish.setEnabled(true);
                    finish.setForeground(Color.BLACK);
            }
        });

        //i decided against locating this action listner into controller because it needs to much referneces to other components
        a3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    //set selected button color to blue and rest black
                    a0.setForeground(Color.BLACK);
                    a1.setForeground(Color.BLACK);
                    a2.setForeground(Color.BLACK);
                    a3.setForeground(Color.BLUE);

                    isQuestionSelected = true;

                    //set selectedQuesion to quesion index
                    selectedQuestion = 3;

                    //enable continue button
                    cont.setEnabled(true);
                    cont.setForeground(Color.BLACK);

                    //enable finish button
                    finish.setEnabled(true);
                    finish.setForeground(Color.BLACK);
            }
        });

        //add buttons to answerpanel
        answersPanel.add(a0);
        answersPanel.add(a1);
        answersPanel.add(a2);
        answersPanel.add(a3);

        //add question
        box.add(question);
        //add answerpanel
        box.add(answersPanel);

        //return component
        return box;
    }

    /** 
     *  question panel constructor to create the quesion components while iterating through the quesion list
     * 
     * @param gameQuestions
     * @param currentGame
     * @param highscore
     * @param onFinish
     */

    public QuestionPanel(List<Question> gameQuestions, Game currentGame, int highscore, ButtonClickHandler onFinish){

        //set given quesions as variables
        this.gameQuestions = gameQuestions;
        this.currentQuestion = gameQuestions.get(questionIndex);
        
        //creat main panel and add styling
        main = new JPanel();
        main.setPreferredSize(new Dimension(300, 300));
        main.setMaximumSize(new Dimension(500, 300));
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        main.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        //create continue button and make it uncickable
        cont = new JButton("Weiter");
        cont.setEnabled(false);
        cont.setForeground(new Color(0,0,0,100));

        //create the quesion panel for the current question
        currentQuestionPanel =  createQuestionPanel();

        //create stats for the current quesion
        highscoreLabel = new JLabel("Bisheriger Highscore:" + highscore);
        scoreLabel = new JLabel("Aktueller Score:" +  currentScore);
        progressLabel = new JLabel("Frage: " + (questionIndex+1) + "/" + currentGame.getAmountQuestions());

        //create finish button
        finish = new JButton("Fertig");

        finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(answers.get(selectedQuestion) == currentQuestion.correctAnswer()){
                    currentGame.setScore(currentGame.getScore() + 100);
                    currentGame.setAmountQuestionsCorrect(currentGame.getAmountQuestionsCorrect() + 1);
                }
                Functions.addGame(currentGame);
                onFinish.handle();
            }
        });

        //i decided against moving this action controller inside the controller package because it woild make the code really complicated and unreadable. It refreces too many variables and components
        cont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //upadte stats when correct anser
                if(answers.get(selectedQuestion) == currentQuestion.correctAnswer()){
                    currentGame.setScore(currentGame.getScore() + 100);
                    currentGame.setAmountQuestionsCorrect(currentGame.getAmountQuestionsCorrect() + 1);
                    currentScore = currentScore + 100;
                    scoreLabel.setText("aktueller Score:" +  currentScore);
                }

                //
                main.remove(currentQuestionPanel);
                main.remove(cont);

                //fo to next question
                questionIndex++;
                currentQuestion = gameQuestions.get(questionIndex);

                //crate panel for new question
                currentQuestionPanel = createQuestionPanel();
                main.add(currentQuestionPanel);

                //only add continue button if last quesion not reached else add finish button
                if(questionIndex < currentGame.getAmountQuestions()-1){
                    main.add(cont);
                }
                else{
                    finish.setEnabled(false);
                    finish.setForeground(new Color(0,0,0,100));
                    main.add(finish);
                }

                //add progress stat
                progressLabel.setText("Frage: " + (questionIndex+1) + "/" + currentGame.getAmountQuestions());

                //add continue button
                cont.setEnabled(false);
                cont.setForeground(new Color(0,0,0,100));

                //force rerender
                main.revalidate();
                main.repaint();
            }
        });

        //add the statistic of the quesion
        highscoreLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        scoreLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        progressLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        main.add(highscoreLabel);
        main.add(scoreLabel);
        main.add(progressLabel);

        //add padding
        main.add(Box.createRigidArea(new Dimension(0, 60)));

        //add the panel with the current quesion
        main.add(currentQuestionPanel);

        //add the continue button
        main.add(cont);
    }
}
