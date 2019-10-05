package datastructs;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class FrequencyTableTest {

    @Test(expected = IllegalArgumentException.class)
    public void outOfRange1() {
        FreqTableCumulative t
                = new FreqTableCumulative(new int[]{1, -1, 1, 1, 1});
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfRange2() {
        FreqTableCumulative t
                = new FreqTableCumulative(new int[]{1, 1, 1, 1, 1});
        t.setFreq(0, -2);
    }

    @Test
    public void getSymbolLimitWorks() {
        FreqTableCumulative t
                = new FreqTableCumulative(new int[]{1, 1, 1, 1, 1});
        t.calcCumFreq();
        assertThat(t.getSymbolLimit(), is(4));
    }

    @Test
    public void calcCumFreqCorrect1() {
        FreqTableCumulative t
                = new FreqTableCumulative(new int[]{1, 1, 1, 1, 1});
        t.calcCumFreq();
        assertThat(t.getCumFreqLow(0), is(0));
        assertThat(t.getCumFreqHigh(0), is(1));
        assertThat(t.getCumFreqLow(1), is(1));
        assertThat(t.getCumFreqHigh(1), is(2));
    }

    @Test
    public void calcCumFreqCorrect2() {
        FreqTableCumulative t
                = new FreqTableCumulative(new int[]{1, 1, 1, 1, 1});
        t.calcCumFreq();
        assertThat(t.getCumFreqLow(1), is(1));
        assertThat(t.getCumFreqHigh(1), is(2));
    }

    @Test
    public void calcCumFreqCorrect3() {
        FreqTableCumulative t
                = new FreqTableCumulative(new int[]{1, 1, 1, 1, 1});
        t.calcCumFreq();
        assertThat(t.getCumFreqLow(4), is(4));
        assertThat(t.getCumFreqHigh(4), is(5));
    }

    @Test
    public void calcCumFreqCorrect4() {
        FreqTableCumulative t
                = new FreqTableCumulative(new int[]{0, 2, 2, 2, 3, 0});
        assertThat(t.getFreq(0), is(0));
        assertThat(t.getFreq(1), is(equalTo(2)));
        assertThat(t.getFreq(2), is(equalTo(2)));
        assertThat(t.getFreq(3), is(2));
        assertThat(t.getFreq(4), is(3));
        assertThat(t.getFreq(5), is(0));
        t.calcCumFreq();
        assertThat(t.getCumFreqLow(0), is(equalTo(0)));
        assertThat(t.getCumFreqLow(1), is(equalTo(0)));
        assertThat(t.getCumFreqLow(2), is(equalTo(2)));
        assertThat(t.getCumFreqLow(3), is(equalTo(4)));
        assertThat(t.getCumFreqLow(4), is(equalTo(6)));
        assertThat(t.getCumFreqLow(5), is(equalTo(9)));
        assertThat(t.getCumFreqHigh(5), is(equalTo(9)));
    }

    @Test
    public void findCumFreqCorrect1() {
        FreqTableCumulative t
                = new FreqTableCumulative(new int[]{0, 2, 2, 2, 3, 0});
        assertThat(t.getFreq(0), is(0));
        assertThat(t.getFreq(1), is(equalTo(2)));
        assertThat(t.getFreq(2), is(equalTo(2)));
        assertThat(t.getFreq(3), is(2));
        assertThat(t.getFreq(4), is(3));
        assertThat(t.getFreq(5), is(0));
        t.calcCumFreq();
        assertThat(t.findCumFreq(7), is(equalTo(4)));
        assertThat(t.findCumFreq(8), is(equalTo(4)));
        assertThat(t.findCumFreq(11), is(equalTo(6)));
        assertThat(t.findCumFreq(0), is(equalTo(1)));
    }

    @Test
    public void findCumFreqCorrect2() {
        FreqTableCumulative t
                = new FreqTableCumulative(new int[]{1, 1, 1, 1, 1, 1});
        t.calcCumFreq();
        assertThat(t.findCumFreq(5), is(equalTo(5)));
        assertThat(t.findCumFreq(4), is(equalTo(4)));
        assertThat(t.findCumFreq(3), is(equalTo(3)));
        assertThat(t.findCumFreq(2), is(equalTo(2)));
        assertThat(t.findCumFreq(1), is(equalTo(1)));
        assertThat(t.findCumFreq(0), is(equalTo(0)));
    }

}
