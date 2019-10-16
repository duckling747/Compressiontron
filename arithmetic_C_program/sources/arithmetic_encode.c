#include "arithmetic_encode.h"
#include "bit_output.h"


static code_value low, high;
static long bits_to_follow;

static void bit_plus_follow(int bit);

/**
 * Initialize high and low, and the amount of underflow bits, bits_to_follow
 */
void start_encoding(void)
{
    low = 0;
    high = Top_value;
    bits_to_follow = 0;
}

/**
 * The main encoding loop. On each call, calculate the new range, update
 * high and low, and keep shifting high order bits and receiving new low 
 * order ones until a symbol is encoded.
 */
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

/**
 * Arithmetic coding must send two extra bits at the end to be able to 
 * differentiate the last symbol.
 */
void done_encoding(void)
{
    bits_to_follow++;
    if (low < First_qtr) {
        bit_plus_follow(0);
    } else {
        bit_plus_follow(1);
    }
}

/**
 * Underflow prevention measure; write a bit and its opposite bits as many 
 * as there are underflow bits waiting.
 */
static void bit_plus_follow(int bit)
{   
    output_bit(bit);
    while(bits_to_follow > 0) {
        output_bit(!bit);
        bits_to_follow--;
    }
}
