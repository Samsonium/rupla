package ru.samsonium;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import ru.samsonium.lexer.Lexer;
import ru.samsonium.lexer.token.Token;

public class App {
    public static void main(String[] args) throws Exception {
        File file = null;
        Scanner sc = null;
        StringBuilder buf = new StringBuilder();

        // Check arguments count
        if (args.length == 0) {
            System.out.println("Ошибка: не указан файл");
            System.exit(1);
        }

        // Check is file exists
        file = new File(args[0]);
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: файл не найден (" + args[0] + ")");
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
        Lexer lex = new Lexer(buf.toString());
        List<Token> tokens = lex.tokenize();

        for (Token t : tokens) {
            System.out.println(t);
        }
    }
}
