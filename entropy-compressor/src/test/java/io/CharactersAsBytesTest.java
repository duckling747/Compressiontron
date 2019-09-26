package io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class CharactersAsBytesTest {

    private static final String S = "Lorem ipsum lorem ipsum lorem lorem lorem ipsum.\n";

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void charactersWork() {
        File f = new File(temp.getRoot(), "test.txt");
        try (BitsWriter writer = new BitsWriter(new DataOutputStream(new FileOutputStream(f.getPath())))) {
            for (int c : S.toCharArray()) {
                writer.writeByte(c);
            }
        } catch (IOException e) {

        }
        try (BitsReader reader = new BitsReader(new DataInputStream(new FileInputStream(f.getPath())))) {
            int c, counter = 0;
            while ((c = reader.readByte()) != -1) {
                assertThat((char) c, is(S.charAt(counter++)));
            }
        } catch (IOException e) {

        }
    }
}
