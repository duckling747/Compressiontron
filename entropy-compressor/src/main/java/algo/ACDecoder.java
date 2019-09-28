package algo;

import io.BitsReader;
import datastructs.FreqTableCumulative;
import java.io.IOException;

public class ACDecoder extends General implements Decoder {

    private FreqTableCumulative freqs;
    private long low, high;
    private long value;

    private boolean flag = false;

    public ACDecoder(FreqTableCumulative f, BitsReader in) throws IOException {
        value = 0;
        for (int i = 1; i <= CODEVALUEBITS; i++) {
            value = 2 * value + inputBit(in);
        }
        freqs = f;
        low = 0;
        high = TOPVALUE;
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
        long range = high - low + 1;
        long cum = (((value - low) + 1) * freqs.getTotalSumFreq() - 1) / range;
        int symbol = freqs.findCumFreq(cum);
        if (symbol == -1) {
            throw new AssertionError("Leq cumulative freq not found! Cum: " + cum);
        }
        long total = freqs.getTotalSumFreq();
        long symbolLow = freqs.getCumFreqLow(symbol);
        long symbolHigh = freqs.getCumFreqHigh(symbol);
        high = low + symbolHigh * range / total - 1;
        low = low + symbolLow * range / total;

        while (true) {
            if (high < HALF) {
                // do nothing
            } else if (low >= HALF) {
                value -= HALF;
                low -= HALF;
                high -= HALF;
            } else if (low >= FIRSTQUARTER && high < THIRDQUARTER) {
                value -= FIRSTQUARTER;
                low -= FIRSTQUARTER;
                high -= FIRSTQUARTER;
            } else {
                break;
            }
            low = 2 * low;
            high = 2 * high + 1;
            int input = inputBit(in);
            if (input == -1) {
                flag = true;
            }
            value = 2 * value + input;
        }
        return symbol;
    }

    private int inputBit(BitsReader in) throws IOException {
        return in.readBit();
    }
}
