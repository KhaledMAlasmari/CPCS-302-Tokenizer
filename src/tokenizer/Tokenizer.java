package tokenizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Tokenizer {

    Scanner scanner;
    String lookahead;
    String lookaheadBuffer;
    ArrayList<Character> lexeme;
    static final ArrayList<String> RESERVED_WORDS = new ArrayList<String>(Arrays.asList("auto", "else", "long", "switch", "break", "enum", "register", "typedef", "case", "extern", "return", "union", "char", "float", "short", "unsigned", "const", "for", "signed", "void", "continue", "goto", "sizeof", "volatile", "default", "if", "static", "while", "do", "int", "struct", "_Packed", "double"));
    final String[] tokensNames = {"",};
    boolean errorFlag = false;
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
        TOKENS_NAMES.put(38, "binary_or");
        TOKENS_NAMES.put(40, "logic_and");
        TOKENS_NAMES.put(41, "binary_and");
        TOKENS_NAMES.put(42, "left_parenth");
        TOKENS_NAMES.put(43, "right_parenth");
        TOKENS_NAMES.put(44, "single_quote");
        TOKENS_NAMES.put(46, "colon");
        TOKENS_NAMES.put(47, "semicolon");
        TOKENS_NAMES.put(48, "comma");
        TOKENS_NAMES.put(49, "left_curly");
        TOKENS_NAMES.put(50, "right_curly");
        TOKENS_NAMES.put(51, "left_bracket");
        TOKENS_NAMES.put(52, "right_bracket");
        TOKENS_NAMES.put(54, "binary_xor");
        TOKENS_NAMES.put(55, "bitwise_xor");
        TOKENS_NAMES.put(56, "bitwise_and");
        TOKENS_NAMES.put(57, "bitwise_or");
        TOKENS_NAMES.put(58, "binary_ones_comp");
        TOKENS_NAMES.put(60, "comment");
        TOKENS_NAMES.put(64, "dot");
        TOKENS_NAMES.put(66, "right_shift_and");
        TOKENS_NAMES.put(67, "binary_shift_right");
        TOKENS_NAMES.put(69, "left_shift_and");
        TOKENS_NAMES.put(70, "binary_shift_left");
        TOKENS_NAMES.put(74, "char_literal");
        TOKENS_NAMES.put(75, "Hashtag");
        TOKENS_NAMES.put(45, "double_quotes");
        TOKENS_NAMES.put(71, "string_literal");
        TOKENS_NAMES.put(72, "string_literal");

    }
    int state = 0;

    public Tokenizer(String path) {
        try {
            scanner = new Scanner(new File(path));
            scanner.useDelimiter("");
            lexeme = new ArrayList<Character>();
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
        while (!lexeme.isEmpty()) {
            switches();
        }
        getChar();
        while (!lookaheadBuffer.equals("")) {
            lookahead = lookaheadBuffer;
            switches();
        }
    }

    private void switches() {
        if (state == 0) {
            case0();
            getChar();
        }
        if (state == 1) {
            case1();
        }
        if (state == 2) {
            case2();
        }
        if (state == 3) {
            case3();
        }
        if (state == 4) {
            case4();
        }
        if (state == 5) {
            case5();
        }
        if (state == 6) {
            case6();
        }
        if (state == 7) {
            case7();
        }
        if (state == 8) {
            case8();
        }
        if (state == 9) {
            case9();
        }
        if (state == 10) {
            case10();
        }
        if (state == 11) {
            case11();
        }
        if (state == 12) {
            case12();
        }
        if (state == 13) {
            case13();
        }
        if (state == 14) {
            case14();
        }
        if (state == 15) {
            case15();
        }
        if (state == 16) {
            case16();
        }
        if (state == 17) {
            case17();
        }
        if (state == 18) {
            case18();
        }
        if (state == 19) {
            case19();
        }
        if (state == 20) {
            case20();
        }
        if (state == 21) {
            case21();
        }
        if (state == 22) {
            case22();
        }
        if (state == 23) {
            case23();
        }
        if (state == 24) {
            case24();
        }
        if (state == 25) {
            case25();
        }
        if (state == 26) {
            case26();
        }
        if (state == 27) {
            case27();
        }
        if (state == 28) {
            case28();
        }
        if (state == 29) {
            case29();
        }
        if (state == 30) {
            case30();
        }
        if (state == 31) {
            case31();
        }
        if (state == 32) {
            case32();
        }
        if (state == 33) {
            case33();
        }
        if (state == 34) {
            case34();
        }
        if (state == 35) {
            case35();
        }
        if (state == 36) {
            case36();
        }
        if (state == 37) {
            case37();
        }
        if (state == 38) {
            case38();
        }
        if (state == 39) {
            case39();
        }
        if (state == 40) {
            case40();
        }
        if (state == 41) {
            case41();
        }
        if (state == 42) {
            case42();
        }
        if (state == 43) {
            case43();
        }
        if (state == 44) {
            case44();
        }
        if (state == 46) {
            case46();
        }
        if (state == 47) {
            case47();
        }
        if (state == 48) {
            case48();
        }
        if (state == 49) {
            case49();
        }
        if (state == 50) {
            case50();
        }
        if (state == 51) {
            case51();
        }
        if (state == 52) {
            case52();
        }
        if (state == 53) {
            case53();
        }
        if (state == 54) {
            case54();
        }
        if (state == 55) {
            case55();
        }
        if (state == 56) {
            case56();
        }
        if (state == 57) {
            case57();
        }
        if (state == 58) {
            case58();
        }
        if (state == 59) {
            case59();
        }
        if (state == 60) {
            case60();
        }
        if (state == 61) {
            case61();
        }
        if (state == 62) {
            case62();
        }
        if (state == 63) {
            case63();
        }
        if (state == 64) {
            case64();
        }
        if (state == 65) {
            case65();
        }
        if (state == 66) {
            case66();
        }
        if (state == 67) {
            case67();
        }
        if (state == 68) {
            case68();
        }
        if (state == 69) {
            case69();
        }
        if (state == 70) {
            case70();
        }
        if (state == 73) {
            case73();
        }
        if (state == 74) {
            case74();
        }
        if (state == 75) {
            case75();
        }
        if (state == 45) {
            case45();
        }
        if (state == 71) {
            case71();
        }
        if (state == 72) {
            case72();
        }
        if (state == 0 && lookahead.equals("")) {
            lookaheadBuffer = "";
        }
    }

    private void checkForErrors() {
        if (scanner == null) {
            System.out.println("The scanner has not been initialized.\nMake sure you provided a correct path to the tokenizer.\nThe program will exit.");
            System.exit(1);
        }
    }

    private void case0() {
        if (lookahead.matches(" |\\t|\\n|\\r|\r")) {
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
        } else if (lookahead.matches("'")) {
            state = 44;
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
        } else if (lookahead.matches("\\^")) {
            state = 53;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("~")) {
            state = 58;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("\\.")) {
            state = 63;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("#")) {
            state = 75;
            storeCharIntoLexemeArray();
        } else if (lookahead.matches("\"")) {
            state = 45;
            storeCharIntoLexemeArray();
        } else {
            error();
        }
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
        } else if (lookahead.matches("/")) {
            state = 59;
            storeCharIntoLexemeArray();
            getChar();
        } else if (lookahead.matches("\\*")) {
            state = 61;
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
        } else if (lookahead.matches("<")) {
            state = 68;
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
        } else if (lookahead.matches(">")) {
            state = 65;
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
            state = 37;
            storeCharIntoLexemeArray();
            getChar();
        } else if (lookahead.matches("=")) {
            state = 57;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 38;
        }
    }

    private void case37() {
        returnToken();

    }

    private void case38() {
        returnToken();
    }

    private void case39() {
        if (lookahead.matches("\\&")) {
            state = 40;
            storeCharIntoLexemeArray();
            getChar();
        }
        else if (lookahead.matches("=")) {
            state = 56;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 41;
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

    private void case45() {
        if (lookahead.matches("\r")) {
            returnToken();
        } else if (lookahead.matches("\"")) {
            state = 71;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 72;
            storeCharIntoLexemeArray();
            getChar();
        }
    }

    private void case71() {
        returnToken();
    }

    private void case72() {
        if (lookahead.matches("\"")) {
            storeCharIntoLexemeArray();
            getChar();
            state = 71;
        } else if (lookahead.matches("\r")) {
            seprateLexemesForInvalidStringLiterals();
        } else {
            state = 72;
            storeCharIntoLexemeArray();
            getChar();
        }
    }
    private void case53() {
        if (lookahead.matches("=")) {
            state = 55;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 54;
        }
    }

    private void case54() {
        returnToken();
    }

    private void case55() {
        returnToken();
    }

    private void case56() {
        returnToken();
    }

    private void case57() {
        returnToken();
    }

    private void case58() {
        returnToken();
    }

    private void case59() {
        if (lookahead.matches("\\n")) {
            storeCharIntoLexemeArray();
            getChar();
            state = 60;
        } else {
            state = 59;
            storeCharIntoLexemeArray();
            getChar();
        }
    }

    // comments - ignore this lol
    private void case60() {
        //returnToken();
        resetLexeme();
        state = 0;
    }

    private void case61() {
        if (lookahead.matches("\\*")) {
            state = 62;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 61;
            storeCharIntoLexemeArray();
            getChar();
        }
    }

    private void case62() {
        if (lookahead.matches("\\*")) {
            state = 62;
            storeCharIntoLexemeArray();
            getChar();
        } else if (lookahead.matches("/")) {
            state = 60;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 61;
            storeCharIntoLexemeArray();
            getChar();
        }
    }

    private void case63() {
        if (lookahead.matches("\\d")) {
            state = 5;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 64;
        }
    }

    private void case64() {
        returnToken();
    }

    private void case65() {
        if (lookahead.matches("=")) {
            state = 66;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 67;
        }
    }

    private void case66() {
        returnToken();
    }

    private void case67() {
        returnToken();
    }

    private void case68() {
        if (lookahead.matches("=")) {
            state = 69;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            state = 70;

        }
    }

    private void case69() {
        returnToken();
    }

    private void case70() {
        returnToken();
    }

    private void case75() {
        returnToken();
        storeCharIntoLexemeArray();
        getChar();
    }

    private void case44() {
        if (lookahead.matches("'")) {
            returnToken();
        } else {
            state = 73;
            storeCharIntoLexemeArray();
            getChar();
        }
    }

    private void case73() {
        if (lookahead.matches("'")) {
            state = 74;
            storeCharIntoLexemeArray();
            getChar();
        } else {
            seprateLexemesForInvalidCharacterLiterals();
        }
    }

    private void case74() {
        returnToken();
    }

    private void seprateLexemesForInvalidCharacterLiterals() {
        lexeme.remove(0);
        ArrayList<Character> copy = new ArrayList<Character>(lexeme);
        lexeme = new ArrayList<Character>();
        lexeme.add('\'');
        state = 44;
        returnToken();
        lexeme = copy;
    }
    private void header() {
        System.out.printf("%-35s%30s\n", "Lexemes","Tokens");
        System.out.println("----------------------------------------------------------------------------------");
    }

    private void error() {
        System.out.println("UNRECOGNIZED_TOKEN");
    }

    private void ungetc() {
        lexeme.add('\0');
        String completeLexeme = lexeme.stream()
                .map(e -> e.toString())
                .reduce((acc, e) -> acc + e)
                .get().trim();
        if (RESERVED_WORDS.contains(completeLexeme)) {
            System.out.printf("%-35s%15s%30s\n", completeLexeme, "|", completeLexeme);
        } else if (state >= 0) {
            System.out.printf("%-35s%15s%30s\n",completeLexeme, "|" , TOKENS_NAMES.get(state));
        }
        resetLexeme();
    }

    private void resetLexeme() {
        lexeme = new ArrayList<>();

    }

    private void returnToken() {
        ungetc();

        state = 0;
    }

    private boolean getChar() {
        try {
            if (!errorFlag) {
                lookahead = scanner.next();
            }
            return true;

        } catch (Exception NoSuchElementException) {
            lookaheadBuffer = lookahead;
            lookahead = "";
            return false;
        }
    }

    private void storeCharIntoLexemeArray() {
        lexeme.add(lookahead.charAt(0));
    }

    private void seprateLexemesForInvalidStringLiterals() {
        lexeme.remove(0);
        ArrayList<Character> copy = new ArrayList<Character>(lexeme);
        lexeme = new ArrayList<Character>();
        lexeme.add('"');
        state = 90;
        returnToken();
        lexeme = new ArrayList<>();
        // "hehe xd
        errorFlag = true;
        for (int i = 0; i < copy.size(); i += 1) {
            lookahead = Character.toString(copy.get(i));
            switches();
        }
        errorFlag = false;
    }
}
