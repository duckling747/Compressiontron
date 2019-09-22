package algo;

import datastructs.FreqTable;
import io.BitsReader;
import java.io.IOException;

public class HuffmanDecoder extends General implements Decoder {

    private FreqTable freqs;

    public HuffmanDecoder(FreqTable f) {
        this.freqs = f;
    }

    public int decode(int bit) {

        return -1;
    }

    @Override
    public int decodeSymbol(BitsReader in) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
