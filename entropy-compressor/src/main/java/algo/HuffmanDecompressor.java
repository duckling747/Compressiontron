package algo;

import datastructs.FreqTable;
import datastructs.FreqTableSimple;
import io.BitsReader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
        if (freqs.getFreq(freqs.getSymbolLimit()) != 1) {
            throw new IllegalStateException("EOF frequency must be 1");
        }
    }

    @Override
    public void readFrequencies() {
        try (InputStream in = new BufferedInputStream(new FileInputStream(filenameInFrequencies))) {
            freqs = new FreqTableSimple(new int[General.SYMBOLLIMIT + 1]);
            for (int i = 0; i <= freqs.getSymbolLimit(); i++) {
                freqs.setFreq(i, in.read());
            }
        } catch (IOException e) {
        }
        if (freqs == null) {
            throw new AssertionError("Frequency table not initialized ");
        }
    }

    @Override
    public void readEncodedText() {
        try (BitsReader in = new BitsReader(new BufferedInputStream(new FileInputStream(filenameInCompression)));
                OutputStream out = new BufferedOutputStream(new FileOutputStream(filenameOut))) {
            HuffmanDecoder decoder = new HuffmanDecoder(freqs);
            int symbol;
            while ((symbol = decoder.decodeSymbol(in)) != freqs.getSymbolLimit()) { // table's symbol limit = EOF
                if (symbol == -1) {
                    throw new EOFException();
                }
                out.write(symbol);
            }
        } catch (IOException e) {
        }
    }

    @Override
    public FreqTable getFrequencyTable() {
        return this.freqs;
    }

}
