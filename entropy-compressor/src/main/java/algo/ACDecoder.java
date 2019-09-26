package algo;

import io.BitsReader;
import datastructs.FreqTableCumulative;
import java.io.IOException;

public class ACDecoder extends General implements Decoder {

    private FreqTableCumulative freqs;
    private long low, high;
    private long value;

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
        long range = high - low + 1;
        long cum = (((value - low) + 1) * freqs.getCumFreq(0) - 1) / range;
        int symbol = freqs.findCumFreq((int) cum);
        high = low + (range * freqs.getCumFreq(symbol - 1))
                / freqs.getCumFreq(0) - 1;
        low = low + (range * freqs.getCumFreq(symbol))
                / freqs.getCumFreq(0);
        while (true) {
            if (high < HALF) {
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
            int input = inputBit(in);
            if (input == -1) {
                return -1;
            }
            low = 2 * low;
            high = 2 * high + 1;
            value = 2 * value + input;
        }
        return symbol;
    }

    private int inputBit(BitsReader in) throws IOException {
        return in.readBit();
    }
}
