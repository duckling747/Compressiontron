package algo;

import io.BitsReader;
import datastructs.FreqTableCumulative;
import java.io.EOFException;
import java.io.IOException;

public class ACDecoder implements Decoder {

    private FreqTableCumulative freqs;
    private int low, high;
    private int value;

    public ACDecoder(FreqTableCumulative f, BitsReader in) throws IOException {
        value = 0;
        for (int i = 0; i < General.CODEVALUEBITS; i++) {
            value <<= 1;
            value += inputBit(in);
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
     * @return symbol
     * @throws IOException
     */
    @Override
    public int decodeSymbol(BitsReader in) throws IOException {
        int range = high - low + 1;
        int total = freqs.getTotalSumFreq();
        int cum = Integer.divideUnsigned(((value - low + 1)
                * total - 1), range);
        int symbol = freqs.findCumFreq(cum);
        if (symbol == freqs.getSymbolLimit()) {
            return symbol;
        }
        high = low + Integer.divideUnsigned((range * freqs.getCumFreqHigh(symbol)), total) - 1;
        low = low + Integer.divideUnsigned((range * freqs.getCumFreqLow(symbol)), total);
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
            low <<= 1;
            high <<= 1;
            high++;
            value <<= 1;
            value += inputBit(in);
        }
        return symbol;
    }

    private int inputBit(BitsReader in) throws IOException {
        int bit = in.readBit();
        if (bit == -1) {
            throw new EOFException();
        } else if (bit != 0 && bit != 1) {
            throw new IllegalStateException();
        }
        return bit;
    }
}
