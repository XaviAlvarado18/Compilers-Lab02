import org.antlr.v4.runtime.*;

public class MiniLangErrorListener extendes BaseErrorListener{
    public static final MiniLangErrorListener INSTANCE = new MiniLangErrorListener();

    @Override
    public void syntaxError(Recognizer<?,?> recognizer,
                            Object offendingSymbol.
                            int line, int charPositionInLine,
                            String msg,
                            RecognitionException e){
                                String sourceName = recognizer.getInputStream().getSourceName;
                                if (!sourceName.isEmpty()){
                                    sourceName = String.format("%s.%d.%d: ", sourceName, line, charPositionInLine);
                                }
                                System.err.println(sourceName + "line " + line + ":" + charPositionInLine + " " + msg);
                            }
}