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
    public void freqsAtStart1() {
        assertThat(f.getFreq(1), is(equalTo(1)));
        assertThat(f.getFreq(2), is(equalTo(1)));
        assertThat(f.getFreq(3), is(equalTo(1)));
        assertThat(f.getFreq(4), is(equalTo(1)));
        assertThat(f.getFreq(5), is(equalTo(1)));
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

    @Test
    public void calcCumFreqCorrect4() {
        for (int i = 0; i < 2; i++) {
            f.addFreq(1);
            f.addFreq(3);
            f.addFreq(5);
        }
        for (int i = 0; i < 3; i++) {
            f.addFreq(2);
        }
        assertThat(f.getFreq(1), is(equalTo(3)));
        assertThat(f.getFreq(2), is(equalTo(4)));
        f.calcCumFreq();
        assertThat(f.getCumFreq(0), is(equalTo(14)));
        assertThat(f.getCumFreq(1), is(equalTo(11)));
        assertThat(f.getCumFreq(2), is(equalTo(7)));
        assertThat(f.getCumFreq(3), is(equalTo(4)));
        assertThat(f.getCumFreq(4), is(equalTo(3)));
        assertThat(f.getCumFreq(5), is(equalTo(0)));
    }

    @Test
    public void findCumFreqCorrect1() {
        for (int i = 0; i < 2; i++) {
            f.addFreq(1);
            f.addFreq(3);
            f.addFreq(5);
        }
        for (int i = 0; i < 3; i++) {
            f.addFreq(2);
        }
        f.calcCumFreq();
        assertThat(((FreqTableSimple) f).findCumFreq(7), is(equalTo(2)));
    }

    @Test
    public void findCumFreqCorrect2() {
        f.calcCumFreq();
        assertThat(((FreqTableSimple) f).findCumFreq(5), is(equalTo(0)));
        assertThat(((FreqTableSimple) f).findCumFreq(4), is(equalTo(1)));
        assertThat(((FreqTableSimple) f).findCumFreq(3), is(equalTo(2)));
        assertThat(((FreqTableSimple) f).findCumFreq(2), is(equalTo(3)));
        assertThat(((FreqTableSimple) f).findCumFreq(1), is(equalTo(4)));
        assertThat(((FreqTableSimple) f).findCumFreq(0), is(equalTo(5)));
    }

}
