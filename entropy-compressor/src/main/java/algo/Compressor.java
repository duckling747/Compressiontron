package algo;

import datastructs.FreqTable;

public interface Compressor {

    /**
     * Write the contents of the used frequency table sequentially to file.
     */
    public void writeFrequencies();

    /**
     * Read a text file, encode its symbols, and write the resulting code to
     * file.
     */
    public void writeEncodedText();

    public FreqTable getFrequencyTable();

    /**
     * Count the instances of symbols from a given text file, and create a
     * frequency table from them.
     */
    public void readFrequencies();

}
