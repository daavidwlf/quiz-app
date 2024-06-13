package data;

import java.util.Date;

public class Game {

    //id serves as date and id because of it's uniqueness
    private Date id;
    private int score;
    private int amountQuestions;
    private int amountQuestionsCorrect;

    public Game(){
        this.id = new Date();
        this.score = 0;
        this.amountQuestions = 0;
        this.amountQuestionsCorrect = 0;
    }

    public Game(int amountQuestions){
        this.id = new Date();
        this.score = 0;
        this.amountQuestions = 0;
        this.amountQuestionsCorrect = 0;
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
        return amountQuestionsCorrect/amountQuestions;
    }
}
