package datastructs;

public class FreqTableSimple implements FreqTable {

    private int sum;
    private int[] freqs;
    private int[] cumFreqs;

    public FreqTableSimple(int symbolLimit) {
        freqs = new int[symbolLimit + 1];
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
    }

    @Override
    public int getSymbolLimit() {
        return freqs.length;
    }

    @Override
    public void calcCumFreq() {
        int cum = 0;
        for (int i = 0; i < freqs.length; i++) {
            cum += freqs[i];
            cumFreqs[i + 1] = cum;
        }
    }

    @Override
    public int getCumFreq(int c) {
        return cumFreqs[c];
    }

}
