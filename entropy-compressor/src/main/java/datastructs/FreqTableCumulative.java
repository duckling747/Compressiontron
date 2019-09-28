package datastructs;

public class FreqTableCumulative extends FreqTable {

    private int[] cumFreqs;

    public FreqTableCumulative(int symbolLimit) {
        super(symbolLimit);
        cumFreqs = new int[freqs.length + 1];
    }

    /**
     * Calculates the cumulative frequencies.
     */
    public void calcCumFreq() {
        int sum = 0;
        for (int i = 0; i < freqs.length; i++) {
            sum += freqs[i];
            cumFreqs[i + 1] = sum;
        }
    }

    @Override
    public int getTotalSumFreq() {
        return cumFreqs[cumFreqs.length - 1];
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
    public int findCumFreq(long value) {
        int a = 0, b = cumFreqs.length - 1, z = -1;
        while (a <= b) {
            int k = (a + b) >>> 1;
            if (cumFreqs[k] == value) {
                return k;
            } else if (cumFreqs[k] > value) {
                b = k - 1;
            } else {
                a = k + 1;
                z = k;
            }
        }
        return z;
    }

    private void cumFreqRangeCheck(int c) {
        if (c < 0 || c > cumFreqs.length - 1) {
            throw new IllegalArgumentException("Out of symbol range " + c);
        }
    }

}
