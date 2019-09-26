package algo;

import datastructs.FreqTable;
import datastructs.FreqTableSimple;
import datastructs.LookupTable;
import io.BitsReader;
import io.BitsWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import static jdk.nashorn.internal.runtime.regexp.joni.constants.AsmConstants.S;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class HuffmanTest {

    @Before
    public void initMe() {

    }

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void writtenTextProducesCorrectHuffman() {
        File f = new File(temp.getRoot(), "test.txt");
        try (BitsWriter writer = new BitsWriter(new DataOutputStream(new FileOutputStream(f.getPath())))) {
            for (int i = 0; i < 39; i++) {
                writer.writeByte('a');
            }
            for (int i = 0; i < 29; i++) {
                writer.writeByte('b');
            }
            for (int i = 0; i < 49; i++) {
                writer.writeByte('c');
            }
            for (int i = 0; i < 1; i++) {
                writer.writeByte('d');
            }
            for (int i = 0; i < 99; i++) {
                writer.writeByte('e');
            }
        } catch (IOException e) {
        }
        FreqTable fre = new FreqTableSimple(5);
        try (BitsReader reader = new BitsReader(new DataInputStream(new FileInputStream(f.getPath())))) {
            int c;
            while ((c = reader.readByte()) != -1) {
                fre.addFreq(c - 'a' + 1);
            }
            HuffmanEncoder en = new HuffmanEncoder(fre);
            LookupTable l = en.getLookupTable();
            assertThat(l.getCode('a' - 'a' + 1), is("111"));
            assertThat(l.getCode('b' - 'a' + 1), is("1101"));
            assertThat(l.getCode('c' - 'a' + 1), is("10"));
            assertThat(l.getCode('d' - 'a' + 1), is("1100"));
            assertThat(l.getCode('e' - 'a' + 1), is("0"));
        } catch (IOException e) {
        }
    }

}
