package algo;

import io.BitsWriter;
import datastructs.FreqTableCumulative;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import main.Main;

public class ACCompressor extends Compressor {

    private ACEncoder encoder;

    public ACCompressor(String fnameIn, String fnameOut) {
        freqs = new FreqTableCumulative(Main.SYMBOLLIMIT);
        encoder = new ACEncoder((FreqTableCumulative) freqs);
        filenameIn = fnameIn;
        filenameOut = fnameOut;
        readFileSetFreqs();
        ((FreqTableCumulative) freqs).calcCumFreq();
    }

    /**
     * Produces the encoded file so that it contains the symbol frequencies
     * before the actual encoded message.
     */
    @Override
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
        return (FreqTableCumulative) freqs;
    }

}
