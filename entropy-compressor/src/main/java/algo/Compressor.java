package algo;

import datastructs.FreqTable;
import io.BitsWriter;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import main.Main;

public abstract class Compressor extends General {

    protected String filenameIn;
    protected String filenameOut;
    protected FreqTable freqs;

    /**
     * Compress the given file. This method is intended to be the top level
     * compression method, and is hence public.
     */
    public void compress() {
        try (BufferedReader in = new BufferedReader(new FileReader(filenameIn));
                BitsWriter bitswriter = new BitsWriter(new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(filenameOut))))) {
            writeFrequencies(bitswriter);
            writeEncodedText(in, bitswriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write the encoded text into the underlying stream.
     *
     * @param in
     * @param out
     * @throws IOException
     */
    protected abstract void writeEncodedText(BufferedReader in,
            BitsWriter out) throws IOException;

    /**
     * Reads a text file and sets up the frequency table.
     */
    protected void readFileSetFreqs() {
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
    private void writeFrequencies(BitsWriter bitswriter)
            throws IOException {
        for (int i = 1; i <= Main.SYMBOLLIMIT; i++) {
            bitswriter.writeByte(freqs.getFreq(i));
        }
    }

    public FreqTable getFreqs() {
        return freqs;
    }
}
