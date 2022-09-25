package tokenizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Tokenizer {

    Scanner scanner;
    String lookahead;
    char[] lexeme;
    final List<String> RESERVED_WORDS = Arrays.asList("auto", "else", "long", "switch", "break", "enum", "register", "typedef", "case", "extern", "return", "union", "char", "float", "short", "unsigned", "const", "for", "signed", "void", "continue", "goto", "sizeof", "volatile", "default", "if", "static", "while", "do", "int", "struct", "_Packed", "double");
    final String[] tokensNames = {"",};
    // state number is mapped to tokens names
    public static Map<Integer, String> TOKENS_NAMES;

    static {
        TOKENS_NAMES = new HashMap<>();
        TOKENS_NAMES.put(2, "identifier");
        TOKENS_NAMES.put(4, "int_literal");
        TOKENS_NAMES.put(6, "float_literal");
        TOKENS_NAMES.put(8, "increment_op");
        TOKENS_NAMES.put(9, "plus_equal");
        TOKENS_NAMES.put(10, "arith_plus");
        TOKENS_NAMES.put(12, "decrement_op");
        TOKENS_NAMES.put(13, "minus_equal");
        TOKENS_NAMES.put(14, "arith_minus");
        TOKENS_NAMES.put(16, "mult_equal");
        TOKENS_NAMES.put(17, "arith_mul");
        TOKENS_NAMES.put(19, "div_equal");
        TOKENS_NAMES.put(20, "arith_div");
        TOKENS_NAMES.put(22, "modlo_equal");
        TOKENS_NAMES.put(23, "arith_modlo");
        TOKENS_NAMES.put(25, "lessThan_equal");
        TOKENS_NAMES.put(26, "lessThan");
        TOKENS_NAMES.put(28, "greaterThan_equal");
        TOKENS_NAMES.put(29, "greaterThan");
        TOKENS_NAMES.put(31, "equal");
        TOKENS_NAMES.put(32, "assign");
        TOKENS_NAMES.put(34, "not_equal");
        TOKENS_NAMES.put(35, "logic_not");
        TOKENS_NAMES.put(37, "logic_or");
        TOKENS_NAMES.put(38, "vertical_bar");
        TOKENS_NAMES.put(40, "ampersand");
        TOKENS_NAMES.put(41, "logic_and");
        TOKENS_NAMES.put(42, "left_parenth");
        TOKENS_NAMES.put(43, "right_parenth");
        TOKENS_NAMES.put(46, "colon");
        TOKENS_NAMES.put(47, "semicolon");
        TOKENS_NAMES.put(48, "comma");
        TOKENS_NAMES.put(49, "left_curly");
        TOKENS_NAMES.put(50, "right_curly");
        TOKENS_NAMES.put(51, "left_bracket");
        TOKENS_NAMES.put(52, "right_bracket");

    }
    int state = 0;
    int lexemeIndex = 0;

    public Tokenizer(String path) {
        try {
            scanner = new Scanner(new File(path));
            scanner.useDelimiter("");
            lexeme = new char[1000];
        } catch (FileNotFoundException error) {
            System.out.println("The file cannot be found.\nThe program will exit.");
            System.exit(1);
        } catch (Exception error) {

        }
    }

    public void readTokens() {
        header();
        checkForErrors();
        getChar();
        while (scanner.hasNext()) {
            switches();
        }
        if (!scanner.hasNext()) {
            storeCharIntoLexemeArray();
            returnToken();

        }
    }

    private void switches() {
        switch (state) {
            case 0:
                case0();
                break;
            case 1:
                case1();
                break;
            case 2:
                case2();
                break;
            case 3:
                case3();
                break;
            case 4:
                case4();
                break;
            case 5:
                case5();
                break;
            case 6:
                case6();
                break;
            case 7:
                case7();
                break;
            case 8:
                case8();
                break;
            case 9:
                case9();
                break;
            case 10:
                case10();
                break;
            case 11:
                case11();
                break;
            case 12:
                case12();
                break;
            case 13:
                case13();
                break;
            case 14:
                case14();
                break;
            case 15:
                case15();
                break;
            case 16:
                case16();
                break;
            case 17:
                case17();
                break;
            case 18:
                case18();
                break;
            case 19:
                case19();
                break;
            case 20:
                case20();
                break;
            case 21:
                case21();
                break;
            case 22:
                case22();
                break;
            case 23:
                case23();
                break;
            case 24:
                case24();
                break;
            case 25:
                case25();
                break;
            case 26:
                case26();
                break;
            case 27:
                case27();
                break;
            case 28:
                case28();
                break;
            case 29:
                case29();
                break;
            case 30:
                case30();
                break;
            case 31:
                case31();
                break;
            case 32:
                case32();
                break;
            case 33:
                case33();
                break;
            case 34:
                case34();
                break;
            case 35:
                case35();
                break;
            case 36:
                case36();
                break;
            case 37:
                case37();
                break;
            case 38:
                case38();
                break;
            case 39:
                case39();
                break;
            case 40:
                case40();
                break;
            case 41:
                case41();
                break;
            case 42:
                case42();
                break;
            case 43:
                case43();
                break;
            case 46:
                case46();
                break;
            case 47:
                case47();
                break;
            case 48:
                case48();
                break;
            case 49:
                case49();
                break;
            case 50:
                case50();
                break;
            case 51:
                case51();
                break;
            case 52:
                case52();
                break;
        }

    }

    private void checkForErrors() {
        if (scanner == null) {
            System.out.println("The scanner has not been initialized.\nMake sure you provided a correct path to the tokenizer.\nThe program will exit.");
            System.exit(1);
        }
    }

    private void case0() {
        if (lookahead.matches(" |\\t|\\n|\\r")) {
            state = 0;
        } else if (lookahead.matches("[a-zA-Z]|_|\\$")) {
            state = 1;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("\\d")) {
            state = 3;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("\\+")) {
            state = 7;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("-")) {
            state = 11;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("\\*")) {
            state = 15;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("/")) {
            state = 18;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("%")) {
            state = 21;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("<")) {
            state = 24;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches(">")) {
            state = 27;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("=")) {
            state = 30;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("!")) {
            state = 33;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("\\|")) {
            state = 36;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("&")) {
            state = 39;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("\\(")) {
            state = 42;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("\\)")) {
            state = 43;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches(":")) {
            state = 46;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches(";")) {
            state = 47;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches(",")) {
            state = 48;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("\\{")) {
            state = 49;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("\\}")) {
            state = 50;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("\\[")) {
            state = 51;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("\\]")) {
            state = 52;
            storeCharIntoLexemeArray();
        } else {
            error();
        }
        getChar();
    }

    private void case1() {
        if (lookahead.matches("[a-zA-Z]|_|\\$|\\d")) {
            state = 1;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 2;
        }
    }

    private void case2() {
        returnToken();
    }

    private void case3() {
        if (lookahead.matches("\\d")) {
            state = 3;
            storeCharIntoLexemeArray();
            getChar();
        } else if (lookahead.matches("\\.")) {
            state = 5;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 4;
        }
    }

    private void case4() {
        returnToken();

    }

    private void case5() {
        if (lookahead.matches("\\d")) {
            state = 5;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 6;
        }
    }

    private void case6() {
        returnToken();

    }

    private void case7() {
        if (lookahead.matches("\\+")) {
            state = 8;
            storeCharIntoLexemeArray();
            getChar();
        } else if (lookahead.matches("=")) {
            state = 9;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 10;
        }
    }

    private void case8() {
        returnToken();
    }

    private void case9() {
        returnToken();
    }

    private void case10() {
        returnToken();
    }

    private void case11() {
        if (lookahead.matches("-")) {
            state = 12;
            storeCharIntoLexemeArray();
            getChar();
        } else if (lookahead.matches("=")) {
            state = 13;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 14;
        }
    }

    private void case12() {
        returnToken();
    }

    private void case13() {
        returnToken();
    }

    private void case14() {
        returnToken();
    }

    private void case15() {
        if (lookahead.matches("=")) {
            state = 16;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 17;
        }
    }

    private void case16() {
        returnToken();
    }

    private void case17() {
        returnToken();
    }

    private void case18() {
        if (lookahead.matches("=")) {
            state = 19;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 20;
        }
    }

    private void case19() {
        returnToken();
    }

    private void case20() {
        returnToken();
    }

    private void case21() {
        if (lookahead.matches("=")) {
            state = 22;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 23;
        }
    }

    private void case22() {
        returnToken();
    }

    private void case23() {
        returnToken();
    }

    private void case24() {
        if (lookahead.matches("=")) {
            state = 25;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 26;
        }
    }

    private void case25() {
        returnToken();
    }

    private void case26() {
        returnToken();
    }

    private void case27() {
        if (lookahead.matches("=")) {
            state = 28;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 29;
        }
    }

    private void case28() {
        returnToken();
    }

    private void case29() {
        returnToken();
    }

    private void case30() {
        if (lookahead.matches("=")) {
            state = 31;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 32;
        }
    }

    private void case31() {
        returnToken();
    }

    private void case32() {
        returnToken();
    }

    private void case33() {
        if (lookahead.matches("=")) {
            state = 34;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 35;
        }
    }

    private void case35() {
        returnToken();
    }

    private void case34() {
        returnToken();
    }

    private void case36() {
        if (lookahead.matches("\\|")) {
            state = 38;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 37;
        }
    }

    private void case37() {
        returnToken();

    }

    private void case38() {
        returnToken();
    }

    private void case39() {
        if (lookahead.matches("&")) {
            state = 41;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 40;
        }
    }

    private void case40() {
        returnToken();
    }

    private void case41() {
        returnToken();
    }

    private void case42() {
        returnToken();
    }

    private void case43() {
        returnToken();
    }

    private void error() {
        System.out.println("UNRECOGNIZED_TOKEN");
    }

    private void ungetc() {
        lexeme[lexemeIndex] = '\0';
        String completeLexeme = String.valueOf(lexeme).trim();
        if (RESERVED_WORDS.contains(completeLexeme)) {
            System.out.printf("%s				%s\n", completeLexeme, completeLexeme);
        } else if (state > 0) {
            System.out.printf("%s				%s\n", completeLexeme, TOKENS_NAMES.get(state));
        }
        lexeme = new char[1000];
    }

    private void returnToken() {
        ungetc();
        state = 0;
    }

    private boolean getChar() {
        try {
            lookahead = scanner.next();
            return true;

        } catch (Exception NoSuchElementException) {
            return false;
        }
    }

    private void storeCharIntoLexemeArray() {
        lexeme[lexemeIndex] = lookahead.charAt(0);
        lexemeIndex++;
    }

    private void case46() {
        returnToken();
    }

    private void case47() {
        returnToken();
    }

    private void case48() {
        returnToken();
    }

    private void case49() {
        returnToken();
    }

    private void case50() {
        returnToken();
    }

    private void case51() {
        returnToken();
    }

    private void case52() {
        returnToken();
    }

    private void header() {
        System.out.println("Lexemes				Tokens");
    }
}
