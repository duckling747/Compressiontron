package io;

import io.BitsWriter;
import io.BitsReader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class BitsReadTest {

    private BitsReader reader;
    private BitsWriter writer;
    private File f;

    @Before
    public void init() throws IOException {
        f = new File(temp.getRoot(), "test.txt");
        writer = new BitsWriter(new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(f.getPath()))));
        for (int i = 0; i < 8; i++) {
            writer.write(1);
        }
        for (int i = 0; i < 8; i++) {
            writer.write(0);
        }
        for (int i = 0; i < 4; i++) {
            writer.write(0);
            writer.write(1);
        }
        writer.close();
        reader = new BitsReader(new DataInputStream(
                new BufferedInputStream(new FileInputStream(f.getPath()))));
    }

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void readerBufferSane1() throws IOException {
        assertThat(reader.getBitsLeft(), is(0));
        reader.close();
    }

    @Test
    public void readerBufferSane2() throws IOException {
        reader.read();
        assertThat(reader.getBitsLeft(), is(7));
        reader.close();
    }

    @Test
    public void readerBufferSane3() throws IOException {
        reader.read();
        reader.read();
        assertThat(reader.getBitsLeft(), is(6));
        reader.close();
    }

    @Test
    public void readerBufferSane4() throws IOException {
        for (int i = 0; i < 8; i++) {
            reader.read();
        }
        assertThat(reader.getBitsLeft(), is(0));
        reader.close();
    }

    @Test
    public void readerReadsBitsCorrect1() throws IOException {
        int i = reader.read();
        assertThat(Integer.numberOfLeadingZeros(i), is(31));
        assertThat(Integer.numberOfTrailingZeros(i), is(0));
        reader.close();
    }

    @Test
    public void readerReadsBitsCorrect2() throws IOException {
        for (int i = 0; i < 8; i++) {
            assertThat(reader.read(), is(1));
        }
        for (int i = 0; i < 8; i++) {
            assertThat(reader.read(), is(0));
        }
        for (int i = 0; i < 4; i++) {
            assertThat(reader.read(), is(0));
            assertThat(reader.read(), is(1));
        }
        reader.close();
    }

    @Test
    public void endOfFileReachable() throws IOException {
        int c;
        while ((c = reader.read()) != -1);
        reader.close();
        assertTrue(true);
    }

}
