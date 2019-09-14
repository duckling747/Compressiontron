package IO;

import algo.ACCompressor;
import algo.ACDecompressor;
import java.io.File;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FreqTableRecoverTest {

    private ACCompressor compr;
    private ACDecompressor decom;

    private final static int SYMBOLLIMIT = 1000;

    @Before
    public void setTable() {
    }

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void compressorStoresFile() throws IOException {
        File f = new File(temp.getRoot(), "test.txt");
        compr = new ACCompressor(getClass().getResource("/lorem_short.txt").getFile(),
                f.getPath());
        compr.compress();
        assertTrue(f.exists());
    }

    @Test
    public void decompressorRetrievesFreqs() {
        File fCompressed = new File(temp.getRoot(), "test.txt");
        compr = new ACCompressor(getClass().getResource("/lorem_short.txt").getFile(),
                fCompressed.getPath());
        compr.compress();
        File fDecompressed = new File(temp.getRoot(), "decompressed.txt");
        decom = new ACDecompressor(fCompressed.getPath(),
                fDecompressed.getPath());
        decom.decompress();
        for (int i = 1; i <= SYMBOLLIMIT; i++) {
            assertThat(compr.getFreqs().getFreq(i),
                    is(equalTo(decom.getFreqTable().getFreq(i))));
        }
    }

    @Test
    public void findFreqsRetrievesSame() {

    }

    @Test
    public void filesIdentical() {
    }

}
