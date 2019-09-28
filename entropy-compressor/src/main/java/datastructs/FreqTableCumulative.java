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

    @Override
    public int getTotalSumFreq() {
        return cumFreqs[0];
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
        /*
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
        return (z == 0) ? 1 : z;
         */
        int symbol = 1;
        for (symbol = 1; cumFreqs[symbol] > value; symbol++) {
            if (symbol == symbolLimit) {
                break;
            }
        }
        return symbol;
    }

    private void cumFreqRangeCheck(int c) {
        if (c < 0 || c > symbolLimit) {
            throw new IllegalArgumentException("Out of symbol range " + c);
        }
    }

}
