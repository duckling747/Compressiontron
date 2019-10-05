package ui;

import algo.ACCompressor;
import algo.ACDecompressor;
import algo.HuffmanCompressor;
import algo.HuffmanDecompressor;
import io.FileUtils;
import java.util.concurrent.TimeUnit;

public final class TextUISimple {

    private static final String AC = "-ac";
    private static final String HUFF = "-huff";
    private static final String COMPR = "-com";
    private static final String DECOMPR = "-decom";
    private static final String NOTBENCHING = "-notbench";

    private static final String ARITHSUFFIX = ".ac";
    private static final String HUFFSUFFIX = ".huff";
    private static final String FREQSSUFFIX = ".freqs";
    private static final String TEXTSUFFIX = ".txt";

    private static long time = 0;
    private static boolean benchmarking = true;

    public static boolean parseInput(String[] input) {
        if (input.length < 4 || input.length > 5) {
            System.out.println(helpMessage());
            return false;
        }
        if (input[3].equals(NOTBENCHING)) {
            benchmarking = false;
        } else {
            time = System.nanoTime();
        }
        if (input[0].equals(AC)) {
            arithmeticSomething(input);
        } else if (input[0].equals(HUFF)) {
            huffmanSomething(input);
        } else {
            System.out.println(helpMessage());
            return false;
        }
        return true;
    }

    private static void arithmeticSomething(String[] input) {
        if (input[1].equals(COMPR)) {
            arithmeticEncoding(input[input.length - 2], input[input.length - 1]);
        } else if (input[1].equals(DECOMPR)) {
            arithmeticDecoding(input[input.length - 2], input[input.length - 1]);
        } else {
            System.out.println(helpMessage());
        }
    }

    private static void huffmanSomething(String[] input) {
        if (input[1].equals(COMPR)) {
            huffmanEncoding(input[input.length - 2], input[input.length - 1]);
        } else if (input[1].equals(DECOMPR)) {
            huffmanDecoding(input[input.length - 2], input[input.length - 1]);
        } else {
            System.out.println(helpMessage());
        }
    }

    private static void arithmeticEncoding(String fnameIn, String fnameOut) {
        if (benchmarking) {
            time = System.nanoTime();
        }
        ACCompressor com = new ACCompressor(
                fnameIn, fnameOut + ARITHSUFFIX, fnameOut + FREQSSUFFIX);
        com.readFrequencies();
        com.writeFrequencies();
        com.writeEncodedText();
        if (benchmarking) {
            time = System.nanoTime() - time;
            System.out.println(benchmarkingResultsCompress(time, fnameIn,
                    fnameOut + ARITHSUFFIX));
        }
    }

    private static void arithmeticDecoding(String fnameIn, String fnameOut) {
        if (benchmarking) {
            time = System.nanoTime();
        }
        ACDecompressor decom = new ACDecompressor(
                fnameIn, fnameIn.split("\\.")[0] + FREQSSUFFIX, fnameOut + TEXTSUFFIX);
        decom.readFrequencies();
        decom.readEncodedText();
        if (benchmarking) {
            time = System.nanoTime() - time;
            System.out.println(benchmarkingResultsDecompress(time));
        }
    }

    private static void huffmanEncoding(String fnameIn, String fnameOut) {
        if (benchmarking) {
            time = System.nanoTime();
        }
        HuffmanCompressor com = new HuffmanCompressor(
                fnameIn, fnameOut + HUFFSUFFIX, fnameOut + FREQSSUFFIX);
        com.readFrequencies();
        com.writeFrequencies();
        com.writeEncodedText();
        if (benchmarking) {
            time = System.nanoTime() - time;
            System.out.println(benchmarkingResultsCompress(time, fnameIn, fnameOut + HUFFSUFFIX));
        }
    }

    private static void huffmanDecoding(String fnameIn, String fnameOut) {
        if (benchmarking) {
            time = System.nanoTime();
        }
        HuffmanDecompressor decom = new HuffmanDecompressor(
                fnameIn, fnameIn.split("\\.")[0] + FREQSSUFFIX, fnameOut + TEXTSUFFIX);
        decom.readFrequencies();
        decom.readEncodedText();
        if (benchmarking) {
            time = System.nanoTime() - time;
            System.out.println(benchmarkingResultsDecompress(time));
        }
    }

    private static String helpMessage() {
        return String.format("Program usage example: java -jar [executable file] [%s|%s] [%s|%s] [fileIn] [fileOut]",
                AC, HUFF, COMPR, DECOMPR);
    }

    private static String benchmarkingResultsCompress(long time, String fileIn, String fileOut) {
        return String.format(
                "Original file size\tCompressed file size\tCompression ratio\tTime\n%s\t\t\t%s\t\t\t%s\t\t\t%d ms",
                FileUtils.getFileSize(fileIn), FileUtils.getFileSize(fileOut),
                FileUtils.getRatio(fileIn, fileOut), TimeUnit.NANOSECONDS.toMillis(time));
    }

    private static String benchmarkingResultsDecompress(long time) {
        return String.format("Time\n%d", TimeUnit.NANOSECONDS.toMillis(time));
    }

}
