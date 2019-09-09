package algo;

import datastructs.FreqTable;

public class ACEncoder {

    private static final int CODEVALUEBITS = 16;
    private static final long TOPVALUE = (1L << CODEVALUEBITS) - 1;
    private static final long FIRSTQUARTER = (TOPVALUE / 4 + 1);
    private static final long HALF = (2 * FIRSTQUARTER);
    private static final long THIRDQUARTER = (3 * FIRSTQUARTER);

    private FreqTable freqs;
    private long low, high;
    private long bitsToFollow;

    public ACEncoder(FreqTable f) {
        freqs = f;
        low = 0;
        high = TOPVALUE;
        bitsToFollow = 0;
    }

    /**
     * Encodes a given symbol
     *
     * @param symbol
     */
    public void encodeSymbol(char symbol) {
        long range = high - low + 1;
        high = low + (range * freqs.getCumFreq((int) symbol - 1)) / freqs.getCumFreq(0) - 1;
        low = low + (range * freqs.getCumFreq((int) symbol)) / freqs.getCumFreq(0);
        while (true) {
            if (high < HALF) {
                bitPlusFollow(0);
            } else if (low >= HALF) {
                bitPlusFollow(1);
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
     */
    public void done() {
        bitsToFollow++;
        if (low < FIRSTQUARTER) {
            bitPlusFollow(0);
        } else {
            bitPlusFollow(1);
        }
    }

    /**
     * Output bits and following opposite bits
     *
     * @param bit
     */
    private void bitPlusFollow(int bit) {
        // output bit
        for (; bitsToFollow > 0; bitsToFollow--) {
            // output bit
        }
    }

}
