package ru.samsonium;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ru.samsonium.support.Config;
import ru.samsonium.support.Log;

public class App {
    private static String filePath;
    private static boolean isHelpFlag = false;

    public static void main(String[] args) {
        File file;
        Scanner sc;

        // Try to parse arguments
        parseArguments(args);

        // Check help flag
        if (isHelpFlag) {
            displayHelp();
            System.exit(0);
        }

        // Check file path string
        if (filePath == null || filePath.trim().isEmpty()) {
            displayHelp();
            sc = new Scanner(System.in);
            
            // Get file path
            System.out.print(Log.colorize("$rВведите путь к файлу (или оставьте строку пустой для выхода): $g!!"));
            filePath = sc.nextLine();

            // Check file path string
            if (filePath.trim().isEmpty())
                System.exit(0);
            
            // Get flags
            System.out.print(Log.colorize("$rВведите флаги (если нужны): $g!!"));
            String[] newArgs = sc.nextLine().split(" ");
            parseArguments(newArgs);
        }

        // Try to read file
        try {
            file = new File(filePath);
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.printf(Log.colorize("$r$a!!Файл не найден или к нему нет доступа$r. Указан путь: $g!!%s$r"), filePath);
            System.exit(1);
            return;
        } catch (Exception e) {
            Log.println("$r$a!!Произошла непредвиденная ошибка при чтении файла!$r");
            System.exit(1);
            return;
        }

        // Read file content
        StringBuilder sourceCode = new StringBuilder();
        while (sc.hasNextLine())
            sourceCode.append(sc.nextLine()).append("\n");

        // Create lexer
    }

    /** Display help message */
    private static void displayHelp() {
        Log.println("$r$4$g!!Справка по использованию интерпретатора Rupla$r\n");
        Log.println("$r-> Запуск программы: $g!!java -jar rupla.jar $r$d!!<путь к исполняемому файлу>$r\n");

        Log.println("$r$g!Доступные флаги:");
        Log.println("$r$g!! --help          $rПоказать справку");
        Log.println("$r$g!! --debug         $rПолная информация о работе интерпретатора");
        Log.println("$r$g!! --debug-lexer   $rСводка о работе лексера");
        Log.println("$r$g!! --debug-parser  $rСводка о работе парсера\n");
    }

    /** Parse arguments */
    private static void parseArguments(String[] args) {
        Config instance = Config.getInstance();
        for (String arg : args) {
            if (arg.startsWith("--")) {
                switch (arg.substring(2)) {
                    case "debug":
                        instance.setDebugLexer(true);
                        instance.setDebugParser(true);
                        break;

                    case "debug-lexer":
                        instance.setDebugLexer(true);
                        break;

                    case "debug-parser":
                        instance.setDebugParser(true);
                        break;

                    case "help":
                        isHelpFlag = true;
                        break;

                    default:
                        System.out.printf(Log.colorize("$r-> $cНеизвестный флаг$r: $g!!%s$r\n"), arg);
                }
            } else {
                if (arg.contains(".sf"))
                    filePath = arg;
            }
        }
    }
}
