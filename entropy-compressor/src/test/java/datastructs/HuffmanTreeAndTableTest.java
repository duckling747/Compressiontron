package datastructs;

import algo.HuffmanEncoder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class HuffmanTreeAndTableTest {

    private int[] f;

    @Test
    public void testCode1() {
        f = new int[]{40, 30, 50, 2, 100};
        FreqTable t = new FreqTableSimple(f);
        HuffmanEncoder enc1 = new HuffmanEncoder(t);
        LookupTable l = enc1.getLookupTable();
        assertThat(l.getCode('a' - 'a'), is("111"));
        assertThat(l.getCode('b' - 'a'), is("1101"));
        assertThat(l.getCode('c' - 'a'), is("10"));
        assertThat(l.getCode('d' - 'a'), is("1100"));
        assertThat(l.getCode('e' - 'a'), is("0"));
    }

    @Test
    public void testCode2() {
        f = new int[] {2, 100, 3, 5, 0, 0, 8};
        FreqTable t = new FreqTableSimple(f);
        HuffmanEncoder enc1 = new HuffmanEncoder(t);
        LookupTable l = enc1.getLookupTable();
        assertThat(l.getCode('g' - 'a'), is("00"));
        assertThat(l.getCode('d' - 'a'), is("010"));
        assertThat(l.getCode('c' - 'a'), is("0111"));
        assertThat(l.getCode('b' - 'a'), is("1"));
        assertThat(l.getCode('a' - 'a'), is("0110"));
    }
}
