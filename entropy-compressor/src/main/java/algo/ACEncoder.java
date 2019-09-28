package algo;

import io.BitsWriter;
import datastructs.FreqTableCumulative;
import java.io.IOException;

public class ACEncoder extends General implements Encoder {

    private FreqTableCumulative freqs;
    private long low, high;
    
    private long bitsToFollow;

    public ACEncoder(FreqTableCumulative f) {
        freqs = f;
        low = 0;
        high = TOPVALUE;
        bitsToFollow = 0;
    }

    /**
     * Encodes a given symbol
     *
     * @param symbol
     * @param out
     * @throws IOException
     */
    @Override
    public void encodeSymbol(int symbol, BitsWriter out) throws IOException {
        long range = high - low + 1;
        long total = freqs.getTotalSumFreq();
        long symbolLow = freqs.getCumFreqLow(symbol);
        long symbolHigh = freqs.getCumFreqHigh(symbol);
        high = low + symbolHigh * range / total - 1;
        low = low + symbolLow * range / total;
        while (true) {
            if (high < HALF) {
                bitPlusFollow(0, out);
            } else if (low >= HALF) {
                bitPlusFollow(1, out);
                low -= HALF;
                high -= HALF;
            } else if (low >= FIRSTQUARTER && high < THIRDQUARTER) {
                bitsToFollow++;
                low -= FIRSTQUARTER;
                high -= FIRSTQUARTER;
            } else {
                break;
            }
            low = 2 * low;
            high = 2 * high + 1;
        }
    }

    /**
     * Store the last bit based on where the range ends up
     *
     * @param out
     * @throws IOException
     */
    public void finalize(BitsWriter out) throws IOException {
        bitsToFollow++;
        if (low < FIRSTQUARTER) {
            bitPlusFollow(0, out);
        } else {
            bitPlusFollow(1, out);
        }
    }

    /**
     * Output bits to follow opposite bits.
     *
     * @param bit
     * @param out
     * @throws IOException
     */
    private void bitPlusFollow(int bit, BitsWriter out) throws IOException {
        out.writeBit(bit);
        for (; bitsToFollow > 0; bitsToFollow--) {
            out.writeBit(bit);
        }
    }

}
