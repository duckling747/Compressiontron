package algo;

import io.BitsReader;
import datastructs.FreqTableCumulative;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import static main.Main.SYMBOLLIMIT;

public class ACDecompressor extends Decompressor {

    private ACDecoder decoder;

    public ACDecompressor(String fnameInCompression, String fnameInFrequencies, String fnameOut) {
        filenameInCompression = fnameInCompression;
        filenameInFrequencies = fnameInFrequencies;
        filenameOut = fnameOut;
        freqs = new FreqTableCumulative(SYMBOLLIMIT);
    }

    @Override
    public void decompress() {
        try (BitsReader in = new BitsReader(
                new DataInputStream(new BufferedInputStream(
                        new FileInputStream(filenameInFrequencies))))) {
            readFreqsCreateTable(in);
            decoder = new ACDecoder((FreqTableCumulative) freqs, in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (decoder == null) {
            throw new IllegalStateException("the decoder is null");
        }
        try (BitsReader in = new BitsReader(
                new DataInputStream(new BufferedInputStream(
                        new FileInputStream(filenameInCompression))));
                DataOutputStream out = new DataOutputStream(
                        new FileOutputStream(filenameOut))) {
            writeDecodedText(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFreqsCreateTable(BitsReader in) throws IOException {
        for (int i = 1; i <= SYMBOLLIMIT; i++) {
            freqs.setFreq(i, in.readByte());
        }
        ((FreqTableCumulative) freqs).calcCumFreq();
    }

    private void writeDecodedText(BitsReader in, DataOutputStream out) throws IOException {
        int symbol;
        while ((symbol = decoder.decodeSymbol(in)) != -1) {
            out.write(symbol);
        }
    }

    /**
     * Return this decompressor's frequency table. Mostly for unit testing
     * purposes.
     *
     * @return
     */
    public FreqTableCumulative getFreqTable() {
        return (FreqTableCumulative) freqs;
    }
}
