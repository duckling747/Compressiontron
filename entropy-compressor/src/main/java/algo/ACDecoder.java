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
     * @return
     * @throws IOException
     */
    @Override
    public int decodeSymbol(BitsReader in) throws IOException {
        long range = high - low + 1;
        int total = freqs.getTotalSumFreq();
        long cum = Long.divideUnsigned(((value - low + 1)
                * total - 1), range);
        int symbol = freqs.findCumFreq(cum);
        if (symbol == freqs.getSymbolLimit()) {
            return symbol;
        }
        high = low + Long.divideUnsigned((range * freqs.getCumFreqHigh(symbol)), total) - 1;
        low = low + Long.divideUnsigned((range * freqs.getCumFreqLow(symbol)), total);
        while (true) {
            if (Long.compareUnsigned(high, General.HALF) < 0) {
                // nothing, bit is zero
            } else if (Long.compareUnsigned(low, General.HALF) >= 0) {
                value -= General.HALF;
                low -= General.HALF;
                high -= General.HALF;
            } else if (Long.compareUnsigned(low, General.FIRSTQUARTER) >= 0 
                    && Long.compareUnsigned(high, General.THIRDQUARTER) < 0) {
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
