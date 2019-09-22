package algo;

import io.BitsWriter;
import datastructs.FreqTable;
import datastructs.FreqTableCumulative;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import main.Main;

public class ACCompressor extends ACCore {

    private ACEncoder encoder;
    private FreqTableCumulative freqs;
    private String filenameIn;
    private String filenameOut;

    public ACCompressor(String fnameIn, String fnameOut) {
        freqs = new FreqTableCumulative(Main.SYMBOLLIMIT);
        encoder = new ACEncoder(freqs);
        filenameIn = fnameIn;
        filenameOut = fnameOut;
    }

    /**
     * Produces the encoded file so that it contains the symbol frequencies
     * before the actual encoded message.
     */
    public void compress() {
        try (BufferedReader in = new BufferedReader(
                new FileReader(filenameIn))) {
            readFileSetFreqs(in);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader in = new BufferedReader(new FileReader(filenameIn));
                BitsWriter bitswriter = new BitsWriter(new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(filenameOut))))) {
            writeFrequencies(bitswriter);
            writeEncodedText(in, bitswriter);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a text file and sets up the frequency table.
     *
     * @param in
     * @throws IOException
     */
    private void readFileSetFreqs(BufferedReader in) throws IOException {
        int c;
        while ((c = in.read()) != -1) {
            freqs.addFreq(c);
        }
        freqs.calcCumFreq();
    }

    /**
     * Writes the frequencies from the table to file.
     *
     * @param bitswriter
     * @throws IOException
     */
    private void writeFrequencies(BitsWriter bitswriter) throws IOException {
        for (int i = 1; i <= Main.SYMBOLLIMIT; i++) {
            bitswriter.writeInt(freqs.getFreq(i));
        }
    }

    /**
     * Writes the encoded message to file.
     *
     * @param in
     * @param bitswriter
     * @throws IOException
     */
    private void writeEncodedText(BufferedReader in, BitsWriter bitswriter)
            throws IOException {
        int c;
        while ((c = in.read()) != -1) {
            encoder.encodeSymbol(c, bitswriter);
        }
        encoder.finalize(bitswriter);
    }

    /**
     * Return this compressor's frequency table. Mostly for unit testing
     * purposes.
     *
     * @return
     */
    public FreqTableCumulative getFreqs() {
        return this.freqs;
    }

}
