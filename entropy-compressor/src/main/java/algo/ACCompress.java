package algo;

import IO.BitsWriter;
import datastructs.FreqTable;
import datastructs.FreqTableSimple;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ACCompress {

    private ACEncoder encoder;
    private FreqTable freqs;

    public ACCompress() {
        freqs = new FreqTableSimple(256);
        encoder = new ACEncoder(freqs);
    }
    
    public void readFileSetFreqs(String filename) {
        try (InputStream in = new BufferedInputStream(new FileInputStream(filename))) {
            
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void compress() {
        try (BitsWriter bitswriter = new BitsWriter(
                new BufferedOutputStream(new FileOutputStream("AC-compressed")))) {
            
        } catch (IOException e) {

        }
    }

}
