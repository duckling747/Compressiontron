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
    }

    @Override
    public void readFrequencies() {
        freqs = new FreqTableSimple(new int[General.SYMBOLLIMIT + 1]);
        try (BitsReader in = new BitsReader(new BufferedInputStream(new FileInputStream(filenameIn)))) {
            int readByte;
            while ((readByte = in.readByte()) != -1) {
                freqs.addFreq(readByte);
            }
        } catch (IOException e) {
        }
    }

    @Override
    public void writeFrequencies() {
        if (freqs == null) {
            throw new AssertionError("Frequencies not initialized ");
        }
        try (BitsWriter out = new BitsWriter(new BufferedOutputStream(new FileOutputStream(filenameOutFreqs)))) {
            for (int i = 0; i <= freqs.getSymbolLimit(); i++) {
                out.writeByte(i);
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
                BitsReader in = new BitsReader(new BufferedInputStream(new FileInputStream(filenameIn)))) {
            HuffmanEncoder encoder = new HuffmanEncoder(freqs);
            int symbol;
            while ((symbol = in.readByte()) != -1) {
                encoder.encodeSymbol(symbol, out);
            }
        } catch (IOException e) {

        }
    }

    @Override
    public FreqTable getFrequencyTable() {
        return this.freqs;
    }

}
