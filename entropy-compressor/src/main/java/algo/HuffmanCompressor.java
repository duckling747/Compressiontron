package algo;

import datastructs.FreqTable;
import datastructs.FreqTableSimple;
import io.BitsWriter;
import java.io.DataInputStream;
import java.io.IOException;
import main.Main;

public class HuffmanCompressor extends Compressor {

    private HuffmanEncoder encoder;

    public HuffmanCompressor(String fileIn, String fileOutCompression, String fileOutFrequencies) {
        freqs = new FreqTableSimple(Main.SYMBOLLIMIT);
        encoder = new HuffmanEncoder(freqs);
        filenameIn = fileIn;
        filenameOutCompressed = fileOutCompression;
        filenameOutFreqs = fileOutFrequencies;
        super.readFileSetFreqs();
    }

    @Override
    protected void writeEncodedText(DataInputStream in, BitsWriter out) throws IOException {
        int c;
        while ((c = in.read()) != -1) {
            encoder.encodeSymbol(c, out);
        }
    }
    
    public FreqTable getTable() {
        return this.freqs;
    }

}
