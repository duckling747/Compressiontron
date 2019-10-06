package algo;

import datastructs.FreqTable;
import datastructs.FreqTableSimple;
import io.BitsReader;
import io.BitsWriter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class HuffmanCompressor implements Compressor {

    private String filenameIn;
    private String filenameOutCompressed;
    private String filenameOutFreqs;
    private FreqTable freqs;

    public HuffmanCompressor(String fileIn, String fileOutCompression, String fileOutFrequencies) {
        this.filenameIn = fileIn;
        this.filenameOutCompressed = fileOutCompression;
        this.filenameOutFreqs = fileOutFrequencies;
    }

    public HuffmanCompressor(String fileIn, String fileOutCompression, String fileOutFrequencies, FreqTable freqs) {
        this.filenameIn = fileIn;
        this.filenameOutCompressed = fileOutCompression;
        this.filenameOutFreqs = fileOutFrequencies;
        this.freqs = freqs;
        if (freqs.getFreq(freqs.getSymbolLimit()) != 1) {
            throw new IllegalStateException("EOF frequency must be 1");
        }
    }

    @Override
    public void readFrequencies() {
        freqs = new FreqTableSimple(new int[General.SYMBOLLIMIT + 1]);
        try (InputStream in = new BufferedInputStream(new FileInputStream(filenameIn))) {
            int readByte;
            while ((readByte = in.read()) != -1) {
                freqs.addFreq(readByte);
            }
            freqs.setFreq(freqs.getSymbolLimit(), 1); // SET EOF
        } catch (IOException e) {
        }
    }

    @Override
    public void writeFrequencies() {
        if (freqs == null) {
            throw new AssertionError("Frequencies not initialized ");
        }
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(filenameOutFreqs))) {
            for (int i = 0; i <= freqs.getSymbolLimit(); i++) {
                out.write(freqs.getFreq(i));
            }
        } catch (IOException e) {
        }
    }

    @Override
    public void writeEncodedText() {
        if (freqs == null) {
            throw new AssertionError("Frequencies not initialized ");
        }
        try (BitsWriter out = new BitsWriter(new BufferedOutputStream(new FileOutputStream(filenameOutCompressed)));
                InputStream in = new BufferedInputStream(new FileInputStream(filenameIn))) {
            HuffmanEncoder encoder = new HuffmanEncoder(freqs);
            int symbol;
            while ((symbol = in.read()) != -1) {
                encoder.encodeSymbol(symbol, out);
            }
            encoder.encodeSymbol(freqs.getSymbolLimit(), out); // EOF
        } catch (IOException e) {

        }
    }

    @Override
    public FreqTable getFrequencyTable() {
        return this.freqs;
    }

}
