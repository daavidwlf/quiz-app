package data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import edu.kit.aifb.atks.opentdb4j.Question;
import functions.QuestionSetCallback;

public class Game {

    //id serves as date and id because of it's uniqueness and is saved as a timestamp
    private Timestamp id;
    private int score;
    private int amountQuestions;
    private int amountQuestionsCorrect;
    private List<Question> questions;

    private static String CSVHeader = "id;score;amountQuestions;amountQuestionsCorrect";

    public Game(){
        Date now =  new Date();
        this.id = new Timestamp(now.getTime());
        this.score = 0;
        this.amountQuestions = 0;
        this.amountQuestionsCorrect = 0;
    }

    public Game(int amountQuestions){
        Date now =  new Date();
        this.id = new Timestamp(now.getTime());
        this.score = 0;
        this.amountQuestions = amountQuestions;
        this.amountQuestionsCorrect = 0;
    }

    public Game(Timestamp id, int score, int amountQuestions, int amountQuestionsCorrect){
        this.id = id;
        this.score = score;
        this.amountQuestions = amountQuestions;
        this.amountQuestionsCorrect = amountQuestionsCorrect;
    }

    public static String getCSVHeader(){
        return CSVHeader;
    }

    public String getId(){
        return id.toString();
    }

    public Date getDate(){
        return id;
    }

    public int getAmountQuestions(){
        return amountQuestions;
    }

    public int getAmountQuestionsCorrect(){
        return amountQuestionsCorrect;
    }

    public int getScore(){
        return score;
    }

    public float getPercentageCorrect(){
        if(amountQuestions != 0){
            return (float)amountQuestionsCorrect/(float)amountQuestions;
        }
        return 0;
    }

    public String getGameAsString(){
        return this.id.toString() + ";" + this.score + ";" + this.amountQuestions + ";" + this.amountQuestionsCorrect;
    }

    public void setQuestions(List<Question> questions, QuestionSetCallback callback){
        this.questions =  questions;
        callback.onQuestionsSet();
    }

    public List<Question> getQuestions(){
        return this.questions;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void setAmountQuestionsCorrect(int amountQuestionsCorrect){
        this.amountQuestionsCorrect = amountQuestionsCorrect;
    }
}
