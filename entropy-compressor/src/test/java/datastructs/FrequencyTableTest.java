package datastructs;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class FrequencyTableTest {

    private FreqTable f;
    private int symbolLimit;

    @Before
    public void init() {
        symbolLimit = 5;
        f = new FreqTableSimple(symbolLimit);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfRangeFreqUp() {
        f.addFreq(symbolLimit + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfRangeFreqDown1() {
        f.addFreq(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfRangeFreqDown2() {
        f.addFreq(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfRangeCumFreqUp() {
        f.getCumFreq(symbolLimit + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfRangeCumFreqDown() {
        f.getCumFreq(-1);
    }

    @Test(expected = Test.None.class)
    public void zeroCumNotOutOfRange() {
        f.getCumFreq(0);
    }

    @Test
    public void getSymbolLimitWorks() {
        int i = f.getSymbolLimit();
        assertThat(i, is(equalTo(symbolLimit)));
    }

    @Test
    public void calcCumFreqCorrect1() {
        assertThat(f.getFreq(1), is(equalTo(1)));
    }
    
    @Test
    public void calcCumFreqCorrect2() {
        assertThat(f.getCumFreq(1), is(equalTo(0)));
    }
    
    @Test
    public void calcCumFreqCorrect3() {
        f.addFreq(1);
        f.calcCumFreq();
        assertThat(f.getCumFreq(0), is(equalTo(6)));
    }
}
