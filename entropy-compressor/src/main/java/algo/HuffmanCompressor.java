package algo;

import datastructs.FreqTable;
import datastructs.FreqTableSimple;
import io.BitsWriter;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import main.Main;

public class HuffmanCompressor extends Compressor {

    private HuffmanEncoder encoder;
    private FreqTable freqs;

    public HuffmanCompressor(String fileIn, String fileOut) {
        filenameIn = fileIn;
        filenameOut = fileOut;
        freqs = new FreqTableSimple(Main.SYMBOLLIMIT);
        readFileSetFreqs();
        encoder = new HuffmanEncoder(freqs);
    }

    @Override
    public void compress() {
        try (BufferedReader in = new BufferedReader(new FileReader(filenameIn));
                BitsWriter bitswriter = new BitsWriter(new DataOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(filenameOut))))) {
            writeFrequencies(bitswriter);
            writeEncodedText(in, bitswriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void writeEncodedText(BufferedReader in, BitsWriter bitswriter) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
