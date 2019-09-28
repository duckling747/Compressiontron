package main;

import algo.ACCompressor;
import algo.ACDecompressor;
import algo.HuffmanCompressor;
import algo.HuffmanDecompressor;

public class Main {

    /**
     * Global maximum amount of symbols supported. Most datastructs are inteded
     * to work with characters as ints, so this effectively translates to the
     * character range 0-SYMBOLLIMIT (inclusive).
     */
    public final static int SYMBOLLIMIT = 255;

    public static void main(String[] args) {

        String s1 = "lorem_short.txt", s2 = "ACcompr.bits", s3 = "HUFFcompr.bits",
                s4 = "ac_freqs", s5 = "huffFreqs",
                s6 = "AC_out.txt", s7 = "Huffman_out.txt";
        ACCompressor ac = new ACCompressor(s1, s2, s4);
        ac.compress();
        ACDecompressor acd = new ACDecompressor(s2, s4, s6);
        acd.decompress();
        
        
    }

}
