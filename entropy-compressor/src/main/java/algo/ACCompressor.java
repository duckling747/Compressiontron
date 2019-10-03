package algo;

import datastructs.FreqTable;
import datastructs.FreqTableCumulative;
import io.BitsReader;
import io.BitsWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import main.Main;

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
    }
    
    @Override
    public void readFrequencies() {
        freqs = new FreqTableCumulative(new int[General.SYMBOLLIMIT + 1]);
        try (BitsReader in = new BitsReader(new DataInputStream(new FileInputStream(filenameIn)))) {
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
        try (BitsWriter out = new BitsWriter(new DataOutputStream(new FileOutputStream(filenameOutFreqs)))) {
            for (int i = 0; i <= freqs.getSymbolLimit(); i++) {
                out.writeByte(i);
            }
        } catch (IOException e) {
        }
    }
    
    @Override
    public void writeEncodedText() {
        freqs.calcCumFreq();
        try (BitsWriter out = new BitsWriter(new DataOutputStream(new FileOutputStream(filenameOutCompressed)));
                BitsReader in = new BitsReader(new DataInputStream(new FileInputStream(filenameIn)))) {
            ACEncoder encoder = new ACEncoder(freqs);
            int readByte;
            while ((readByte = in.readByte()) != -1) {
                encoder.encodeSymbol(readByte, out);
            }
        } catch (IOException e) {
        }
        
    }
    
    @Override
    public FreqTable getFrequencyTable() {
        return this.freqs;
    }
    
}
