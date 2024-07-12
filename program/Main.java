import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Verificar que se haya proporcionado un archivo de entrada
        if (args.length != 1) {
            System.err.println("Uso: java Main <archivo>");
            return;
        }

        // Leer el archivo de entrada
        String inputFile = args[0];
        CharStream input = CharStreams.fromFileName(inputFile);

        // Crear el lexer
        MiniLangLexer lexer = new MiniLangLexer(input);

        // Añadir el manejador de errores personalizado al lexer
        lexer.removeErrorListeners();
        lexer.addErrorListener(MiniLangErrorListener.INSTANCE);

        // Crear el token stream
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Crear el parser
        MiniLangParser parser = new MiniLangParser(tokens);

        // Añadir el manejador de errores personalizado al parser
        parser.removeErrorListeners();
        parser.addErrorListener(MiniLangErrorListener.INSTANCE);

        // Parsear el archivo de entrada
        ParseTree tree = parser.prog(); // La regla de inicio de tu gramática

        // Si no hubo errores, imprimir el árbol sintáctico
       
        System.out.println(tree.toStringTree(parser));
        
    }
}


public class MiniLangErrorListener extends BaseErrorListener {
    public static final MiniLangErrorListener INSTANCE = new MiniLangErrorListener();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg,
                            RecognitionException e) {
        String sourceName = recognizer.getInputStream().getSourceName();
        if (!sourceName.isEmpty()) {
            sourceName = String.format("%s:%d:%d: ", sourceName, line, charPositionInLine);
        }
        System.err.println(sourceName + "linee " + line + ":" + charPositionInLine + " " + msg);
    }
}