package datastructs;

public class FreqTableCumulative extends FreqTable {

    private int[] cum;

    public FreqTableCumulative(int[] freqs) {
        super(freqs);
        cum = new int[freqs.length + 1];
    }

    /**
     * Calculates the cumulative frequencies.
     */
    public void calcCumFreq() {
        int sum = 0;
        for (int i = 0; i < freqs.length; i++) {
            sum += freqs[i];
            cum[i + 1] = sum;
        }
    }

    @Override
    public int getTotalSumFreq() {
        return cum[cum.length - 1];
    }

    /**
     * Returns the cumulative frequency for all less than given character
     * (represented as integer). Returns -1 instead if not found.
     *
     * @param c
     * @return Cumulative frequency
     */
    public int getCumFreqLow(int c) {
        if (c < 0 || c > cum.length - 1) {
            return -1;
        }
        return cum[c];
    }

    /**
     * Returns the cumulative frequency for for all less than or equal to the
     * given character. Returns -1 instead if not found.
     *
     * @param c
     * @return
     */
    public int getCumFreqHigh(int c) {
        if (c < 0 || c > cum.length - 1) {
            return -1;
        }
        return cum[c + 1];
    }

    /**
     * Implements a binary search for the index of the highest cumulative
     * frequency such that it is less than or equal to the inputted symbol.
     *
     * @param value
     * @return index
     */
    public int findCumFreq(long value) {
        int a = 0, b = cum.length - 1, z = -1;
        while (a <= b) {
            int k = (a + b) / 2;
            if (cum[k] == value) {
                return k;
            } else if (cum[k] > value) {
                b = k - 1;
            } else {
                a = k + 1;
                z = k;
            }
        }
        return z;
    }

}
