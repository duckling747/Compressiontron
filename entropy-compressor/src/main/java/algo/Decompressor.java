package algo;

import datastructs.FreqTable;

public abstract class Decompressor extends General {

    protected String filenameInCompression;
    protected String filenameInFrequencies;
    protected String filenameOut;
    protected FreqTable freqs;

    public abstract void decompress();

}
