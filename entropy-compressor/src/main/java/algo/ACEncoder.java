package algo;

import io.BitsWriter;
import datastructs.FreqTableCumulative;
import java.io.IOException;

public class ACEncoder implements Encoder {

    private FreqTableCumulative freqs;
    private int low, high;

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
        int total = freqs.getTotalSumFreq();
        int range = high - low + 1;
        high = low + (range * Integer.divideUnsigned(freqs.getCumFreqHigh(symbol), total)) - 1;
        low = low + (range * Integer.divideUnsigned(freqs.getCumFreqLow(symbol), total));
        while (true) {
            if (high < General.HALF) {
                bitPlusFollow(0, out);
            } else if (low >= General.FIRSTQUARTER) {
                bitPlusFollow(1, out);
            } else if (low >= General.FIRSTQUARTER
                    && high < General.THIRDQUARTER) {
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
        if (low < General.FIRSTQUARTER) {
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
        while (bitsToFollow-- > 0) {
            out.writeBit(bit ^ 1);
        }
    }

}
