grammar MiniLang;

prog:   stat+ ;

stat:   expr NEWLINE                 # printExpr
    |   ID '=' expr NEWLINE          # assign
    |   NEWLINE                      # blank
    ;

expr:   expr ('*'|'/') expr          # MulDiv
    |   expr ('+'|'-') expr          # AddSub
    |   expr ('=='|'!='|'<'|'>'|'<='|'>=') expr  # Comparison
    |   INT                          # int
    |   ID                           # id
    |   '(' expr ')'                 # parens
    ;


MUL : '*' ; // define token for multiplication
DIV : '/' ; // define token for division
ADD : '+' ; // define token for addition
SUB : '-' ; // define token for subtraction
EQ  : '==' ; // define token for equal to
NEQ : '!=' ; // define token for not equal to
LT  : '<' ; // define token for less than
GT  : '>' ; // define token for greater than
LE  : '<=' ; // define token for less than or equal to
GE  : '>=' ; // define token for greater than or equal to
ID  : [a-zA-Z]+ ; // match identifiers
INT : [0-9]+ ; // match integers
NEWLINE:'\r'? '\n' ; // return newlines to parser (is end-statement signal)
WS  : [ \t]+ -> skip ; // toss out whitespace
LINE_COMMENT:'--' .*? [\n\r] -> skip;
