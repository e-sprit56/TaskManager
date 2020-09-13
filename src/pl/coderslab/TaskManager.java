package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

    public static String FILE = "tasks.csv";


    public static void menu() {

        System.out.println("\n \n ");
        System.out.println("!!!!  Welcome in Task Manager 2020 XP PRO   !!!!");
        System.out.println("**************************************************");
        System.out.println(" ");
        boolean running = true;

        do {

            System.out.println(ConsoleColors.BLUE + "Pleas select an option");
            System.out.println(ConsoleColors.RESET + "");
            System.out.println("list");
            System.out.println(ConsoleColors.GREEN_BOLD +"add" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BOLD + "remove" + ConsoleColors.RESET);
            System.out.println("exit");
            System.out.println(ConsoleColors.RESET);

            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();

            switch (option) {
                case "list" -> read();
                case "add" -> add();
                case "remove" -> remove();
                case "exit" -> {
                    System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "Thank you for using Task Manager 2020 XP PRO. \nYour credit card has ben charged for 15k PLN");
                    running = false;
                }
                default -> System.out.println("Invalid command");
            }
        }while (running);

        System.out.println();
    }


    public static void read() {

        String[] lines = dataImport();

        System.out.println("Your current tasks list: \n");

        for (int i = 0; i < lines.length; i++) {
            System.out.println(i+1 + " || " + lines[i]);
        }
    }
        public static void add (){

            Scanner scanner = new Scanner(System.in);

            System.out.println("Pleas enter a new task:");
            System.out.println("Task name");
            String task = scanner.nextLine();



            String date;
            do{
                System.out.println(ConsoleColors.BLUE_BOLD + "Pleas enter a Due Date in format: yyyy-mm-dd" + ConsoleColors.RESET);
                date = scanner.nextLine();

                if(GenericValidator.isDate(date, "yyyy-mm-dd", true)){
                    break;
                }else System.out.println("Invalid date format");
            }while (true);


            String important;
            do {
                System.out.println(ConsoleColors.BLUE_BOLD + "Important: True or False" + ConsoleColors.RESET);
                important = scanner.nextLine().toLowerCase();

                if(important.equals("true")||important.equals("false")){
                    break;
                }else System.out.println("please enter True or False");


            }while(true);

            String newEntry = String.join(", " ,task, date, important );

            try {
                FileWriter writer = new FileWriter(FILE, true);
                writer.append(newEntry); // usuniety powrót karetki
                writer.close();
            }catch (IOException e){
                System.out.println(ConsoleColors.RED_BOLD + "File not found"  + ConsoleColors.RESET);
            }
        }

        public static void remove(){

            String[] lines = dataImport();
            Scanner scan = new Scanner(System.in);


            read();
            System.out.println("");

            int taskNumber;
            while(true){
                System.out.println(ConsoleColors.BLUE_BOLD + "Which task number do you want to delete?" + ConsoleColors.RESET);
                try {
                    taskNumber = scan.nextInt();

                }catch (NumberFormatException e){
                    System.out.println("Invalid format");
                    continue;
                }

                if(taskNumber> lines.length || taskNumber<=0){
                    System.out.println(ConsoleColors.RED_BOLD + "Task not found" + ConsoleColors.RESET);
                    continue;
                }
                break;
            }

            lines= ArrayUtils.remove(lines,taskNumber-1);
            try {
                FileWriter writer = new FileWriter(FILE, false);
                for (int j=0; j<lines.length; j++) {
                    String s = lines[j];
                    writer.append(s + "\n");
                }
                writer.close();
            }catch (IOException e){
                System.out.println("File not found");
            }
            System.out.println("Your updated task list:");
            read();
        }


    public static String [] dataImport () {


        Path path = Paths.get(FILE);
        List<String> lines = new ArrayList<String>();

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            System.err.println("Brak Pliku");
        }

        String[] tab = new String[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            tab[i] = lines.get(i);
        }
        return tab;
    }



    public static void main(String[] args) {

        menu();














    }
}


//        Path path = Paths.get(FILE);

//        try{
//            if(!Files.exists(path)){
//                Files.createFile(path);
//            }
//        }catch (IOException ignored){}

//System.out.println((dataImport()));