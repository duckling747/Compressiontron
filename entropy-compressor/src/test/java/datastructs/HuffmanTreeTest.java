package datastructs;

import algo.HuffmanEncoder;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class HuffmanTreeTest {
    
    private HuffmanEncoder enc;

    @Before
    public void initMe() {
        FreqTable f = new FreqTableSimple(256);
        f.setFreq('a', 40);
        f.setFreq('b', 30);
        f.setFreq('c', 50);
        f.setFreq('d', 2);
        f.setFreq('e', 100);
        enc = new HuffmanEncoder(f);
    }
    
    @Test
    public void testCode1() {
        LookUpTable l = enc.getLookupTable();;
        System.out.println(l.getCode('a'));
        System.out.println(l.getCode('b'));
        System.out.println(l.getCode('c'));
        System.out.println(l.getCode('d'));
        System.out.println(l.getCode('e'));
        assertThat(l.getCode('a'), is("111"));
        assertThat(l.getCode('b'), is("1101"));
        assertThat(l.getCode('c'), is("10"));
        assertThat(l.getCode('d'), is("1100"));
        assertThat(l.getCode('e'), is("0"));
    }
}
