package datastructs;

public class FreqTableTesting extends FreqTableSimple {

    public FreqTableTesting(int symbolLimit) {
        super(symbolLimit);
        for (int i = 1; i < symbolLimit; i++) {
            super.addFreq(i);
        }
        super.calcCumFreq();
    }

}
