package algo;

import datastructs.FreqTable;

public interface Decompressor {

    /**
     * Read frequencies for the used probability model from file.
     */
    public void readFrequencies();

    /**
     * Read, decompress, and write a given text file decoded.
     */
    public void readEncodedText();

    public FreqTable getFrequencyTable();

}
