#include "arithmetic_encode.h"
#include "bit_output.h"


static code_value low, high;
static long bits_to_follow;

static void bit_plus_follow(int bit);

void start_encoding(void)
{
    low = 0;
    high = Top_value;
    bits_to_follow = 0;
}

void encode_symbol(int symbol, int cum_freq[])
{   
    long range;
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

void done_encoding(void)
{
    bits_to_follow++;
    if (low < First_qtr) {
        bit_plus_follow(0);
    } else {
        bit_plus_follow(1);
    }
}

static void bit_plus_follow(int bit)
{   
    output_bit(bit);
    while(bits_to_follow > 0) {
        output_bit(!bit);
        bits_to_follow--;
    }
}
