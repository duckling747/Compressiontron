package datastructs;

public abstract class FreqTable {

    protected int[] freqs;

    private static final int MAXFREQ = 255;

    public FreqTable(int[] freqs) {
        for (int freq : freqs) {
            if (freq < 0) {
                throw new IllegalArgumentException("Freqs cannot be negative");
            }
        }
        this.freqs = freqs;
    }

    /**
     * Returns the frequency of the character c.
     *
     * @param c
     * @return Frequency in table for char c
     */
    public int getFreq(int c) {
        if (c < 0 || c > freqs.length - 1) {
            return -1;
        }
        return freqs[c];
    }

    /**
     * Return the sum of all frequencies.
     *
     * @return Sum of frequencies in table
     */
    public int getTotalSumFreq() {
        int sum = 0;
        for (int i = 0; i < freqs.length; i++) {
            sum += freqs[i];
        }
        return sum;
    }

    /**
     * Increment frequency by one for specified symbol.
     *
     * @param c
     * @return wasSuccessful
     */
    public boolean addFreq(int c) {
        if (c < 0 || c > freqs.length - 1) {
            return false;
        }
        freqs[c]++;
        maxFreqCheck(c);
        return true;
    }

    /**
     * Return the symbol limit (maximum integer/char possible).
     *
     * @return Sum of different characters in table
     */
    public int getSymbolLimit() {
        return freqs.length - 1;
    }

    /**
     * Set specified frequency.
     * @param c
     * @param freq
     * @return wasSuccesful
     */
    public final boolean setFreq(int c, int freq) {
        if (c < 0 || c > freqs.length - 1) {
            return false;
        }
        if (freq < 0) {
            throw new IllegalArgumentException("Freq cannot be negative");
        }
        freqs[c] = freq;
        maxFreqCheck(c);
        return true;
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
            for (int i = 0; i < freqs.length; i++) {
                freqs[i] = (freqs[i] + 1) / 2;
            }
        }
    }

}
