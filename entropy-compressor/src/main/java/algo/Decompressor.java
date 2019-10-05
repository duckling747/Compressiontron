package algo;

import datastructs.FreqTable;

public interface Decompressor {

    public void readFrequencies();

    public void readEncodedText();

    public FreqTable getFrequencyTable();

}
