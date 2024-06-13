package components;

import javax.swing.JLabel;

import data.Game;

public class IntroductionMessage {
    public JLabel main;

    String message = "";
    String lasQuiz;

    public IntroductionMessage(){

        Game game = new Game();

        message = "Hi, dein letztes Quiz ist" + lasQuiz + " her";

        main =  new JLabel(message);
    }
}
