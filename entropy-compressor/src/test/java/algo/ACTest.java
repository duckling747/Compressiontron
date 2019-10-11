package algo;

import datastructs.FreqTableCumulative;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class ACTest {

    //private static final String S = "Trust me. I'm a String.";
    private static final String S = "a" + ((char) 254);
    private static final String RESULTS = "00100000";

    private FreqTableCumulative freqs;

    @Before
    public void initMe() {
        freqs = new FreqTableCumulative(new int[255]);
        for (char c : S.toCharArray()) {
            freqs.addFreq(c);
        }
        freqs.calcCumFreq();
    }

    @Test
    public void ACCompressTest() {
        StringBuilder bits = new StringBuilder();
        long high = 0xFFFF - 1; // 16 bits codeblocks
        long low = 0;
        int bitsToFollow = 0;

        for (char symbol : S.toCharArray()) {
            long total = freqs.getTotalSumFreq();
            long range = high - low + 1;
            high = low + Long.divideUnsigned(range * freqs.getCumFreqHigh(symbol), total) - 1;
            low = low + (range * Long.divideUnsigned(freqs.getCumFreqLow(symbol), total));
            for (;;) {
                if (Long.compareUnsigned(high, General.HALF) < 0) {
                    bitPlusFollow(0, bits, bitsToFollow);
                    bitsToFollow = 0;
                } else if (Long.compareUnsigned(low, General.HALF) >= 0) {
                    bitPlusFollow(1, bits, bitsToFollow);
                    bitsToFollow = 0;
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
        bitsToFollow++;
        if (Long.compareUnsigned(low, General.FIRSTQUARTER) < 0) {
            bitPlusFollow(0, bits, bitsToFollow);
        } else {
            bitPlusFollow(1, bits, bitsToFollow);
        }
        while (bits.length() % 8 != 0) {
            bits.append('0');
        }
        assertThat(bits.toString(), is(RESULTS));
    }

    private static void bitPlusFollow(int bit, StringBuilder b, int pending) {
        b.append(Integer.toString(bit));
        for (int i = 0; i < pending; i++) {
            b.append(Integer.toString(bit ^ 1));
        }
    }

    @Test
    public void ACDecompressTest() {
        StringBuilder symbols = new StringBuilder();
        long high = 0xFFFF - 1; // 16 bits codeblocks
        long low = 0;
        long value = 0x0000;
        int codebits = 16;
        int i;
        for (i = 0; i < codebits && i < RESULTS.length(); i++) {
            value <<= 1;
            value += RESULTS.charAt(i) - 48;
        }
        for (int j = 0; j < RESULTS.length(); j++) {
            long range = high - low + 1;
            if (range == 0) {
                range = 1;
            }
            long total = freqs.getTotalSumFreq();
            long cum = Long.divideUnsigned(((value - low + 1)
                    * total - 1), range);
            int symbol = freqs.findCumFreq(cum);
            if (symbol >= 254) {
                symbols.append((char) 254);
                break;
            }
            high = low + (range * Long.divideUnsigned(freqs.getCumFreqHigh(symbol), total)) - 1;
            low = low + (range * Long.divideUnsigned(freqs.getCumFreqLow(symbol), total));
            for (;;) {
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
                value += RESULTS.charAt(j) - 48;
            }
            symbols.append((char) symbol);
        }
        assertThat(symbols.toString(), is(S));
    }

}
