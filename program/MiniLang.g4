grammar MiniLang;

prog:   stat+ ;

stat:   expr NEWLINE                 # printExpr
    |   ID '=' expr NEWLINE          # assign
    |   ifStat                       # ifStatement
    |   funcDef                      # functionDefinition
    |   funcCall NEWLINE             # functionCall
    |   NEWLINE                      # blank
    ;

ifStat: 'if' expr 'then' stat* ('else' stat*)? 'endif' NEWLINE ;

funcDef: 'def' ID '(' params? ')' 'do' stat* 'end' ;

params: ID (',' ID)* ;

funcCall: ID '(' args? ')' ;

args: expr (',' expr)* ;

expr:   expr op=(MUL|DIV) expr       # MulDiv
    |   expr op=(ADD|SUB) expr       # AddSub
    |   expr op=(EQ|NEQ|LT|GT|LE|GE) expr  # Comparison
    |   INT                          # int
    |   STRING                       # string
    |   ID                           # id
    |   funcCall                     # functionCallExpr
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
STRING : '"' .*? '"' ; // match strings
NEWLINE: '\r'? '\n' ; // return newlines to parser (is end-statement signal)
WS  : [ \t]+ -> skip ; // toss out whitespace
COMMENT: '--' ~[\r\n]* -> skip ;
