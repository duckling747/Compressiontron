package datastructs;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class FrequencyTableTest {

    private FreqTableCumulative f;
    private int symbolLimit;

    @Before
    public void init() {
        symbolLimit = 5;
        f = new FreqTableCumulative(symbolLimit);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfRangeFreqUp() {
        f.addFreq(symbolLimit + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfRangeFreqDown1() {
        f.addFreq(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfRangeFreqDown2() {
        f.addFreq(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfRangeCumFreqUp() {
        f.getCumFreqLow(symbolLimit + 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfRangeCumFreqDown() {
        f.getCumFreqLow(-1);
    }

    @Test(expected = Test.None.class)
    public void zeroCumNotOutOfRange() {
        f.getCumFreqLow(0);
    }

    @Test
    public void getSymbolLimitWorks() {
        int i = f.getSymbolLimit();
        assertThat(i, is(equalTo(symbolLimit)));
    }

    @Test
    public void freqsAtStart1() {
        assertThat(f.getFreq(1), is(equalTo(0)));
        assertThat(f.getFreq(2), is(equalTo(0)));
        assertThat(f.getFreq(3), is(equalTo(0)));
        assertThat(f.getFreq(4), is(equalTo(0)));
        assertThat(f.getFreq(5), is(equalTo(0)));
    }

    @Test
    public void calcCumFreqCorrect1() {
        assertThat(f.getCumFreqLow(1), is(equalTo(0)));
    }

    @Test
    public void calcCumFreqCorrect2() {
        f.addFreq(1);
        assertThat(f.getCumFreqLow(1), is(equalTo(0)));
    }

    @Test
    public void calcCumFreqCorrect3() {
        f.addFreq(1);
        f.calcCumFreq();
        assertThat(f.getCumFreqLow(2), is(equalTo(1)));
    }

    @Test
    public void calcCumFreqCorrect4() {
        f.setFreq(0, 0);
        f.setFreq(1, 2);
        f.setFreq(3, 2);
        f.setFreq(5, 2);
        f.setFreq(2, 3);
        f.setFreq(4, 0);
        assertThat(f.getFreq(1), is(equalTo(2)));
        assertThat(f.getFreq(2), is(equalTo(3)));
        f.calcCumFreq();
        assertThat(f.getCumFreqLow(0), is(equalTo(0)));
        assertThat(f.getCumFreqLow(1), is(equalTo(0)));
        assertThat(f.getCumFreqLow(2), is(equalTo(2)));
        assertThat(f.getCumFreqLow(3), is(equalTo(5)));
        assertThat(f.getCumFreqLow(4), is(equalTo(7)));
        assertThat(f.getCumFreqLow(5), is(equalTo(7)));
        assertThat(f.getCumFreqLow(6), is(equalTo(9)));
    }

    @Test
    public void findCumFreqCorrect1() {
        f.setFreq(0, 0);
        f.setFreq(1, 2);
        f.setFreq(3, 2);
        f.setFreq(5, 2);
        f.setFreq(2, 3);
        f.setFreq(4, 0);
        assertThat(f.getFreq(0), is(0));
        assertThat(f.getFreq(1), is(2));
        assertThat(f.getFreq(2), is(3));
        assertThat(f.getFreq(3), is(2));
        assertThat(f.getFreq(4), is(0));
        assertThat(f.getFreq(5), is(2));
        f.calcCumFreq();
        assertThat(f.findCumFreq(7), is(equalTo(5)));
        assertThat(f.findCumFreq(8), is(equalTo(5)));
        assertThat(f.findCumFreq(11), is(equalTo(6)));
        assertThat(f.findCumFreq(0), is(equalTo(1)));
    }

    @Test
    public void findCumFreqCorrect2() {
        f.setFreq(0, 1);
        f.setFreq(1, 1);
        f.setFreq(3, 1);
        f.setFreq(5, 1);
        f.setFreq(2, 1);
        f.setFreq(4, 1);
        f.calcCumFreq();
        assertThat((f).findCumFreq(5), is(equalTo(5)));
        assertThat((f).findCumFreq(4), is(equalTo(4)));
        assertThat((f).findCumFreq(3), is(equalTo(3)));
        assertThat((f).findCumFreq(2), is(equalTo(2)));
        assertThat((f).findCumFreq(1), is(equalTo(1)));
        assertThat((f).findCumFreq(0), is(equalTo(0)));
    }

}
