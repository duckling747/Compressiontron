/**
 * Set things up and while EOF symbol is not encountered, keep
 * outputing the decoded characters and updating the model on each
 * symbol.
 */


#include "decode.h"
#include "bit_input.h"


int main(void)
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
