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
            writer.writeBit(1);
        }
        for (int i = 0; i < 8; i++) {
            writer.writeBit(0);
        }
        for (int i = 0; i < 4; i++) {
            writer.writeBit(0);
            writer.writeBit(1);
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
        reader.readBit();
        assertThat(reader.getBitsLeft(), is(7));
        reader.close();
    }

    @Test
    public void readerBufferSane3() throws IOException {
        reader.readBit();
        reader.readBit();
        assertThat(reader.getBitsLeft(), is(6));
        reader.close();
    }

    @Test
    public void readerBufferSane4() throws IOException {
        for (int i = 0; i < 8; i++) {
            reader.readBit();
        }
        assertThat(reader.getBitsLeft(), is(0));
        reader.close();
    }

    @Test
    public void readerReadsBitsCorrect1() throws IOException {
        int i = reader.readBit();
        assertThat(Integer.numberOfLeadingZeros(i), is(31));
        assertThat(Integer.numberOfTrailingZeros(i), is(0));
        reader.close();
    }

    @Test
    public void readerReadsBitsCorrect2() throws IOException {
        for (int i = 0; i < 8; i++) {
            assertThat(reader.readBit(), is(1));
        }
        for (int i = 0; i < 8; i++) {
            assertThat(reader.readBit(), is(0));
        }
        for (int i = 0; i < 4; i++) {
            assertThat(reader.readBit(), is(0));
            assertThat(reader.readBit(), is(1));
        }
        reader.close();
    }

    @Test
    public void endOfFileReachable() throws IOException {
        int c;
        while ((c = reader.readBit()) != -1);
        reader.close();
        assertTrue(true);
    }

}
