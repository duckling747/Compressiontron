package algo;

import IO.BitsWriter;
import datastructs.FreqTable;
import datastructs.FreqTableSimple;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class ACCompress {

    private ACEncoder encoder;
    private FreqTable freqs;
    private String filename;

    private final static int SYMBOLLIMIT = 1000;
    // private final static int EOF = SYMBOLLIMIT + 1;

    public ACCompress(String fname) {
        freqs = new FreqTableSimple(SYMBOLLIMIT);
        encoder = new ACEncoder(freqs);
        filename = fname;
    }

    /**
     * Produces the encoded file so that it contains the symbol frequencies
     * before the actual encoded message.
     */
    public void compress() {
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            readFileSetFreqs(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader in = new BufferedReader(new FileReader(filename));
                BitsWriter bitswriter = new BitsWriter(new DataOutputStream(
                        new BufferedOutputStream(new FileOutputStream("AC-compressed"))))) {
            writeFrequencies(bitswriter);
            writeEncodedText(in, bitswriter);
        } catch (IOException e) {
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
        for (int i = 1; i <= SYMBOLLIMIT; i++) {
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
    private void writeEncodedText(BufferedReader in, BitsWriter bitswriter) throws IOException {
        int c;
        while ((c = in.read()) != -1) {
            encoder.encodeSymbol((char) c, bitswriter);
        }
        encoder.finalize(bitswriter);
    }

}
