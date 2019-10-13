package algo;

import datastructs.FreqTable;
import datastructs.FreqTableCumulative;
import io.BitsWriter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ACCompressor implements Compressor {

    private String filenameIn;
    private String filenameOutCompressed;
    private String filenameOutFreqs;
    private FreqTableCumulative freqs;

    public ACCompressor(String fnameIn, String fnameOutCompression, String fnameOutFrequencies) {
        this.filenameIn = fnameIn;
        this.filenameOutCompressed = fnameOutCompression;
        this.filenameOutFreqs = fnameOutFrequencies;
    }

    public ACCompressor(String fnameIn, String fnameOutCompression, String fnameOutFrequencies,
            FreqTableCumulative freqs) {
        this.filenameIn = fnameIn;
        this.filenameOutCompressed = fnameOutCompression;
        this.filenameOutFreqs = fnameOutFrequencies;
        this.freqs = freqs;
        if (freqs.getFreq(freqs.getSymbolLimit()) != 1) {
            throw new IllegalStateException("EOF frequency must be 1");
        }
    }

    @Override
    public void readFrequencies() {
        freqs = new FreqTableCumulative(new int[General.SYMBOLLIMIT + 1]);
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
        try (BitsWriter out = new BitsWriter(new BufferedOutputStream(new FileOutputStream(filenameOutCompressed)));
                InputStream in = new BufferedInputStream(new FileInputStream(filenameIn))) {
            ACEncoder encoder = new ACEncoder(freqs);
            int readByte;
            while ((readByte = in.read()) != -1) {
                encoder.encodeSymbol(readByte, out);
                freqs.update(readByte);
            }
            encoder.encodeSymbol(freqs.getSymbolLimit(), out); // EOF
            encoder.done(out);
        } catch (IOException e) {
        }

    }

    @Override
    public FreqTable getFrequencyTable() {
        return this.freqs;
    }

}
