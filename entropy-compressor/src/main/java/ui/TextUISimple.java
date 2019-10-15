package ui;

import algo.HuffmanCompressor;
import algo.HuffmanDecompressor;
import io.FileUtils;
import java.util.concurrent.TimeUnit;

public final class TextUISimple {

    private static final String COMPR = "-com";
    private static final String DECOMPR = "-decom";
    private static final String NOTBENCHING = "-notbench";

    private static final String HUFFSUFFIX = ".huff";
    private static final String FREQSSUFFIX = ".freqs";
    private static final String TEXTSUFFIX = ".txt";

    private static long time = 0;
    private static boolean benchmarking = true;

    public static boolean parseInput(String[] input) {
        if (input.length < 3 || input.length > 4) {
            System.out.println(helpMessage());
            return false;
        }
        if (input[2].equals(NOTBENCHING)) {
            benchmarking = false;
        } else {
            time = System.nanoTime();
        }
        if (input[0].equals(COMPR)) {
            huffmanEncoding(input[input.length - 2], input[input.length - 1]);
        } else if (input[0].equals(DECOMPR)) {
            huffmanDecoding(input[input.length - 2], input[input.length - 1]);
        } else {
            System.out.println(helpMessage());
        }
        return true;
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
        return String.format("Program usage example: java -jar [executable file] [%s|%s] [fileIn] [fileOut]",
                COMPR, DECOMPR);
    }

    private static String benchmarkingResultsCompress(long time, String fileIn, String fileOut) {
        return String.format(
                "Original file size\tCompressed file size\tCompression ratio\tTime\n%s\t\t%s\t\t%s\t\t\t%d ms",
                FileUtils.getFileSize(fileIn), FileUtils.getFileSize(fileOut),
                FileUtils.getRatio(fileIn, fileOut), TimeUnit.NANOSECONDS.toMillis(time));
    }

    private static String benchmarkingResultsDecompress(long time) {
        return String.format("Time\n%d", TimeUnit.NANOSECONDS.toMillis(time));
    }

}
