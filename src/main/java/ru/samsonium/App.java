package ru.samsonium;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import ru.samsonium.lexer.Lexer;
import ru.samsonium.lexer.token.Token;
import ru.samsonium.parser.Parser;

public class App {
    private static boolean debugLexer = false;
    private static boolean debugParser = false;
    private static boolean showHelp = false;
    private static String filePath = "";

    public static void main(String[] args) throws Exception {
        File file = null;
        Scanner sc = null;
        StringBuilder buf = new StringBuilder();

        // Check is file exists
        parseArgs(args);

        // Check arguments count
        if (filePath.isEmpty()) {
            System.out.println("Ошибка: не указан файл\n");
            showHelpHint();

            sc = new Scanner(System.in);
            System.out.print("Введите путь к файлу (или пустую строку, чтобы закончить): ");

            filePath = sc.nextLine();
            if (filePath.trim().isEmpty()) {
                System.out.println("Пока-пока!");
                System.exit(0);
            }

            System.out.print("Введите флаги (если не нужно, оставьте строку пустой): ");
            String flags = sc.nextLine();
            if (!flags.isEmpty())
                parseArgs(flags.split(" "));
        }

        // Show help if needed
        if (showHelp) {
            showHelpHint();
            System.exit(0);
        }

        // Read file
        file = new File(filePath);
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл не найден (" + filePath + ")");
            System.exit(1);
        }

        // Read file line by line
        while (sc.hasNextLine()) {
            buf.append(sc.nextLine() + "\n");
        }

        // Check for emptiness
        if (buf.toString().isEmpty()) {
            System.out.println("Ошибка: файл пуст");
            System.exit(1);
        }

        // Lexical analysis
        if (debugLexer) System.out.println("-> Чтение токенов...");
        Lexer lex = new Lexer(buf.toString());
        List<Token> tokens = lex.tokenize();
        if (tokens.get(tokens.size() - 1).getType().getName() != "EOF") {
            System.out.println("Ошибка: выполнение команды невозможно, так как лексер не закончил свою работу.\n");
            System.exit(1);
        }

        // Output tokens list if --debug-lexer or --debug-all
        if (debugLexer) {
            System.out.println("--> Найдены токены:");
            for (Token t : tokens) {
                System.out.println(t);
            }
            System.out.println("-----------------------------------");
            System.out.println(" Всего токенов: " + tokens.size());
            System.out.println("-----------------------------------");
            System.out.println();
        }

        // Parsing
        if (debugParser) System.out.println("-> Парсинг...");
        Parser parser = new Parser(tokens);
        int result = parser.parse();

        // if ()

        System.out.println("Пока-пока!");
    }

    /** Parse arguments */
    private static void parseArgs(String[] args) {
        for (String arg : args) {
            if (!arg.startsWith("--"))
                filePath = arg;
            else {
                if (arg.contains("debug-lexer"))
                    debugLexer = true;
                else if (arg.contains("debug-parser"))
                    debugParser = true;
                else if (arg.contains("debug-all"))
                    debugLexer = debugParser = true;
                else if (arg.contains("help"))
                    showHelp = true;
                else System.out.println("Неизвестный флаг \"" + arg + "\"");
            }
        }
    }

    /** Show help hint */
    private static void showHelpHint() {
        System.out.println("Помощь:");
        System.out.println("Запуск программы: java -jar rupla <путь к файлу> [флаги]\n");
        System.out.println("Доступные флаги:");
        System.out.println("> --help         Показать справку");
        System.out.println("> --debug-lexer  Запустить с информацией от лексера");
        System.out.println("> --debug-parser Запустить с информацией от парсера");
        System.out.println("> --debug-all    Запустить с информацией от парсера");
    }
}
