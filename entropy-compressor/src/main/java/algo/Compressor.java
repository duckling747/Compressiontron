package algo;

import datastructs.FreqTable;
import io.BitsWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import main.Main;

public abstract class Compressor extends General {

    protected String filenameIn;
    protected String filenameOut;
    protected FreqTable freqs;

    public abstract void compress();

    protected abstract void writeEncodedText(BufferedReader in, BitsWriter bitswriter)
            throws IOException;

    /**
     * Reads a text file and sets up the frequency table.
     */
    protected final void readFileSetFreqs() {
        try (BufferedReader in = new BufferedReader(
                new FileReader(filenameIn))) {
            int c;
            while ((c = in.read()) != -1) {
                freqs.addFreq(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes the frequencies from the table to file.
     *
     * @param bitswriter
     * @throws IOException
     */
    protected final void writeFrequencies(BitsWriter bitswriter) throws IOException {
        for (int i = 1; i <= Main.SYMBOLLIMIT; i++) {
            bitswriter.writeByte(freqs.getFreq(i));
        }
    }
}
