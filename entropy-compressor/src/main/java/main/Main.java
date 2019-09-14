package main;

import algo.ACCompressor;
import algo.ACDecompressor;

public class Main {

    public static void main(String[] args) {
        ACCompressor compressor = new ACCompressor("lorem_short.txt", "compr.bits");
        compressor.compress();
        ACDecompressor decom = new ACDecompressor("compr.bits", "decompressed.txt");
        decom.decompress();
    }

}
