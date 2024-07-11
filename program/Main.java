import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Main <input-file>");
            System.exit(1);
        }

        String inputFile = args[0];

        try {
            // Cargar el archivo de entrada
            CharStream input = CharStreams.fromFileName(inputFile);

            // Crear el lexer
            MiniLangLexer lexer = new MiniLangLexer(input);

            // Crear el buffer de tokens
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Crear el parser
            MiniLangParser parser = new MiniLangParser(tokens);

            // Iniciar el análisis sintáctico por la regla inicial de tu gramática
            ParseTree tree = parser.prog(); // Asumiendo que 'prog' es la regla inicial

            // Imprimir el árbol de sintaxis (opcional)
            System.out.println(tree.toStringTree(parser));

            // Implementar el análisis semántico (opcional)
            // MiniLangBaseVisitorImpl visitor = new MiniLangBaseVisitorImpl();
            // visitor.visit(tree);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}