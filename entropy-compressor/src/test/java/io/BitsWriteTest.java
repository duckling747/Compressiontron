package io;

import io.BitsWriter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class BitsWriteTest {

    private BitsWriter writer;
    private File f;

    @Before
    public void init() throws IOException {
        f = new File(temp.getRoot(), "test.txt");
        writer = new BitsWriter(new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(f.getPath()))));
    }

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void writerBufferSane1() throws IOException {
        assertThat(writer.getBitsReady(), is(equalTo(0)));
        writer.write(1);
        assertThat(writer.getBitsReady(), is(equalTo(1)));
        writer.write(0);
        assertThat(writer.getBitsReady(), is(equalTo(2)));
        assertThat(writer.getBuffer(), is(equalTo(2)));
        writer.close();
        assertThat(writer.getBuffer(), is(equalTo(0)));
    }

    @Test
    public void writerBufferSane2() throws IOException {
        for (int i = 0; i < 8; i++) {
            writer.write(1);
        }
        writer.close();
        assertThat(writer.getBuffer(), is(equalTo(0)));
        assertThat(Integer.bitCount(writer.getBuffer()), is(0));
        assertThat(Integer.numberOfLeadingZeros(writer.getBuffer()), is(32));
        assertThat(Integer.numberOfTrailingZeros(writer.getBuffer()), is(32));
    }

    @Test
    public void writerBufferSane3() throws IOException {
        for (int i = 0; i < 7; i++) {
            writer.write(1);
        }
        assertThat(Integer.bitCount(writer.getBuffer()), is(7));
        assertThat(Integer.numberOfLeadingZeros(writer.getBuffer()), is(25));
        writer.close();
        assertThat(Integer.bitCount(writer.getBuffer()), is(0));
        assertThat(Integer.numberOfLeadingZeros(writer.getBuffer()), is(32));
    }

    @Test
    public void writerBufferSane4() throws IOException {
        for (int i = 0; i < 3; i++) {
            writer.write(0);
            writer.write(1);
        }
        writer.write(0);
        assertThat(Integer.bitCount(writer.getBuffer()), is(3));
        assertThat(Integer.numberOfLeadingZeros(writer.getBuffer()), is(26));
        writer.close();
        assertThat(Integer.bitCount(writer.getBuffer()), is(0));
        assertThat(Integer.numberOfLeadingZeros(writer.getBuffer()), is(32));
    }

    @Test
    public void writerWritesBitsCorrect1() throws IOException {
        for (int i = 0; i < 4; i++) {
            writer.write(0);
            writer.write(1);
        }
        writer.close();
        DataInputStream read = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
        int i = read.read();
        assertThat(Integer.bitCount(i), is(4));
        assertThat(Integer.numberOfLeadingZeros(i), is(25));
        assertThat(Integer.numberOfTrailingZeros(i), is(0));
        read.close();
    }

    @Test
    public void writerWritesBitsCorrect2() throws IOException {
        for (int i = 0; i < 8; i++) {
            writer.write(1);
        }
        writer.close();
        DataInputStream read = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
        int i = read.read();
        assertThat(Integer.bitCount(i), is(8));
        assertThat(Integer.numberOfLeadingZeros(i), is(24));
        assertThat(Integer.numberOfTrailingZeros(i), is(0));
        read.close();
    }

    @Test
    public void writerWritesBitsCorrect3() throws IOException {
        for (int i = 0; i < 8; i++) {
            writer.write(0);
        }
        writer.close();
        DataInputStream read = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
        int i = read.read();
        assertThat(Integer.bitCount(i), is(0));
        assertThat(Integer.numberOfLeadingZeros(i), is(32));
        assertThat(Integer.numberOfTrailingZeros(i), is(32));
        read.close();
    }

}
