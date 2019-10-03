package algo;

import datastructs.FreqTable;

public interface Compressor {

    public void writeFrequencies();

    public void writeEncodedText();

    public FreqTable getFrequencyTable();
    
    public void readFrequencies();

}
