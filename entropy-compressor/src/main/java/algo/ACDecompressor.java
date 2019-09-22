package algo;

import io.BitsReader;
import datastructs.FreqTable;
import datastructs.FreqTableCumulative;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import main.Main;

public class ACDecompressor extends Decompressor {

    private ACDecoder decoder;

    public ACDecompressor(String fnameIn, String fnameOut) {
        filenameIn = fnameIn;
        filenameOut = fnameOut;
        freqs = new FreqTableCumulative(Main.SYMBOLLIMIT);
    }

    @Override
    public void decompress() {
        try (BitsReader in = new BitsReader(
                new DataInputStream(new BufferedInputStream(
                        new FileInputStream(filenameIn))));
                BufferedWriter out = new BufferedWriter(
                        new FileWriter(filenameOut))) {
            readFreqsCreateTable(in);
            decoder = new ACDecoder((FreqTableCumulative) freqs, in);
            writeDecodedText(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFreqsCreateTable(BitsReader in) throws IOException {
        for (int i = 1; i <= Main.SYMBOLLIMIT; i++) {
            freqs.setFreq(i, in.readInt());
        }
        ((FreqTableCumulative) freqs).calcCumFreq();
    }

    private void writeDecodedText(BitsReader in, BufferedWriter out) throws IOException {
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
