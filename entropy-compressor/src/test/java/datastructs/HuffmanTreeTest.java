package datastructs;

import algo.HuffmanEncoder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class HuffmanTreeTest {

    @Before
    public void initMe() {
    }

    @Test
    public void testCode1() {
        FreqTable f = new FreqTableSimple(256);
        f.setFreq('a', 40);
        f.setFreq('b', 30);
        f.setFreq('c', 50);
        f.setFreq('d', 2);
        f.setFreq('e', 100);
        HuffmanEncoder enc1 = new HuffmanEncoder(f);
        LookupTable l = enc1.getTable();
        assertThat(l.getCode('a'), is("111"));
        assertThat(l.getCode('b'), is("1101"));
        assertThat(l.getCode('c'), is("10"));
        assertThat(l.getCode('d'), is("1100"));
        assertThat(l.getCode('e'), is("0"));
    }

    @Test
    public void testCode2() {
        FreqTable f = new FreqTableSimple(1000);
        f.setFreq('a', 1);
        f.setFreq('b', 2);
        f.setFreq('c', 3);
        f.setFreq('d', 5);
        f.setFreq('g', 8);
        HuffmanEncoder enc1 = new HuffmanEncoder(f);
        LookupTable l = enc1.getTable();
        assertThat(l.getCode('g'), is("0"));
        assertThat(l.getCode('d'), is("10"));
        assertThat(l.getCode('c'), is("110"));
        assertThat(l.getCode('b'), is("1111"));
        assertThat(l.getCode('a'), is("1110"));
    }
}
