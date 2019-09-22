package datastructs;

public class FreqTableCumulative extends FreqTable {

    private int[] cumFreqs;

    public FreqTableCumulative(int symbolLimit) {
        super(symbolLimit);
        cumFreqs = new int[symbolLimit + 1];
    }

    /**
     * Calculates the cumulative frequencies.
     */
    public void calcCumFreq() {
        for (int i = symbolLimit; i > 0; i--) {
            cumFreqs[i - 1] = cumFreqs[i] + freqs[i];
        }
    }

    /**
     * Returns the cumulative frequency for given character (represented as
     * integer)
     *
     * @param c
     * @return Cumulative frequency
     */
    public int getCumFreq(int c) {
        cumFreqRangeCheck(c);
        return cumFreqs[c];
    }

    /**
     * Implements a binary search for the index of the highest cumulative
     * frequency such that it is less than or equal to the inputted symbol.
     *
     * @param value
     * @return index
     */
    public int findCumFreq(int value) {
        int a = 0, b = symbolLimit, z = -1;
        while (a <= b) {
            int k = (a + b) >>> 1;
            if (cumFreqs[k] <= value) {
                b = k - 1;
                z = k;
            } else {
                a = k + 1;
            }
        }
        return z;
    }

    protected void cumFreqRangeCheck(int c) {
        if (c < 0 || c > symbolLimit) {
            throw new IllegalArgumentException("Out of symbol range");
        }
    }

}
