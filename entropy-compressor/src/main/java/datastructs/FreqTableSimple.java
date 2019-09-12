package datastructs;

public class FreqTableSimple implements FreqTable {

    private int sum;
    private int symbolLimit;
    private int[] freqs;
    private int[] cumFreqs;

    private static final int MAXFREQ = 16000;

    public FreqTableSimple(int symbolLimit) {
        this.symbolLimit = symbolLimit;
        freqs = new int[symbolLimit + 1];
        for (int i = 1; i < freqs.length; i++) {
            freqs[i] = 1;
        }
        cumFreqs = new int[symbolLimit + 1];
        
        sum = 0;
    }

    @Override
    public int getFreq(int c) {
        return freqs[c];
    }

    @Override
    public int getTotalSumFreq() {
        return sum;
    }

    @Override
    public void addFreq(int c) {
        freqs[c]++;
        sum++;
        if (freqs[0] == MAXFREQ) {
            int sum = 0;
            for (int i = symbolLimit; i >= 0; i--) {
                freqs[i] = (freqs[i] + 1) / 2;
                cumFreqs[i] = sum;
                sum += freqs[i];
            }
        }
    }

    @Override
    public int getSymbolLimit() {
        return freqs.length;
    }

    @Override
    public void calcCumFreq() {
        for (int i = symbolLimit; i > 0; i--) {
            cumFreqs[i - 1] = cumFreqs[i] + freqs[i];
        }
    }

    @Override
    public int getCumFreq(int c) {
        return cumFreqs[c];
    }
    
    /**
     * Implements a binary search for the index of the highest 
     * cumulative frequency such that it is less than or equal 
     * to the inputted symbol.
     * @param value
     * @return index
     */
    public int findCumFreq(int value) {
        int a = 1, b = symbolLimit;
        while (b - a > 1) {
            int k = (a + b) >>> 1;
            if (cumFreqs[k] > value) {
                a = k;
            } else {
                b = k;
            }
        }
        return b;
    }

}
