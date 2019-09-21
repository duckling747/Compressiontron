package datastructs;

public class FreqTableSimple implements FreqTable {

    private int[] fArr;
    private int sum;

    private final int symbolLimit;

    public FreqTableSimple(int symbolLimit) {
        fArr = new int[symbolLimit + 1];
        sum = 0;
        this.symbolLimit = symbolLimit;
    }

    @Override
    public int getFreq(int c) {
        return fArr[c];
    }

    @Override
    public int getTotalSumFreq() {
        return sum;
    }

    @Override
    public void addFreq(int c) {
        fArr[c]++;
        sum++;
    }

    @Override
    public int getSymbolLimit() {
        return symbolLimit;
    }

    @Override
    public void setFreq(int symbol, int freq) {
        fArr[symbol] = freq;
    }

}
