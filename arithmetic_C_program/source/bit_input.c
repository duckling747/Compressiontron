#include <stdio.h>
#include "arithmetic_coding.h"

static int buffer;
static int bits_to_go;
static int garbage_bits;

start_inputing_bits()
{
    bits_to_go = 0;
    garbage_bits = 0;
}

int input_bit()
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
