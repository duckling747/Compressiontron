package algo;

import io.BitsReader;
import datastructs.FreqTableCumulative;
import java.io.EOFException;
import java.io.IOException;

public class ACDecoder implements Decoder {

    private FreqTableCumulative freqs;
    private long low, high;
    private long value;

    public ACDecoder(FreqTableCumulative f, BitsReader in) throws IOException {
        value = 0;
        for (int i = 0; i < General.CODEVALUEBITS; i++) {
            value = 2 * value + inputBit(in);
        }
        freqs = f;
        if (f.getTotalSumFreq() == 0) {
            throw new IllegalStateException("Cumulative frequencies not calculated");
        }
        low = 0;
        high = General.TOPVALUE;
    }

    /**
     * Decodes a given symbol.
     *
     * @param in
     * @return
     * @throws IOException
     */
    @Override
    public int decodeSymbol(BitsReader in) throws IOException {
        long total = freqs.getTotalSumFreq();
        long range = high - low + 1;
        long cum = ((value - low + 1) * total - 1) / range;
        int symbol = freqs.findCumFreq(cum);
        high = low + (range * freqs.getCumFreqHigh(symbol)) / total - 1;
        low = low + (range * freqs.getCumFreqLow(symbol)) / total;
        while (true) {
            if (high < General.HALF) {
                // nothing, bit is zero
            } else if (low >= General.HALF) {
                value -= General.HALF;
                low -= General.HALF;
                high -= General.HALF;
            } else if (low >= General.FIRSTQUARTER
                    && high < General.THIRDQUARTER) {
                value -= General.FIRSTQUARTER;
                low -= General.FIRSTQUARTER;
                high -= General.FIRSTQUARTER;
            } else {
                break;
            }
            low *= 2;
            high = 2 * high + 1;
            value = 2 * value + inputBit(in);
        }
        return symbol;
    }

    private int inputBit(BitsReader in) throws IOException {
        int bit = in.readBit();
        if (bit == -1) {
            throw new EOFException();
        }
        return bit;
    }
}
