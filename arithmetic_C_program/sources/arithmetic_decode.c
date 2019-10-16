#include "arithmetic_decode.h"
#include "bit_input.h"


static code_value value;
static code_value low, high;

void start_decoding(void)
{
    int i;
    value = 0;
    for (i = 1; i <= Code_value_bits; i++) {
        value = 2 * value + input_bit();
    }
    low = 0;
    high = Top_value;
}

int decode_symbol(int cum_freq[])
{   
    long range;
    int cum;
    int symbol;
    range = (long)(high - low) + 1;
    cum = (((long)(value - low) + 1) * cum_freq[0] - 1) / range;
    for (symbol = 1; cum_freq[symbol] > cum; symbol++);
    high = low + (range * cum_freq[symbol - 1]) / cum_freq[0] - 1;
    low = low + (range * cum_freq[symbol]) / cum_freq[0];
    for (;;) {
        if (high < Half) {
            /* nothing */
        } else if (low >= Half) {
            value -= Half;
            low -= Half;
            high -= Half;
        } else if (low >= First_qtr && high < Third_qtr) {
            value -= First_qtr;
            low -= First_qtr;
            high -= First_qtr;
        } else {
            break;
        }
        low = 2 * low;
        high = 2 * high + 1;
        value = 2 * value + input_bit();
    }
    return symbol;
}   
