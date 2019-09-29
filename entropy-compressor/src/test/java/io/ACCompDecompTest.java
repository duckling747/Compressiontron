package io;

import algo.ACCompressor;
import algo.ACDecompressor;
import algo.General;
import java.io.File;
import java.io.IOException;
import static main.Main.SYMBOLLIMIT;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ACCompDecompTest {

    private ACCompressor compr;
    private ACDecompressor decom;

    @Before
    public void init() {
    }

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void compressorStoresFile() throws IOException {
        File fCompression = new File(temp.getRoot(), "testCompression.txt");
        File fFreqs = new File(temp.getRoot(), "testFreqs.txt");
        compr = new ACCompressor(getClass().getResource("/lorem_short.txt").getFile(),
                fCompression.getPath(), fFreqs.getPath());
        compr.compress();
        assertTrue(fFreqs.exists());
        assertTrue(fCompression.exists());
    }

    @Test
    public void decompressorRetrievesFreqs() {
        File fCompressed = new File(temp.getRoot(), "test.txt");
        File fFreqs = new File(temp.getRoot(), "testFreqs.txt");
        compr = new ACCompressor(getClass().getResource("/lorem_short.txt").getFile(),
                fCompressed.getPath(), fFreqs.getPath());
        compr.compress();
        File fDecompressed = new File(temp.getRoot(), "decompressed.txt");
        decom = new ACDecompressor(fCompressed.getPath(), fFreqs.getPath(),
                fDecompressed.getPath());
        decom.decompress();
        for (int i = 0; i <= General.SYMBOLLIMIT; i++) {
            assertThat(compr.getFreqs().getFreq(i),
                    is(equalTo(decom.getFreqTable().getFreq(i))));
        }
    }

    @Test
    public void decompressorRetrievesCumFreqs() {
        File fCompressed = new File(temp.getRoot(), "test.txt");
        File fFreqs = new File(temp.getRoot(), "testFreqs.txt");
        compr = new ACCompressor(getClass().getResource("/lorem_short.txt").getFile(),
                fCompressed.getPath(), fFreqs.getPath());
        compr.compress();
        File fDecompressed = new File(temp.getRoot(), "decompressed.txt");
        decom = new ACDecompressor(fCompressed.getPath(), fFreqs.getPath(),
                fDecompressed.getPath());
        assertTrue(decom.getFreqTable() != null);
        assertTrue(compr.getFreqs() != null);
        decom.decompress();
        compr.getFreqs().calcCumFreq();
        decom.getFreqTable().calcCumFreq();
        for (int i = 0; i <= General.SYMBOLLIMIT; i++) {
            assertThat(compr.getFreqs().getCumFreqLow(i),
                    is(equalTo(decom.getFreqTable().getCumFreqLow(i))));
        }
    }

    @Test
    public void findFreqsRetrievesSame() {
        File fCompressed = new File(temp.getRoot(), "test.txt");
        File fFreqs = new File(temp.getRoot(), "testFreqs.txt");

        compr = new ACCompressor(getClass().getResource("/lorem_short.txt").getFile(),
                fCompressed.getPath(), fFreqs.getPath());
        compr.compress();
        File fDecompressed = new File(temp.getRoot(), "decompressed.txt");
        decom = new ACDecompressor(fCompressed.getPath(), fFreqs.getPath(),
                fDecompressed.getPath());
        decom.decompress();
        compr.getFreqs().calcCumFreq();
        decom.getFreqTable().calcCumFreq();
        for (int i = 0; i <= General.SYMBOLLIMIT; i++) {
            assertThat(compr.getFreqs().findCumFreq(i),
                    is(equalTo(decom.getFreqTable().findCumFreq(i))));
        }
    }
    /*
    @Test
    public void filesIdentical() throws IOException, URISyntaxException {
        File fCompressed = new File(temp.getRoot(), "test.txt");
        compr = new ACCompressor(getClass().getResource("/lorem_short.txt").getFile(),
                fCompressed.getPath());
        compr.compress();
        String text1 = Files.readString(Paths.get(getClass()
                .getResource("/lorem_short.txt").toURI()));
        File fDecompressed = new File(temp.getRoot(), "decompressed.txt");
        decom = new ACDecompressor(fCompressed.getPath(),
                fDecompressed.getPath());
        decom.decompress();
        String text2 = Files.readString(Paths.get(fDecompressed.getPath()));
        assertThat(text2, is(equalTo(text1)));
    }
     */
}
