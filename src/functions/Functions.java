package functions;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import data.Game;

public class Functions {

    public static boolean fileExists(String path){
        Path file  = Paths.get(path);
        return Files.exists(file);
    }

    public static void validateCSVFile(){
        String path = "src/data/games.csv";
        Path createPath = Paths.get(path);

        if(!fileExists(path)){
            try{
                Files.createFile(createPath);
                Files.write(createPath, Game.getCSVHeader().getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
                
            }catch(IOException err){
                System.out.println(err);
            }
        }
        else{
            System.out.println("csv exists");
        }
    }

    public static Game getLastGame(Game game){
        
        String line = "";

        InputStream input = Functions.class.getClassLoader().getResourceAsStream("/data/games.csv");

        System.out.println(input);
        // try{
        // }catch(IOException err){
        //     System.out.println(err);
        // }

        return game;
    }
}
