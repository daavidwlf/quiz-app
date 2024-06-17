package view;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.kit.aifb.atks.opentdb4j.QuestionDifficulty;
import edu.kit.aifb.atks.opentdb4j.QuestionType;

public class Settings {
    public JPanel main;

    private static String selectedLevel = "Medium";
    private static int amountQuestions = 5;

    public Settings(){
        main = new JPanel();
        main.setPreferredSize(new Dimension(300, 200));
        main.setMaximumSize(new Dimension(500, 200));
        main.setLayout(new GridLayout(2,2));
        main.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel amountLabel = new JLabel("Anzahl Spiele:");
        JLabel selectionAmountLabel = new JLabel("5");
        SpinnerModel spinnerModel = new SpinnerNumberModel(5, 5, 50, 1);
        JSpinner spinner = new JSpinner(spinnerModel);
        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner spinner = (JSpinner) e.getSource();
                amountQuestions = (Integer) spinner.getValue();
                System.out.println("Spinner: " + spinner.getValue().toString());
                selectionAmountLabel.setText(""+amountQuestions);
            }
        });
        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinner.getEditor();
        editor.getTextField().setEditable(false);
        editor.setCursor(null);
        selectionAmountLabel.setMaximumSize(new Dimension(20,20));
        


        //create label and selection for difficulty
        JLabel difficultyLabel = new JLabel("Schwirigkeitsgrad:");
        JLabel selectionDifficultyLabel = new JLabel("Medium");
        String[] items = {"Easy", "Medium", "Hard"};
        JComboBox<String> difficultyInput = new JComboBox<>(items);

        difficultyInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
                selectedLevel = (String) comboBox.getSelectedItem();
                selectionDifficultyLabel.setText(selectedLevel);
            }
        });
        difficultyInput.setSelectedIndex(1);

        main.add(amountLabel);
        main.add(spinner);
        main.add(difficultyLabel);
        main.add(difficultyInput);
    }

    /**
     * function to get selected difficulty level
     * 
     * @return selectedLevel
     */

    public static QuestionDifficulty getDifficulty(){
        switch (selectedLevel) {
            case "Easy":
                return QuestionDifficulty.EASY;
            case "Hard":
                return QuestionDifficulty.HARD;
            default:
                return QuestionDifficulty.MEDIUM;
        }
    }

     /**
     * function to get amount of questions
     * 
     * @return amountQuestions
     */

    public static int getAmountQuestions(){
        return amountQuestions;
    }
}
