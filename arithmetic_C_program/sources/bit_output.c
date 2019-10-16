#include "bit_output.h"


static int buffer;
static int bits_to_go;

void start_outputing_bits(void)
{
    buffer = 0;
    bits_to_go = 8;
}

void output_bit(int bit)
{   
    buffer >>= 1;
    if (bit) {
        buffer |= 0x80;
    }
    bits_to_go--;
    if (bits_to_go == 0) {
        putc(buffer, stdout);
        bits_to_go = 8;
    }
}

void done_outputing_bits(void)
{
    putc(buffer >> bits_to_go, stdout);
}
