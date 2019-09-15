package main;

import algo.ACCompressor;
import algo.ACDecompressor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        ACCompressor compressor = new ACCompressor("lorem_short.txt", "compr.bits");
        compressor.compress();
        ACDecompressor decom = new ACDecompressor("compr.bits", "decompressed.txt");
        decom.decompress();

        /*
        String s1 = null,s2 = null;
        try {
            s1 = Files.readString(Paths.get("lorem_short.txt"));
            s2 = Files.readString(Paths.get("decompressed.txt"));
        } catch (IOException e) {
            return;
        }
        for (int i = 0; i < s1.length(); i++) {
            System.out.println((int) s1.charAt(i) + " : " + (int) s2.charAt(i));
        }
         */
    }

}
