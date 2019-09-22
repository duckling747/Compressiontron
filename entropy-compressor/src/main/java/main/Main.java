package main;

import algo.ACCompressor;
import algo.ACDecompressor;
import algo.HuffmanCompressor;

public class Main {

    /**
     * Global maximum amount of symbols supported. Most datastructs are inteded
     * to work with characters as ints, so this effectively translates to the
     * character range 0-SYMBOLLIMIT (inclusive).
     */
    public final static int SYMBOLLIMIT = 1000;

    public static void main(String[] args) {

        String s1 = "lorem_short.txt", s2 = "ACcompr.bits", s3 = "HUFFcompr.bits";
        ACCompressor compressor = new ACCompressor(s1, s2);
        compressor.compress();
        ACDecompressor decom = new ACDecompressor(s2, s1);
        decom.decompress();

        HuffmanCompressor compressor2 = new HuffmanCompressor(s1, s3);
        compressor2.compress();
    }

}
