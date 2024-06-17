package model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import controller.QuestionSetCallback;
import edu.kit.aifb.atks.opentdb4j.Question;

/**
 * This class represents a game and its props
 * 
 * @author David Wolf
 * @version 17-06-2024
 * 
 */

public class Game {

    //id serves as date and id because of it's uniqueness and is saved as a timestamp
    private Timestamp id;
    private int score;
    private int amountQuestions;
    private int amountQuestionsCorrect;
    private List<Question> questions;

    //static csv header for csv file creation
    private static String CSVHeader = "id;score;amountQuestions;amountQuestionsCorrect";

    /**
     * create geame with default props
     */
    public Game(){
        Date now =  new Date();
        this.id = new Timestamp(now.getTime());
        this.score = 0;
        this.amountQuestions = 0;
        this.amountQuestionsCorrect = 0;
    }

    /**
     * create geame with amount of questions
     */
    public Game(int amountQuestions){
        Date now =  new Date();
        this.id = new Timestamp(now.getTime());
        this.score = 0;
        this.amountQuestions = amountQuestions;
        this.amountQuestionsCorrect = 0;
    }

    /**
     * create geame with id, score, amount questions and amount questions correct
     * 
     */
    public Game(Timestamp id, int score, int amountQuestions, int amountQuestionsCorrect){
        this.id = id;
        this.score = score;
        this.amountQuestions = amountQuestions;
        this.amountQuestionsCorrect = amountQuestionsCorrect;
    }

    /**
     * get csv header for csv file creation
     * @return String
     */
    public static String getCSVHeader(){
        return CSVHeader;
    }

    /**
     *  get game id
     * @return String
     */
    public String getId(){
        return id.toString();
    }

    /**
     * get game date
     * @return Date
     */
    public Date getDate(){
        return id;
    }

    /**
     * get amount questions of given game
     * @return int
     */
    public int getAmountQuestions(){
        return amountQuestions;
    }

    /**
     * get amount of correct quesions of given game
     * @return int
     */
    public int getAmountQuestionsCorrect(){
        return amountQuestionsCorrect;
    }

    /**
     * get sore of given game
     * @return int
     */
    public int getScore(){
        return score;
    }

    /**
     * get percentage of correct answered quesions
     * @return float
     */
    public float getPercentageCorrect(){
        if(amountQuestions != 0){
            return (float)amountQuestionsCorrect/(float)amountQuestions;
        }
        return 0;
    }

    /**
     * get game stats as string
     * @return String
     */
    public String getGameAsString(){
        return this.id.toString() + ";" + this.score + ";" + this.amountQuestions + ";" + this.amountQuestionsCorrect;
    }

    /**
     * set game questions
     * 
     * the callback is ues to switch to game screen afte questions are fetched an stored in ne game entity
     * @param questions
     * @param callback
     */
    public void setQuestions(List<Question> questions, QuestionSetCallback callback){
        this.questions =  questions;
        callback.onQuestionsSet();
    }

    /**
     * get quuestions of game
     * @return List<Questions>
     */
    public List<Question> getQuestions(){
        return this.questions;
    }

    /**
     * set score of game
     * @param score
     */
    public void setScore(int score){
        this.score = score;
    }

    /**
     * set the amount questions that have been correctly answered
     * 
     * @param amountQuestionsCorrect
     */
    public void setAmountQuestionsCorrect(int amountQuestionsCorrect){
        this.amountQuestionsCorrect = amountQuestionsCorrect;
    }
}
