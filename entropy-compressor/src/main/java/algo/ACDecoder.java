package algo;

import io.BitsReader;
import datastructs.FreqTableCumulative;
import java.io.IOException;

public class ACDecoder implements Decoder {

    private FreqTableCumulative freqs;
    private long low, high;
    private long value;

    private boolean flag;

    public ACDecoder(FreqTableCumulative f, BitsReader in) throws IOException {
        value = 0;
        for (int i = 0; i < General.CODEVALUEBITS; i++) {
            value = ((value << 1) | inputBit(in));
        }
        freqs = f;
        if (f.getTotalSumFreq() == 0) {
            throw new IllegalStateException("Cumulative frequencies not calculated");
        }
        low = 0;
        high = General.MASK;
        flag = false;
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
        if (flag) {
            return -1;
        }

        long total = freqs.getTotalSumFreq();
        long range = high - low + 1;
        long cum = ((value - low + 1) * total - 1) / range;
        int symbol = freqs.findCumFreq(cum);
        high = low + (range * freqs.getCumFreqHigh(symbol)) / freqs.getTotalSumFreq() - 1;
        low = low + (range * freqs.getCumFreqLow(symbol)) / freqs.getTotalSumFreq();
        
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
            int bit = inputBit(in);
            if (bit == -1) {
                flag = true;
            }
            low <<= 1;
            high = (high << 1) | 1;
            value = (value << 1) | bit;
        }
        return symbol;
    }

    private int inputBit(BitsReader in) throws IOException {
        return in.readBit();
    }
}
