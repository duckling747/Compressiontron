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


}
