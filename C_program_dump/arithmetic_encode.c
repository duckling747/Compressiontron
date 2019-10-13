#include "arithmetic_coding.h"

static void bit_plus_follow();

static code_value low, high;
static long bits_to_follow;

start_encoding()
{
    low = 0;
    high = Top_value;
    bits_to_follow = 0;
}

encode_symbol(symbol, cum_freq)
    int symbol;
    int cum_freq[];
{   long range;
    range = (long) (high - low) + 1;
    high = low + (range * cum_freq[symbol - 1]) / cum_freq[0] - 1;
    low = low + (range * cum_freq[symbol]) / cum_freq[0];
    for (;;) {
        if (high < Half) {
            bit_plus_follow(0);
        } else if (low >= Half) {
            bit_plus_follow(1);
            low -= Half;
            high -= Half;
        } else if (low >= First_qtr && high < Third_qtr) {
            bits_to_follow++;
            low -= First_qtr;
            high -= First_qtr;
        } else {
            break;
        }
        low = 2 * low;
        high = 2 * high + 1;
    }
}

done_encoding()
{
    bits_to_follow++;
    if (low < First_qtr) {
        bit_plus_follow(0);
    } else {
        bit_plus_follow(1);
    }
}

static void bit_plus_follow(bit)
    int bit;
{   output_bit(bit);
    while(bits_to_follow > 0) {
        output_bit(!bit);
        bits_to_follow--;
    }
}
