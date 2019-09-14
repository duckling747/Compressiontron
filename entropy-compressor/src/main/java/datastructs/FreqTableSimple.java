package datastructs;

public class FreqTableSimple implements FreqTable {

    private int symbolLimit;
    private int[] freqs;
    private int[] cumFreqs;

    private static final int MAXFREQ = 16000;

    public FreqTableSimple(int symbolLimit) {
        if (symbolLimit < 2) {
            throw new IllegalArgumentException("Symbol limit cannot be less than 2");
        }
        this.symbolLimit = symbolLimit;
        freqs = new int[symbolLimit + 1];
        for (int i = 1; i < freqs.length; i++) {
            freqs[i] = 1;
        }
        cumFreqs = new int[symbolLimit + 1];
    }

    @Override
    public int getFreq(int c) {
        freqRangeCheck(c);
        return freqs[c];
    }

    @Override
    public int getTotalSumFreq() {
        return cumFreqs[0];
    }

    @Override
    public void addFreq(int c) {
        freqRangeCheck(c);
        freqs[c]++;
        maxFreqCheck(c);
    }

    @Override
    public int getSymbolLimit() {
        return symbolLimit;
    }

    @Override
    public void calcCumFreq() {
        for (int i = symbolLimit; i > 0; i--) {
            cumFreqs[i - 1] = cumFreqs[i] + freqs[i];
        }
    }

    @Override
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
    @Override
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

    private void cumFreqRangeCheck(int c) {
        if (c < 0 || c > symbolLimit) {
            throw new IllegalArgumentException("Out of symbol range");
        }
    }

    private void freqRangeCheck(int c) {
        if (c < 1 || c > symbolLimit) {
            throw new IllegalArgumentException("Out of symbol range");
        }
    }

    @Override
    public void setFreq(int c, int freq) {
        freqRangeCheck(c);
        freqs[c] = freq;
        maxFreqCheck(c);
    }

    /**
     * Checks whether a frequency is over a specified limit. If the frequency at the given
     *  index is over the {@code MAXFREQ} limit, then halve all the frequencies in the table. 
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
