package datastructs;

import algo.HuffmanEncoder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class HuffmanTreeAndTableTest {

    @Before
    public void initMe() {
    }

    @Test
    public void testCode1() {
        FreqTable f = new FreqTableSimple(5);
        f.setFreq('a' - 'a' + 1, 40);
        f.setFreq('b' - 'a' + 1, 30);
        f.setFreq('c' - 'a' + 1, 50);
        f.setFreq('d' - 'a' + 1, 2);
        f.setFreq('e' - 'a' + 1, 100);
        HuffmanEncoder enc1 = new HuffmanEncoder(f);
        LookupTable l = enc1.getTable();
        assertThat(l.getCode('a' - 'a' + 1), is("111"));
        assertThat(l.getCode('b' - 'a' + 1), is("1101"));
        assertThat(l.getCode('c' - 'a' + 1), is("10"));
        assertThat(l.getCode('d' - 'a' + 1), is("1100"));
        assertThat(l.getCode('e' - 'a' + 1), is("0"));
    }

    @Test
    public void testCode2() {
        FreqTable f = new FreqTableSimple(7);
        f.setFreq('a' - 'a' + 1, 2);
        f.setFreq('b' - 'a' + 1, 100);
        f.setFreq('c' - 'a' + 1, 3);
        f.setFreq('d' - 'a' + 1, 5);
        f.setFreq('e' - 'a' + 1, 4);
        f.setFreq('g' - 'a' + 1, 8);
        HuffmanEncoder enc1 = new HuffmanEncoder(f);
        LookupTable l = enc1.getTable();
        assertThat(l.getCode('g' - 'a' + 1), is("011"));
        assertThat(l.getCode('d' - 'a' + 1), is("001"));
        assertThat(l.getCode('c' - 'a' + 1), is("0100"));
        assertThat(l.getCode('b' - 'a' + 1), is("1"));
        assertThat(l.getCode('a' - 'a' + 1), is("01011"));
    }
}
