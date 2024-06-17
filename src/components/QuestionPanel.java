package components;

import java.awt.Color;
import java.awt.Dimension;
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

import data.Game;
import edu.kit.aifb.atks.opentdb4j.Question;
import functions.Functions;
import functions.ButtonClickHandler;

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

    private JPanel createQuestionPanel(){

        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));

        JPanel answersPanel = new JPanel();
        answersPanel.setLayout(new GridLayout(2,2));
        answers = new ArrayList<>();
        for(String x: currentQuestion.incorrectAnswers()){
            answers.add(x);
        };
        answers.add(currentQuestion.correctAnswer());

        Collections.shuffle(answers);

        JButton a0 = new JButton(answers.get(0).toString());
        JButton a1 = new JButton(answers.get(1).toString());
        JButton a2 = new JButton(answers.get(2).toString());
        JButton a3 = new JButton(answers.get(3).toString());

        question = new JTextArea(currentQuestion.question().toString());
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setEditable(false);
        question.setOpaque(false);

        a0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(selectedQuestion);
                    a0.setForeground(Color.BLUE);
                    a1.setForeground(Color.BLACK);
                    a2.setForeground(Color.BLACK);
                    a3.setForeground(Color.BLACK);
                    isQuestionSelected = true;
                    selectedQuestion = 0;
                    cont.setEnabled(true);
                    cont.setForeground(Color.BLACK);
                    finish.setEnabled(true);
                    finish.setForeground(Color.BLACK);
            }
        });

        a1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(selectedQuestion);
                    a0.setForeground(Color.BLACK);
                    a1.setForeground(Color.BLUE);
                    a2.setForeground(Color.BLACK);
                    a3.setForeground(Color.BLACK);
                    isQuestionSelected = true;
                    selectedQuestion = 1;
                    cont.setEnabled(true);
                    cont.setForeground(Color.BLACK);
                    finish.setEnabled(true);
                    finish.setForeground(Color.BLACK);
            }
        });

        a2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(selectedQuestion);
                    a0.setForeground(Color.BLACK);
                    a1.setForeground(Color.BLACK);
                    a2.setForeground(Color.BLUE);
                    a3.setForeground(Color.BLACK);
                    isQuestionSelected = true;
                    selectedQuestion = 2;
                    cont.setEnabled(true);
                    cont.setForeground(Color.BLACK);
                    finish.setEnabled(true);
                    finish.setForeground(Color.BLACK);
            }
        });

        a3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(selectedQuestion);
                    a0.setForeground(Color.BLACK);
                    a1.setForeground(Color.BLACK);
                    a2.setForeground(Color.BLACK);
                    a3.setForeground(Color.BLUE);
                    isQuestionSelected = true;
                    selectedQuestion = 3;
                    cont.setEnabled(true);
                    cont.setForeground(Color.BLACK);
                    finish.setEnabled(true);
                    finish.setForeground(Color.BLACK);
            }
        });

        answersPanel.add(a0);
        answersPanel.add(a1);
        answersPanel.add(a2);
        answersPanel.add(a3);

        box.add(question);
        box.add(answersPanel);

        return box;
    }


    public QuestionPanel(List<Question> gameQuestions, Game currentGame, int highscore, ButtonClickHandler onFinish){

        this.gameQuestions = gameQuestions;
        this.currentQuestion = gameQuestions.get(questionIndex);
        System.out.println(currentQuestion);
        
        main = new JPanel();
        main.setPreferredSize(new Dimension(300, 300));
        main.setMaximumSize(new Dimension(500, 300));
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        main.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        cont = new JButton("Weiter");
        cont.setEnabled(false);
        cont.setForeground(new Color(0,0,0,100));

        currentQuestionPanel =  createQuestionPanel();

        highscoreLabel = new JLabel("Bisheriger Highscore:" + highscore);
        scoreLabel = new JLabel("Aktueller Score:" +  currentScore);
        progressLabel = new JLabel("Frage: " + (questionIndex+1) + "/" + currentGame.getAmountQuestions());

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

        System.out.println(currentQuestion.correctAnswer());

        cont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(answers.get(selectedQuestion) == currentQuestion.correctAnswer()){
                    currentGame.setScore(currentGame.getScore() + 100);
                    currentGame.setAmountQuestionsCorrect(currentGame.getAmountQuestionsCorrect() + 1);
                    currentScore = currentScore + 100;
                    scoreLabel.setText("aktueller Score:" +  currentScore);
                }
                main.remove(currentQuestionPanel);
                main.remove(cont);
                questionIndex++;
                currentQuestion = gameQuestions.get(questionIndex);
                currentQuestionPanel = createQuestionPanel();
                main.add(currentQuestionPanel);
                System.out.println(currentGame.getAmountQuestions());
                if(questionIndex < currentGame.getAmountQuestions()-1){
                    main.add(cont);
                }
                else{
                    finish.setEnabled(false);
                    finish.setForeground(new Color(0,0,0,100));
                    main.add(finish);
                }
                progressLabel.setText("Frage: " + (questionIndex+1) + "/" + currentGame.getAmountQuestions());
                main.revalidate();
                main.repaint();
            }
        });
        highscoreLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        scoreLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        progressLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        main.add(highscoreLabel);
        main.add(scoreLabel);
        main.add(progressLabel);
        main.add(Box.createRigidArea(new Dimension(0, 60)));
        main.add(currentQuestionPanel);
        main.add(cont);
    }
}