package algo;

import datastructs.FreqTable;
import datastructs.FreqTableCumulative;
import io.BitsReader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ACDecompressor implements Decompressor {

    private String fnInCompression;
    private String fnInFrequencies;
    private String fnOut;
    private FreqTableCumulative freqs;

    public ACDecompressor(String fnameInCompression, String fnameInFrequencies, String fnameOut) {
        this.fnInCompression = fnameInCompression;
        this.fnInFrequencies = fnameInFrequencies;
        this.fnOut = fnameOut;
    }

    public ACDecompressor(String fnameInCompression, String fnameInFrequencies, String fnameOut, FreqTableCumulative freqs) {
        this.fnInCompression = fnameInCompression;
        this.fnInFrequencies = fnameInFrequencies;
        this.fnOut = fnameOut;
        this.freqs = freqs;
    }

    @Override
    public void readFrequencies() {
        try (BitsReader in = new BitsReader(new DataInputStream(new FileInputStream(fnInFrequencies)))) {
            freqs = new FreqTableCumulative(new int[General.SYMBOLLIMIT + 1]);
            for (int i = 0; i <= freqs.getSymbolLimit(); i++) {
                freqs.setFreq(i, in.readByte());
            }
        } catch (IOException e) {
        }
        if (freqs == null) {
            throw new AssertionError("Frequency table not initialized ");
        }
        freqs.calcCumFreq();
    }

    @Override
    public void readEncodedText() {
        try (BitsReader in = new BitsReader(new BufferedInputStream(new FileInputStream(fnInCompression)));
                OutputStream out = new BufferedOutputStream(new FileOutputStream(fnOut))) {
            ACDecoder decoder = new ACDecoder(freqs, in);
            int symbol;
            while ((symbol = decoder.decodeSymbol(in)) != -1) {
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
