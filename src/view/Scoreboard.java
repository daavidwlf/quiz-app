package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.Functions;
import model.Game;

public class Scoreboard {
    public JPanel main;
    CustomPanel box;
    JLabel test;

    public static int highscore;

    public Scoreboard(){

        //add main panel with padding
        main = new JPanel();
        main.setBorder(new EmptyBorder(0,20,0,20));
        main.setMaximumSize(new Dimension(500, 200));

        //add clored box
        box = new CustomPanel(40);
        box.setPreferredSize(new Dimension(300, 200));
        box.setMaximumSize(new Dimension(500, 200));
        box.setBackground(CustomColors.lightBlue);
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Game> previousGames = Functions.getGames();
        int score = calcHighscore(previousGames);
        int amountQuestions = calcAmountQuestions(previousGames);
        int percentageCorrect = Math.round(calcPercentageCorrect(previousGames)*100);

        JLabel scoreLabelTitle = new JLabel("Highscore");
        scoreLabelTitle.setFont(new Font("default", Font.BOLD, 22));
        scoreLabelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel scoreLabel =  new JLabel(""+score);
        scoreLabel.setFont(new Font("default", Font.BOLD, 40));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel boxUpper = new JPanel();
        boxUpper.setLayout(new BoxLayout(boxUpper, BoxLayout.Y_AXIS));
        boxUpper.add(scoreLabelTitle);
        boxUpper.add(scoreLabel);
        boxUpper.setBackground(new Color(0,0,0,0));

        JLabel amountGamesLabelTitle =  new JLabel("Spiele");
        scoreLabel.setFont(new Font("default", Font.PLAIN, 16));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel amountGamesLabel =  new JLabel("" + previousGames.size());
        scoreLabel.setFont(new Font("default", Font.PLAIN, 16));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel amountQuestionsLabelTitle =  new JLabel("Fragen");
        scoreLabel.setFont(new Font("default", Font.PLAIN, 16));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel amountQuestionsLabel =  new JLabel("" + amountQuestions);
        scoreLabel.setFont(new Font("default", Font.PLAIN, 16));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel percentCorrectLabelTitle =  new JLabel("Quote");
        scoreLabel.setFont(new Font("default", Font.PLAIN, 16));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel percentCorrectLabel =  new JLabel("" + percentageCorrect + " %");
        scoreLabel.setFont(new Font("default", Font.PLAIN, 16));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel boxLower = new JPanel();
        boxLower.setLayout(new GridLayout(2, 3));
        boxLower.setBackground(new Color(0,0,0,0));
        boxLower.setAlignmentX(Component.CENTER_ALIGNMENT);

        boxLower.add(amountGamesLabel);
        boxLower.add(amountQuestionsLabel);
        boxLower.add(percentCorrectLabel);

        boxLower.add(amountGamesLabelTitle);
        boxLower.add(amountQuestionsLabelTitle);
        boxLower.add(percentCorrectLabelTitle);

        box.add(boxUpper);
        box.add(Box.createRigidArea(new Dimension(0, 60)));
        box.add(boxLower);


        main.add(box);
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

    public int calcAmountQuestions(List<Game> games){
        int amount = 0;
        for(Game current : games){
            amount += current.getAmountQuestions();
        }
        return amount;
    }

    public float calcPercentageCorrect(List<Game> games){
        float percent = 0f;
        int divider = 0;
        for(Game current : games){
            if(current.getAmountQuestions() != 0){
                percent += current.getPercentageCorrect();
                divider++;
            }
        }
        if(divider != 0){
            return percent/divider;
        }
        return 0;
    }
}
