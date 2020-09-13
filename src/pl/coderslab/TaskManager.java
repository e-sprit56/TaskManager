package pl.coderslab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TaskManager {

    public static void menu(){
        System.out.println(ConsoleColors.BLUE + "Pleas select an option");
        System.out.println(ConsoleColors.RESET +"");
        System.out.println("list");
        System.out.println("add");
        System.out.println("remove");
        System.out.println("exit");
    }

    public static void read(){

        Path path = Paths.get("tasks.csv");

        try{
            for(String line: Files.readAllLines(path)) {
                System.out.println(line);
            }
        }catch (IOException e){
            System.err.println("Brak Pliku");

        }


    }

    public static void main(String[] args) {

        read();








    }
}
