/**
 * Maintain a buffer of bits and write to disk each time eight are ready.
 */

#include "bit_input.h"


static int buffer;
static int bits_to_go;
static int garbage_bits;

void start_inputing_bits(void)
{
    bits_to_go = 0;
    garbage_bits = 0;
}

int input_bit(void)
{
    int t;
    if (bits_to_go == 0) {
        buffer = getc(stdin);
        if (buffer == EOF) {
            garbage_bits++;
            if (garbage_bits > Code_value_bits - 2) {
                fprintf(stderr, "Bad input file\n");
                exit(-1);
            }
        }
        bits_to_go = 8;
    }
    t = buffer & 1;
    buffer >>= 1;
    bits_to_go--;
    return t;
}
