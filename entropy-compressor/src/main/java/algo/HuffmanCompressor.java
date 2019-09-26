package algo;

import datastructs.FreqTable;
import datastructs.FreqTableSimple;
import io.BitsWriter;
import java.io.DataInputStream;
import java.io.IOException;
import main.Main;

public class HuffmanCompressor extends Compressor {

    private HuffmanEncoder encoder;

    public HuffmanCompressor(String fileIn, String fileOut) {
        freqs = new FreqTableSimple(Main.SYMBOLLIMIT);
        encoder = new HuffmanEncoder(freqs);
        filenameIn = fileIn;
        filenameOut = fileOut;
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
