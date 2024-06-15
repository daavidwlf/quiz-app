package components;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JLabel;

import data.Game;
import functions.Functions;

public class IntroductionMessage {
    public JLabel main;

    String message = "";
    String lastQuiz;

    public IntroductionMessage(){

        List<Game> games = Functions.getLastGame();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if(games.size() < 1){
            message = "Hi,\ndu hast noch kein Quiz gespielt!";
        }else{
            message = "Hi,\n dein letztes Spiel war am" + games.get(0).getDate();
        }
        main =  new JLabel(message);
    }
}
