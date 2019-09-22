package algo;

import datastructs.FreqTable;

public abstract class Decompressor extends General {

    protected String filenameIn;
    protected String filenameOut;
    protected FreqTable freqs;

    public abstract void decompress();

}
