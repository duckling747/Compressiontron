package algo;

import IO.BitsReader;
import datastructs.FreqTable;
import datastructs.FreqTableSimple;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class ACDecompressor extends ACCore {

    private FreqTable freqs;
    private ACDecoder decoder;
    private String filenameIn;
    private String filenameOut;

    public ACDecompressor(String fnameIn, String fnameOut) {
        filenameIn = fnameIn;
        filenameOut = fnameOut;
        freqs = new FreqTableSimple(SYMBOLLIMIT);
    }

    public void decompress() {
        try (BitsReader in = new BitsReader(
                new DataInputStream(new BufferedInputStream(
                        new FileInputStream(filenameIn))));
                BufferedWriter out = new BufferedWriter(new FileWriter(filenameOut))) {
            readFreqsCreateTable(in);
            decoder = new ACDecoder(freqs, in);
            writeDecodedText(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFreqsCreateTable(BitsReader in) throws IOException {
        for (int i = 1; i <= SYMBOLLIMIT; i++) {
            freqs.setFreq(i, in.readInt());
        }
        freqs.calcCumFreq();
    }

    private void writeDecodedText(BitsReader in, BufferedWriter out) throws IOException {
        int symbol;
        while ((symbol = decoder.decodeSymbol(in)) != -1) {
            out.write(symbol);
        }
    }
    
    /**
     * Return this decompressor's frequency table. Mostly for unit testing purposes.
     * @return 
     */
    public FreqTable getFreqTable() {
        return this.freqs;
    }
}
