package datastructs;

public abstract class FreqTable {

    protected int[] freqs;
    protected int symbolLimit;

    private static final int MAXFREQ = 16000;

    public FreqTable(int symbolLimit) {
        if (symbolLimit < 2) {
            throw new IllegalArgumentException("Symbol limit cannot "
                    + "be less than 2");
        }
        freqs = new int[symbolLimit + 1];
        for (int i = 1; i <= symbolLimit; i++) {
            setFreq(i, 1);
        }
        this.symbolLimit = symbolLimit;
    }

    /**
     * Returns the frequency of the character c.
     *
     * @param c
     * @return Frequency in table for char c
     */
    public int getFreq(int c) {
        freqRangeCheck(c);
        return freqs[c];
    }

    /**
     * Return the sum of all frequencies.
     *
     * @return Sum of frequencies in table
     */
    public int getTotalSumFreq() {
        int sum = 0;
        for (int i = 1; i <= symbolLimit; i++) {
            sum += freqs[i];
        }
        return sum;
    }

    /**
     * Increment frequency by one for specified symbol.
     *
     * @param c
     */
    public void addFreq(int c) {
        freqRangeCheck(c);
        freqs[c]++;
        maxFreqCheck(c);
    }

    /**
     * Return the symbol limit (maximum integer possible).
     *
     * @return Sum of different characters in table
     */
    public int getSymbolLimit() {
        return symbolLimit;
    }

    public final void setFreq(int c, int freq) {
        freqRangeCheck(c);
        freqs[c] = freq;
        maxFreqCheck(c);
    }

    protected void freqRangeCheck(int c) {
        if (c < 1 || c > symbolLimit) {
            throw new IllegalArgumentException("Out of symbol range");
        }
    }

    /**
     * Checks whether a frequency is over a specified limit. If the frequency at
     * the given index is over the {@code MAXFREQ} limit, then halve all the
     * frequencies in the table.
     *
     * @param c
     */
    private void maxFreqCheck(int c) {
        if (freqs[c] >= MAXFREQ) {
            for (int i = symbolLimit; i > 0; i--) {
                freqs[i] = (freqs[i] + 1) / 2;
            }
        }
    }

}
