package algo;

import io.BitsWriter;
import datastructs.FreqTableCumulative;
import java.io.BufferedReader;
import java.io.IOException;
import main.Main;

public class ACCompressor extends Compressor {

    private ACEncoder encoder;

    public ACCompressor(String fnameIn, String fnameOut) {
        freqs = new FreqTableCumulative(Main.SYMBOLLIMIT);
        encoder = new ACEncoder((FreqTableCumulative) freqs);
        filenameIn = fnameIn;
        filenameOut = fnameOut;
        super.readFileSetFreqs();
        ((FreqTableCumulative) freqs).calcCumFreq();
    }

    /**
     * Writes the encoded message to file.
     *
     * @param in
     * @param out
     * @throws IOException
     */
    @Override
    protected void writeEncodedText(BufferedReader in, BitsWriter out)
            throws IOException {
        int c;
        while ((c = in.read()) != -1) {
            encoder.encodeSymbol(c, out);
        }
        encoder.finalize(out);
    }

    /**
     * Return this compressor's frequency table. Mostly for unit testing
     * purposes.
     *
     * @return
     */
    @Override
    public FreqTableCumulative getFreqs() {
        return (FreqTableCumulative) freqs;
    }

}
