package algo;

import IO.BitsReader;
import datastructs.FreqTable;

public class ACDecoder extends ACCore {

    private FreqTable freqs;
    private long low, high;
    private long value;

    public ACDecoder(FreqTable f, long val) {
        value = val;
        freqs = f;
        low = 0;
        high = TOPVALUE;
    }

    public void decodeSymbol() {
        long range = high - low + 1;
        int symbol;
        long cum;
        cum = (((value - low) + 1) * freqs.getCumFreq(0) - 1) / range;
        ///////////////////////////////////
        // HERE
    }

    private int inputBit(BitsReader in) {

    }
}
