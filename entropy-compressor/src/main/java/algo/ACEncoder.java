package algo;

import io.BitsWriter;
import datastructs.FreqTableCumulative;
import java.io.IOException;

public class ACEncoder implements Encoder {

    private FreqTableCumulative freqs;
    private long low, high;

    private long bitsToFollow;

    public ACEncoder(FreqTableCumulative f) {
        freqs = f;
        low = 0;
        high = General.TOPVALUE;
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
        long total = freqs.getTotalSumFreq();
        long range = high - low + 1;
        high = low + (range * Long.divideUnsigned(freqs.getCumFreqHigh(symbol), total)) - 1;
        low = low + (range * Long.divideUnsigned(freqs.getCumFreqLow(symbol), total));
        while (true) {
            if (Long.compareUnsigned(high, General.HALF) < 0) {
                bitPlusFollow(0, out);
            } else if (Long.compareUnsigned(low, General.HALF) >= 0) {
                bitPlusFollow(1, out);
            } else if (Long.compareUnsigned(low, General.FIRSTQUARTER) >= 0
                    && Long.compareUnsigned(high, General.THIRDQUARTER) < 0) {
                bitsToFollow++;
                low -= General.FIRSTQUARTER;
                high -= General.FIRSTQUARTER;
            } else {
                break;
            }
            high <<= 1;
            high++;
            low <<= 1;
            high &= General.TOPVALUE;
            low &= General.TOPVALUE;
        }
    }

    /**
     * Store the last bit based on where the range ends up
     *
     * @param out
     * @throws IOException
     */
    public void done(BitsWriter out) throws IOException {
        bitsToFollow++;
        if (Long.compareUnsigned(low, General.FIRSTQUARTER) < 0) {
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
        for (int i = 0; i < bitsToFollow; i++) {
            out.writeBit(bit ^ 1);
        }
        bitsToFollow = 0;
    }

}
