package ru.samsonium;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import ru.samsonium.lexer.Lexer;
import ru.samsonium.lexer.token.Token;
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
            System.out.print(Log.colorize("Введите путь к файлу (или оставьте строку пустой для выхода): "));
            filePath = sc.nextLine();

            // Check file path string
            if (filePath.trim().isEmpty())
                System.exit(0);
            
            // Get flags
            System.out.print(Log.colorize("Введите флаги (если нужны): "));
            String[] newArgs = sc.nextLine().split(" ");
            parseArguments(newArgs);

            sc.close();
        }

        // Try to read file
        try {
            file = new File(filePath);
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.printf(Log.colorize("Файл не найден или к нему нет доступа. Указан путь: %s"), filePath);
            System.exit(1);
            return;
        } catch (Exception e) {
            Log.println("Произошла непредвиденная ошибка при чтении файла!");
            System.exit(1);
            return;
        }

        // Read file content
        StringBuilder sourceCode = new StringBuilder();
        while (sc.hasNextLine())
            sourceCode.append(sc.nextLine()).append("\n");

        sc.close();
        System.out.println("File contents successfully red");

        // Create lexer
        Lexer lex = new Lexer(sourceCode.toString());
        List<Token> tokens = lex.analyze();

        for (Token t : tokens)
            System.out.println(t.toString());
    }

    /** Display help message */
    private static void displayHelp() {
        Log.println("Справка по использованию интерпретатора Rupla\n");
        Log.println("-> Запуск программы: java -jar rupla.jar <путь к исполняемому файлу>\n");

        Log.println("Доступные флаги:");
        Log.println(" --help          Показать справку");
        Log.println(" --debug         Полная информация о работе интерпретатора");
        Log.println(" --debug-lexer   Сводка о работе лексера");
        Log.println(" --debug-parser  Сводка о работе парсера\n");
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
                        System.out.printf(Log.colorize("-> Неизвестный флаг: %s\n"), arg);
                }
            } else {
                if (arg.contains(".sf"))
                    filePath = arg;
            }
        }
    }
}
