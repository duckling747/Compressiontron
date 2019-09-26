package algo;

import datastructs.FreqTable;
import io.BitsWriter;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import main.Main;

public abstract class Compressor extends General {

    protected String filenameIn;
    protected String filenameOutCompressed;
    protected String filenameOutFreqs;
    protected FreqTable freqs;

    /**
     * Compress the given file. This method is intended to be the top level
     * compression method, and is hence public. The frequencies are stored in
     * one file, the compressed message in another. 
     */
    public void compress() {
        try (BitsWriter bitswriter = new BitsWriter(new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(filenameOutFreqs))))) {
            writeFrequencies(bitswriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (DataInputStream in = new DataInputStream(new FileInputStream(filenameIn));
                BitsWriter bitswriter = new BitsWriter(new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(filenameOutCompressed))))) {
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
    protected abstract void writeEncodedText(DataInputStream in,
            BitsWriter out) throws IOException;

    /**
     * Reads a text file and sets up the frequency table.
     */
    protected void readFileSetFreqs() {
        try (DataInputStream in = new DataInputStream(
                new FileInputStream(filenameIn))) {
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
