#include <stdio.h>
#include <stdlib.h>
#include "model.h"

main()
{
    start_model();
    start_inputing_bits();
    start_decoding();
    for (;;) {
        int ch;
        int symbol;
        symbol = decode_symbol(cum_freq);
        if (symbol == EOF_symbol) {
            break;
        }
        ch = index_to_char[symbol];
        putc(ch, stdout);
        update_model(symbol);
    }
    exit(0);
}
