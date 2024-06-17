package model;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.naming.TimeLimitExceededException;

import edu.kit.aifb.atks.opentdb4j.OpenTDB;
import edu.kit.aifb.atks.opentdb4j.OpenTDBAPIException;
import edu.kit.aifb.atks.opentdb4j.Question;
import edu.kit.aifb.atks.opentdb4j.QuestionDifficulty;
import edu.kit.aifb.atks.opentdb4j.QuestionType;
import view.ErrorPopUp;

/**
 * This class is used to acess the question database and handle errors
 * 
 * @author David Wolf
 * @version 2024-06-13
 */

public class FetchQuestions {

    /**
     * returns list with one random question based on no params
     * 
     * @param none
     * @return List<Question>
     */

    public static List<Question> fetch(){
        try {
            return OpenTDB.fetchQuestions();
        } catch (OpenTDBAPIException e) {
            return Collections.emptyList();
        }
    }

    /**
     * returns list with a specified amount of random questions
     * 
     * @param amount
     * @return List<Question>
     */

    public static List<Question> fetch(int amount){
        try {
            return OpenTDB.fetchQuestions(amount);
        } catch (OpenTDBAPIException e) {
            return Collections.emptyList();
        }
    }

    /**
     * returns list with a specified amount of random questions of a given question type
     * 
     * @param amount 
     * @param type
     * @return List<Question>
     */

    public static List<Question> fetch(int amount, QuestionType type){
        try {
            return OpenTDB.fetchQuestions(amount, type);
        } catch (OpenTDBAPIException e) {
            new ErrorPopUp();
            return Collections.emptyList();
        }
    }

    /**
     * returns list with a specified amount of random questions of a given question type, dificulty and category
     * 
     * @param amount
     * @param difficulty
     * @param catgory
     * @return List<Question>
     */

    public static List<Question> fetch(int amount, QuestionType type, QuestionDifficulty difficulty, String category){
        try {
            return OpenTDB.fetchQuestions(amount, type, difficulty, category);
        } catch (OpenTDBAPIException e) {
            new ErrorPopUp();
            return Collections.emptyList();
        }
    }
}
