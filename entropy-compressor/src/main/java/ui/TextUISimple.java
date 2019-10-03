package ui;

import java.util.Arrays;

public final class TextUISimple {

    private static final String[][] ACCEPT = {
        {"-ac", "-enc"},
        {"-huff", "-enc"},
        {"-ac", "-dec"},
        {"-huff", "-dec"},};

    public static int checkInput(String[] input) {
        for (int counter = 0; counter < ACCEPT.length; counter++) {
            if (Arrays.deepEquals(ACCEPT[counter], input)) {
                return counter;
            }
        }
        return -1;
    }

    public static String helpMessage() {
        return "Program usage example: java -jar [-ac|-huff] [-enc|-dec] [file]";
    }

    public void work(String[] input) {
        switch (checkInput(input)) {
            case -1:
                System.out.println(helpMessage());
                return;
            case 0:
                return;
            // encode ac
            case 1:
                return;
            // encode huffman
            case 2:
                return;
            // decode ac
            case 3:
                return;
            //decode huffman
            default:
                throw new IllegalStateException();
        }
    }

}
