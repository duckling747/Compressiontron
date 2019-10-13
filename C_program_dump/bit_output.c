#include <stdio.h>

static int buffer;
static int bits_to_go;

start_outputing_bits()
{
    buffer = 0;
    bits_to_go = 8;
}

output_bit(bit)
    int bit;
{   buffer >>= 1;
    if (bit) {
        buffer |= 0x80;
    }
    bits_to_go--;
    if (bits_to_go == 0) {
        putc(buffer, stdout);
        bits_to_go = 8;
    }
}

done_outputing_bits()
{
    putc(buffer >> bits_to_go, stdout);
}
