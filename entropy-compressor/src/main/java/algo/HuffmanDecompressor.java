package algo;

import datastructs.FreqTable;
import datastructs.FreqTableSimple;
import io.BitsReader;
import io.BitsWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class HuffmanDecompressor implements Decompressor {

    private String filenameInCompression;
    private String filenameInFrequencies;
    private String filenameOut;
    private FreqTable freqs;

    public HuffmanDecompressor(String fnameInCompression, String fnameInFrequencies, String fnameOut) {
        this.filenameInCompression = fnameInCompression;
        this.filenameInFrequencies = fnameInFrequencies;
        this.filenameOut = fnameOut;
    }

    public HuffmanDecompressor(String fnameInCompression, String fnameInFrequencies, String fnameOut, FreqTable freqs) {
        this.filenameInCompression = fnameInCompression;
        this.filenameInFrequencies = fnameInFrequencies;
        this.filenameOut = fnameOut;
        this.freqs = freqs;
    }

    @Override
    public void readFrequencies() {
        try (BitsReader in = new BitsReader(new DataInputStream(new FileInputStream(filenameInFrequencies)))) {
            freqs = new FreqTableSimple(new int[General.SYMBOLLIMIT + 1]);
            for (int i = 0; i <= freqs.getSymbolLimit(); i++) {
                freqs.setFreq(i, in.readByte());
            }
        } catch (IOException e) {
        }
        if (freqs == null) {
            throw new AssertionError("Frequency table not initialized ");
        }
    }

    @Override
    public void readEncodedText() {
        try (BitsReader in = new BitsReader(new DataInputStream(new FileInputStream(filenameInCompression)));
                BitsWriter out = new BitsWriter((new DataOutputStream(new FileOutputStream(filenameOut))))) {
            HuffmanDecoder decoder = new HuffmanDecoder(freqs);
            int symbol;
            while ((symbol = decoder.decodeSymbol(in)) != -1) {
                out.writeByte(symbol);
            }
        } catch (IOException e) {
        }
    }

    @Override
    public FreqTable getFrequencyTable() {
        return this.freqs;
    }

}
