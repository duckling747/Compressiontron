package io;

import algo.ACCompressor;
import algo.ACDecompressor;
import algo.Compressor;
import algo.Decompressor;
import algo.General;
import algo.HuffmanCompressor;
import algo.HuffmanDecompressor;
import datastructs.FreqTable;
import datastructs.FreqTableCumulative;
import datastructs.FreqTableSimple;
import java.io.File;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class CompressorTest {

    @Before
    public void init() {
    }

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void ACcompressorStoreFile() throws IOException {
        File fCompression = new File(temp.getRoot(), "testCompression");
        File fFreqs = new File(temp.getRoot(), "testFreqs");
        File fLetterA = new File(getClass().getResource("/a.txt").getFile());
        Compressor com = new ACCompressor(fLetterA.getPath(), fCompression.getPath(), fFreqs.getPath());
        com.readFrequencies();
        com.writeFrequencies();
        com.writeEncodedText();
        assertTrue(fFreqs.exists());
        assertTrue(fCompression.exists());
    }

    @Test
    public void HuffcompressorStoreFile() throws IOException {
        File fCompression = new File(temp.getRoot(), "testCompression");
        File fFreqs = new File(temp.getRoot(), "testFreqs");
        File fLetterA = new File(getClass().getResource("/a.txt").getFile());
        Compressor com = new HuffmanCompressor(fLetterA.getPath(), fCompression.getPath(), fFreqs.getPath());
        com.readFrequencies();
        com.writeFrequencies();
        com.writeEncodedText();
        assertTrue(fFreqs.exists());
        assertTrue(fCompression.exists());
    }

    @Test
    public void ACRetrievesCorrectFreqs() {
        File fCompression = new File(temp.getRoot(), "testCompression");
        File fFreqs = new File(temp.getRoot(), "testFreqs");
        File fLetterA = new File(getClass().getResource("/a.txt").getFile());
        File fDecompression = new File(temp.getRoot(), "testDecompression.txt");
        FreqTableCumulative ft = new FreqTableCumulative(new int[General.SYMBOLLIMIT + 1]);
        for (int i = 0; i <= ft.getSymbolLimit(); i++) {
            ft.setFreq(i, i);
        }
        Compressor com = new ACCompressor(fLetterA.getPath(), fCompression.getPath(), fFreqs.getPath(), ft);
        com.writeFrequencies();

        Decompressor decom = new ACDecompressor(fCompression.getPath(), fFreqs.getPath(), fDecompression.getPath());
        decom.readFrequencies();

        for (int i = 0; i <= ft.getSymbolLimit(); i++) {
            assertThat(com.getFrequencyTable().getFreq(i), is(decom.getFrequencyTable().getFreq(i)));
        }
    }

    @Test
    public void HuffRetrievesCorrectFreqs() {
        File fCompression = new File(temp.getRoot(), "testCompression");
        File fFreqs = new File(temp.getRoot(), "testFreqs");
        File fLetterA = new File(getClass().getResource("/a.txt").getFile());
        File fDecompression = new File(temp.getRoot(), "testDecompression.txt");
        FreqTable ft = new FreqTableSimple(new int[General.SYMBOLLIMIT + 1]);
        for (int i = 0; i <= ft.getSymbolLimit(); i++) {
            ft.setFreq(i, i);
        }
        Compressor com = new HuffmanCompressor(fLetterA.getPath(), fCompression.getPath(), fFreqs.getPath(), ft);
        com.writeFrequencies();

        Decompressor decom = new HuffmanDecompressor(fCompression.getPath(), fFreqs.getPath(), fDecompression.getPath());
        decom.readFrequencies();

        for (int i = 0; i <= ft.getSymbolLimit(); i++) {
            assertThat(com.getFrequencyTable().getFreq(i), is(decom.getFrequencyTable().getFreq(i)));
        }
    }

    @Test
    public void ACCompressesCorrectOneLetter() {

    }

    @Test
    public void ACDecompressesCorrectOneLetter() {

    }
}
