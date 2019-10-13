package datastructs;

public class FreqTableCumulative extends FreqTable {

    private int[] cum;

    /**
     * Create a new cumulative frequency table. Frequencies are taken to be at
     * least one.
     *
     * @param freqs
     */
    public FreqTableCumulative(int[] freqs) {
        super(freqs);
        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] < 0) {
                throw new AssertionError();
            }
            freqs[i]++; // "Initialize" all frequencies to 1
        }
        cum = new int[freqs.length + 1];
        for (int i = 0; i < cum.length; i++) {
            cum[i] = i;
        }
    }

    /**
     * Update the model
     *
     * @param c
     */
    public void update(int c) {
        for (int i = c + 1; i < freqs.length; i++) {
            cum[i]++;
        }
    }

    @Override
    public int getTotalSumFreq() {
        return cum[cum.length - 1];
    }

    /**
     * Returns the cumulative frequency for all less than given character.
     * Returns -1 instead if not found.
     *
     * @param c
     * @return Cumulative frequency
     */
    public int getCumFreqLow(int c) {
        if (c < 0 || c > super.getSymbolLimit()) {
            throw new IllegalArgumentException("Out of symbol range: " + c);
        }
        return cum[c];
    }

    /**
     * Returns the cumulative frequency for for all less than or equal to the
     * given character. Returns -1 instead if not found.
     *
     * @param c
     * @return Cumulative frequency
     */
    public int getCumFreqHigh(int c) {
        if (c < 0 || c > super.getSymbolLimit()) {
            throw new IllegalArgumentException("Out of symbol range: " + c);
        }
        return cum[c + 1];
    }

    /**
     * Find the symbols such that its index is less than or equal to the given
     * value. Implements a binary search for the index of the highest cumulative
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
