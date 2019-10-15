package io;

import algo.Compressor;
import algo.Decompressor;
import algo.General;
import algo.HuffmanCompressor;
import algo.HuffmanDecompressor;
import datastructs.FreqTable;
import datastructs.FreqTableSimple;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public void HuffcompressorStoreFile() {
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
    public void HuffRetrievesCorrectFreqs() {
        File fCompression = new File(temp.getRoot(), "testCompression");
        File fFreqs = new File(temp.getRoot(), "testFreqs");
        File fLetterA = new File(getClass().getResource("/a.txt").getFile());
        File fDecompression = new File(temp.getRoot(), "testDecompression.txt");
        FreqTable ft = new FreqTableSimple(new int[General.SYMBOLLIMIT + 1]);
        for (int i = 0; i < ft.getSymbolLimit(); i++) {
            ft.setFreq(i, i);
        }
        ft.setFreq(ft.getSymbolLimit(), 1);
        Compressor com = new HuffmanCompressor(fLetterA.getPath(), fCompression.getPath(), fFreqs.getPath(), ft);
        com.writeFrequencies();

        Decompressor decom = new HuffmanDecompressor(fCompression.getPath(), fFreqs.getPath(), fDecompression.getPath());
        decom.readFrequencies();

        for (int i = 0; i <= ft.getSymbolLimit(); i++) {
            assertThat(com.getFrequencyTable().getFreq(i), is(decom.getFrequencyTable().getFreq(i)));
        }
    }

    @Test(timeout = 500)
    public void HuffmanResultantFilesSame1() {
        File fCompression = new File(temp.getRoot(), "testCompression");
        File fFreqs = new File(temp.getRoot(), "testFreqs");
        File fLetterA = new File(getClass().getResource("/a.txt").getFile());
        File fDecompression = new File(temp.getRoot(), "testDecompression.txt");

        Compressor com = new HuffmanCompressor(fLetterA.getPath(), fCompression.getPath(), fFreqs.getPath());
        com.readFrequencies();
        com.writeFrequencies();
        com.writeEncodedText();

        Decompressor decom = new HuffmanDecompressor(fCompression.getPath(), fFreqs.getPath(), fDecompression.getPath());
        decom.readFrequencies();
        decom.readEncodedText();

        String a = null, b = null;
        try {
            a = Files.readString(Paths.get(fLetterA.getPath()));
            b = Files.readString(Paths.get(fDecompression.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(a != null);
        assertTrue(b != null);
        assertThat(b, is(a));

    }

    @Test(timeout = 500)
    public void HuffmanResultantFilesSame2() {
        File fCompression = new File(temp.getRoot(), "testCompression");
        File fFreqs = new File(temp.getRoot(), "testFreqs");
        File fLetterA = new File(getClass().getResource("/b.txt").getFile());
        File fDecompression = new File(temp.getRoot(), "testDecompression.txt");

        Compressor com = new HuffmanCompressor(fLetterA.getPath(), fCompression.getPath(), fFreqs.getPath());
        com.readFrequencies();
        com.writeFrequencies();
        com.writeEncodedText();

        Decompressor decom = new HuffmanDecompressor(fCompression.getPath(), fFreqs.getPath(), fDecompression.getPath());
        decom.readFrequencies();
        decom.readEncodedText();

        String a = null, b = null;
        try {
            a = Files.readString(Paths.get(fLetterA.getPath()));
            b = Files.readString(Paths.get(fDecompression.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(b, is(a));

    }

    @Test(timeout = 500)
    public void HuffmanResultantFilesSame3() {
        File fCompression = new File(temp.getRoot(), "testCompression");
        File fFreqs = new File(temp.getRoot(), "testFreqs");
        File fLetterA = new File(getClass().getResource("/lorem_short.txt").getFile());
        File fDecompression = new File(temp.getRoot(), "testDecompression.txt");

        Compressor com = new HuffmanCompressor(fLetterA.getPath(), fCompression.getPath(), fFreqs.getPath());
        com.readFrequencies();
        com.writeFrequencies();
        com.writeEncodedText();

        Decompressor decom = new HuffmanDecompressor(fCompression.getPath(), fFreqs.getPath(), fDecompression.getPath());
        decom.readFrequencies();
        decom.readEncodedText();
        String a = null, b = null;
        try {
            a = Files.readString(Paths.get(fLetterA.getPath()));
            b = Files.readString(Paths.get(fDecompression.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(b, is(a));
    }

    @Test(timeout = 500)
    public void HuffmanResultantFilesSame4() {
        File fCompression = new File(temp.getRoot(), "testCompression");
        File fFreqs = new File(temp.getRoot(), "testFreqs");
        File fLetterA = new File(getClass().getResource("/Lorem_ipsum.txt").getFile());
        File fDecompression = new File(temp.getRoot(), "testDecompression.txt");

        Compressor com = new HuffmanCompressor(fLetterA.getPath(), fCompression.getPath(), fFreqs.getPath());
        com.readFrequencies();
        com.writeFrequencies();
        com.writeEncodedText();

        Decompressor decom = new HuffmanDecompressor(fCompression.getPath(), fFreqs.getPath(), fDecompression.getPath());
        decom.readFrequencies();
        decom.readEncodedText();

        String a = null, b = null;
        try {
            a = Files.readString(Paths.get(fLetterA.getPath()));
            b = Files.readString(Paths.get(fDecompression.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(b, is(a));

    }

    @Test(timeout = 500)
    public void HuffmanResultantFilesSame5() {
        File fCompression = new File(temp.getRoot(), "testCompression");
        File fFreqs = new File(temp.getRoot(), "testFreqs");
        File fLetterA = new File(getClass().getResource("/satunnaisteksti.txt").getFile());
        File fDecompression = new File(temp.getRoot(), "testDecompression.txt");

        Compressor com = new HuffmanCompressor(fLetterA.getPath(), fCompression.getPath(), fFreqs.getPath());
        com.readFrequencies();
        com.writeFrequencies();
        com.writeEncodedText();

        Decompressor decom = new HuffmanDecompressor(fCompression.getPath(), fFreqs.getPath(), fDecompression.getPath());
        decom.readFrequencies();
        decom.readEncodedText();

        String a = null, b = null;
        try {
            a = Files.readString(Paths.get(fLetterA.getPath()));
            b = Files.readString(Paths.get(fDecompression.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(b, is(a));
    }

}
