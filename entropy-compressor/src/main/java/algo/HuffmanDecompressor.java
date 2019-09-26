package algo;

import datastructs.FreqTable;
import datastructs.FreqTableSimple;
import io.BitsReader;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import main.Main;

public class HuffmanDecompressor extends Decompressor {

    private HuffmanDecoder decoder;

    public HuffmanDecompressor(String fnameInCompression, String fnameInFrequencies, String fnameOut) {
        filenameInCompression = fnameInCompression;
        filenameInFrequencies = fnameInFrequencies;
        filenameOut = fnameOut;
        freqs = new FreqTableSimple(Main.SYMBOLLIMIT);

    }

    @Override
    public void decompress() {
        try (BitsReader in = new BitsReader(
                new DataInputStream(new FileInputStream(filenameInFrequencies)))) {
            readFileCreateFreqTable(in);
            decoder = new HuffmanDecoder(freqs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (decoder == null) {
            throw new IllegalStateException("decoder is null");
        }
        try (BitsReader in = new BitsReader(
                new DataInputStream(new BufferedInputStream(
                        new FileInputStream(filenameInCompression))));
                BufferedWriter out = new BufferedWriter(
                        new FileWriter(filenameOut))) {
            writeDecodedText(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFileCreateFreqTable(BitsReader in) throws IOException {
        for (int i = 1; i <= Main.SYMBOLLIMIT; i++) {
            freqs.setFreq(i, in.readByte());
        }
    }

    private void writeDecodedText(BitsReader in, BufferedWriter out) throws IOException {
        int symbol;
        while ((symbol = decoder.decodeSymbol(in)) != -1) {
            out.write(symbol);
        }
    }

    public FreqTable getTable() {
        return this.freqs;
    }

}
