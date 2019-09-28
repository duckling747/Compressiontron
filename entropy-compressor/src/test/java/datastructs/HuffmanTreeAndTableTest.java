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
        f.setFreq('a' - 'a', 40);
        f.setFreq('b' - 'a', 30);
        f.setFreq('c' - 'a', 50);
        f.setFreq('d' - 'a', 2);
        f.setFreq('e' - 'a', 100);
        HuffmanEncoder enc1 = new HuffmanEncoder(f);
        LookupTable l = enc1.getLookupTable();
        assertThat(l.getCode('a' - 'a'), is("111"));
        assertThat(l.getCode('b' - 'a'), is("1101"));
        assertThat(l.getCode('c' - 'a'), is("10"));
        assertThat(l.getCode('d' - 'a'), is("1100"));
        assertThat(l.getCode('e' - 'a'), is("0"));
    }

    @Test
    public void testCode2() {
        FreqTable f = new FreqTableSimple(7);
        f.setFreq('a' - 'a', 2);
        f.setFreq('b' - 'a', 100);
        f.setFreq('c' - 'a', 3);
        f.setFreq('d' - 'a', 5);
        f.setFreq('g' - 'a', 8);
        HuffmanEncoder enc1 = new HuffmanEncoder(f);
        LookupTable l = enc1.getLookupTable();
        assertThat(l.getCode('g' - 'a'), is("00"));
        assertThat(l.getCode('d' - 'a'), is("010"));
        assertThat(l.getCode('c' - 'a'), is("0111"));
        assertThat(l.getCode('b' - 'a'), is("1"));
        assertThat(l.getCode('a' - 'a'), is("0110"));
    }
}
