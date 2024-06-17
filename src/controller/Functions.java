package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import model.Game;

public class Functions {

    public static boolean fileExists(String path){
        Path file  = Paths.get(path);
        return Files.exists(file);
    }

    public static void validateCSVFile(){
        String path = "src/model/games.csv";
        Path createPath = Paths.get(path);

        if(!fileExists(path)){
            try{
                Files.createFile(createPath);
                Files.write(createPath, (Game.getCSVHeader() + "\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
                
            }catch(IOException err){
                System.out.println(err);
            }
        }
        else{
            System.out.println("csv exists");
        }
    }

    public static List<Game> getGames(){

        String path = "src/model/games.csv";

        if(!fileExists("src/model/games.csv")){
            System.out.println("No Games saved");
            return Collections.emptyList();
        }

        LinkedList<Game> games = new LinkedList<Game>();

        try(BufferedReader reader = new BufferedReader(new FileReader(path))){

            String currentLine = "";

            //skips header
            reader.readLine();

            while((currentLine = reader.readLine()) != null){

                String[] currentLineData = currentLine.split(";");

                Game game = new Game(
                    Timestamp.valueOf(currentLineData[0]),
                    Integer.parseInt(currentLineData[1]),
                    Integer.parseInt(currentLineData[2]),
                    Integer.parseInt(currentLineData[3])
                );

                games.add(game);
            }

        }catch(IOException err){
            System.out.println(err);
        }
        
        return games;
    }

    public static List<Game> getLastGame(){

        String path = "src/model/games.csv";

        if(!fileExists("src/model/games.csv")){
            System.out.println("No Games saved");
            return Collections.emptyList();
        }

        LinkedList<Game> games = new LinkedList<Game>();

        try(BufferedReader reader = new BufferedReader(new FileReader(path))){

            String currentLine = "";
            String lastLine = null;

            //skips header
            reader.readLine();

            while((currentLine = reader.readLine()) != null){
                lastLine = currentLine;
            }

            if(lastLine != null){
                String[] lastLineData = lastLine.split(";");

                Game game = new Game(
                    Timestamp.valueOf(lastLineData[0]),
                    Integer.parseInt(lastLineData[1]),
                    Integer.parseInt(lastLineData[2]),
                    Integer.parseInt(lastLineData[3])
                );
                games.add(game);
            }else{
                return Collections.emptyList();
            }

        }catch(IOException err){
            System.out.println(err);
        }
        
        return games;
    }

    public static List<Game> addGame(Game game){

        String path = "src/model/games.csv";
        Path createPath = Paths.get(path);

        if(fileExists(path)){
            try{
                Files.write(createPath, (game.getGameAsString()+ "\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
                
            }catch(IOException err){
                System.out.println(err);
            }
        }
        
        return getGames();
    }
}
