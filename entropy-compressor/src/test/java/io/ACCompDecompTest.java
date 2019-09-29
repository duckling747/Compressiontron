package io;

import algo.ACCompressor;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ACCompDecompTest {

    @Before
    public void init() {
    }

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();
/*
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
*/
}
